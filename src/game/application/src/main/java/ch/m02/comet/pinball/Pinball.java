package ch.m02.comet.pinball;

import javax.inject.Inject;

import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.picocontainer.behaviors.OptInCaching;
import org.picocontainer.gems.monitors.Slf4jComponentMonitor;
import org.picocontainer.injectors.AnnotatedFieldInjection;
import org.picocontainer.injectors.ProviderAdapter;
import org.picocontainer.parameters.ConstantParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.adapter.PinballDisplayAdapter;
import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.core.internal.ApplicationContextImpl;
import ch.m02.comet.pinball.core.internal.ConfigurationImpl;
import ch.m02.comet.pinball.core.logic.command.NewSimulationCommand;
import ch.m02.comet.pinball.core.logic.command.ShowHighscoresCommand;
import ch.m02.comet.pinball.core.logic.command.SplashFinishedCommand;
import ch.m02.comet.pinball.core.logic.event.EventHandler;
import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.playfield.BumperElementFactory;
import ch.m02.comet.pinball.core.presentation.playfield.ObstacleElementFactory;
import ch.m02.comet.pinball.core.presentation.playfield.SlingshotElementFactory;
import ch.m02.comet.pinball.game.ApplicationProvider;
import ch.m02.comet.pinball.game.PinballGame;
import ch.m02.comet.pinball.logic.LogicManager;
import ch.m02.comet.pinball.logic.internal.PinballLogicManager;
import ch.m02.comet.pinball.logic.internal.command.NewSimulationCommandImpl;
import ch.m02.comet.pinball.logic.internal.command.ShowHighscoresCommandImpl;
import ch.m02.comet.pinball.logic.internal.command.SplashFinishedCommandImpl;
import ch.m02.comet.pinball.logic.internal.event.EventHandlerImpl;
import ch.m02.comet.pinball.logic.internal.state.HighscoreState;
import ch.m02.comet.pinball.logic.internal.state.MainMenuState;
import ch.m02.comet.pinball.logic.internal.state.SimulationState;
import ch.m02.comet.pinball.logic.internal.state.SplashState;
import ch.m02.comet.pinball.logic.internal.state.StateContext;
import ch.m02.comet.pinball.logic.persistence.PlayFieldStoreDao;
import ch.m02.comet.pinball.logic.persistence.SimulationStoreDao;
import ch.m02.comet.pinball.logic.persistence.internal.PlayFieldStoreDaoImpl;
import ch.m02.comet.pinball.logic.persistence.internal.SimulationStoreDaoImpl;
import ch.m02.comet.pinball.logic.simulation.SimulationManager;
import ch.m02.comet.pinball.logic.simulation.rule.RuleEngine;
import ch.m02.comet.pinball.logic.simulation.rule.basic.HitScoreRule;
import ch.m02.comet.pinball.physics.PhysicsPlayField;
import ch.m02.comet.pinball.physics.PhysicsPlayFieldImpl;
import ch.m02.comet.pinball.physics.placable.BumperElementFactoryImpl;
import ch.m02.comet.pinball.physics.placable.EventCreator;
import ch.m02.comet.pinball.physics.placable.ObstacleElementFactoryImpl;
import ch.m02.comet.pinball.physics.placable.SlingshotElementFactoryImpl;
import ch.m02.comet.pinball.presentation.PinballDisplay;
import ch.m02.comet.pinball.presentation.PinballPresentationManager;
import ch.m02.comet.pinball.presentation.ScreenManager;
import ch.m02.comet.pinball.presentation.graphics.GraphicsDisplay;
import ch.m02.comet.pinball.presentation.screens.GameScreenImpl;
import ch.m02.comet.pinball.presentation.screens.HighscoreScreenImpl;
import ch.m02.comet.pinball.presentation.screens.MainMenuScreenImpl;
import ch.m02.comet.pinball.presentation.screens.PinballScreenManager;
import ch.m02.comet.pinball.presentation.screens.SplashScreenImpl;

import com.badlogic.gdx.Game;

public class Pinball {
	
	private static final Logger log = LoggerFactory.getLogger(Pinball.class);

	private MainApplication application;
	private MutablePicoContainer container;

	public Pinball(MainApplication application) {
		this.application = application;
		container = new PicoBuilder()
				.withMonitor(new Slf4jComponentMonitor()) // Monitor container events in log
				.withBehaviors(new OptInCaching()) // Enable singletons
				.withLocking() // Enable locking
				.withComponentFactory(new AnnotatedFieldInjection(Inject.class)) // use JSR-330
				.withHiddenImplementations() // Hide concrete implementations when using an interface
				.withJavaEE5Lifecycle() // use JSR-250 (@PostConstruct) annotations
				.build();
		registerSingletons();
		registerPrototypes();
	}

	private void registerSingletons() {
		log.debug("Registering pico component singletons");
		// In order to ensure thread safety, we use the LOCK characteristics on singletons (CACHE)
		MutablePicoContainer singletonContainer = container.as(Characteristics.LOCK, Characteristics.CACHE);
		singletonContainer.addAdapter(new ProviderAdapter(new ApplicationProvider(application)));
		singletonContainer.addComponent(Game.class, PinballGame.class);
		singletonContainer.addComponent(Configuration.class, ConfigurationImpl.class);
		singletonContainer.addComponent(ApplicationContext.class, ApplicationContextImpl.class, 
				new ConstantParameter(container));

		// Presentation
		singletonContainer.addComponent(ScreenManager.class, PinballScreenManager.class);
		singletonContainer.addComponent(PhysicsPlayField.class, PhysicsPlayFieldImpl.class);
		singletonContainer.addComponent(PresentationManager.class, PinballPresentationManager.class);
		singletonContainer.addComponent(GraphicsDisplay.class, PinballDisplay.class);
		singletonContainer.addAdapter(new PinballDisplayAdapter());
		
		// Logic
		singletonContainer.addComponent(LogicManager.class, PinballLogicManager.class);
		singletonContainer.addComponent(PlayFieldStoreDao.class, PlayFieldStoreDaoImpl.class);
		singletonContainer.addComponent(SimulationStoreDao.class, SimulationStoreDaoImpl.class);
		singletonContainer.addComponent(EventHandler.class, EventHandlerImpl.class);
		singletonContainer.addComponent(StateContext.class);
		
		// Rule engine
		singletonContainer.addComponent(RuleEngine.class);
	}
	
	private void registerPrototypes() {
		log.debug("Registering pico component prototypes");
		// IMPORTANT NOTICE:
		// Lifecycle works only for singletons! Ensure that there are no prototypes which use @PostConstruct!
		
		// Element factories
		container.addComponent(BumperElementFactory.class, BumperElementFactoryImpl.class);
		container.addComponent(SlingshotElementFactory.class, SlingshotElementFactoryImpl.class);
		container.addComponent(ObstacleElementFactory.class, ObstacleElementFactoryImpl.class);
		container.addComponent(EventCreator.class);

		// Screens
		container.addComponent(SplashScreenImpl.class);
		container.addComponent(MainMenuScreenImpl.class);
		container.addComponent(GameScreenImpl.class);
		container.addComponent(HighscoreScreenImpl.class);

		// Commands
		container.addComponent(NewSimulationCommand.class, NewSimulationCommandImpl.class);
		container.addComponent(SplashFinishedCommand.class, SplashFinishedCommandImpl.class);
		container.addComponent(ShowHighscoresCommand.class, ShowHighscoresCommandImpl.class);
		
		// State
		container.addComponent(MainMenuState.class);
		container.addComponent(SplashState.class);
		container.addComponent(SimulationState.class);
		container.addComponent(HighscoreState.class);
		
		// Simulation
		container.addComponent(SimulationManager.class);
		
		// Rules
		container.addComponent(HitScoreRule.class);
	}
	
	public void start() {
		container.start();
	}

	public Game getGame() {
		return container.getComponent(Game.class);
	}
	
	ApplicationContext getApplicationContext() {
		return container.getComponent(ApplicationContext.class);
	}
}

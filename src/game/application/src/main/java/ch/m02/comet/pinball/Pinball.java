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
import ch.m02.comet.pinball.core.logic.command.BallDownCommand;
import ch.m02.comet.pinball.core.logic.command.BallResetCommand;
import ch.m02.comet.pinball.core.logic.command.NewSimulationCommand;
import ch.m02.comet.pinball.core.logic.command.PlungeCommand;
import ch.m02.comet.pinball.core.logic.command.SavePlayerNameCommand;
import ch.m02.comet.pinball.core.logic.command.ShowHighscoresCommand;
import ch.m02.comet.pinball.core.logic.command.ShowMainMenuCommand;
import ch.m02.comet.pinball.core.logic.event.EventHandler;
import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.playfield.BumperElementFactory;
import ch.m02.comet.pinball.core.presentation.playfield.ObstacleElementFactory;
import ch.m02.comet.pinball.core.presentation.playfield.SlingshotElementFactory;
import ch.m02.comet.pinball.game.ApplicationProvider;
import ch.m02.comet.pinball.game.PinballGame;
import ch.m02.comet.pinball.logic.LogicManager;
import ch.m02.comet.pinball.logic.command.BallDownCommandImpl;
import ch.m02.comet.pinball.logic.command.BallResetCommandImpl;
import ch.m02.comet.pinball.logic.command.NewSimulationCommandImpl;
import ch.m02.comet.pinball.logic.command.PlungeCommandImpl;
import ch.m02.comet.pinball.logic.command.SavePlayerNameCommandImpl;
import ch.m02.comet.pinball.logic.command.ShowHighscoresCommandImpl;
import ch.m02.comet.pinball.logic.command.ShowMainMenuCommandImpl;
import ch.m02.comet.pinball.logic.event.EventHandlerImpl;
import ch.m02.comet.pinball.logic.internal.PinballLogicManager;
import ch.m02.comet.pinball.logic.simulation.SimulationManager;
import ch.m02.comet.pinball.logic.simulation.rule.RuleEngine;
import ch.m02.comet.pinball.logic.simulation.rule.basic.HitScoreRule;
import ch.m02.comet.pinball.logic.state.HighscoreState;
import ch.m02.comet.pinball.logic.state.MainMenuState;
import ch.m02.comet.pinball.logic.state.PlayerNameState;
import ch.m02.comet.pinball.logic.state.SimulationState;
import ch.m02.comet.pinball.logic.state.SplashState;
import ch.m02.comet.pinball.logic.state.StateContext;
import ch.m02.comet.pinball.persistence.PlayFieldStoreDao;
import ch.m02.comet.pinball.persistence.SimulationStoreDao;
import ch.m02.comet.pinball.persistence.internal.PlayFieldStoreDaoImpl;
import ch.m02.comet.pinball.persistence.internal.SimulationStoreDaoImpl;
import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsPlayField;
import ch.m02.comet.pinball.physics.PhysicsPlayFieldImpl;
import ch.m02.comet.pinball.physics.ball.Ball;
import ch.m02.comet.pinball.physics.ball.GroundSensorElement;
import ch.m02.comet.pinball.physics.box2d.keys.KeyMap;
import ch.m02.comet.pinball.physics.internal.PhysicsDefinitionLoader;
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
import ch.m02.comet.pinball.presentation.screens.PlayerNameScreenImpl;
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
		singletonContainer.addComponent(PresentationManager.class, PinballPresentationManager.class);
		singletonContainer.addComponent(GraphicsDisplay.class, PinballDisplay.class);
		singletonContainer.addAdapter(new PinballDisplayAdapter());
		singletonContainer.addComponent(KeyMap.class);
		
		// Physics
		singletonContainer.addComponent(Ball.class);
		singletonContainer.addComponent(PhysicsPlayField.class, PhysicsPlayFieldImpl.class);
		singletonContainer.addComponent(GroundSensorElement.class);
		
		// Logic
		singletonContainer.addComponent(LogicManager.class, PinballLogicManager.class);
		singletonContainer.addComponent(PlayFieldStoreDao.class, PlayFieldStoreDaoImpl.class);
		singletonContainer.addComponent(SimulationStoreDao.class, SimulationStoreDaoImpl.class);
		singletonContainer.addComponent(EventHandler.class, EventHandlerImpl.class);
		singletonContainer.addComponent(StateContext.class);
		
		// Rule engine
		singletonContainer.addComponent(RuleEngine.class);
		
		// Properties
		singletonContainer.addComponent(PhysicsDefinitionLoader.class);
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
		container.addComponent(PlayerNameScreenImpl.class);

		// Commands
		container.addComponent(NewSimulationCommand.class, NewSimulationCommandImpl.class);
		container.addComponent(ShowMainMenuCommand.class, ShowMainMenuCommandImpl.class);
		container.addComponent(ShowHighscoresCommand.class, ShowHighscoresCommandImpl.class);
		container.addComponent(BallDownCommand.class, BallDownCommandImpl.class);
		container.addComponent(PlungeCommand.class, PlungeCommandImpl.class);
		container.addComponent(SavePlayerNameCommand.class, SavePlayerNameCommandImpl.class);
		container.addComponent(BallResetCommand.class, BallResetCommandImpl.class);
		
		// State
		container.addComponent(MainMenuState.class);
		container.addComponent(SplashState.class);
		container.addComponent(SimulationState.class);
		container.addComponent(HighscoreState.class);
		container.addComponent(PlayerNameState.class);
		
		// Simulation
		container.addComponent(SimulationManager.class);
		
		// Rules
		container.addComponent(HitScoreRule.class);
	}
	
	public void start() {
		container.start();
		PhysicsDefinitionLoader loader = container.getComponent(PhysicsDefinitionLoader.class);
		PhysicsDefinition.INSTANCE.initialize(loader);
	}

	public Game getGame() {
		return container.getComponent(Game.class);
	}
	
	ApplicationContext getApplicationContext() {
		return container.getComponent(ApplicationContext.class);
	}
}

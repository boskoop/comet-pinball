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

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.core.internal.ApplicationContextImpl;
import ch.m02.comet.pinball.core.internal.ConfigurationImpl;
import ch.m02.comet.pinball.core.logic.command.NewGameCommand;
import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.playfield.BumperElementFactory;
import ch.m02.comet.pinball.core.presentation.playfield.ObstacleElementFactory;
import ch.m02.comet.pinball.core.presentation.playfield.SlingshotElementFactory;
import ch.m02.comet.pinball.game.ApplicationProvider;
import ch.m02.comet.pinball.game.PinballGame;
import ch.m02.comet.pinball.logic.LogicManager;
import ch.m02.comet.pinball.logic.command.NewGameCommandImpl;
import ch.m02.comet.pinball.logic.internal.PinballLogicManager;
import ch.m02.comet.pinball.logic.persistence.PlayFieldStoreManager;
import ch.m02.comet.pinball.logic.persistence.SimulationStoreManager;
import ch.m02.comet.pinball.logic.persistence.internal.PlayFieldStoreManagerImpl;
import ch.m02.comet.pinball.logic.persistence.internal.SimulationStoreManagerImpl;
import ch.m02.comet.pinball.physics.PhysicPlayField;
import ch.m02.comet.pinball.physics.PhysicPlayFieldImpl;
import ch.m02.comet.pinball.physics.placable.BumperElementFactoryImpl;
import ch.m02.comet.pinball.physics.placable.ObstacleElementFactoryImpl;
import ch.m02.comet.pinball.physics.placable.SlingshotElementFactoryImpl;
import ch.m02.comet.pinball.presentation.PinballPresentationManager;
import ch.m02.comet.pinball.presentation.ScreenManager;
import ch.m02.comet.pinball.presentation.screens.GameScreenImpl;
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
		container.start();
	}

	private void registerSingletons() {
		log.debug("Registering pico component singletons");
		// In order to ensure thread safety, we use the LOCK characteristics on singletons (CACHE)
		MutablePicoContainer singletonContainer = container.as(Characteristics.LOCK, Characteristics.CACHE);
		singletonContainer.addAdapter(new ProviderAdapter(new ApplicationProvider(application)));
		singletonContainer.addComponent(Game.class, PinballGame.class);
		singletonContainer.addComponent(Configuration.class, ConfigurationImpl.class);
		singletonContainer.addComponent(PlayFieldStoreManager.class, PlayFieldStoreManagerImpl.class);
		singletonContainer.addComponent(SimulationStoreManager.class, SimulationStoreManagerImpl.class);
		singletonContainer.addComponent(ApplicationContext.class, ApplicationContextImpl.class, 
				new ConstantParameter(container));

		singletonContainer.addComponent(ScreenManager.class, PinballScreenManager.class);
		singletonContainer.addComponent(PhysicPlayField.class, PhysicPlayFieldImpl.class);
		singletonContainer.addComponent(PresentationManager.class, PinballPresentationManager.class);
		singletonContainer.addComponent(LogicManager.class, PinballLogicManager.class);
	}
	
	private void registerPrototypes() {
		log.debug("Registering pico component prototypes");
		// IMPORTANT NOTICE:
		// Lifecycle works only for singletons! Ensure that there are no prototypes which use @PostConstruct!
		
		// Element factories
		container.addComponent(BumperElementFactory.class, BumperElementFactoryImpl.class);
		container.addComponent(SlingshotElementFactory.class, SlingshotElementFactoryImpl.class);
		container.addComponent(ObstacleElementFactory.class, ObstacleElementFactoryImpl.class);

		// Screens
		container.addComponent(SplashScreenImpl.class);
		container.addComponent(MainMenuScreenImpl.class);
		container.addComponent(GameScreenImpl.class);

		// Commands
		container.addComponent(NewGameCommand.class, NewGameCommandImpl.class);
	}

	public Game getGame() {
		return container.getComponent(Game.class);
	}
	
	ApplicationContext getApplicationContext() {
		return container.getComponent(ApplicationContext.class);
	}
}

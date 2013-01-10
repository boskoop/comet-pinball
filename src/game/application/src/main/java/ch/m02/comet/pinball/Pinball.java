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

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.core.config.internal.ConfigurationImpl;
import ch.m02.comet.pinball.core.internal.ApplicationContextImpl;
import ch.m02.comet.pinball.game.ApplicationProvider;
import ch.m02.comet.pinball.game.PinballGame;
import ch.m02.comet.pinball.presentation.PinballScreenManager;

import com.badlogic.gdx.Game;

public class Pinball {

	private MainApplication application;
	private MutablePicoContainer container;

	public Pinball(MainApplication application) {
		this.application = application;
		container = new PicoBuilder()
				.withMonitor(new Slf4jComponentMonitor())
				.withBehaviors(new OptInCaching()) // Enable singletons
				.withComponentFactory(new AnnotatedFieldInjection(Inject.class)) // use JSR-330
				.withHiddenImplementations() // Hide concrete implementations when using an interface
				.withJavaEE5Lifecycle() // use JSR-250 (@PostConstruct) annotations
				.build();
		registerSingletons();
		registerPrototypes();
		container.start();
	}

	private void registerSingletons() {
		MutablePicoContainer singletonContainer = container.as(Characteristics.CACHE);
		singletonContainer.addAdapter(new ProviderAdapter(new ApplicationProvider(application)));
		singletonContainer.addComponent(Game.class, PinballGame.class);
		singletonContainer.addComponent(Configuration.class, ConfigurationImpl.class);
		singletonContainer.addComponent(ApplicationContext.class, ApplicationContextImpl.class, 
				new ConstantParameter(container));

		singletonContainer.addComponent(PinballScreenManager.class);
	}
	
	private void registerPrototypes() {
	}

	public Game getGame() {
		return container.getComponent(Game.class);
	}
	
	public ApplicationContext getApplicationContext() {
		return container.getComponent(ApplicationContext.class);
	}
}

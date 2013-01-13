package ch.m02.comet.pinball.presentation;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.picocontainer.PicoContainer;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.config.BooleanProperties;
import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.presentation.screens.GameScreen;
import ch.m02.comet.pinball.presentation.screens.MainMenuScreen;
import ch.m02.comet.pinball.presentation.screens.ManagedScreen;
import ch.m02.comet.pinball.presentation.screens.SplashScreen;

import com.badlogic.gdx.Screen;

public class PinballScreenManager implements ScreenManager {

	@Inject
	private Configuration configuration;
	
	@Inject
	private ApplicationContext context;

	private ScreenPresenter presenter;

	private Map<Class<? extends ManagedScreen>, ManagedScreen> screens;

	@Override
	public void init(ScreenPresenter presenter) {
		this.presenter = presenter;

		registerAndInitScreens();

		if (configuration
				.getBooleanProperty(BooleanProperties.SKIP_SPLASHSCREEN)) {
			changeScreenTo(MainMenuScreen.class);
		} else {
			changeScreenTo(SplashScreen.class);
		}
	}

	private void registerAndInitScreens() {
		PicoContainer container = context.getComponentContainer();
		SplashScreen splashScreen = container.getComponent(SplashScreen.class);
		MainMenuScreen menuScreen = container.getComponent(MainMenuScreen.class);
		GameScreen gameScreen = container.getComponent(GameScreen.class);

		screens = new HashMap<Class<? extends ManagedScreen>, ManagedScreen>();
		screens.put(SplashScreen.class, splashScreen);
		screens.put(MainMenuScreen.class, menuScreen);
		screens.put(GameScreen.class, gameScreen);
		
		for (ManagedScreen screen : screens.values()) {
			screen.init();
		}
	}

	@Override
	public void dispose() {
		for (Screen screen : screens.values()) {
			screen.dispose();
		}
	}

	@Override
	public void changeScreenTo(Class<? extends ManagedScreen> screenKey) {
		Screen screen = screens.get(screenKey);
		presenter.setScreen(screen);
	}
}

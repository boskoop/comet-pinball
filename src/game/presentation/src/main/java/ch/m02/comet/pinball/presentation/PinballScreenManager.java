package ch.m02.comet.pinball.presentation;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.BooleanProperties;
import ch.m02.comet.pinball.core.Configuration;
import ch.m02.comet.pinball.presentation.screens.GameScreen;
import ch.m02.comet.pinball.presentation.screens.MainMenuScreen;
import ch.m02.comet.pinball.presentation.screens.ManagedScreen;
import ch.m02.comet.pinball.presentation.screens.ScreenManager;
import ch.m02.comet.pinball.presentation.screens.SplashScreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;

public class PinballScreenManager implements Disposable, ScreenManager {

	@Inject
	private Configuration configuration;

	private ScreenPresenter presenter;

	private Map<Class<? extends ManagedScreen>, ManagedScreen> screens;

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
		SplashScreen splashScreen = new SplashScreen(this);
		MainMenuScreen menuScreen = new MainMenuScreen(this);
		GameScreen gameScreen = new GameScreen(this);

		screens = new HashMap<Class<? extends ManagedScreen>, ManagedScreen>();
		screens.put(SplashScreen.class, splashScreen);
		screens.put(MainMenuScreen.class, menuScreen);
		screens.put(GameScreen.class, gameScreen);

		gameScreen.init();
		splashScreen.init();
		menuScreen.init();
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

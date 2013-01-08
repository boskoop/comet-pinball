package ch.m02.comet.pinball.presentation;


import java.util.HashMap;
import java.util.Map;

import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.presentation.screens.GameScreen;
import ch.m02.comet.pinball.presentation.screens.MainMenuScreen;
import ch.m02.comet.pinball.presentation.screens.ManagedScreen;
import ch.m02.comet.pinball.presentation.screens.ScreenManager;
import ch.m02.comet.pinball.presentation.screens.SplashScreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;

public class PinballScreenManager implements Disposable, ScreenManager {

	private ScreenPresenter presenter;

	private Map<Class<? extends ManagedScreen>, ManagedScreen> screens;
	
	public PinballScreenManager(ScreenPresenter presenter) {
		this.presenter = presenter;
	}
	
	public void init() {
		registerAndInitScreens();
		
		if(Configuration.INSTANCE.skipSplashscreen()) {
			changeScreenTo(GameScreen.class);
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

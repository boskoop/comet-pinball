package ch.m02.comet.pinball;

import ch.m02.comet.pinball.config.Configuration;
import ch.m02.comet.pinball.screens.GameScreen;
import ch.m02.comet.pinball.screens.MainMenuScreen;
import ch.m02.comet.pinball.screens.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;


public class PinballGame extends Game {
	
	public static final String LOG = "comet-pinball-game";
	
	private GameScreen gameScreen;
	private SplashScreen splashScreen;
	private MainMenuScreen menuScreen;

	private FPSLogger fpsLogger;

	private MainApplication application;
	
	public PinballGame(MainApplication application) {
		this.application = application;
	}
	
	public Screen getGameScreen() {
		return gameScreen;
	}

	public Screen getSplashScreen() {
		return splashScreen;
	}

	public Screen getMenuScreen() {
		return menuScreen;
	}

	@Override
	public void create() {
		
		
		splashScreen = new SplashScreen(this);
		menuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		gameScreen.init();
		splashScreen.init();
		menuScreen.init();
		if(Configuration.INSTANCE.skipSplashscreen()) {
			setScreen(gameScreen);
		} else {
			setScreen(splashScreen);
		}
		fpsLogger = new FPSLogger();
	}

	@Override
	public void dispose() {
		super.dispose();
		gameScreen.dispose();
		splashScreen.dispose();
		menuScreen.dispose();
	}

	@Override
	public void render() {
		checkExit();
		if(Configuration.INSTANCE.isDebugEnabled()) {
			fpsLogger.log();
		}
		super.render();
	}

	private void checkExit() {
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			application.exit();
		}
	}
}
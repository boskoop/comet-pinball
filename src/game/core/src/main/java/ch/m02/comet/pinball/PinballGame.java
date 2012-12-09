package ch.m02.comet.pinball;

import ch.m02.comet.pinball.screens.GameScreen;
import ch.m02.comet.pinball.screens.MainMenuScreen;
import ch.m02.comet.pinball.screens.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class PinballGame extends Game {
	
	public static final String LOG = "comet-pinball-game";
	
	private GameScreen gameScreen;
	private SplashScreen splashScreen;
	private MainMenuScreen menuScreen;
	
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
		if(Configuration.SKIP_SPLASHSCREEN) {
			splashScreen.init();
			menuScreen.init();
			setScreen(gameScreen);
		} else {
			setScreen(splashScreen);
		}
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
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
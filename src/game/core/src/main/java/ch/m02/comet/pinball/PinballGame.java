package ch.m02.comet.pinball;

import ch.m02.comet.pinball.screens.GameScreen;
import ch.m02.comet.pinball.screens.SplashScreen;

import com.badlogic.gdx.Game;


public class PinballGame extends Game {
	
	public static final String LOG = "comet-pinball-prototype";
	
	@Override
	public void create() {		
		if(Configuration.SKIP_SPLASHSCREEN)
			setScreen(new GameScreen(this));
		else
			setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
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
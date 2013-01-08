package ch.m02.comet.pinball;

import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.presentation.PinballScreenManager;
import ch.m02.comet.pinball.presentation.ScreenPresenter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;


public class PinballGame extends Game implements ScreenPresenter {
	
	public static final String LOG = "comet-pinball-game";
	
	private PinballScreenManager screenManager;

	private FPSLogger fpsLogger;

	private MainApplication application;
	
	public PinballGame(MainApplication application) {
		this.application = application;
		this.screenManager = new PinballScreenManager(this);
	}

	@Override
	public void create() {
		screenManager.init();
		fpsLogger = new FPSLogger();
	}

	@Override
	public void dispose() {
		super.dispose();
		screenManager.dispose();
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
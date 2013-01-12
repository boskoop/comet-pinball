package ch.m02.comet.pinball.game;

import javax.inject.Inject;

import ch.m02.comet.pinball.MainApplication;
import ch.m02.comet.pinball.core.BooleanProperties;
import ch.m02.comet.pinball.core.Configuration;
import ch.m02.comet.pinball.presentation.PinballScreenManager;
import ch.m02.comet.pinball.presentation.ScreenPresenter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;


public class PinballGame extends Game implements ScreenPresenter {
	
	public static final String LOG = "comet-pinball-game";

	private FPSLogger fpsLogger;
	
	@Inject
	private Configuration configuration;

	@Inject
	private MainApplication application;

	@Inject
	private PinballScreenManager screenManager;

	@Override
	public void create() {
		screenManager.init(this);
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
		if(configuration.getBooleanProperty(BooleanProperties.DEBUG)) {
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
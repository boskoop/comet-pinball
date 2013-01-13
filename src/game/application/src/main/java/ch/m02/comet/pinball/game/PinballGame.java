package ch.m02.comet.pinball.game;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ch.m02.comet.pinball.MainApplication;
import ch.m02.comet.pinball.core.config.BooleanProperties;
import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.core.config.KeyProperties;
import ch.m02.comet.pinball.physics.box2d.keys.KeyMap;
import ch.m02.comet.pinball.presentation.ScreenManager;
import ch.m02.comet.pinball.presentation.ScreenPresenter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;


public class PinballGame extends Game implements ScreenPresenter {
	
	public static final String LOG = "comet-pinball-game";

	private FPSLogger fpsLogger;
	
	@Inject
	private Configuration configuration;

	@Inject
	private MainApplication application;

	@Inject
	private ScreenManager screenManager;

	@Inject
	private KeyMap keyMap;

	private int exitKey;
	
	@PostConstruct
	public void loadKey() {
		exitKey = keyMap.getKey(KeyProperties.EXIT_GAME);
	}

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
		if (Gdx.input.isKeyPressed(exitKey)) {
			application.exit();
		}
	}
}
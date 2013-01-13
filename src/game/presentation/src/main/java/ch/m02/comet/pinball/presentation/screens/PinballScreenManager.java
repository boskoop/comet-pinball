package ch.m02.comet.pinball.presentation.screens;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.picocontainer.PicoContainer;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.config.BooleanProperties;
import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.core.logic.command.SplashFinishedCommand;
import ch.m02.comet.pinball.core.presentation.screen.GameScreen;
import ch.m02.comet.pinball.core.presentation.screen.MainMenuScreen;
import ch.m02.comet.pinball.core.presentation.screen.PinballScreen;
import ch.m02.comet.pinball.core.presentation.screen.SplashScreen;
import ch.m02.comet.pinball.presentation.ScreenManager;
import ch.m02.comet.pinball.presentation.ScreenPresenter;

import com.badlogic.gdx.Screen;

public class PinballScreenManager implements ScreenManager {

	@Inject
	private Configuration configuration;
	
	@Inject
	private ApplicationContext context;

	private ScreenPresenter presenter;

	private Map<Class<? extends PinballScreen>, ManagedScreen> screens;

	@Override
	public void init(ScreenPresenter presenter) {
		this.presenter = presenter;

		registerAndInitScreens();

		if (configuration
				.getBooleanProperty(BooleanProperties.SKIP_SPLASHSCREEN)) {
			context.getComponentContainer().getComponent(SplashFinishedCommand.class).execute();
		} else {
			changeScreenTo(SplashScreen.class);
		}
	}

	private void registerAndInitScreens() {
		PicoContainer container = context.getComponentContainer();
		SplashScreenImpl splashScreen = container.getComponent(SplashScreenImpl.class);
		MainMenuScreenImpl menuScreen = container.getComponent(MainMenuScreenImpl.class);
		GameScreenImpl gameScreen = container.getComponent(GameScreenImpl.class);

		screens = new HashMap<Class<? extends PinballScreen>, ManagedScreen>();
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
	public void changeScreenTo(Class<? extends PinballScreen> screenKey) {
		Screen screen = screens.get(screenKey);
		presenter.setScreen(screen);
	}
}

package ch.m02.comet.pinball.presentation.screens;


import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.screen.PinballScreen;
import ch.m02.comet.pinball.presentation.ScreenManager;

import com.badlogic.gdx.Screen;

public abstract class ManagedScreen implements Screen, PinballScreen {

	@Inject
	private ScreenManager manager;
	
	protected void fireChangeScreen(Class<? extends PinballScreen> screen) {
		manager.changeScreenTo(screen);
	}
	
	public abstract void init();

}

package ch.m02.comet.pinball.presentation.screens;


import com.badlogic.gdx.Screen;

public abstract class ManagedScreen implements Screen {

	private final ScreenManager manager;

	public ManagedScreen(ScreenManager manager) {
		this.manager = manager;
	}
	
	protected void fireChangeScreen(Class<? extends ManagedScreen> screen) {
		manager.changeScreenTo(screen);
	}

}

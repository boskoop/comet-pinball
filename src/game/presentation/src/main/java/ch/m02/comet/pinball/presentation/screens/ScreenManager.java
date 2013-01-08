package ch.m02.comet.pinball.presentation.screens;


public interface ScreenManager {

	public void changeScreenTo(Class<? extends ManagedScreen> screen);
	
}

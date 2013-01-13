package ch.m02.comet.pinball.presentation;

import com.badlogic.gdx.utils.Disposable;

import ch.m02.comet.pinball.presentation.screens.ManagedScreen;


public interface ScreenManager extends Disposable {
	
	public void init(ScreenPresenter presenter);

	public void changeScreenTo(Class<? extends ManagedScreen> screen);
	
}

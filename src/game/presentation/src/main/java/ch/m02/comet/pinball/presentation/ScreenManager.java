package ch.m02.comet.pinball.presentation;

import ch.m02.comet.pinball.core.presentation.screen.PinballScreen;

import com.badlogic.gdx.utils.Disposable;


public interface ScreenManager extends Disposable {
	
	public void init(ScreenPresenter presenter);

	public void changeScreenTo(Class<? extends PinballScreen> screen);
	
}

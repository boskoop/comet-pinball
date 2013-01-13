package ch.m02.comet.pinball.presentation;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldElement;
import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.screen.PinballScreen;

public class PinballPresentationManager implements PresentationManager {

	@Inject
	private ScreenManager screenManager;
	
	@Override
	public void clearElements() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeElement(PlayFieldElement element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showScreen(Class<? extends PinballScreen> screen) {
		screenManager.changeScreenTo(screen);
	}

}

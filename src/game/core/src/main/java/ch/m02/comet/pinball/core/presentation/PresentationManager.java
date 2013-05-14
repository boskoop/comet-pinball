package ch.m02.comet.pinball.core.presentation;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldElement;
import ch.m02.comet.pinball.core.presentation.screen.PinballScreen;

public interface PresentationManager {

	public void clearElements();
	
	public void placeElement(PlayFieldElement element);
	
	public void showScreen(Class<? extends PinballScreen> screen);
	
	public void resetBall();
	
}

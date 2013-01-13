package ch.m02.comet.pinball.core.presentation;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldElement;

public interface PresentationManager {

	public void clearElements();
	
	public void placeElement(PlayFieldElement element);
	
}

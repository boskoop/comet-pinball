package ch.m02.comet.pinball.core.presentation.playfield;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldElement;

public interface ElementFactory<T extends PlayFieldElement> {

	public void createAndPlacePlayFieldElement();
	
	public void setPlayFieldElement(T element);
	
}

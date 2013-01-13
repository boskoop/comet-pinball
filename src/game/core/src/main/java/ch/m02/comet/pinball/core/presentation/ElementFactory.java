package ch.m02.comet.pinball.core.presentation;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldElement;

public interface ElementFactory<T extends PlayFieldElement> {

	public void createAndPlacePlayFieldElement();
	
	public int getElementId();
	
	public void setPlayFieldElement(T element);
	
}

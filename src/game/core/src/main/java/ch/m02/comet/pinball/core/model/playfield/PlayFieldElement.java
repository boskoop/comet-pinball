package ch.m02.comet.pinball.core.model.playfield;

import ch.m02.comet.pinball.core.model.simulation.Placement;


public interface PlayFieldElement {

	public PlayFieldElementType getType();
	
	public Placement getPlacement();
	
}

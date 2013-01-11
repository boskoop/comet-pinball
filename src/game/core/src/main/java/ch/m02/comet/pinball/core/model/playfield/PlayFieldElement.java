package ch.m02.comet.pinball.core.model.playfield;



public interface PlayFieldElement {
	
	public int getId();

	public PlayFieldElementType getType();
	
	public Placement getPlacement();
	
}

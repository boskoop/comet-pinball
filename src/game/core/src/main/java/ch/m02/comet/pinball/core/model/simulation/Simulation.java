package ch.m02.comet.pinball.core.model.simulation;

import java.io.Serializable;

public interface Simulation extends Serializable {

	public Player getPlayer();
	
	public PlayFieldId getPlayFieldId();
	
	public Score getScore();
	
}
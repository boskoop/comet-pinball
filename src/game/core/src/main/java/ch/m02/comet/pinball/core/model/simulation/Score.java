package ch.m02.comet.pinball.core.model.simulation;

import java.io.Serializable;

public interface Score extends Serializable {

	public int getScoreValue();
	
	public Simulation getSimulation();
	
}

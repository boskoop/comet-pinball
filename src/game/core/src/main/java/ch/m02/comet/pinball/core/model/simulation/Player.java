package ch.m02.comet.pinball.core.model.simulation;

import java.io.Serializable;
import java.util.List;


public interface Player extends Serializable {

	public List<? extends Simulation> getSimulations();
	
	public String getName();
	
}

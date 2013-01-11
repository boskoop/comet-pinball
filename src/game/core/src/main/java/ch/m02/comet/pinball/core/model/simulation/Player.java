package ch.m02.comet.pinball.core.model.simulation;

import java.io.Serializable;
import java.util.Set;


public interface Player extends Serializable {

	public Set<? extends Simulation> getSimulations();
	
	public String getName();
	
}

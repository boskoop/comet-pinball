package ch.m02.comet.pinball.core.model.simulation;

import java.io.Serializable;
import java.util.Set;

public interface PlayFieldId extends Serializable {

	public String getName();
	
	public Set<? extends Simulation> getSimulations();
	
}

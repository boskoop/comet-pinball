package ch.m02.comet.pinball.core.model;

import java.io.Serializable;
import java.util.List;

public interface Player extends Serializable {

	public List<Simulation> getSimulations();
	
	public String getName();
	
}

package ch.m02.comet.pinball.persistence.model.simulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SimulationStorePdo implements Serializable {
	
	private static final long serialVersionUID = -357491596663068908L;
	
	private List<SimulationPdo> simulations = new ArrayList<SimulationPdo>();
	
	public void addSimulation(SimulationPdo simulation) {
		simulations.add(simulation);
	}
	
	public List<SimulationPdo> getSimulations() {
		return Collections.unmodifiableList(simulations);
	}
	
	public void clear() {
		simulations.clear();
	}
	
}

package ch.m02.comet.pinball.logic.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.m02.comet.pinball.logic.model.simulation.SimulationPdo;

public class SimulationStore implements Serializable {

	private static final long serialVersionUID = -1848393705840620200L;
	
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

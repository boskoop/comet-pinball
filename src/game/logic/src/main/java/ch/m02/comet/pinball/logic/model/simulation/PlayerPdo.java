package ch.m02.comet.pinball.logic.model.simulation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.m02.comet.pinball.core.model.simulation.Player;

public class PlayerPdo implements Player {
	
	private static final long serialVersionUID = 5548565934830840382L;

	private String name;
	
	private Set<SimulationPdo> simulations = new HashSet<SimulationPdo>();
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Set<SimulationPdo> getSimulations() {
		return Collections.unmodifiableSet(simulations);
	}
	
	void addSimulation(SimulationPdo simulation) {
		simulations.add(simulation);
	}
	
	void removeSimulation(SimulationPdo simulation) {
		simulations.remove(simulation);
	}

}

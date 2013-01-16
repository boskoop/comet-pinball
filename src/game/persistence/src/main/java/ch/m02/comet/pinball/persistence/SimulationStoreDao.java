package ch.m02.comet.pinball.persistence;

import java.util.List;
import java.util.Set;

import ch.m02.comet.pinball.persistence.model.simulation.PlayFieldIdPdo;
import ch.m02.comet.pinball.persistence.model.simulation.PlayerPdo;
import ch.m02.comet.pinball.persistence.model.simulation.ScorePdo;
import ch.m02.comet.pinball.persistence.model.simulation.SimulationPdo;


public interface SimulationStoreDao {

	public Set<PlayFieldIdPdo> getPlayFieldIds();
	
	public PlayFieldIdPdo findPlayFieldId(String id);
	
	public List<SimulationPdo> getSimulations();
	
	public void addSimulation(SimulationPdo simulation);
	
	public PlayerPdo findPlayer(String name);
	
	public Set<PlayerPdo> getPlayers();
	
	public List<ScorePdo> getScores();
	
}

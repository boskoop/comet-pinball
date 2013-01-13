package ch.m02.comet.pinball.logic.persistence;

import java.util.List;
import java.util.Set;

import ch.m02.comet.pinball.logic.model.simulation.PlayFieldIdPdo;
import ch.m02.comet.pinball.logic.model.simulation.PlayerPdo;
import ch.m02.comet.pinball.logic.model.simulation.ScorePdo;
import ch.m02.comet.pinball.logic.model.simulation.SimulationPdo;


public interface SimulationStoreDao {

	public Set<PlayFieldIdPdo> getPlayFieldIds();
	
	public PlayFieldIdPdo findPlayFieldId(String id);
	
	public List<SimulationPdo> getSimulations();
	
	public void addSimulation(SimulationPdo simulation);
	
	public PlayerPdo findPlayer(String name);
	
	public Set<PlayerPdo> getPlayers();
	
	public List<ScorePdo> getScores();
	
}

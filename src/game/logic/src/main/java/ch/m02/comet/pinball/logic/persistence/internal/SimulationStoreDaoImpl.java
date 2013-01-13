package ch.m02.comet.pinball.logic.persistence.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import ch.m02.comet.pinball.logic.model.simulation.PlayFieldIdPdo;
import ch.m02.comet.pinball.logic.model.simulation.PlayerPdo;
import ch.m02.comet.pinball.logic.model.simulation.ScorePdo;
import ch.m02.comet.pinball.logic.model.simulation.SimulationPdo;
import ch.m02.comet.pinball.logic.persistence.SimulationStore;
import ch.m02.comet.pinball.logic.persistence.SimulationStoreDao;

public class SimulationStoreDaoImpl implements SimulationStoreDao {

	private SimulationStoreManager storeManager;

	@PostConstruct
	public void init() {
		storeManager = new SimulationStoreManager();
		storeManager.initializePersistence();
	}
	
	@Override
	public Set<PlayFieldIdPdo> getPlayFieldIds() {
		List<SimulationPdo> simulations = storeManager.getSimulationStore().getSimulations();
		Set<PlayFieldIdPdo> playFieldIds = new HashSet<PlayFieldIdPdo>();
		for (SimulationPdo sim : simulations) {
			playFieldIds.add(sim.getPlayFieldId());
		}
		return playFieldIds;
	}
	
	@Override
	public PlayFieldIdPdo findPlayFieldId(String id) {
		Set<PlayFieldIdPdo> fieldIds = getPlayFieldIds();
		for (PlayFieldIdPdo fieldId : fieldIds) {
			if (id.equals(fieldId.getName())) {
				return fieldId;
			}
		}
		return null;
	}
	
	@Override
	public List<SimulationPdo> getSimulations() {
		return storeManager.getSimulationStore().getSimulations();
	}
	
	@Override
	public void addSimulation(SimulationPdo simulation) {
		SimulationStore store = storeManager.getSimulationStore();
		store.addSimulation(simulation);
		storeManager.saveSimulationStore(store);
	}

	@Override
	public PlayerPdo findPlayer(String name) {
		Set<PlayerPdo> players = getPlayers();
		for (PlayerPdo player : players) {
			if (name.equals(player.getName())) {
				return player;
			}
		}
		return null;
	}

	@Override
	public Set<PlayerPdo> getPlayers() {
		List<SimulationPdo> simulations = storeManager.getSimulationStore().getSimulations();
		Set<PlayerPdo> players = new HashSet<PlayerPdo>();
		for (SimulationPdo sim : simulations) {
			players.add(sim.getPlayer());
		}
		return players;
	}

	@Override
	public List<ScorePdo> getScores() {
		List<SimulationPdo> simulations = storeManager.getSimulationStore().getSimulations();
		List<ScorePdo> scores = new ArrayList<ScorePdo>();
		for (SimulationPdo sim : simulations) {
			if(sim.getScore() != null)
				scores.add(sim.getScore());
		}
		Collections.sort(scores, new Comparator<ScorePdo>() {
			@Override
			public int compare(ScorePdo score1, ScorePdo score2) {
				if (score1.getScoreValue() > score2.getScoreValue()) {
					return -1;
				} else if (score1.getScoreValue() < score2.getScoreValue()) {
					return 1;
				}
				return 0;
			}
		});
		return scores;
	}
	
}

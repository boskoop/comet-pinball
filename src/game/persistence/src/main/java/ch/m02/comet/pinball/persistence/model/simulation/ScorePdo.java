package ch.m02.comet.pinball.persistence.model.simulation;

import ch.m02.comet.pinball.core.model.simulation.Score;

public class ScorePdo implements Score {
	
	private static final long serialVersionUID = 3276227074017261480L;
	
	private int score;
	private SimulationPdo simulation;

	@Override
	public int getScoreValue() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	@Override
	public SimulationPdo getSimulation() {
		return simulation;
	}
	
	void setSimulation(SimulationPdo simulation) {
		this.simulation = simulation;
	}

}

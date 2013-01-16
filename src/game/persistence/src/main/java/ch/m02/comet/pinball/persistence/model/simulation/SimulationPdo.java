package ch.m02.comet.pinball.persistence.model.simulation;

import ch.m02.comet.pinball.core.model.simulation.Simulation;

public class SimulationPdo implements Simulation {

	private static final long serialVersionUID = -1067223287595852686L;

	private PlayerPdo player;
	private PlayFieldIdPdo playFieldId;
	private ScorePdo score;

	@Override
	public PlayerPdo getPlayer() {
		return player;
	}

	public void setPlayer(PlayerPdo player) {
		if (this.player != null) {
			this.player.removeSimulation(this);
		}
		this.player = player;
		if (player != null) {
			player.addSimulation(this);
		}
	}

	@Override
	public PlayFieldIdPdo getPlayFieldId() {
		return playFieldId;
	}

	public void setPlayFieldId(PlayFieldIdPdo playFieldId) {
		if (this.playFieldId != null) {
			this.playFieldId.removeSimulation(this);
		}
		this.playFieldId = playFieldId;
		if (playFieldId != null) {
			playFieldId.addSimulation(this);
		}
	}

	@Override
	public ScorePdo getScore() {
		return score;
	}

	public void setScore(ScorePdo score) {
		if (this.score != null) {
			this.score.setSimulation(null);
		}
		this.score = score;
		if (score != null) {
			score.setSimulation(this);
		}
	}

}

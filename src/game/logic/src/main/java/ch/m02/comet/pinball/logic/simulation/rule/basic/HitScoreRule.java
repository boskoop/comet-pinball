package ch.m02.comet.pinball.logic.simulation.rule.basic;

import java.util.List;

public class HitScoreRule extends AbstractRule {

	private int id;
	private int scorePerHit;
	
	@Override
	public void handleHit(int id) {
		if (id != this.id) {
			return;
		}
		engine.addScore(scorePerHit);
	}


	@Override
	public void init(List<Integer> parameters) {
		id = parameters.get(0);
		scorePerHit = parameters.get(1);
	}

}

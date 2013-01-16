package ch.m02.comet.pinball.logic.simulation.rule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.playfield.Rule;
import ch.m02.comet.pinball.core.presentation.Display;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldRulePdo;
import ch.m02.comet.pinball.persistence.model.simulation.ScorePdo;

public class RuleEngine {
	
	@Inject
	private ApplicationContext context;
	
	@Inject
	private Display display;
	
	private ScorePdo score;
	
	private List<Rule> registeredRules;

	public void initScore(ScorePdo score) {
		this.score = score;
		updateDisplay();
	}
	
	public void registerRules(List<PlayFieldRulePdo> rules) {
		registeredRules = new ArrayList<Rule>(rules.size());
		for (PlayFieldRulePdo rule : rules) {
			registeredRules.add(instantiateRule(rule));
		}
	}
	
	private Rule instantiateRule(PlayFieldRulePdo rulePdo) {
		Class<? extends Rule> ruleClass = rulePdo.getClassName();
		Rule rule = context.getComponentContainer().getComponent(ruleClass);
		rule.init(rulePdo.getParameters());
		return rule;
	}

	private void updateDisplay() {
		display.displayScore(score.getScoreValue());
	}

	public void handleBallHitsId(int id) {
		for (Rule rule : registeredRules) {
			rule.handleHit(id);
		}
	}

	public void addScore(int scoreValue) {
		score.addScore(scoreValue);
		updateDisplay();
	}

}

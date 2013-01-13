package ch.m02.comet.pinball.logic.simulation.rule.basic;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.model.playfield.Rule;
import ch.m02.comet.pinball.logic.simulation.rule.RuleEngine;

public abstract class AbstractRule implements Rule {

	@Inject
	protected RuleEngine engine;
	
}

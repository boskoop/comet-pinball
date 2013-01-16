package ch.m02.comet.pinball.logic.simulation;

import java.util.List;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.screen.GameScreen;
import ch.m02.comet.pinball.logic.simulation.rule.RuleEngine;
import ch.m02.comet.pinball.persistence.PlayFieldStoreDao;
import ch.m02.comet.pinball.persistence.SimulationStoreDao;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldElementPdo;
import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldPdo;
import ch.m02.comet.pinball.persistence.model.simulation.PlayFieldIdPdo;
import ch.m02.comet.pinball.persistence.model.simulation.PlayerPdo;
import ch.m02.comet.pinball.persistence.model.simulation.ScorePdo;
import ch.m02.comet.pinball.persistence.model.simulation.SimulationPdo;

public class SimulationManager {
	
	@Inject
	private PlayFieldStoreDao playfieldDao;
	
	@Inject
	private SimulationStoreDao simulationDao;
	
	@Inject
	private PresentationManager presentation;

	private SimulationPdo simulation;
	
	@Inject
	private RuleEngine ruleEngine;

	private PlayFieldPdo playField;

	public void startNewSimulation() {
		clearPlayField();
		createSimulation();
		loadPlayField();
		initRuleEngine();
		loadScreen();
	}

	private void clearPlayField() {
		presentation.clearElements();
	}
	
	private void createSimulation() {
		simulation = new SimulationPdo();
		ScorePdo score = new ScorePdo();
		simulation.setScore(score);
	}
	
	private void loadPlayField() {
		List<PlayFieldPdo> playFields = playfieldDao.findPlayFields();
		playField = playFields.get(0);
		for (PlayFieldElementPdo element : playField.getElements()) {
			presentation.placeElement(element);
		}
		PlayFieldIdPdo fieldId = simulationDao.findPlayFieldId(playField.getName());
		if (fieldId == null) {
			fieldId = new PlayFieldIdPdo();
			fieldId.setName(playField.getName());
		}
		simulation.setPlayFieldId(fieldId);
	}

	private void initRuleEngine() {
		ruleEngine.initScore(simulation.getScore());
		ruleEngine.registerRules(playField.getGameRules());
	}
	
	private void loadScreen() {
		presentation.showScreen(GameScreen.class);
	}

	public void endSimulation(String playerName) {
		PlayerPdo player = new PlayerPdo();
		player.setName(playerName);
		simulation.setPlayer(player);
		
		simulationDao.addSimulation(simulation);
	}

	public RuleEngine getRuleEngine() {
		return ruleEngine;
	}
	
	public SimulationPdo getSimulation() {
		return simulation;
	}
	
}

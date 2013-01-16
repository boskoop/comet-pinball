package ch.m02.comet.pinball.logic.state;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.Display;
import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.screen.PlayerNameScreen;
import ch.m02.comet.pinball.logic.simulation.SimulationManager;

public class PlayerNameState extends AbstractState {

	@Inject
	private PresentationManager presentation;
	
	@Inject
	private Display display;
	
	private SimulationManager simulationManager;
	
	@Override
	protected void enterState() {
		display.setHighscore(simulationManager.getSimulation().getScore().getScoreValue());
		presentation.showScreen(PlayerNameScreen.class);
	}
	
	public void setSimulation(SimulationManager simulation) {
		this.simulationManager = simulation;
	}
	
	@Override
	public void savePlayerName(String playerName) {
		this.simulationManager.endSimulation(playerName);
		stateContext.setState(createNewState(HighscoreState.class));
	}
	
}

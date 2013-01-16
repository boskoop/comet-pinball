package ch.m02.comet.pinball.logic.internal.state;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.Display;
import ch.m02.comet.pinball.logic.simulation.SimulationManager;

public class SimulationState extends AbstractState {
	
	@Inject
	private Display display;

	private SimulationManager simulationManager;
	
	private int gamesPlayed = 0;
	
	private boolean ballPlunged = false;

	@Override
	protected void enterState() {
		simulationManager = applicationContext.getComponentContainer().getComponent(SimulationManager.class);
		display.displayMessage("Ball 1");
		simulationManager.startNewSimulation();
	}
	
	@Override
	public void handleBallHitsId(int id) {
		simulationManager.getRuleEngine().handleBallHitsId(id);
	}
	
	@Override
	public void ballDown() {
		if (ballPlunged) {
			gamesPlayed++;
			display.displayMessage("Ball " + (gamesPlayed + 1));
		}
		ballPlunged = false;
		if (gamesPlayed >= 3) {
			PlayerNameState playerNameState = applicationContext.getComponentContainer().getComponent(PlayerNameState.class);
			playerNameState.setSimulation(simulationManager);
			stateContext.setState(playerNameState);
		}
	}

	@Override
	public void ballPlunged() {
		ballPlunged = true;
	}
}

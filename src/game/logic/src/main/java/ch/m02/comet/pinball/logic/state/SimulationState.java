package ch.m02.comet.pinball.logic.state;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.Display;
import ch.m02.comet.pinball.logic.simulation.SimulationManager;

public class SimulationState extends AbstractState {
	
	@Inject
	private Display display;

	private SimulationManager simulationManager;

	@Override
	protected void enterState() {
		simulationManager = applicationContext.getComponentContainer().getComponent(SimulationManager.class);
		simulationManager.startNewSimulation();
	}
	
	@Override
	public void handleBallHitsId(int id) {
		simulationManager.getRuleEngine().handleBallHitsId(id);
	}
	
	@Override
	public void ballDown() {
		simulationManager.ballDown();
	}

	@Override
	public void ballPlunged() {
		simulationManager.ballPlunged();
	}
	
	@Override
	public void ballReset() {
		if (!simulationManager.hasGamesLeft()) {
			PlayerNameState playerNameState = applicationContext.getComponentContainer().getComponent(PlayerNameState.class);
			playerNameState.setSimulation(simulationManager);
			stateContext.setState(playerNameState);
		}
		simulationManager.ballReset();
	}
}

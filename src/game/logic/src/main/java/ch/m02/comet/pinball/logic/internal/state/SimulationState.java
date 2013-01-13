package ch.m02.comet.pinball.logic.internal.state;

import ch.m02.comet.pinball.logic.simulation.SimulationManager;

public class SimulationState extends AbstractState {

	private SimulationManager simulationManager;
	
	private int gamesPlayed = 0;

	@Override
	protected void enterState() {
		simulationManager = applicationContext.getComponentContainer().getComponent(SimulationManager.class);
		simulationManager.startNewSimulation();
	}
	
	@Override
	protected
	void exitState() {
		simulationManager.endSimulation();
	}
	
	@Override
	public void gameOver() {
		gamesPlayed++;
		if (gamesPlayed >= 3) {
			stateContext.setState(createNewState(MainMenuState.class));
		}
	}
}

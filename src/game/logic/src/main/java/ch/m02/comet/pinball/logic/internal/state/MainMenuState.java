package ch.m02.comet.pinball.logic.internal.state;


public class MainMenuState extends AbstractState {

	@Override
	public void startNewSimulation() {
		stateContext.setState(createNewState(SimulationState.class));
	}

}

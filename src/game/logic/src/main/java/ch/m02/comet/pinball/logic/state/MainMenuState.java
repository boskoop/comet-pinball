package ch.m02.comet.pinball.logic.state;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.screen.MainMenuScreen;


public class MainMenuState extends AbstractState {
	
	@Inject
	private PresentationManager presentation;

	@Override
	public void startNewSimulation() {
		stateContext.setState(createNewState(SimulationState.class));
	}
	
	@Override
	public void showHighscores() {
		stateContext.setState(createNewState(HighscoreState.class));
	}
	
	@Override
	protected void enterState() {
		presentation.showScreen(MainMenuScreen.class);
	}

}

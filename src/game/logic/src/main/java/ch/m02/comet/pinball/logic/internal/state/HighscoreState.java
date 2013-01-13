package ch.m02.comet.pinball.logic.internal.state;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.Display;
import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.screen.HighscoreScreen;
import ch.m02.comet.pinball.logic.State;
import ch.m02.comet.pinball.logic.persistence.SimulationStoreDao;

public class HighscoreState extends AbstractState implements State{

	
	@Inject
	private PresentationManager presentation;
	
	@Inject
	private Display display;
	
	@Inject
	private SimulationStoreDao simulationStoreDao;
	
	@Override
	protected void enterState() {
		display.setHighscores(simulationStoreDao.getScores());
		presentation.showScreen(HighscoreScreen.class);
	}
	
	@Override
	public void showMainMenu() {
		stateContext.setState(createNewState(MainMenuState.class));
	}
	
}

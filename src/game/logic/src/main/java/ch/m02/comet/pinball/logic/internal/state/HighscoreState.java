package ch.m02.comet.pinball.logic.internal.state;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.screen.HighscoreScreen;
import ch.m02.comet.pinball.logic.State;

public class HighscoreState extends AbstractState implements State{

	
	@Inject
	PresentationManager presentation;
	
	@Override
	protected void enterState() {

		presentation.showScreen(HighscoreScreen.class);
	}
	
}

package ch.m02.comet.pinball.logic.internal.state;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.screen.MainMenuScreen;


public class SplashState extends AbstractState {
	
	@Inject
	private PresentationManager presentation;

	@Override
	public void splashFinished() {
		presentation.showScreen(MainMenuScreen.class);
		stateContext.setState(createNewState(MainMenuState.class));
	}
}

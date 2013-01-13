package ch.m02.comet.pinball.logic.internal.state;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.PresentationManager;


public class SplashState extends AbstractState {
	
	@Inject
	private PresentationManager presentation;

	@Override
	public void showMainMenu() {
		stateContext.setState(createNewState(MainMenuState.class));
	}
}

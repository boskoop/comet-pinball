package ch.m02.comet.pinball.logic.internal;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.logic.LogicManager;
import ch.m02.comet.pinball.logic.State;
import ch.m02.comet.pinball.logic.internal.state.StateContext;

public class PinballLogicManager implements LogicManager {
	
	@Inject
	private PresentationManager presentationManager;
	
	@Inject
	private StateContext stateContext;

	@Override
	public State getCurrentState() {
		return stateContext.getCurrentState();
	}
	
	
	
}

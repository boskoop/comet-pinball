package ch.m02.comet.pinball.logic.internal.state;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.logic.State;

public class StateContext {
	
	@Inject
	private ApplicationContext context;

	private AbstractState currentState;
	
	@PostConstruct
	public void init() {
		currentState = context.getComponentContainer().getComponent(SplashState.class);
	}
	
	void setState(AbstractState state) {
		currentState.exitState();
		currentState = state;
		state.enterState();
	}
	
	public State getCurrentState() {
		return currentState;
	}
	
}

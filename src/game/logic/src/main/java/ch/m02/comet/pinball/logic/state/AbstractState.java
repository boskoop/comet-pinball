package ch.m02.comet.pinball.logic.state;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.logic.State;

abstract class AbstractState implements State {
	
	@Inject
	protected ApplicationContext applicationContext;
	
	@Inject
	protected StateContext stateContext;
	
	protected AbstractState createNewState(Class<? extends AbstractState> state) {
		return applicationContext.getComponentContainer().getComponent(state);
	}
	
	protected void enterState() {
	}
	
	protected void exitState() {
	}

	@Override
	public void startNewSimulation() {
	}
	
	@Override
	public void showMainMenu() {
	}
	
	@Override
	public void ballDown() {
	}
	
	@Override
	public void handleBallHitsId(int id) {
	}
	
	@Override
	public void showHighscores() {
	}
	
	@Override
	public void ballPlunged() {
	}
	
	@Override
	public void savePlayerName(String playerName) {
	}
	
	@Override
	public void ballReset() {
	}
	
}

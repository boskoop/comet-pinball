package ch.m02.comet.pinball.logic.internal.event;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.logic.event.EventHandler;
import ch.m02.comet.pinball.logic.internal.PinballLogicManager;

public class EventHandlerImpl implements EventHandler {
	
	@Inject
	private PinballLogicManager logic;

	@Override
	public void handleBallHitsId(int id) {
		logic.getCurrentState().handleBallHitsId(id);
	}

}

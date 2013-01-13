package ch.m02.comet.pinball.physics.placable;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.logic.event.EventHandler;

public class EventCreator {
	
	@Inject
	private EventHandler handler;
	
	private int id;
	

	public void fireEvent() {
		handler.handleBallHitsId(id);
	}

	public void setId(int id) {
		this.id = id;
	}

}

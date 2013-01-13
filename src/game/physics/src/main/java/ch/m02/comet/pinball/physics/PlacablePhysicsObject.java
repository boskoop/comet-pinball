package ch.m02.comet.pinball.physics;

import ch.m02.comet.pinball.physics.placable.EventCreator;

import com.badlogic.gdx.physics.box2d.ContactListener;

public abstract class PlacablePhysicsObject implements PhysicsObject {
	
	private EventCreator creator;

	public PlacablePhysicsObject(EventCreator creator) {
		this.creator = creator;
	}
	
	protected void fireEvent() {
		creator.fireEvent();
	}

	public abstract ContactListener getContactListener();
	
	public abstract void dispose();
	
}

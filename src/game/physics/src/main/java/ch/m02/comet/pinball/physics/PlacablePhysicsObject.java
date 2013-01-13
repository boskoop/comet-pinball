package ch.m02.comet.pinball.physics;

import com.badlogic.gdx.physics.box2d.ContactListener;

public interface PlacablePhysicsObject extends PhysicsObject {

	public ContactListener getContactListener();
	
	public void dispose();
	
}

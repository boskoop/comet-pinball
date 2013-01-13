package ch.m02.comet.pinball.physics;

import com.badlogic.gdx.physics.box2d.ContactListener;


public interface PhysicsPlayField extends InteractivePhysicsObject {

	public void placePhysicsObject(PlacablePhysicsObject object);
	
	public void clearField();
	
	public void registerContactListener(ContactListener listener);
	
}
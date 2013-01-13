package ch.m02.comet.pinball.physics;


public interface PhysicsPlayField extends InteractivePhysicsObject {

	public void placePhysicsObject(PlacablePhysicsObject object);
	
	public void clearField();
	
}
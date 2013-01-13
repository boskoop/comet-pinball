package ch.m02.comet.pinball.physics;


public interface PhysicPlayField extends InteractivePhysicsObject {

	public void placePhysicsObject(PlacablePhysicsObject object);
	
	public void clearField();
	
}
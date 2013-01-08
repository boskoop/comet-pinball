package ch.m02.comet.pinball.physics;

import com.badlogic.gdx.physics.box2d.World;

public interface InteractivePhysicsObject extends PhysicsObject {

	@Override
	public void init(World world);
	
	/**
	 * Handles events in the physics world. Should get called each time the screen is rendered.
	 */
	public void handlePhysicsEvents();
	
}

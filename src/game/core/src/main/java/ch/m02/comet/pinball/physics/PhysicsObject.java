package ch.m02.comet.pinball.physics;

import com.badlogic.gdx.physics.box2d.World;

public interface PhysicsObject {

	/**
	 * Initializes this physics object in the given world.
	 */
	public void init(World world);
	
	/**
	 * Handles events in the physics world. Should get called each time the screen is rendered.
	 */
	public void handlePhysicsEvents();
	
}

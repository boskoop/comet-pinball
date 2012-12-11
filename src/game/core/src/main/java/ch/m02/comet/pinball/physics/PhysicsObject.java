package ch.m02.comet.pinball.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public interface PhysicsObject {

	/**
	 * Initializes this physics object in the given world.
	 */
	public void init(World world);
	
	/**
	 * Returns the body of the PhysicsObject
	 */
	public Body getBody();
	
	
	/**
	 * Returns the fixture of the PhysicsObject
	 */
	//public Fixture getFixture();
	
}

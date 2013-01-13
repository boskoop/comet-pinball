package ch.m02.comet.pinball.presentation.graphics.objects;

import ch.m02.comet.pinball.physics.PhysicsObject;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GraphicsObject {

	/**
	 * Initializes given GraphicsObject with the given PhysicsObject
	 */
	public void init(PhysicsObject physicsObject);

	/**
	 * Draws given GraphicsObject during the given SpriteBatch
	 * 
	 * @param spriteBatch
	 */
	public void draw(Camera camera, SpriteBatch spriteBatch);

	/**
	 * Method used to destroy the object
	 */
	public void dispose();

}

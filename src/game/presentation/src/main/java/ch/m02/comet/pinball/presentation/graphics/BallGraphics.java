package ch.m02.comet.pinball.presentation.graphics;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class BallGraphics implements GraphicsObject {
	private Texture texture;
	private Sprite sprite;
	private PhysicsObject physicsObject;

	public BallGraphics() {
		texture = new Texture(Gdx.files.internal("data/fussball.png"));
	}

	@Override
	public void init(PhysicsObject physicsObject) {
		this.physicsObject = physicsObject;
		sprite = new Sprite(texture);
	}

	@Override
	public void draw(Camera camera, SpriteBatch spriteBatch) {
		if (physicsObject == null) {
			// uninitialized!!
			// TODO handle uninitialized physics object!
		}
		Vector3 ballCoordinates = new Vector3(physicsObject.getBody().getPosition().x 
				* PhysicsDefinition.BOX2D_TO_WORLD
				- PhysicsDefinition.PINBALL_RADIUS * PhysicsDefinition.BOX2D_TO_WORLD, 
				physicsObject.getBody().getPosition().y
				* PhysicsDefinition.BOX2D_TO_WORLD
				- PhysicsDefinition.PINBALL_RADIUS* PhysicsDefinition.BOX2D_TO_WORLD, 0);
		camera.project(ballCoordinates, 0, 0, camera.viewportWidth,
				camera.viewportHeight);

		Vector3 ballSize = new Vector3(PhysicsDefinition.PINBALL_RADIUS * 2 * PhysicsDefinition.BOX2D_TO_WORLD,
				PhysicsDefinition.PINBALL_RADIUS * 2 * PhysicsDefinition.BOX2D_TO_WORLD, 0);
		camera.project(ballSize, 0, 0, camera.viewportWidth,
				camera.viewportHeight);

		final float rotation = physicsObject.getBody().getAngle() * 180 / MathUtils.PI;
		
		sprite.setPosition(ballCoordinates.x, ballCoordinates.y);
		sprite.setSize(ballSize.x, ballSize.y);
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setRotation(rotation);
		sprite.draw(spriteBatch);
	}

	@Override
	public void dispose() {
		texture.dispose();
	}

}

package ch.m02.comet.pinball.physics.element;

import ch.m02.comet.pinball.util.DisposeUtil;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

class BodyCreator {
	
	private World world;
	
	public BodyCreator(World world) {
		this.world = world;
	}

	/**
	 * Creates a static body with a box-shape of the given dimension and position.
	 */
	public Body createStaticBoxBody(Vector2 dimension, Vector2 position) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);

		PolygonShape shape = null;
		try {
			shape = new PolygonShape();
			shape.setAsBox(dimension.x, dimension.y);

			body.createFixture(shape, 0.0f);
		} finally {
			DisposeUtil.safelyDispose(shape);
		}
		return body;
	}
	
	/**
	 * Creates a static body with the given vertices as shape.
	 */
	public Body createStaticPolygonBody(Vector2[] vertices, Vector2 position) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);

		PolygonShape shape = null;
		try {
			shape = new PolygonShape();
			shape.set(vertices);
			body.createFixture(shape, 0.0f);
		} finally {
			DisposeUtil.safelyDispose(shape);
		}
		return body;
	}
}

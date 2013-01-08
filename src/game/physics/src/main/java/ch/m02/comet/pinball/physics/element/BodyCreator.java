package ch.m02.comet.pinball.physics.element;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.util.DisposeUtil;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

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
			FixtureDef fixture = new FixtureDef();
			fixture.shape = shape;
			fixture.restitution = PhysicsDefinition.STEEL_RESTITUTION;
			body.createFixture(fixture);
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
			FixtureDef fixture = new FixtureDef();
			fixture.shape = shape;
			fixture.restitution = PhysicsDefinition.STEEL_RESTITUTION;
			body.createFixture(fixture);
		} finally {
			DisposeUtil.safelyDispose(shape);
		}
		return body;
	}

	/**
	 * Creates a static body with a circle-shape of the given radius and position.
	 */
	public Body createStaticCircleBody(float radius, Vector2 position) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);

		CircleShape shape = null;
		try {
			shape = new CircleShape();
			shape.setRadius(radius);
			FixtureDef fixture = new FixtureDef();
			fixture.shape = shape;
			fixture.restitution = PhysicsDefinition.STEEL_RESTITUTION;
			body.createFixture(fixture);
		} finally {
			DisposeUtil.safelyDispose(shape);
		}
		return body;
	}
	
	public World getWorld() {
		return world;
	}
}

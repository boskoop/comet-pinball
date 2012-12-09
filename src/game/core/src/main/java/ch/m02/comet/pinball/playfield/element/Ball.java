package ch.m02.comet.pinball.playfield.element;

import ch.m02.comet.pinball.playfield.PhysicsDefinition;
import ch.m02.comet.pinball.playfield.PlayfieldElement;
import ch.m02.comet.pinball.util.DisposeUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Ball implements PlayfieldElement {

	// Steel has a density of 0.008 kg/cm^3
	private static final float BALL_DENSITY = 0.008f;

	// Steel on stell friction coefficient
	private static final float BALL_FRICTION = 0.4f;

	// Steel restitution coefficient
	private static final float BALL_RESTITUTION = 0.56f;

	private Body ball;

	@Override
	public void init(World world) {
		BodyDef ballDefinition = defineBallBody();
		ball = world.createBody(ballDefinition);

		CircleShape circle = null;
		try {
			circle = new CircleShape();
			circle.setRadius(PhysicsDefinition.PINBALL_RADIUS);
			FixtureDef ballfixtureDefinition = defineBallFixture(circle);
			ball.createFixture(ballfixtureDefinition);
		} finally {
			DisposeUtil.safelyDispose(circle);
		}
	}

	private FixtureDef defineBallFixture(CircleShape circle) {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = BALL_DENSITY;
		fixtureDef.friction = BALL_FRICTION;
		fixtureDef.restitution = BALL_RESTITUTION;
		return fixtureDef;
	}

	private BodyDef defineBallBody() {
		BodyDef ballDef = new BodyDef();
		ballDef.type = BodyType.DynamicBody;
		ballDef.position.set(PhysicsDefinition.FIELD_WIDTH / 2,
				PhysicsDefinition.FIELD_HEIGHT / 2);
		return ballDef;
	}

	@Override
	public void render() {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			final float force = Math.abs(ball.getMass() * 2 * PhysicsDefinition.RAMP_GRAVITY);
			ball.applyForceToCenter(0, force);
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			final float force = Math.abs(ball.getMass() * PhysicsDefinition.RAMP_GRAVITY);
			ball.applyForceToCenter(-force, 0);
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			final float force = Math.abs(ball.getMass() * PhysicsDefinition.RAMP_GRAVITY);
			ball.applyForceToCenter(force, 0);
		}
	}
}

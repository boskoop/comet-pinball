package ch.m02.comet.pinball.physics;

import ch.m02.comet.pinball.util.DisposeUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Ball implements PhysicsObject {

	// Steel has a density of 8000 kg/m^3
	private static final float BALL_DENSITY = 8000f / PhysicsDefinition.CUBE_METER_SCALE_FACTOR;

	// Steel on stell friction coefficient
	private static final float BALL_FRICTION = 0.4f;

	// Steel restitution coefficient
	private static final float BALL_RESTITUTION = 0.56f;

	private Body ball;
	
	private long lastPlunge = 0L;
	// min plunge interval in units of milliseconds
	private static final long MIN_PLUNGE_INTERVAL = 1000L;
	// plunge force in cm/s^2
	private static final float PLUNGE_FORCE = Math.abs(PhysicsDefinition.EARTH_GRAVITY) * 2.0f;
	// plunge duration in seconds
	private static final float PLUNGE_DURATION = 0.1f;
	private static final long MILLISECONDS_TO_NANOSECONDS = 1000000L;
	private static final long MIN_PLUNGE_INTERVAL_NANOS = MIN_PLUNGE_INTERVAL * MILLISECONDS_TO_NANOSECONDS;

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
	public void handlePhysicsEvents() {
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

		long currentTime = System.nanoTime();
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			if (currentTime > (lastPlunge + MIN_PLUNGE_INTERVAL_NANOS)) {
				Gdx.app.log(Ball.class.getCanonicalName(),
						"plunged ball, lastPlunge=" + lastPlunge
						+ ", currentTime=" + currentTime);
				lastPlunge = currentTime;
				plungeBall();
			}
		}
	}
	
	private void plungeBall() {
		final Vector2 ballWorldCenter = ball.getWorldCenter();
		final Vector2 impulse = new Vector2();
		// unit is kg * m / s
		impulse.y = ball.getMass() * PLUNGE_FORCE * PLUNGE_DURATION;
		Gdx.app.log(Ball.class.getCanonicalName(),
				"force: " + impulse.y + "Ns");
		ball.applyLinearImpulse(impulse, ballWorldCenter);
	}
}

package ch.m02.comet.pinball.physics.ball;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.config.KeyProperties;
import ch.m02.comet.pinball.core.logic.command.PlungeCommand;
import ch.m02.comet.pinball.physics.InteractivePhysicsObject;
import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.box2d.keys.KeyMap;
import ch.m02.comet.pinball.physics.util.DisposeUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball implements InteractivePhysicsObject {
	
	private static final Logger log = LoggerFactory.getLogger(Ball.class);
	
	@Inject
	private ApplicationContext context;
	
	@Inject
	private KeyMap keyMap;
	
	// Steel on steel friction coefficient
	private static final float BALL_FRICTION = 0.4f;

	// Bouncing shoud be dependent on ground (tends to bigger value)
	private static final float BALL_RESTITUTION = 0f;

	private Body ball;
	
	private long lastPlunge = 0L;
	// min plunge interval in units of milliseconds
	private static final long MIN_PLUNGE_INTERVAL = 1000L;
	// plunge force in m/s^2
	private float plungeForce;
	// plunge duration in seconds
	private static final float PLUNGE_DURATION = 0.1f;
	private static final long MILLISECONDS_TO_NANOSECONDS = 1000000L;
	private static final long MIN_PLUNGE_INTERVAL_NANOS = MIN_PLUNGE_INTERVAL * MILLISECONDS_TO_NANOSECONDS;
	
	private Vector2 ballResetPosition;

	private int upKey;
	private int leftKey;
	private int rightKey;
	private int resetKey;
	private int plungeKey;
	
	@PostConstruct
	public void loadKey() {
		upKey = keyMap.getKey(KeyProperties.BALL_UP);
		leftKey = keyMap.getKey(KeyProperties.BALL_LEFT);
		rightKey = keyMap.getKey(KeyProperties.BALL_RIGHT);
		resetKey = keyMap.getKey(KeyProperties.BALL_RESET);
		plungeKey = keyMap.getKey(KeyProperties.PLUNGE);
	}

	@Override
	public void init(World world) {
		float defaultBallResetX = PhysicsDefinition.FIELD_WIDTH
				- (0.025f * PhysicsDefinition.METER_SCALE_FACTOR);
		float defaultBallResetY = (0.02f * PhysicsDefinition.METER_SCALE_FACTOR)
				+ PhysicsDefinition.INSTANCE.getPinballRadius();
		ballResetPosition = new Vector2(defaultBallResetX, defaultBallResetY);
		plungeForce = Math.abs(PhysicsDefinition.INSTANCE.getEarthGravity()) * 2.0f;
		
		BodyDef ballDefinition = defineBallBody();
		ball = world.createBody(ballDefinition);

		CircleShape circle = null;
		try {
			circle = new CircleShape();
			circle.setRadius(PhysicsDefinition.INSTANCE.getPinballRadius());
			FixtureDef ballfixtureDefinition = defineBallFixture(circle);
			ball.createFixture(ballfixtureDefinition);
		} finally {
			DisposeUtil.safelyDispose(circle);
		}
		ball.setBullet(true);
		
		GroundSensorElement groundSensor = context.getComponentContainer().getComponent(GroundSensorElement.class);
		groundSensor.init(world);
	}

	private FixtureDef defineBallFixture(CircleShape circle) {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = PhysicsDefinition.STEEL_DENSITY;
		fixtureDef.friction = BALL_FRICTION;
		fixtureDef.restitution = BALL_RESTITUTION;
		return fixtureDef;
	}

	private BodyDef defineBallBody() {
		BodyDef ballDef = new BodyDef();
		ballDef.type = BodyType.DynamicBody;
		ballDef.position.set(ballResetPosition);
		return ballDef;
	}

	@Override
	public void handlePhysicsEvents() {
		if (Gdx.input.isKeyPressed(upKey)) {
			final float force = Math.abs(ball.getMass() * 2 * PhysicsDefinition.INSTANCE.getRampGravity());
			ball.applyForceToCenter(0, force);
		}

		if (Gdx.input.isKeyPressed(leftKey)) {
			final float force = Math.abs(ball.getMass() * PhysicsDefinition.INSTANCE.getRampGravity());
			ball.applyForceToCenter(-force, 0);
		}

		if (Gdx.input.isKeyPressed(rightKey)) {
			final float force = Math.abs(ball.getMass() * PhysicsDefinition.INSTANCE.getRampGravity());
			ball.applyForceToCenter(force, 0);
		}

		long currentTime = System.nanoTime();
		if (Gdx.input.isKeyPressed(plungeKey)) {
			if (currentTime > (lastPlunge + MIN_PLUNGE_INTERVAL_NANOS)
					&& isInPlungerRegion()) {
				Gdx.app.log(Ball.class.getCanonicalName(),
						"plunged ball, lastPlunge=" + lastPlunge
						+ ", currentTime=" + currentTime);
				log.info("Ball plunged, lastPlunge='{}', currentTime='{}'", lastPlunge, currentTime);
				PlungeCommand command = context.getComponentContainer()
						.getComponent(PlungeCommand.class);
				command.execute();
				lastPlunge = currentTime;
				plungeBall();
			}
		}
		
		// Should be the last handled event since it overrides the others
		if (Gdx.input.isKeyPressed(resetKey)) {
			resetBall();
		}
	}

	public void resetBall() {
		ball.setLinearVelocity(Vector2.Zero);
		ball.setAngularVelocity(0f);
		ball.setTransform(ballResetPosition, 0);
		ball.setAwake(true);
	}
	
	private boolean isInPlungerRegion() {
		Vector2 position = this.ball.getPosition();
		if (position.x > (0.7f * PhysicsDefinition.METER_SCALE_FACTOR)
				&& position.y < (PhysicsDefinition.INSTANCE.getPinballRadius() * 2)) {
			return true;
		}
		return false;
	}

	private void plungeBall() {
		final Vector2 ballWorldCenter = ball.getWorldCenter();
		final Vector2 impulse = new Vector2();
		// unit is kg * m / s
		impulse.y = ball.getMass() * plungeForce * PLUNGE_DURATION;
		Gdx.app.log(Ball.class.getCanonicalName(),
				"force: " + impulse.y + "Ns");
		ball.applyLinearImpulse(impulse, ballWorldCenter);
	}

	@Override
	public Body getBody() {
		return ball;
	}

}

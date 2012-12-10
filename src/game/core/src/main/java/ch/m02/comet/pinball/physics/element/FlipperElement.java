package ch.m02.comet.pinball.physics.element;

import ch.m02.comet.pinball.physics.InteractivePhysicsObject;
import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.util.DisposeUtil;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class FlipperElement implements InteractivePhysicsObject {

	private static final float BIG_RADIUS = 0.02f * PhysicsDefinition.METER_SCALE_FACTOR;
	private static final float SMALL_RADIUS = 0.01f * PhysicsDefinition.METER_SCALE_FACTOR;
	private static final float FLIPPER_CIRCLE_DISTANCE = 0.11f * PhysicsDefinition.METER_SCALE_FACTOR;

	private static final float LEFT_FLIPPER_X = 0.226f * PhysicsDefinition.METER_SCALE_FACTOR;
	private static final float RIGHT_FLIPPER_X = 0.494f * PhysicsDefinition.METER_SCALE_FACTOR;
	private static final float FLIPPER_Y = (0.2f - 0.044f)
			* PhysicsDefinition.METER_SCALE_FACTOR;
	private Body leftFlipper;
	private Body rightFlipper;

	@Override
	public void init(World world) {
		Vector2 leftFlipperPosition = new Vector2(LEFT_FLIPPER_X, FLIPPER_Y);
		leftFlipper = createFlipper(world, leftFlipperPosition, FLIPPER_CIRCLE_DISTANCE, BIG_RADIUS, SMALL_RADIUS);
		float leftFlipperAngle = MathUtils.atan2(-3f, 4f);
		leftFlipper.setTransform(LEFT_FLIPPER_X, FLIPPER_Y, leftFlipperAngle);
		
		Vector2 rightFlipperPosition = new Vector2(RIGHT_FLIPPER_X, FLIPPER_Y);
		rightFlipper = createFlipper(world, rightFlipperPosition, -FLIPPER_CIRCLE_DISTANCE, -BIG_RADIUS, -SMALL_RADIUS);
		float rightFlipperAngle = MathUtils.atan2(3f, 4f);
		rightFlipper.setTransform(RIGHT_FLIPPER_X, FLIPPER_Y, rightFlipperAngle);
	}

	private Body createFlipper(World world, Vector2 position, float flipperCircleDistance, float bigRadius, float smallRadius) {
		BodyDef flipperDef = new BodyDef();
		flipperDef.type = BodyType.KinematicBody;
		flipperDef.position.set(position);
		Body flipper = world.createBody(flipperDef);

		CircleShape bigCircle = null;
		CircleShape smallCircle = null;
		PolygonShape flipperBody = null;
		try {
			bigCircle = new CircleShape();
			bigCircle.setRadius(Math.abs(bigRadius));
			FixtureDef fixture = new FixtureDef();
			fixture.shape = bigCircle;
			fixture.restitution = PhysicsDefinition.STEEL_RESTITUTION;
			flipper.createFixture(fixture);

			smallCircle = new CircleShape();
			smallCircle.setRadius(Math.abs(smallRadius));
			smallCircle.setPosition(new Vector2(flipperCircleDistance, 0));
			fixture.shape = smallCircle;
			flipper.createFixture(fixture);

			Vector2[] leftFlipperVertices = new Vector2[4];
			leftFlipperVertices[0] = new Vector2(0f, -bigRadius);
			leftFlipperVertices[1] = new Vector2(flipperCircleDistance, -smallRadius);
			leftFlipperVertices[2] = new Vector2(flipperCircleDistance, smallRadius);
			leftFlipperVertices[3] = new Vector2(0f, bigRadius);
			flipperBody = new PolygonShape();
			flipperBody.set(leftFlipperVertices);
			fixture.shape = flipperBody;
			flipper.createFixture(fixture);
		} finally {
			DisposeUtil.safelyDispose(smallCircle);
			DisposeUtil.safelyDispose(bigCircle);
			DisposeUtil.safelyDispose(flipperBody);
		}
		return flipper;
	}

	@Override
	public void handlePhysicsEvents() {

	}

}

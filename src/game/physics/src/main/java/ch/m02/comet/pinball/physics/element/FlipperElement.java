package ch.m02.comet.pinball.physics.element;

import ch.m02.comet.pinball.physics.InteractivePhysicsObject;
import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.util.DisposeUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

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
		BodyCreator creator = new BodyCreator(world);
		
		Vector2 leftFlipperPosition = new Vector2(LEFT_FLIPPER_X, FLIPPER_Y);
		leftFlipper = createFlipper(world, leftFlipperPosition, FLIPPER_CIRCLE_DISTANCE, BIG_RADIUS, SMALL_RADIUS);
		float leftFlipperAngle = MathUtils.atan2(-3f, 4f);
		leftFlipper.setTransform(leftFlipperPosition, leftFlipperAngle);
		Vector2 angleLimits = new Vector2(-90f * MathUtils.degreesToRadians, 0f);
		createJoint(creator, leftFlipper, -leftFlipperAngle, angleLimits);
		
		Vector2 rightFlipperPosition = new Vector2(RIGHT_FLIPPER_X, FLIPPER_Y);
		rightFlipper = createFlipper(world, rightFlipperPosition, -FLIPPER_CIRCLE_DISTANCE, -BIG_RADIUS, -SMALL_RADIUS);
		float rightFlipperAngle = MathUtils.atan2(3f, 4f);
		rightFlipper.setTransform(rightFlipperPosition, rightFlipperAngle);
		angleLimits = new Vector2(0f * MathUtils.degreesToRadians, 90f * MathUtils.degreesToRadians);
		createJoint(creator, rightFlipper, -rightFlipperAngle, angleLimits);
	}

	private Body createFlipper(World world, Vector2 position, float flipperCircleDistance, float bigRadius, float smallRadius) {
		BodyDef flipperDef = new BodyDef();
		flipperDef.type = BodyType.DynamicBody;
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
			fixture.density = PhysicsDefinition.STEEL_RESTITUTION * 10;
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
		flipper.setSleepingAllowed(false);
		flipper.setBullet(true);
		return flipper;
	}
	
	private Joint createJoint(BodyCreator creator, Body flipper, float referenceAngle, Vector2 angleLimits) {
		Body anchor = creator.createStaticCircleBody(SMALL_RADIUS, flipper.getPosition());
		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.bodyA = flipper;
		jointDef.bodyB = anchor;
		jointDef.collideConnected = false;
		jointDef.localAnchorA.set(0f, 0f);
		jointDef.localAnchorB.set(0f, 0f);
		jointDef.referenceAngle = referenceAngle;
		jointDef.enableLimit = true;
		jointDef.lowerAngle = angleLimits.x;
		jointDef.upperAngle = angleLimits.y;
		return creator.getWorld().createJoint(jointDef);
	}

	@Override
	public void handlePhysicsEvents() {
		if (Gdx.input.isKeyPressed(Keys.TAB)) {
			leftFlipper.setAngularVelocity(50f);
		} else {	
			leftFlipper.setAngularVelocity(-15f);
		}
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			rightFlipper.setAngularVelocity(-50f);
		} else {
			rightFlipper.setAngularVelocity(15f);
		}
	}
	
	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

}

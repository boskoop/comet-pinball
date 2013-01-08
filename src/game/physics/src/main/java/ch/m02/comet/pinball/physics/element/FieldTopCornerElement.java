package ch.m02.comet.pinball.physics.element;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsObject;
import ch.m02.comet.pinball.physics.util.DisposeUtil;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class FieldTopCornerElement implements PhysicsObject {

	static final float CORNER_SIZE = 0.3f * PhysicsDefinition.METER_SCALE_FACTOR;
	
	private static final float TOTAL_ANGLE_DEGREES = 90f;
	private static final int NUMBER_OF_ELEMENTS = 30;
	private static final float ANGLE_PER_ELEMENT = (TOTAL_ANGLE_DEGREES / (NUMBER_OF_ELEMENTS - 1));
	
	private static final float CURVE_ELEMENT_LENGTH = 0.04f * PhysicsDefinition.METER_SCALE_FACTOR;
	private static final float CURVE_ELEMENT_WIDTH = 0.01f * PhysicsDefinition.METER_SCALE_FACTOR;
	private static final float ELEMENT_HALF_LENGTH = CURVE_ELEMENT_LENGTH / 2;
	private static final float ELEMENT_HALF_WIDTH = CURVE_ELEMENT_WIDTH / 2;
	
	private Body fieldTopCornerElementBody;
	
	@Override
	public void init(World world) {
		Vector2 topRightPosition = new Vector2(PhysicsDefinition.FIELD_WIDTH, PhysicsDefinition.FIELD_HEIGHT);
		createFieldCornerBody(world, topRightPosition);
		
		Vector2 topLeftPosition = new Vector2(0, PhysicsDefinition.FIELD_HEIGHT);
		Body topLeftBody = createFieldCornerBody(world, topLeftPosition);
		float rotation = TOTAL_ANGLE_DEGREES * MathUtils.degreesToRadians;
		topLeftBody.setTransform(topLeftPosition, rotation);
	}
	
	private Body createFieldCornerBody(World world, Vector2 position) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(position);
		fieldTopCornerElementBody = world.createBody(bodyDef);
		

		Vector2 positionToRotationCenter = new Vector2(-CORNER_SIZE, -CORNER_SIZE);
		Vector2 rotationBaseVector = new Vector2(CORNER_SIZE - ELEMENT_HALF_WIDTH, 0);
		PolygonShape shape = new PolygonShape();
		try {
			for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
				final Vector2 positionVector = new Vector2(rotationBaseVector);
				final float rotationDegrees = i * ANGLE_PER_ELEMENT;
				positionVector.rotate(rotationDegrees);
				positionVector.add(positionToRotationCenter);
				shape.setAsBox(ELEMENT_HALF_WIDTH, ELEMENT_HALF_LENGTH, positionVector, MathUtils.degreesToRadians * rotationDegrees);

				FixtureDef fixture = new FixtureDef();
				fixture.shape = shape;
				fixture.restitution = PhysicsDefinition.STEEL_RESTITUTION;
				fieldTopCornerElementBody.createFixture(fixture);
			}
		} finally {
			DisposeUtil.safelyDispose(shape);
		}
		return fieldTopCornerElementBody;
	}
	
	@Override
	public Body getBody() {
		return fieldTopCornerElementBody;
	}

}

package ch.m02.comet.pinball.playfield.element;

import ch.m02.comet.pinball.playfield.PhysicsDefinition;
import ch.m02.comet.pinball.playfield.PlayfieldElement;
import ch.m02.comet.pinball.util.DisposeUtil;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class FieldCorner implements PlayfieldElement {

	private static final float CORNER_SIZE = 0.3f * PhysicsDefinition.METER_SCALE_FACTOR;
	
	private static final float TOTAL_ANGLE_DEGREES = 90f;
	private static final int NUMBER_OF_ELEMENTS = 30;
	
	private static final float CURVE_ELEMENT_LENGTH = 0.04f * PhysicsDefinition.METER_SCALE_FACTOR;
	private static final float CURVE_ELEMENT_WIDTH = 0.01f * PhysicsDefinition.METER_SCALE_FACTOR;
	
	@Override
	public void init(World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(PhysicsDefinition.FIELD_WIDTH, PhysicsDefinition.FIELD_HEIGHT);
		Body body = world.createBody(bodyDef);
		
		final float elementHalfWidth = CURVE_ELEMENT_WIDTH / 2;
		final float elementHalfLenght = CURVE_ELEMENT_LENGTH / 2;
		
		final Vector2 bodyOriginToRotationCenter = new Vector2(-CORNER_SIZE, -CORNER_SIZE);
		final Vector2 rotationBaseVector = new Vector2(CORNER_SIZE - elementHalfWidth, 0);
		
		final float anglePerElement = (TOTAL_ANGLE_DEGREES / (NUMBER_OF_ELEMENTS - 1));

		PolygonShape shape = new PolygonShape();
		try {
			for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
				Vector2 positionVector = new Vector2(rotationBaseVector);
				float rotationDegrees = i * anglePerElement;
				positionVector.rotate(rotationDegrees);
				positionVector.add(bodyOriginToRotationCenter);
				shape.setAsBox(elementHalfWidth, elementHalfLenght, positionVector, MathUtils.degreesToRadians * rotationDegrees);

				body.createFixture(shape, 0.0f);
			}
		} finally {
			DisposeUtil.safelyDispose(shape);
		}
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

}

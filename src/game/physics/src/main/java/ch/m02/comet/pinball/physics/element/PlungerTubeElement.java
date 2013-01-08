package ch.m02.comet.pinball.physics.element;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class PlungerTubeElement implements PhysicsObject {
	
	private static final float ELEMENT_LENGTH = PhysicsDefinition.FIELD_HEIGHT - FieldTopCornerElement.CORNER_SIZE;
	private static final float ELEMENT_LENGTH_RADIUS = ELEMENT_LENGTH / 2;
	
	// the element has 4cm space to right screen border
	private static final float TUBE_TO_BORDER_DISTANCE = 0.04f * PhysicsDefinition.METER_SCALE_FACTOR;
	
	// the element is 1cm wide
	private static final float ELEMENT_THICKNESS = 0.01f * PhysicsDefinition.METER_SCALE_FACTOR;
	private static final float ELEMENT_THICKNESS_RADIUS = ELEMENT_THICKNESS / 2;
	
	private Body plungerTubeElementBody;

	@Override
	public void init(World world) {
		BodyCreator creator = new BodyCreator(world);
		
		Vector2 dimension = new Vector2(ELEMENT_THICKNESS_RADIUS, ELEMENT_LENGTH_RADIUS);
		float xPosition = PhysicsDefinition.FIELD_WIDTH - TUBE_TO_BORDER_DISTANCE - ELEMENT_THICKNESS_RADIUS;
		Vector2 position = new Vector2(xPosition, ELEMENT_LENGTH_RADIUS);
		plungerTubeElementBody = creator.createStaticBoxBody(dimension, position);
	}
	
	@Override
	public Body getBody() {
		return plungerTubeElementBody;
	}

}

package ch.m02.comet.pinball.physics.element;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsObject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class FieldBoundsElement implements PhysicsObject {

	// the border is 1cm wide
	private static final float BORDER_THICKNESS = 0.01f * PhysicsDefinition.METER_SCALE_FACTOR;

	private static final float BORDER_RADIUS = BORDER_THICKNESS / 2;
	private static final float FIELD_WIDTH_RADIUS = PhysicsDefinition.FIELD_WIDTH / 2;
	private static final float FIELD_HEIGHT_RADIUS = PhysicsDefinition.FIELD_HEIGHT / 2;

	@Override
	public void init(World world) {
		BoxPolygonCreator creator = new BoxPolygonCreator(world);
		
		Vector2 groundDimension = new Vector2(FIELD_WIDTH_RADIUS, BORDER_RADIUS);
		Vector2 groundPosition = new Vector2(FIELD_WIDTH_RADIUS, BORDER_RADIUS);
		creator.createBoxPolygonBody(groundDimension, groundPosition);

		Vector2 ceilingDimension = new Vector2(FIELD_WIDTH_RADIUS, BORDER_RADIUS);
		Vector2 ceilingPosition = new Vector2(FIELD_WIDTH_RADIUS, PhysicsDefinition.FIELD_HEIGHT - BORDER_RADIUS);
		creator.createBoxPolygonBody(ceilingDimension, ceilingPosition);

		Vector2 leftBoundDimension = new Vector2(BORDER_RADIUS, FIELD_HEIGHT_RADIUS);
		Vector2 leftBoundPosition = new Vector2(BORDER_RADIUS, FIELD_HEIGHT_RADIUS);
		creator.createBoxPolygonBody(leftBoundDimension, leftBoundPosition);

		Vector2 rightBoundDimension = new Vector2(BORDER_RADIUS, FIELD_HEIGHT_RADIUS);
		Vector2 rightBoundPosition = new Vector2(PhysicsDefinition.FIELD_WIDTH - BORDER_RADIUS, FIELD_HEIGHT_RADIUS);
		creator.createBoxPolygonBody(rightBoundDimension, rightBoundPosition);
		
	}

}

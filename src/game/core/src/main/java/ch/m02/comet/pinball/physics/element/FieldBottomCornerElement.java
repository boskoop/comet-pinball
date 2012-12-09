package ch.m02.comet.pinball.physics.element;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsObject;

public class FieldBottomCornerElement implements PhysicsObject {

	private static final float PLUNGER_TUBE_WIDTH = 0.04f * PhysicsDefinition.METER_SCALE_FACTOR;
	
	@Override
	public void init(World world) {
		BodyCreator creator = new BodyCreator(world);
		
		final float scale = PhysicsDefinition.METER_SCALE_FACTOR;
		
		Vector2[] leftFieldCorner = new Vector2[4];
		leftFieldCorner[0] = new Vector2(0f, 0f);
		leftFieldCorner[1] = new Vector2(0.25f * scale, 0f);
		leftFieldCorner[2] = new Vector2(0.25f * scale, 0.05f * scale);
		leftFieldCorner[3] = new Vector2(0f, 0.2f * scale);
		
		Vector2 position = new Vector2(0f, 0.1f * scale);
		creator.createStaticPolygonBody(leftFieldCorner, position);
		
		Vector2[] rightFieldCorner = new Vector2[4];
		rightFieldCorner[0] = new Vector2(0f, 0f);
		rightFieldCorner[1] = new Vector2(0f, 0.2f * scale);
		rightFieldCorner[2] = new Vector2(-0.25f * scale, 0.05f * scale);
		rightFieldCorner[3] = new Vector2(-0.25f * scale, 0f);
		
		position = new Vector2(PhysicsDefinition.FIELD_WIDTH - PLUNGER_TUBE_WIDTH, 0.1f * scale);
		creator.createStaticPolygonBody(rightFieldCorner, position);

	}

}

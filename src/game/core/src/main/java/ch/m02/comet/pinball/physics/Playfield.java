package ch.m02.comet.pinball.physics;

import java.util.ArrayList;
import java.util.List;

import ch.m02.comet.pinball.physics.element.FieldBoundsElement;
import ch.m02.comet.pinball.physics.element.FieldTopCornerElement;
import ch.m02.comet.pinball.physics.element.FlipperElement;

import com.badlogic.gdx.physics.box2d.World;

public class Playfield implements PhysicsObject {

	private List<PhysicsObject> physicsObjects = new ArrayList<PhysicsObject>();

	public Playfield() {
		physicsObjects.add(new FieldBoundsElement());
		physicsObjects.add(new FieldTopCornerElement());
		physicsObjects.add(new FlipperElement());
		physicsObjects.add(new FlipperElement());
	}
	
	@Override
	public void init(World world) {
		for (PhysicsObject object : physicsObjects) {
			object.init(world);
		}
	}

	@Override
	public void handlePhysicsEvents() {
		for (PhysicsObject object : physicsObjects) {
			object.handlePhysicsEvents();
		}
	}

}

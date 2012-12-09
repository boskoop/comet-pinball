package ch.m02.comet.pinball.physics;

import java.util.ArrayList;
import java.util.List;

import ch.m02.comet.pinball.physics.element.FieldBoundsElement;
import ch.m02.comet.pinball.physics.element.FieldTopCornerElement;
import ch.m02.comet.pinball.physics.element.FlipperElement;

import com.badlogic.gdx.physics.box2d.World;

public class Playfield implements InteractivePhysicsObject {

	private List<PhysicsObject> physicsObjects = new ArrayList<PhysicsObject>();
	private List<InteractivePhysicsObject> interactiveObjects = new ArrayList<InteractivePhysicsObject>();

	public Playfield() {
		physicsObjects.add(new FieldBoundsElement());
		physicsObjects.add(new FieldTopCornerElement());

		interactiveObjects.add(new FlipperElement());
		interactiveObjects.add(new FlipperElement());
	}

	@Override
	public void init(World world) {
		for (PhysicsObject object : physicsObjects) {
			object.init(world);
		}
		for (InteractivePhysicsObject object : interactiveObjects) {
			object.init(world);
		}
	}

	@Override
	public void handlePhysicsEvents() {
		for (InteractivePhysicsObject object : interactiveObjects) {
			object.handlePhysicsEvents();
		}
	}

}

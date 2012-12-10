package ch.m02.comet.pinball.physics;

import java.util.ArrayList;
import java.util.List;

import ch.m02.comet.pinball.physics.element.FieldBottomCornerElement;
import ch.m02.comet.pinball.physics.element.FieldBoundsElement;
import ch.m02.comet.pinball.physics.element.FieldTopCornerElement;
import ch.m02.comet.pinball.physics.element.FlipperElement;
import ch.m02.comet.pinball.physics.element.PlungerTubeElement;

import com.badlogic.gdx.physics.box2d.World;

public class Playfield implements InteractivePhysicsObject {

	private List<PhysicsObject> physicsObjects = new ArrayList<PhysicsObject>();
	private List<InteractivePhysicsObject> interactiveObjects = new ArrayList<InteractivePhysicsObject>();

	public Playfield() {
		physicsObjects.add(new FieldBoundsElement());
		physicsObjects.add(new FieldTopCornerElement());
		physicsObjects.add(new FieldBottomCornerElement());
		physicsObjects.add(new PlungerTubeElement());

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

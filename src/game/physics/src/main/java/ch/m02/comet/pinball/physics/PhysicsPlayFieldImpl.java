package ch.m02.comet.pinball.physics;

import java.util.ArrayList;
import java.util.List;

import ch.m02.comet.pinball.physics.element.FieldBottomCornerElement;
import ch.m02.comet.pinball.physics.element.FieldBoundsElement;
import ch.m02.comet.pinball.physics.element.FieldTopCornerElement;
import ch.m02.comet.pinball.physics.element.FlipperElement;
import ch.m02.comet.pinball.physics.element.PlungerTubeElement;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsPlayFieldImpl implements PhysicsPlayField {

	private List<PhysicsObject> physicsObjects = new ArrayList<PhysicsObject>();
	private List<InteractivePhysicsObject> interactiveObjects = new ArrayList<InteractivePhysicsObject>();
	private List<ContactListener> contactListeners = new ArrayList<ContactListener>();
	
	private List<PlacablePhysicsObject> placedObjects = new ArrayList<PlacablePhysicsObject>();
	
	private World world;

	public PhysicsPlayFieldImpl() {
		physicsObjects.add(new FieldBoundsElement());
		physicsObjects.add(new FieldTopCornerElement());
		physicsObjects.add(new FieldBottomCornerElement());
		physicsObjects.add(new PlungerTubeElement());
		interactiveObjects.add(new FlipperElement());
	}
	
	@Override
	public void placePhysicsObject(PlacablePhysicsObject object) {
		object.init(world);
		physicsObjects.add(object);
		contactListeners.add(object.getContactListener());
		placedObjects.add(object);
	}

	@Override
	public void clearField() {
		for (PlacablePhysicsObject o : placedObjects) {
			contactListeners.remove(o.getContactListener());
			physicsObjects.remove(o);
			o.dispose();
			// TODO: is there any more to dispose?
		}
		placedObjects.clear();
	}

	@Override
	public void init(World world) {
		this.world = world;
		for (PhysicsObject object : physicsObjects) {
			object.init(world);
		}
		for (InteractivePhysicsObject object : interactiveObjects) {
			object.init(world);
		}

		ContactListener contactListener = createCollectionContactListener();
		world.setContactListener(contactListener);
	}

	@Override
	public void handlePhysicsEvents() {
		for (InteractivePhysicsObject object : interactiveObjects) {
			object.handlePhysicsEvents();
		}
	}

	@Override
	public Body getBody() {
		throw new UnsupportedOperationException("PhysicPlayField does not have a body");
	}

	private ContactListener createCollectionContactListener() {
		return new ContactListener() {

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				for (ContactListener contactListener : contactListeners) {
					contactListener.preSolve(contact, oldManifold);
				}
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				for (ContactListener contactListener : contactListeners) {
					contactListener.postSolve(contact, impulse);
				}
			}

			@Override
			public void endContact(Contact contact) {
				for (ContactListener contactListener : contactListeners) {
					contactListener.endContact(contact);
				}
			}

			@Override
			public void beginContact(Contact contact) {
				for (ContactListener contactListener : contactListeners) {
					contactListener.beginContact(contact);
				}
			}
		};
	}

}

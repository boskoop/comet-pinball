package ch.m02.comet.pinball.physics;

import java.util.ArrayList;
import java.util.List;

import ch.m02.comet.pinball.physics.element.FieldBottomCornerElement;
import ch.m02.comet.pinball.physics.element.FieldBoundsElement;
import ch.m02.comet.pinball.physics.element.FieldTopCornerElement;
import ch.m02.comet.pinball.physics.element.FlipperElement;
import ch.m02.comet.pinball.physics.element.PlungerTubeElement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class Playfield implements InteractivePhysicsObject {

	private List<PhysicsObject> physicsObjects = new ArrayList<PhysicsObject>();
	private List<InteractivePhysicsObject> interactiveObjects = new ArrayList<InteractivePhysicsObject>();
	private List<ContactListener> contactListeners = new ArrayList<ContactListener>();

	public Playfield() {
		physicsObjects.add(new FieldBoundsElement());
		physicsObjects.add(new FieldTopCornerElement());
		physicsObjects.add(new FieldBottomCornerElement());
		physicsObjects.add(new PlungerTubeElement());
		interactiveObjects.add(new FlipperElement());
		
		Bumper leftTopBumper = new Bumper(new Vector2(0.25f * PhysicsDefinition.METER_SCALE_FACTOR,1.1f * PhysicsDefinition.METER_SCALE_FACTOR));
		Bumper rightTopBumper = new Bumper(new Vector2(0.5f * PhysicsDefinition.METER_SCALE_FACTOR,1.1f * PhysicsDefinition.METER_SCALE_FACTOR));
		physicsObjects.add(rightTopBumper);
		physicsObjects.add(leftTopBumper);
		
		// TODO: this should only be done at init time (not in constructor!), see todo in Bumper
		contactListeners.add(leftTopBumper.getContactListener());
		contactListeners.add(rightTopBumper.getContactListener());
		
	}

	@Override
	public void init(World world) {
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
		// TODO Auto-generated method stub
		return null;
	}
	
	private ContactListener createCollectionContactListener(){
		return new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				for (ContactListener contactListener : contactListeners) {
					contactListener.preSolve(contact,oldManifold);
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

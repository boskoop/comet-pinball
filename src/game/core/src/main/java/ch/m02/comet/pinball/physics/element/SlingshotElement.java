package ch.m02.comet.pinball.physics.element;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import ch.m02.comet.pinball.physics.Bumper;
import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsObject;
import ch.m02.comet.pinball.physics.Placeable;

public class SlingshotElement implements PhysicsObject,Placeable{
	private Vector2 centerPosition;
	private float rotationAngle;
	private Body reactableBody;
	private float size = PhysicsDefinition.SLINGSHOT_SIZE;
	
	
	
	public SlingshotElement(Vector2 centerPosition,float rotationAngle) {
		this.centerPosition = centerPosition;
		this.rotationAngle = rotationAngle;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(World world) {
		// TODO Auto-generated method stub
		reactableBody = createSlingshotBody(world);
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ContactListener getContactListener(){
		return new ContactListener() {
			
			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// nothing to do
			}
			
			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// nothing to do
			}
			
			@Override
			public void endContact(Contact contact) {
				if(contact.getFixtureA().getBody() == SlingshotElement.this.getBody()){
					Body ball = contact.getFixtureB().getBody();
					Vector2 normalizedCollisionNormal = ball.getPosition().sub(SlingshotElement.this.getBody().getPosition()).nor();
					ball.applyForceToCenter(normalizedCollisionNormal.mul(PhysicsDefinition.BUMPER_FORCE));
				} else if (contact.getFixtureB().getBody() == SlingshotElement.this.getBody()){
					Body ball = contact.getFixtureA().getBody();
					Vector2 normalizedCollisionNormal = ball.getPosition().sub(SlingshotElement.this.getBody().getPosition()).nor();
					ball.applyForceToCenter(normalizedCollisionNormal.mul(PhysicsDefinition.BUMPER_FORCE));
				}
			}
			
			@Override
			public void beginContact(Contact contact) {
				// nothing to do
			}
		};
	}

	
	private Body createSlingshotBody(World world){
		BodyDef slingshotDef = new BodyDef();
		slingshotDef.type = BodyType.DynamicBody;
		slingshotDef.position.set(centerPosition);
		Body slingshot = world.createBody(slingshotDef);
		
		//Rectan
		return slingshot;
	}
}

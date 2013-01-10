package ch.m02.comet.pinball.physics.element;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsObject;
import ch.m02.comet.pinball.physics.Placeable;
import ch.m02.comet.pinball.physics.box2d.ContactListenerAdapter;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;

public class SlingshotElement implements PhysicsObject, Placeable {

	private Vector2 centerPosition;
	private float rotationAngle;
	private Body slingshotBody;
	private float size = PhysicsDefinition.SLINGSHOT_SIZE;
	private ContactListener contactListener;

	public SlingshotElement(Vector2 centerPosition, float rotationAngle) {
		this.centerPosition = centerPosition;
		this.rotationAngle = rotationAngle;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(World world) {
		slingshotBody = createSlingshotBody(world);
		
		createContactListener();
	}

	private Body createSlingshotBody(World world){
		BodyDef slingshotDef = new BodyDef();
		slingshotDef.type = BodyType.DynamicBody;
		slingshotDef.position.set(centerPosition);
		Body slingshot = world.createBody(slingshotDef);
		
		//Rectan
		return slingshot;
	}

	private void createContactListener() {
		contactListener = new ContactListenerAdapter() {
			
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
		};
	}

	@Override
	public Body getBody() {
		// TODO: implement
		return null;
	}
	
	public ContactListener getContactListener(){
		return contactListener;
	}
}
package ch.m02.comet.pinball.physics.placable;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PlacablePhysicsObject;
import ch.m02.comet.pinball.physics.box2d.ContactListenerAdapter;
import ch.m02.comet.pinball.physics.util.DisposeUtil;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Slingshot implements PlacablePhysicsObject {

	private Vector2 cornerPosition, vectorToCornerA, vectorToCornerB;
	private Vector2 normalOfReactiveSide;
	private Body slingshotBody;
	private Body reactiveSlingshotBody;
	
	private ContactListener contactListener;

	/**
	 * The three parameters cornerPosition, vectorToCornerA, vectorToCornerB
	 * should be filled with vertices that are arranged in a counterclockwise
	 * manner.
	 * 
	 * @param cornerPosition The position of the corner in world coordinates
	 * @param vectorToCornerA The position of corner A in object coordinates
	 * @param vectorToCornerB The position of corner B in object coordinates
	 */
	public Slingshot(Vector2 cornerPosition, Vector2 vectorToCornerA,
			Vector2 vectorToCornerB) {
		this.cornerPosition = cornerPosition;
		this.vectorToCornerA = vectorToCornerA;
		this.vectorToCornerB = vectorToCornerB;
	}

	@Override
	public void init(World world) {
		createSlingshotBody(world);
	}

	private Body createSlingshotBody(World world){
		createContactListener();
		// create body definition of slingshot
		BodyDef slingshotDef = new BodyDef();
		slingshotDef.type = BodyType.StaticBody;
		slingshotDef.position.set(cornerPosition);
		slingshotBody = world.createBody(slingshotDef);
		reactiveSlingshotBody = world.createBody(slingshotDef);

		PolygonShape reactiveSlingshotShape = null;
		PolygonShape slingshotShapeCB = null;
		PolygonShape slingshotShapeCA = null;
		CircleShape slingshotCornerAShape = null;
		CircleShape slingshotCornerBShape = null;
		CircleShape slingshotCornerCShape = null;

		FixtureDef slingshotFixtureDef = new FixtureDef();
		slingshotFixtureDef.density = PhysicsDefinition.SLINGSHOT_DENSITY;
		slingshotFixtureDef.restitution = PhysicsDefinition.SLINGSHOT_RESTITUTION;
		
		//FixtureDef SlingShot
		
		FixtureDef slingshotCornerFixtureDef = new FixtureDef();
		slingshotCornerFixtureDef.density = PhysicsDefinition.SLINGSHOT_DENSITY;
		slingshotCornerFixtureDef.restitution = PhysicsDefinition.SLINGSHOT_RESTITUTION;
		
		
		try {
			
			// ***********************************
			// create reactive part of slingshot
			// ***********************************
			Vector2 vectorAB = vectorToCornerB.cpy().sub(vectorToCornerA); 
			normalOfReactiveSide = vectorAB.cpy().rotate(-90).nor();
			Vector2 rotatedRadius = vectorAB.cpy().rotate(90).nor().mul(PhysicsDefinition.SLINGSHOT_CORNER_SIZE);
			Vector2[] reactiveSlingshotVerticesAB = new Vector2[4];

			reactiveSlingshotVerticesAB[0] = vectorToCornerA.cpy().sub(rotatedRadius);
			reactiveSlingshotVerticesAB[1] = vectorToCornerB.cpy().sub(rotatedRadius);
			reactiveSlingshotVerticesAB[2] = vectorToCornerB.cpy().add(rotatedRadius);
			reactiveSlingshotVerticesAB[3] = vectorToCornerA.cpy().add(rotatedRadius);
			
			reactiveSlingshotShape = new PolygonShape();
			reactiveSlingshotShape.set(reactiveSlingshotVerticesAB);
			slingshotFixtureDef.shape = reactiveSlingshotShape;
			reactiveSlingshotBody.createFixture(slingshotFixtureDef);
			
			// *************************************
			// create nonreactive part of slingshot
			// *************************************
			
			Vector2 vectorCB = vectorToCornerB.cpy(); 
			Vector2 rotatedRadiusCB = vectorCB.cpy().rotate(90).nor().mul(PhysicsDefinition.SLINGSHOT_CORNER_SIZE);
			
			Vector2[] slingshotVerticesCB = new Vector2[4];
			slingshotVerticesCB[0] = new Vector2().sub(rotatedRadiusCB);
			slingshotVerticesCB[1] = vectorToCornerB.cpy().sub(rotatedRadiusCB);
			slingshotVerticesCB[2] = vectorToCornerB.cpy().add(rotatedRadiusCB);
			slingshotVerticesCB[3] = new Vector2().add(rotatedRadiusCB);
			
			slingshotShapeCB = new PolygonShape();
			slingshotShapeCB.set(slingshotVerticesCB);
			slingshotFixtureDef.shape = slingshotShapeCB;
			slingshotBody.createFixture(slingshotFixtureDef);
			
			Vector2 vectorCA = vectorToCornerA.cpy(); 
			Vector2 rotatedRadiusCA = vectorCA.cpy().rotate(90).nor().mul(PhysicsDefinition.SLINGSHOT_CORNER_SIZE);
			//rotatedRadiusCA = new Vector2(0.05f, 0.05f);
			Vector2[] slingshotVerticesCA = new Vector2[4];
			slingshotVerticesCA[0] = new Vector2().sub(rotatedRadiusCA);
			slingshotVerticesCA[1] = vectorToCornerA.cpy().sub(rotatedRadiusCA);
			slingshotVerticesCA[2] = vectorToCornerA.cpy().add(rotatedRadiusCA);
			slingshotVerticesCA[3] = new Vector2().add(rotatedRadiusCA);
			
			slingshotShapeCA = new PolygonShape();
			slingshotShapeCA.set(slingshotVerticesCA);
			slingshotFixtureDef.shape = slingshotShapeCA;
			slingshotBody.createFixture(slingshotFixtureDef);
			
			
			// ***************
			// create corners
			// ***************
			// center corner
			slingshotCornerCShape = new CircleShape();
			slingshotCornerCShape.setRadius(PhysicsDefinition.SLINGSHOT_CORNER_SIZE);
			slingshotCornerFixtureDef.shape = slingshotCornerCShape;
			slingshotBody.createFixture(slingshotCornerFixtureDef);
			
			// corner A
			slingshotCornerAShape = new CircleShape();
			slingshotCornerAShape.setRadius(PhysicsDefinition.SLINGSHOT_CORNER_SIZE);
			slingshotCornerAShape.setPosition(vectorToCornerA);
			slingshotCornerFixtureDef.shape = slingshotCornerAShape;
			slingshotBody.createFixture(slingshotCornerFixtureDef);
			
			// corner B
			slingshotCornerBShape = new CircleShape();
			slingshotCornerBShape.setRadius(PhysicsDefinition.SLINGSHOT_CORNER_SIZE);
			slingshotCornerBShape.setPosition(vectorToCornerB);
			slingshotCornerFixtureDef.shape = slingshotCornerBShape;
			slingshotBody.createFixture(slingshotCornerFixtureDef);
			
			
		} finally {
			DisposeUtil.safelyDispose(reactiveSlingshotShape);
			DisposeUtil.safelyDispose(slingshotCornerAShape);
			DisposeUtil.safelyDispose(slingshotCornerBShape);
			DisposeUtil.safelyDispose(slingshotCornerCShape);
		}
		
		return slingshotBody;
	}

	private void createContactListener() {
		contactListener = new ContactListenerAdapter() {
			
			@Override
			public void endContact(Contact contact) {
				if(contact.getFixtureA().getBody() == Slingshot.this.getReactiveBody()){
					Body ball = contact.getFixtureB().getBody();
					ball.applyForceToCenter(normalOfReactiveSide.cpy().mul(PhysicsDefinition.BUMPER_FORCE));
				} else if (contact.getFixtureB().getBody() == Slingshot.this.getReactiveBody()){
					Body ball = contact.getFixtureA().getBody();
					ball.applyForceToCenter(normalOfReactiveSide.cpy().mul(PhysicsDefinition.BUMPER_FORCE));
				}
			}
		};
	}

	@Override
	public Body getBody() {
		return slingshotBody;
	}
	
	public Body getReactiveBody(){
		return reactiveSlingshotBody;
	}
	
	@Override
	public ContactListener getContactListener(){
		return contactListener;
	}

	@Override
	public void dispose() {
		World world = slingshotBody.getWorld();
		world.destroyBody(slingshotBody);
		world.destroyBody(reactiveSlingshotBody);
	}
}

package ch.m02.comet.pinball.physics.placable;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PlacablePhysicsObject;
import ch.m02.comet.pinball.physics.box2d.ContactListenerAdapter;
import ch.m02.comet.pinball.physics.util.DisposeUtil;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Obstacle implements PlacablePhysicsObject {
	private Vector2 position;
	private Vector2[] vertices;
	
	private Body body;
	private ContactListener contactListener;

	/**
	 * This is an obstacle of a given shape. The obstacle does not alter the score and can be used to design a complex playfield.
	 * 
	 * @param position position of the obstacle in Box2D coordinates
	 * @param vertices first vertice is the position on the play field and all following vertices define the obstacles
	 * body in an counterclockwise manner
	 */
	public Obstacle(Vector2 position, Vector2[] vertices) {
		if (vertices == null || vertices.length == 0) {
			throw new IllegalArgumentException("parameter vertices of an obstancle can not be null or empty");
		}
		if (position == null) {
			throw new IllegalArgumentException("parameter position of an obstancle can not be null ");
		}
		this.position = position;
		this.vertices = vertices;
	}
	

	@Override
	public void init(World world) {
		
		BodyDef obstacleBodyDef = defineObstacleBody();
		this.body = world.createBody(obstacleBodyDef);
		Shape obstacleShape = null;

		try {
			FixtureDef obstacleFixtureDef = defineObstacleFixtue();
			obstacleShape = generateObstacleShape();
			obstacleFixtureDef.shape = obstacleShape;
			this.body.createFixture(obstacleFixtureDef);
		} finally {
			DisposeUtil.safelyDispose(obstacleShape);
		}
		createContactListener();
	}

	private BodyDef defineObstacleBody() {
		BodyDef obstacleBodyDef = new BodyDef();
		obstacleBodyDef.type = BodyType.StaticBody;
		obstacleBodyDef.position.set(this.position);
		return obstacleBodyDef;
	}

	private FixtureDef defineObstacleFixtue(){
		FixtureDef obstacleFixtureDef = new FixtureDef();
		obstacleFixtureDef.density = PhysicsDefinition.OBSTACLE_DENSITY;
		obstacleFixtureDef.restitution = PhysicsDefinition.OBSTACLE_RESTITUTION;
		
		return obstacleFixtureDef;
	}
	
	private Shape generateObstacleShape(){
		
		PolygonShape obstacleShape= new PolygonShape();
		obstacleShape.set(vertices);
		return obstacleShape;
	}
	
	
	private void createContactListener() {
		contactListener = new ContactListenerAdapter() {
			// no special action is needed
		};
	}

	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public ContactListener getContactListener() {
		return contactListener;
	}

	@Override
	public void dispose() {
		World world = body.getWorld();
		world.destroyBody(body);
	}

}

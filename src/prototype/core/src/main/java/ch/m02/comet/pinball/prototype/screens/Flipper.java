package ch.m02.comet.pinball.prototype.screens;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Flipper {
	private float scale = 0.75f;
	private float force = 3000000f;
	public final static float FACTOR_DEG_TO_RAD = (float) (Math.PI / 180);
	private BodyDef bodyDef;
	private Body body;
	private Shape shape;
	private Fixture fixture;
	
	private Body fixPoint;
	private Joint joint;
	
	public Flipper(World world){
		this(world,0,0);
	}
	
	public Flipper(World world, float xPosition, float yPosition){
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(xPosition,yPosition);
		bodyDef.angle = (float)Math.PI;
		body = world.createBody(bodyDef);
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.set(new float[]{0,0,100 * scale,0,100 * scale,30 * scale,0,30 * scale});
		//polygonShape.set(new float[]{0,0,100,0,100,50,0,50});
		
		//polygonShape.
		
		shape = polygonShape;
		
		//shape = new CircleShape();
		//Shape leftFlipper = new CircleShape();
		//shape.setRadius(20f);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 2f;
		fixtureDef.restitution = 0.2f;
		fixture = body.createFixture(fixtureDef);

		BodyDef leftFlipperFixPointBodyDef = new BodyDef();
		leftFlipperFixPointBodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(xPosition, yPosition);
		fixPoint = world.createBody(leftFlipperFixPointBodyDef);
		
		
		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.bodyB = fixPoint;
		jointDef.bodyA = body;
		jointDef.upperAngle = 20 * FACTOR_DEG_TO_RAD;
		jointDef.lowerAngle =  -30*  FACTOR_DEG_TO_RAD;
		jointDef.enableLimit = true;
		jointDef.maxMotorTorque = -10000000f;
		jointDef.motorSpeed = 20000000;
		//jointDef.enableMotor = true;
		jointDef.referenceAngle = 0;
		jointDef.localAnchorA.set(15 * scale,15 * scale);
		jointDef.localAnchorB.set(50,50);
		
		joint = world.createJoint(jointDef);
	}

	
	public void dispose(){
	}
	
	public Fixture getFixture(){
		return fixture;
	}
	
	public Body getBody(){
		return body;
	}
	
	public Joint getJoint(){
		return joint;
	}
	
	public void moveUpward(){
		body.applyAngularImpulse(force);
	}
	
	public void moveDownward(){
		body.applyAngularImpulse(-force);
	}
}

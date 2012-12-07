package ch.m02.comet.pinball.prototype.screens;

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
		
		
		shape = new PolygonShape();
		shape = new CircleShape();
		//Shape leftFlipper = new CircleShape();
		shape.setRadius(20f);

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
		
		
		RevoluteJointDef leftFlipperJointDef = new RevoluteJointDef();
		leftFlipperJointDef.bodyB = fixPoint;
		leftFlipperJointDef.bodyA = body;
		leftFlipperJointDef.upperAngle = 90 * FACTOR_DEG_TO_RAD;
		leftFlipperJointDef.lowerAngle = 0;
		leftFlipperJointDef.enableLimit = true;
		leftFlipperJointDef.maxMotorTorque = -100000f;
		leftFlipperJointDef.motorSpeed = 20000000;
		leftFlipperJointDef.enableMotor = true;
		leftFlipperJointDef.referenceAngle = 0;
		joint = world.createJoint(leftFlipperJointDef);
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
		body.applyAngularImpulse(50000f);
	}
	
}

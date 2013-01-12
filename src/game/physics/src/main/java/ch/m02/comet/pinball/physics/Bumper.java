package ch.m02.comet.pinball.physics;

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
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Bumper implements PhysicsObject {
	
	private Vector2 position;
	private Body body;
	private ContactListener contactListener;

	public Bumper() {
		this(new Vector2(0, 0));
	}

	public Bumper(Vector2 position) {
		if (position == null)
			throw new IllegalArgumentException();
		this.position = position;
	}

	@Override
	public void init(World world) {

		BodyDef bumperBodyDef = defineBumperBody(this.position);
		this.body = world.createBody(bumperBodyDef);

		CircleShape circle = null;
		try {
			circle = new CircleShape();
			circle.setRadius(PhysicsDefinition.BUMPER_RADIUS);
			FixtureDef bumperFixtureDefinition = defineBumperFixture(circle);
			body.createFixture(bumperFixtureDefinition);
		} finally {
			DisposeUtil.safelyDispose(circle);
		}
		createContactListener();
	}

	private void createContactListener() {
		contactListener = new ContactListenerAdapter() {

			@Override
			public void endContact(Contact contact) {
				if (contact.getFixtureA().getBody() == Bumper.this.getBody()) {
					Body ball = contact.getFixtureB().getBody();
					Vector2 normalizedCollisionNormal = ball.getPosition()
							.sub(body.getPosition()).nor();
					ball.applyForceToCenter(normalizedCollisionNormal
							.mul(PhysicsDefinition.BUMPER_FORCE));
				} else if (contact.getFixtureB().getBody() == Bumper.this
						.getBody()) {
					Body ball = contact.getFixtureA().getBody();
					Vector2 normalizedCollisionNormal = ball.getPosition()
							.sub(body.getPosition()).nor();
					ball.applyForceToCenter(normalizedCollisionNormal
							.mul(PhysicsDefinition.BUMPER_FORCE));
				}
			}
		};
	}

	@Override
	public Body getBody() {
		return this.body;
	}

	private BodyDef defineBumperBody(Vector2 position) {
		BodyDef bumperBodyDef = new BodyDef();
		bumperBodyDef.type = BodyType.StaticBody;
		bumperBodyDef.position.set(position);
		return bumperBodyDef;
	}

	private FixtureDef defineBumperFixture(Shape shape) {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = PhysicsDefinition.BUMPER_RESTITUTION;
		return fixtureDef;
	}

	public void setPosition(Vector2 position) {
		this.position.set(position);
	}

	public ContactListener getContactListener() {
		return contactListener;
	}

}

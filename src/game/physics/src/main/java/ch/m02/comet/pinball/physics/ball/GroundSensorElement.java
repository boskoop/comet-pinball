package ch.m02.comet.pinball.physics.ball;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.logic.command.BallDownCommand;
import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsObject;
import ch.m02.comet.pinball.physics.PhysicsPlayField;
import ch.m02.comet.pinball.physics.box2d.ContactListenerAdapter;
import ch.m02.comet.pinball.physics.util.DisposeUtil;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GroundSensorElement implements PhysicsObject {
	
	private static final Logger log = LoggerFactory.getLogger(GroundSensorElement.class);

	@Inject
	private ApplicationContext context;
	
	private Body body;

	@Override
	public Body getBody() {
		return body;
	}

	@Override
	public void init(World world) {
		BodyDef sensorDef = new BodyDef();
		sensorDef.type = BodyType.StaticBody;
		sensorDef.position.set(0.35f * PhysicsDefinition.METER_SCALE_FACTOR, 0.02f * PhysicsDefinition.METER_SCALE_FACTOR);
		body = world.createBody(sensorDef);
		
		PolygonShape shape = null;
		try {
			shape = new PolygonShape();
			shape.setAsBox(0.35f * PhysicsDefinition.METER_SCALE_FACTOR, 0.02f * PhysicsDefinition.METER_SCALE_FACTOR);
			FixtureDef fixture = new FixtureDef();
			fixture.shape = shape;
			fixture.isSensor = true;
			body.createFixture(fixture);
		} finally {
			DisposeUtil.safelyDispose(shape);
		}
		
		ContactListenerAdapter listener = new ContactListenerAdapter() {
			@Override
			public void beginContact(Contact contact) {
				if (contact.getFixtureA().getBody() == body
						|| contact.getFixtureB().getBody() == body) {
					log.info("Ball went down through the drain");
					BallDownCommand command = context.getComponentContainer()
							.getComponent(BallDownCommand.class);
					command.execute();
				}
			}
		};
		PhysicsPlayField playField = context.getComponentContainer()
				.getComponent(PhysicsPlayField.class);
		playField.registerContactListener(listener);

	}

}

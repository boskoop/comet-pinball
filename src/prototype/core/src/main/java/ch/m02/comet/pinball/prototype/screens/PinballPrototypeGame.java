package ch.m02.comet.pinball.prototype.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PinballPrototypeGame implements Screen {

	public final static float FACTOR_DEG_TO_RAD = (float) (Math.PI / 180);
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	// private Texture texture;
	// private Sprite sprite;

	private CircleShape circle;
	private Body circleBody;

	private static final int WINDOW_WIDTH = 512, WINDOW_HEIGHT = 1024;
	private World world;

	private Sound pinballMachineSound, hornSound;

	// private static final float WORLD_TO_BOX = 0.01f, BOX_WORLD_TO = 100f;
	private Box2DDebugRenderer debugRender;

	private Fixture groundFixture, ceilingFixture, ballFixture;
	
	private Flipper leftFlipper;

	// private Game game;

	public PinballPrototypeGame(Game game) {
		// this.game = game;
		create();
	}

	public void create() {
		// Setting up a new world for simulating the play field
		// Vector2 param1 = Gravity in x plane (-10 is a downwards force like in
		// real life)
		// Vector2 param2 = Gravity in y plane (-10 is a downwards force like in
		// real life)
		world = new World(new Vector2(0, -10), true);

		//camera = new OrthographicCamera(WINDOW_WIDTH,WINDOW_HEIGHT);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);

		batch = new SpriteBatch();

		// texture = new Texture(Gdx.files.internal("data/metallkugel.jpg"));
		// texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		/*
		 * Physics definition
		 */
		BodyDef bodyDef = new BodyDef();
		// This thing should move so we set it dynamic. A floor is a static body
		bodyDef.type = BodyType.DynamicBody;
		// starting point
		bodyDef.position.set(WINDOW_WIDTH / 6, WINDOW_HEIGHT / 2);
		circleBody = world.createBody(bodyDef);

		// Create a shape
		circle = new CircleShape();
		circle.setRadius(10f);

		// Create a fixture definition
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f; // 0.5f
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0f; // Make it bounce a little bit

		/* Fixture circleFixture = */
		ballFixture = circleBody.createFixture(fixtureDef);

		/*
		 * Left flipper
		 */
		
		leftFlipper = new Flipper(world,WINDOW_WIDTH/4,WINDOW_HEIGHT/4);
		
		/*
		BodyDef leftFlipperBodyDef = new BodyDef();
		leftFlipperBodyDef.type = BodyType.DynamicBody;
		leftFlipperBodyDef.position.set(WINDOW_WIDTH / 4, WINDOW_HEIGHT / 4);
		leftFlipperBodyDef.angle = (float)Math.PI;
		leftFlipperBody = world.createBody(leftFlipperBodyDef);
		
		
		PolygonShape leftFlipper = new PolygonShape();
		leftFlipper.
		
		//Shape leftFlipper = new CircleShape();
		leftFlipper.setRadius(20f);

		FixtureDef leftFlipperFixtureDef = new FixtureDef();
		leftFlipperFixtureDef.shape = leftFlipper;
		leftFlipperFixtureDef.density = 1f;
		leftFlipperFixtureDef.friction = 2f;
		leftFlipperFixtureDef.restitution = 0.2f;
		Fixture leftFlipperFixture = leftFlipperBody
				.createFixture(leftFlipperFixtureDef);

		BodyDef leftFlipperFixPointBodyDef = new BodyDef();
		leftFlipperFixPointBodyDef.type = BodyType.StaticBody;
		leftFlipperBodyDef.position.set(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
		Body leftFlipperFixPoint = world.createBody(leftFlipperFixPointBodyDef);
		
		
		RevoluteJointDef leftFlipperJointDef = new RevoluteJointDef();
		leftFlipperJointDef.bodyB = leftFlipperFixPoint;
		leftFlipperJointDef.bodyA = leftFlipperBody;
		leftFlipperJointDef.upperAngle = 90 * FACTOR_DEG_TO_RAD;
		leftFlipperJointDef.lowerAngle = 0;
		leftFlipperJointDef.enableLimit = true;
		leftFlipperJointDef.maxMotorTorque = -100000f;
		leftFlipperJointDef.motorSpeed = 20000000;
		leftFlipperJointDef.enableMotor = true;
		leftFlipperJointDef.referenceAngle = 0;
		world.createJoint(leftFlipperJointDef);
		*/
		/*
		 * Static Bodies
		 */

		float borderThickness = 3;

		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, borderThickness));

		// Create body of ground

		Body groundBody = world.createBody(groundBodyDef);

		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(camera.viewportWidth * 2, borderThickness);

		groundFixture = groundBody.createFixture(groundBox, 0.0f);

		// Create body of ceiling
		BodyDef ceilingBodyDef = new BodyDef();
		ceilingBodyDef.position.set(new Vector2(0, camera.viewportHeight
				- borderThickness));
		Body ceilingBody = world.createBody(ceilingBodyDef);

		PolygonShape ceilingBox = new PolygonShape();
		ceilingBox.setAsBox(camera.viewportWidth * 2, borderThickness);

		ceilingFixture = ceilingBody.createFixture(ceilingBox, 0.0f);

		// This debugger is useful for testing purposes
		debugRender = new Box2DDebugRenderer();
	

		// Load sounds

		pinballMachineSound = loadSound("pinball-machine.mp3");
		hornSound = loadSound("horn.mp3");

		// Create contact listener
		ContactListener groundContactListener = new ContactListener() {
			// http://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/physics/box2d/ContactListener.html

			@Override
			public void postSolve(Contact arg0, ContactImpulse arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void endContact(Contact arg0) {
				hornSound.stop();
				pinballMachineSound.stop();
			}

			@Override
			public void beginContact(Contact contact) {
				
				
				// Ball hits floor
				if (contact.getFixtureA() == ballFixture
						&& contact.getFixtureB() == groundFixture
						|| contact.getFixtureB() == ballFixture
						&& contact.getFixtureA() == groundFixture)
					hornSound.play();
				
				// Ball hits ceiling
				if (contact.getFixtureA() == ballFixture
						&& contact.getFixtureB() == ceilingFixture
						|| contact.getFixtureB() == ballFixture
						&& contact.getFixtureA() == ceilingFixture)
					pinballMachineSound.play();

			}

			@Override
			public void preSolve(Contact arg0, Manifold arg1) {
				// TODO Auto-generated method stub

			}
		};

		world.setContactListener(groundContactListener);
	}

	/**
	 * Load sound file
	 * 
	 * @param filename
	 *            name of file ( relative to data/sounds/ )
	 * @return Sound file
	 */
	private static Sound loadSound(String filename) {
		return Gdx.audio
				.newSound(Gdx.files.internal("data/sounds/" + filename));
	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
		debugRender.dispose();
		// texture.dispose();
		
		hornSound.dispose();
		pinballMachineSound.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.UP)) {
			/*
			 * // Get touched position Vector3 touchPos = new Vector3();
			 * touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
			 * camera.unproject(touchPos);
			 */

			circleBody.applyForceToCenter(0,5000);
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT))
			circleBody.applyForceToCenter(-2000, 0);

		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			circleBody.applyForceToCenter(2000, 0);

		if (Gdx.input.isKeyPressed(Keys.Q)) {
			// leftFlipperBody.applyTorque(500000f);
			//leftFlipperBody.applyAngularImpulse(50000f);
			leftFlipper.moveUpward();
		}
		if (!Gdx.input.isKeyPressed(Keys.Q)) {
			// leftFlipperBody.applyTorque(500000f);
			//leftFlipperBody.applyAngularImpulse(50000f);
			leftFlipper.moveDownward();
		}
		
		/*
		if (Gdx.input.isKeyPressed(Keys.W)) {
			//leftFlipperBody.applyTorque(-500000f);
			//leftFlipperBody.applyAngularImpulse(-50000f);
		}
		 */
		// update actors and sprites
		/*
		 * Iterator<Body> bodies = world.getBodies();
		 * 
		 * while(bodies.hasNext()){ Body b = bodies.next(); Entity e =
		 * b.getUserData();
		 * 
		 * e.setPosition(b.getPosition().x,b.getPosition().y);
		 * 
		 * e.setRotation(MathUtils.radiansToDegrees*b.getAngle()); }
		 */

		// Render physics should be called before physical rendering
		debugRender.render(world, camera.combined);

		// step/update the world
		// param1 = timestep 1/60 of a second...
		// param2 = velocityIterations
		// param2 = positionIterations
		world.step(1 / 30f, 6, 2);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}

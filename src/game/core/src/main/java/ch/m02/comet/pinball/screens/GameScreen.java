package ch.m02.comet.pinball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen implements Screen {

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Body circleBody;

	// a typical pinball machine is 76cm x 140cm
	// we use scale 6px = 1cm in real
	// 6*76 = 456
	public static final int WINDOW_WIDTH = 456;
	// 6*140 = 840
	public static final int WINDOW_HEIGHT = 840;

	private World world;

	// 600px are 1m in real -> 1 box2d unit = 1m
//	private static final float BOX_TO_WORLD = 600f;
//	private static final float WORLD_TO_BOX = 1 / BOX_TO_WORLD;
	private static final float FACTOR = 100f;
	
	private static final float FIELD_WIDTH = 0.76f * FACTOR;
	private static final float FIELD_HEIGHT = 1.40f * FACTOR;
	
	// typical diameter = 2.7cm
	// pinball radius: 1.35cm
	private static final float PINBALL_RADIUS = 0.0135f * FACTOR;
	
	// typical value ~6.5-7 degrees
	private static final float RAMP_ANGLE_DEGREES = 7f;
	private static final float EARTH_GRAVITY = -9.81f * FACTOR;
	private static final float RAMP_GRAVITY = EARTH_GRAVITY * MathUtils.sinDeg(RAMP_ANGLE_DEGREES);

	private Box2DDebugRenderer debugRender;
	private static final int VELOCITY_ITERATIONS = 8;
	private static final int POSITION_ITERATIONS = 8;

	// private Game game;

	public GameScreen(Game game) {
		// this.game = game;
	}

	public void init() {
		// Setting up a new world for simulating the play field
		final Vector2 gravity = new Vector2(0, RAMP_GRAVITY);
		final boolean dontSimulateInactiveBodies = true;
		world = new World(gravity, dontSimulateInactiveBodies);

		camera = new OrthographicCamera();
		final boolean yPointsDown = false;
		camera.setToOrtho(yPointsDown, FIELD_WIDTH, FIELD_HEIGHT);

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
		bodyDef.position.set(FIELD_WIDTH / 2, FIELD_HEIGHT / 2);
		circleBody = world.createBody(bodyDef);

		// Create a shape
		CircleShape circle = new CircleShape();
		circle.setRadius(PINBALL_RADIUS);

		// Create a fixture definition
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 8000f / (FACTOR * FACTOR * FACTOR); // kg/cm^3
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.56f; // Make it bounce a little bit

		/* Fixture circleFixture = */
		circleBody.createFixture(fixtureDef);
		circle.dispose();

		/*
		 * Static Bodies
		 */
		final float borderThickness = 1f;

		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.type = BodyType.StaticBody;
		groundBodyDef.position.set(FIELD_WIDTH / 2, borderThickness / 2);

		// Create body of ground
		Body groundBody = world.createBody(groundBodyDef);

		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(FIELD_WIDTH / 2, borderThickness / 2);

		groundBody.createFixture(groundBox, 0.0f);
		groundBox.dispose();

		// Create body of ceiling
		BodyDef ceilingBodyDef = new BodyDef();
		ceilingBodyDef.type = BodyType.StaticBody;
		ceilingBodyDef.position.set(FIELD_WIDTH / 2, FIELD_HEIGHT - (borderThickness / 2));
		Body ceilingBody = world.createBody(ceilingBodyDef);

		PolygonShape ceilingBox = new PolygonShape();
		ceilingBox.setAsBox(FIELD_WIDTH / 2, borderThickness / 2);

		ceilingBody.createFixture(ceilingBox, 0.0f);
		ceilingBox.dispose();

		// Create left body
		BodyDef leftBodyDef = new BodyDef();
		leftBodyDef.type = BodyType.StaticBody;
		leftBodyDef.position.set(borderThickness / 2, FIELD_HEIGHT / 2);
		Body leftBody = world.createBody(leftBodyDef);

		PolygonShape leftBox = new PolygonShape();
		leftBox.setAsBox(borderThickness / 2, FIELD_HEIGHT / 2);

		leftBody.createFixture(leftBox, 0.0f);
		leftBox.dispose();

		// Create right body
		BodyDef rightBodyDef = new BodyDef();
		rightBodyDef.type = BodyType.StaticBody;
		rightBodyDef.position.set(FIELD_WIDTH - (borderThickness / 2), FIELD_HEIGHT / 2);
		Body rightBody = world.createBody(rightBodyDef);

		PolygonShape rightBox = new PolygonShape();
		rightBox.setAsBox(borderThickness / 2, FIELD_HEIGHT / 2);

		rightBody.createFixture(rightBox, 0.0f);
		rightBox.dispose();
		
		// This debugger is useful for testing purposes
		// debugRender = new Box2DDebugRenderer(true, true, false, true);
		debugRender = new Box2DDebugRenderer();

	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
		debugRender.dispose();
		// texture.dispose();
	}

	@Override
	public void render(float delta) {
		clearScreen();

		if (Gdx.input.isKeyPressed(Keys.Q)) {
			camera.zoom = 1f;
		}
		
		if (Gdx.input.isKeyPressed(Keys.Z)) {
			camera.zoom *= 1.02f;
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			camera.zoom /= 1.02f;
		}
		camera.update();

		batch.setProjectionMatrix(camera.combined);

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			circleBody.applyForceToCenter(0, circleBody.getMass() * (-2) * RAMP_GRAVITY);
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			circleBody.applyForceToCenter(circleBody.getMass() * RAMP_GRAVITY, 0);
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			circleBody.applyForceToCenter(circleBody.getMass() * -RAMP_GRAVITY, 0);
		}

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
		world.step(delta, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

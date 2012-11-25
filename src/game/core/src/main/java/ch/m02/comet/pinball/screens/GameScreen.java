package ch.m02.comet.pinball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
//	private Texture texture;
	// private Sprite sprite;

	private CircleShape circle;
	private Body circleBody;

	private static final int WINDOW_WIDTH = 512, WINDOW_HEIGHT = 1024;
	private World world;

	// private static final float WORLD_TO_BOX = 0.01f, BOX_WORLD_TO = 100f;
	private Box2DDebugRenderer debugRender;

//	private Game game;

	public GameScreen(Game game) {
//		this.game = game;
		create();
	}

	public void create() {
		// Setting up a new world for simulating the play field
		// Vector2 param1 = Gravity in x plane (-10 is a downwards force like in
		// real life)
		// Vector2 param2 = Gravity in y plane (-10 is a downwards force like in
		// real life)
		world = new World(new Vector2(0, -10), true);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, WINDOW_WIDTH, WINDOW_HEIGHT);

		batch = new SpriteBatch();

//		texture = new Texture(Gdx.files.internal("data/metallkugel.jpg"));
		// texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		/*
		 * Physics definition
		 */
		BodyDef bodyDef = new BodyDef();
		// This thing should move so we set it dynamic. A floor is a static body
		bodyDef.type = BodyType.DynamicBody;
		// starting point
		bodyDef.position.set(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
		circleBody = world.createBody(bodyDef);

		// Create a shape
		circle = new CircleShape();
		circle.setRadius(10f);

		// Create a fixture definition
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f; // 0.5f
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; // Make it bounce a little bit

		/* Fixture circleFixture = */
		circleBody.createFixture(fixtureDef);

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

		groundBody.createFixture(groundBox, 0.0f);

		// Create body of ceiling
		BodyDef ceilingBodyDef = new BodyDef();
		ceilingBodyDef.position.set(new Vector2(0, camera.viewportHeight
				- borderThickness));
		Body ceilingBody = world.createBody(ceilingBodyDef);

		PolygonShape ceilingBox = new PolygonShape();
		ceilingBox.setAsBox(camera.viewportWidth * 2, borderThickness);

		ceilingBody.createFixture(ceilingBox, 0.0f);

		// This debugger is useful for testing purposes
		debugRender = new Box2DDebugRenderer();

	}

	@Override
	public void dispose() {
		batch.dispose();
		world.dispose();
		debugRender.dispose();
//		texture.dispose();
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

			circleBody.applyForceToCenter(0, 5000);
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT))
			circleBody.applyForceToCenter(-2000, 0);

		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			circleBody.applyForceToCenter(2000, 0);

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
		world.step(1 / 60f, 6, 2);
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

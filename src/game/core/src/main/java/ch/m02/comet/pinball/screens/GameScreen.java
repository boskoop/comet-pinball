package ch.m02.comet.pinball.screens;

import ch.m02.comet.pinball.playfield.PhysicsDefinition;
import ch.m02.comet.pinball.playfield.PlayfieldElement;
import ch.m02.comet.pinball.playfield.element.Ball;
import ch.m02.comet.pinball.playfield.element.FieldBounds;
import ch.m02.comet.pinball.playfield.element.FieldTopCorner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen implements Screen {

	private OrthographicCamera camera;

	// a typical pinball machine is 76cm x 140cm
	// we use scale 6px = 1cm in real
	// 6*76 = 456
	public static final int WINDOW_WIDTH = 456;
	// 6*140 = 840
	public static final int WINDOW_HEIGHT = 840;

	private World world;

	// 600px are 1m in real -> 1 box2d unit = 1m
	// private static final float BOX_TO_WORLD = 600f;
	// private static final float WORLD_TO_BOX = 1 / BOX_TO_WORLD;

	private static final int VELOCITY_ITERATIONS = 6;
	private static final int POSITION_ITERATIONS = 2;

	private Box2DDebugRenderer debugRender;

	private PlayfieldElement ball;

	private PlayfieldElement fieldBounds;

	private FieldTopCorner fieldTopCorner;

	public GameScreen(Game game) {
	}

	public void init() {
		final Vector2 gravity = new Vector2(0, PhysicsDefinition.RAMP_GRAVITY);
		final boolean dontSimulateInactiveBodies = true;
		world = new World(gravity, dontSimulateInactiveBodies);
		// TODO use a DestructionListener

		camera = new OrthographicCamera();
		final boolean yPointsDown = false;
		camera.setToOrtho(yPointsDown, PhysicsDefinition.FIELD_WIDTH,
				PhysicsDefinition.FIELD_HEIGHT);
		
		ball = new Ball();
		ball.init(world);
		
		fieldBounds = new FieldBounds();
		fieldBounds.init(world);
		
		fieldTopCorner = new FieldTopCorner();
		fieldTopCorner.init(world);

		// This debugger is useful for testing purposes
		final boolean drawBodies = true;
		final boolean drawJoints = true;
		final boolean drawAABBs = false;
		final boolean drawInactiveBodies = true;
		debugRender = new Box2DDebugRenderer(drawBodies, drawJoints, drawAABBs, drawInactiveBodies);
	}

	@Override
	public void dispose() {
		debugRender.dispose();
		world.dispose();
	}

	@Override
	public void render(float delta) {
		clearScreen();
		updateCamera();
		
		ball.render();

		// Render physics should be called before physical rendering
		debugRender.render(world, camera.combined);

		// step/update the world
		world.step(delta, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
	}

	private void updateCamera() {
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
	}

	private void clearScreen() {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

}

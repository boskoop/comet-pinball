package ch.m02.comet.pinball.presentation.screens;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.presentation.screen.GameScreen;
import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsPlayField;
import ch.m02.comet.pinball.physics.ball.Ball;
import ch.m02.comet.pinball.presentation.graphics.GraphicsDisplay;
import ch.m02.comet.pinball.presentation.graphics.objects.BallGraphics;
import ch.m02.comet.pinball.presentation.graphics.objects.GraphicsObject;
import ch.m02.comet.pinball.presentation.graphics.objects.PlayFieldMessageDisplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreenImpl extends ManagedScreen implements GameScreen {

	@Inject
	private PhysicsPlayField playfield;
	
	@Inject
	private Ball ball;
	
	@Inject
	private GraphicsDisplay display;

	private OrthographicCamera camera;

	// a typical pinball machine is 76cm x 140cm
	// we use scale 6px = 1cm in real
	// 6*76 = 456
	public static final int VIRTUAL_WINDOW_WIDTH = 456;
	// 6*140 = 840
	public static final int VIRTUAL_WINDOW_HEIGHT = 840;
	private static final float ASPECT_RATIO =
			PhysicsDefinition.FIELD_WIDTH / PhysicsDefinition.FIELD_HEIGHT;
	
	private Rectangle viewport;
	
	private World world;

	// 600px are 10m in real -> 1 box2d unit = 1m
	// private static final float BOX_TO_WORLD = 600f;
	// private static final float WORLD_TO_BOX = 1 / BOX_TO_WORLD;

	private static final int VELOCITY_ITERATIONS = 6;
	private static final int POSITION_ITERATIONS = 2;

	private Box2DDebugRenderer debugRenderer;

	private SpriteBatch spriteBatch;

	private GraphicsObject ballGraphicsObject;
	private PlayFieldMessageDisplay scoreDisplayGraphicsObject;
	private PlayFieldMessageDisplay messageDisplayGraphicsObject;

	@Override
	public void init() {
		final Vector2 gravity = new Vector2(0, PhysicsDefinition.INSTANCE.getRampGravity());
		final boolean dontSimulateInactiveBodies = true;
		world = new World(gravity, dontSimulateInactiveBodies);
		spriteBatch = new SpriteBatch();
		// TODO use a DestructionListener

		camera = new OrthographicCamera();
		final boolean yPointsDown = false;
		camera.setToOrtho(yPointsDown, PhysicsDefinition.FIELD_WIDTH,
				PhysicsDefinition.FIELD_HEIGHT);

		playfield.init(world);
		ball.init(world);

		ballGraphicsObject = new BallGraphics();
		ballGraphicsObject.init(ball);
		
		scoreDisplayGraphicsObject = new PlayFieldMessageDisplay(new Vector2(100,100));
		scoreDisplayGraphicsObject.init(null);
		display.registerScoreDisplay(scoreDisplayGraphicsObject);
		
		messageDisplayGraphicsObject = new PlayFieldMessageDisplay(new Vector2(420,100));
		messageDisplayGraphicsObject.init(null);
		display.registerMessageDisplay(messageDisplayGraphicsObject);

		// This debugger is useful for testing purposes
		final boolean drawBodies = true;
		final boolean drawJoints = true;
		final boolean drawAABBs = false;
		final boolean drawInactiveBodies = true;
		final boolean drawVelocities = false;
		debugRenderer = new Box2DDebugRenderer(drawBodies, drawJoints, drawAABBs,
				drawInactiveBodies, drawVelocities);
	}

	@Override
	public void dispose() {
		debugRenderer.dispose();
		world.dispose();
	}

	@Override
	public void render(float delta) {
		if (delta > 0.02) {
			Gdx.app.log(GameScreenImpl.class.getCanonicalName(),
					"Slow frame, took " + delta + " seconds");
		}
		clearScreen();
		updateCamera();

		playfield.handlePhysicsEvents();
		ball.handlePhysicsEvents();

		// Render physics should be called before physical rendering
		debugRenderer.render(world, camera.combined);

		spriteBatch.begin();
		ballGraphicsObject.draw(camera, spriteBatch);
		scoreDisplayGraphicsObject.draw(camera, spriteBatch);
		messageDisplayGraphicsObject.draw(camera, spriteBatch);
		
		spriteBatch.end();

		// step/update the world
		world.step(delta, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
	}

	private void updateCamera() {
		// update camera
		
        // set viewport to correct aspect failures
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
		
		camera.update();
	}

	private void clearScreen() {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		// calculate new viewport
        float aspectRatio = (float) width / (float) height;

        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        
        if(aspectRatio > ASPECT_RATIO) {
            scale = (float) height / (float) VIRTUAL_WINDOW_HEIGHT;
            crop.x = (width - VIRTUAL_WINDOW_WIDTH * scale) / 2f;
        }
        else if (aspectRatio < ASPECT_RATIO) {
            scale = (float) width / (float) VIRTUAL_WINDOW_WIDTH;
            crop.y = (height - VIRTUAL_WINDOW_HEIGHT * scale) / 2f;
        }
        else {
            scale = (float) width / (float) VIRTUAL_WINDOW_WIDTH;
        }


        float w = (float) VIRTUAL_WINDOW_WIDTH * scale;
        float h = (float) VIRTUAL_WINDOW_HEIGHT * scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
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

package ch.m02.comet.pinball.prototype;

import java.awt.Rectangle;

import javax.swing.text.Position;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PinballPrototype implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	//private Sprite sprite;
	
	private Rectangle bucket;
	private CircleShape circle;
	private Fixture circleFixture;
	private Body circleBody;
	
	private static final int WINDOW_WIDTH= 480,WINDOW_HEIGHT = 800;;
	private World world;
	
	private static final float WORLD_TO_BOX = 0.01f, BOX_WORLD_TO = 100f;
	private Box2DDebugRenderer debugRender;
	
	
	@Override
	public void create() {		
		// Setting up a new world for simulating the play field
		// Vector2 param1 = Gravity in x plane (-10 is a downwards force like in real life)
		// Vector2 param2 = Gravity in y plane (-10 is a downwards force like in real life)
		world = new World(new Vector2(0,-10),true);
		
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,WINDOW_WIDTH,WINDOW_HEIGHT);
		
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/metallkugel.jpg"));
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		/*
		bucket = new Rectangle();
		bucket.x = WINDOW_WIDTH /2;
		bucket.y = 48;
		bucket.width = 48;
		bucket.height = 48;
		*/
		
		/* 
		 * Physics definition
		 */
		BodyDef bodyDef =  new BodyDef();
		// This thing should move so we set it dynamic. A floor is a static body
		bodyDef.type =  BodyType.DynamicBody;
		// starting point
		bodyDef.position.set(WINDOW_WIDTH/2,WINDOW_HEIGHT/2);
		
		circleBody = world.createBody(bodyDef);
		
		// Create a shape
		circle = new CircleShape();
		circle.setRadius(10f);
		
		// Create a fixture definition
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; // Make it bounce a little bit
		
		circleFixture = circleBody.createFixture(fixtureDef);
		
		
		/*
		 *  Static Bodies
		 */
		
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0,10));
		
		// Create body of ground
		
		Body groundBody = world.createBody(groundBodyDef);
		
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(camera.viewportWidth * 2, 10f);
		
		groundBody.createFixture(groundBox,0.0f);
		
		// This debugger is useful for testing purposes
		debugRender = new Box2DDebugRenderer();
		
		
		
		
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		//batch.begin();
		//batch.draw(texture, bucket.x, bucket.y);	
		//batch.end();
		/*
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		*/
		
		if(Gdx.input.isTouched()){
			/*
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
			camera.unproject(touchPos);
			*/
			
			circleBody.applyForceToCenter(0, 10000);
		}
		
		
		/*
		if(Gdx.input.isKeyPressed(Keys.LEFT))
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
			bucket.x += 200 * Gdx.graphics.getDeltaTime();
		
		if(bucket.x < 0)
			bucket.x = 0;
		if(bucket.x > WINDOW_WIDTH - bucket.width)
			bucket.x = WINDOW_WIDTH - bucket.width;
		
		//bucket.y -= 200 * Gdx.graphics.getDeltaTime();
		
		if(bucket.y <= 0)
			bucket.y = WINDOW_HEIGHT-48;
		
		*/
		
		// update actors and sprites
		/*
		Iterator<Body> bodies = world.getBodies();
		
		while(bodies.hasNext()){
			Body b = bodies.next();
			Entity e = b.getUserData();
			
			e.setPosition(b.getPosition().x,b.getPosition().y);
			
			e.setRotation(MathUtils.radiansToDegrees*b.getAngle());
		}
		
		*/
		
		
		// Render physics should be called before physical rendering
		debugRender.render(world, camera.combined);
		
		// step/update the world
		// param1 = timestep 1/60 of a second...
		// param2 = velocityIterations
		// param2 = positionIterations
		world.step(1/60f,6,2);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

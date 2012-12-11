package ch.m02.comet.pinball.graphics;

import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BallGraphics implements GraphicsObject{
	private Texture texture;
	private Sprite sprite;
	private PhysicsObject physicsObject;
	
	public BallGraphics(){
		texture = new Texture(Gdx.files.internal("data/fussball.png"));
		}

	@Override
	public void init(PhysicsObject physicsObject) {
		this.physicsObject = physicsObject;
		//sprite.setSize(PhysicsDefinition.PINBALL_RADIUS,PhysicsDefinition.PINBALL_RADIUS);
		
		sprite = new Sprite(texture);
		sprite.setSize(PhysicsDefinition.PINBALL_RADIUS * PhysicsDefinition.BOX2D_TO_WORLD *2,PhysicsDefinition.PINBALL_RADIUS * PhysicsDefinition.BOX2D_TO_WORLD * 2);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		if(physicsObject == null){
			// uninitialized!!
			// TODO handle uninitialized physics object!
		}
			
		sprite.setPosition(physicsObject.getBody().getPosition().x * PhysicsDefinition.BOX2D_TO_WORLD - PhysicsDefinition.PINBALL_RADIUS * PhysicsDefinition.BOX2D_TO_WORLD, 
				physicsObject.getBody().getPosition().y* PhysicsDefinition.BOX2D_TO_WORLD- PhysicsDefinition.PINBALL_RADIUS * PhysicsDefinition.BOX2D_TO_WORLD);
		sprite.setRotation((float)(physicsObject.getBody().getAngle() * 180/ Math.PI));
		sprite.draw(spriteBatch);
	}

	@Override
	public void dispose() {
		texture.dispose();
	}
	
}

package ch.m02.comet.pinball.presentation.graphics;

import ch.m02.comet.pinball.physics.PhysicsObject;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ScoreDisplay implements GraphicsObject{
	private BitmapFont font;
	private Vector2 position;
	private String text;
	private long counter = 0;
	
	public ScoreDisplay(Vector2 position) {
		this.position = position;
		this.text = "Score!!!!";
	}
	
	@Override
	public void init(PhysicsObject physicsObject) {
		font = new BitmapFont();
		font.setColor(Color.YELLOW);
		//textWidth = font.getBounds(textToDisplay).width;
		//textHeight = font.getBounds(textToDisplay).height;
		//v = new Vector<Point>();
	}

	@Override
	public void draw(Camera camera, SpriteBatch spriteBatch) {
		text = "" + counter++;
		font.draw(spriteBatch, text, position.x-font.getBounds(text).width, position.y -font.getBounds(text).height);
	}

	@Override
	public void dispose() {
		font.dispose();
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	
}

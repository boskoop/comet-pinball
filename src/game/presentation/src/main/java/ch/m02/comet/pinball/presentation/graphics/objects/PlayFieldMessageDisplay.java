package ch.m02.comet.pinball.presentation.graphics.objects;

import ch.m02.comet.pinball.physics.PhysicsObject;
import ch.m02.comet.pinball.presentation.graphics.DisplayObject;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PlayFieldMessageDisplay implements GraphicsObject, DisplayObject {
	
	private BitmapFont font;
	private Vector2 position;
	private String text;
	
	public PlayFieldMessageDisplay(Vector2 position) {
		this.position = position;
		this.text = "Score!!!!";
	}
	
	@Override
	public void init(PhysicsObject physicsObject) {
		font = new BitmapFont();
		font.setColor(Color.YELLOW);
	}

	@Override
	public void draw(Camera camera, SpriteBatch spriteBatch) {
		font.draw(spriteBatch, text, position.x - font.getBounds(text).width,
				position.y - font.getBounds(text).height);
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	@Override
	public void displayValue(String value) {
		this.text = value;
	}
	
	
}

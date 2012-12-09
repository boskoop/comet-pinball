package ch.m02.comet.pinball.desktop;

import ch.m02.comet.pinball.PinballGame;
import ch.m02.comet.pinball.screens.GameScreen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "comet-pinball";
		cfg.useGL20 = true;
		cfg.width = GameScreen.WINDOW_WIDTH;
		cfg.height = GameScreen.WINDOW_HEIGHT;
		
		new LwjglApplication(new PinballGame(), cfg);
	}
	
}

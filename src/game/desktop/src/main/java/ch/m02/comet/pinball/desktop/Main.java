package ch.m02.comet.pinball.desktop;

import ch.m02.comet.pinball.PinballGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "comet-pinball";
		cfg.useGL20 = false;
		cfg.width = 512;
		cfg.height = 1024;
		
		new LwjglApplication(new PinballGame(), cfg);
	}
	
}

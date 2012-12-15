package ch.m02.comet.pinball.prototype;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "comet-pinball-prototype";
		cfg.useGL20 = true;
		cfg.width = 512;
		cfg.height = 1024;
		
		LwjglApplication application = new LwjglApplication(new PinballPrototype(), cfg);
		application.log("Main", "Prototype application started");
	}
	
}

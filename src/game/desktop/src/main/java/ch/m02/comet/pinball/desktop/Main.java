package ch.m02.comet.pinball.desktop;

import ch.m02.comet.pinball.MainApplication;
import ch.m02.comet.pinball.Pinball;
import ch.m02.comet.pinball.presentation.screens.GameScreen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main implements MainApplication {

	private LwjglApplication application;

	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

	private void run() {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "comet-pinball";
		cfg.useGL20 = true;
		cfg.width = GameScreen.WINDOW_WIDTH;
		cfg.height = GameScreen.WINDOW_HEIGHT;
		
		Pinball pinball = new Pinball(this);
		application = new LwjglApplication(pinball.getGame(), cfg);
	}

	@Override
	public void exit() {
		if (application != null) {
			application.exit();
		}
	}

}

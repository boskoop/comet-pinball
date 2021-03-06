package ch.m02.comet.pinball.desktop;

import uk.org.lidalia.sysoutslf4j.context.LogLevel;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;
import ch.m02.comet.pinball.MainApplication;
import ch.m02.comet.pinball.Pinball;
import ch.m02.comet.pinball.presentation.screens.GameScreenImpl;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main implements MainApplication {

	private LwjglApplication application;

	public static void main(String[] args) {
		SysOutOverSLF4J.sendSystemOutAndErrToSLF4J(LogLevel.DEBUG, LogLevel.ERROR);
		Main m = new Main();
		m.run();
	}

	private void run() {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "comet-pinball";
		cfg.useGL20 = true;
		cfg.width = GameScreenImpl.VIRTUAL_WINDOW_WIDTH;
		cfg.height = GameScreenImpl.VIRTUAL_WINDOW_HEIGHT;
		
		Pinball pinball = new Pinball(this);
		pinball.start();
		application = new LwjglApplication(pinball.getGame(), cfg);
	}

	@Override
	public void exit() {
		if (application != null) {
			application.exit();
		}
	}

}

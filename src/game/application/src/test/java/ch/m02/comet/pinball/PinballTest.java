package ch.m02.comet.pinball;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.m02.comet.pinball.MainApplication;
import ch.m02.comet.pinball.Pinball;

import com.badlogic.gdx.Game;

public class PinballTest {

	@Test
	public void testPinballGameSingleton() {
		TestMainApplication application = new TestMainApplication();
		Pinball pinball = new Pinball(application);
		pinball.init();
		
		Game game1 = pinball.getGame();
		Game game2 = pinball.getGame();
		
		assertSame(game1, game2);
	}
	
	private static class TestMainApplication implements MainApplication {

		@Override
		public void exit() {
			// Does nothing
		}
		
	}

}

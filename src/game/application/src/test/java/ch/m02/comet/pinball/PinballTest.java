package ch.m02.comet.pinball;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import ch.m02.comet.pinball.core.ApplicationContext;

import com.badlogic.gdx.Game;

public class PinballTest {

	@Test
	public void testPinballGameSingleton() {
		TestMainApplication application = new TestMainApplication();
		Pinball pinball = new Pinball(application);
		
		Game game1 = pinball.getGame();
		Game game2 = pinball.getGame();
		
		assertSame(game1, game2);
	}
	
	@Test
	public void testPinballGameApplicationContext() {
		TestMainApplication application = new TestMainApplication();
		Pinball pinball = new Pinball(application);
		
		ApplicationContext context = pinball.getApplicationContext();
		Game game1 = context.getComponentContainer().getComponent(Game.class);
		assertNotNull(game1);
		
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

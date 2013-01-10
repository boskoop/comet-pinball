package ch.m02.comet.pinball;

import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoBuilder;
import org.picocontainer.behaviors.OptInCaching;
import org.picocontainer.injectors.ProviderAdapter;

import ch.m02.comet.pinball.game.ApplicationProvider;
import ch.m02.comet.pinball.game.PinballGame;

import com.badlogic.gdx.Game;

public class Pinball {

	private MainApplication application;
	private MutablePicoContainer container;

	public Pinball(MainApplication application) {
		this.application = application;
		container = new PicoBuilder().withBehaviors(new OptInCaching()).build();
	}
	
	public void init() {
		registerSingletons();
		registerPrototypes();
	}

	private void registerSingletons() {
		MutablePicoContainer singletonContainer = container.as(Characteristics.CACHE);
		singletonContainer.addAdapter(new ProviderAdapter(new ApplicationProvider(application)));
		singletonContainer.addComponent(PinballGame.class);
	}
	
	private void registerPrototypes() {
		// TODO Auto-generated method stub
		
	}

	public Game getGame() {
		return container.getComponent(PinballGame.class);
	}
}

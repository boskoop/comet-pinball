package ch.m02.comet.pinball.game;

import org.picocontainer.injectors.Provider;

import ch.m02.comet.pinball.MainApplication;

public class ApplicationProvider implements Provider {

	private MainApplication application;

	public ApplicationProvider(MainApplication application) {
		this.application = application;
	}
	
	public MainApplication provide() {
		return application;
	}
	
}

package ch.m02.comet.pinball.core.internal;

import javax.inject.Inject;

import org.picocontainer.PicoContainer;

import ch.m02.comet.pinball.core.ApplicationContext;

public class ApplicationContextImpl implements ApplicationContext {

	@Inject
	private PicoContainer container;

	@Override
	public PicoContainer getComponentContainer() {
		return container;
	}
	
}

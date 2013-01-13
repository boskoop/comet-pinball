package ch.m02.comet.pinball.adapter;

import java.lang.reflect.Type;

import org.picocontainer.PicoCompositionException;
import org.picocontainer.PicoContainer;
import org.picocontainer.adapters.AbstractAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.presentation.Display;
import ch.m02.comet.pinball.presentation.graphics.GraphicsDisplay;

public class PinballDisplayAdapter extends AbstractAdapter<Display> {

	private static final long serialVersionUID = 4271926519056125262L;
	
	private static final Logger log = LoggerFactory.getLogger(PinballDisplayAdapter.class);

	public PinballDisplayAdapter() {
		super(Display.class, GraphicsDisplay.class);
	}

	@Override
	public Display getComponentInstance(PicoContainer container, Type into)
			throws PicoCompositionException {
		Display display = container.getComponent(GraphicsDisplay.class);
		log.debug("returning display instance {}", display);
		return display;
	}

	@Override
	public void verify(PicoContainer container) throws PicoCompositionException {
	}

	@Override
	public String getDescriptor() {
		return "PinballDisplayAdapter";
	}

}

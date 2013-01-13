package ch.m02.comet.pinball.core.model.playfield;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.presentation.ElementFactory;

public interface PlayFieldElement {
	
	public int getId();

	public Vector getPosition();
	
	public ElementFactory getElementFactory(ApplicationContext context);
	
}

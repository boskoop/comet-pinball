package ch.m02.comet.pinball.core.model.playfield;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.presentation.playfield.ElementFactory;

public interface PlayFieldElement {
	
	public int getId();

	public Vector getPosition();
	
	public ElementFactory<? extends PlayFieldElement> getElementFactory(ApplicationContext context);
	
}

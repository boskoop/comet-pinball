package ch.m02.comet.pinball.presentation;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.playfield.PlayFieldElement;
import ch.m02.comet.pinball.core.presentation.PresentationManager;
import ch.m02.comet.pinball.core.presentation.playfield.ElementFactory;
import ch.m02.comet.pinball.core.presentation.screen.PinballScreen;
import ch.m02.comet.pinball.physics.PhysicsPlayField;

public class PinballPresentationManager implements PresentationManager {

	@Inject
	private ScreenManager screenManager;
	
	@Inject
	private PhysicsPlayField playField;
	
	@Inject
	private ApplicationContext context;
	
	private Map<Integer, PlayFieldElement> placedElements = new HashMap<Integer, PlayFieldElement>();
	
	@Override
	public void clearElements() {
		playField.clearField();
		placedElements.clear();
	}

	@Override
	public void placeElement(PlayFieldElement element) {
		if (placedElements.get(element.getId()) != null) {
			throw new IllegalArgumentException("Element with given id already exists on playfield: " + element.getId());
		}
		ElementFactory<? extends PlayFieldElement> elementFactory = element.getElementFactory(context);
		elementFactory.createAndPlacePlayFieldElement();
		placedElements.put(element.getId(), element);
	}

	@Override
	public void showScreen(Class<? extends PinballScreen> screen) {
		screenManager.changeScreenTo(screen);
	}

}

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
import ch.m02.comet.pinball.physics.ball.Ball;

public class PinballPresentationManager implements PresentationManager {

	@Inject
	private ApplicationContext context;

	private Map<Integer, PlayFieldElement> placedElements = new HashMap<Integer, PlayFieldElement>();

	@Override
	public void clearElements() {
		PhysicsPlayField playField = context.getComponentContainer()
				.getComponent(PhysicsPlayField.class);
		playField.clearField();
		placedElements.clear();
		Ball ball = context.getComponentContainer().getComponent(Ball.class);
		ball.resetBall();
	}

	@Override
	public void placeElement(PlayFieldElement element) {
		if (placedElements.get(element.getId()) != null) {
			throw new IllegalArgumentException(
					"Element with given id already exists on playfield: "
							+ element.getId());
		}
		ElementFactory<? extends PlayFieldElement> elementFactory = element
				.getElementFactory(context);
		elementFactory.createAndPlacePlayFieldElement();
		placedElements.put(element.getId(), element);
	}

	@Override
	public void showScreen(Class<? extends PinballScreen> screen) {
		ScreenManager screenManager = context.getComponentContainer()
				.getComponent(ScreenManager.class);
		screenManager.changeScreenTo(screen);
	}
}

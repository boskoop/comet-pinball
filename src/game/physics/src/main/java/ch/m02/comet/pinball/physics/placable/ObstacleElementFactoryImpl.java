package ch.m02.comet.pinball.physics.placable;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldObstacleElement;
import ch.m02.comet.pinball.core.presentation.playfield.ObstacleElementFactory;
import ch.m02.comet.pinball.physics.PhysicPlayField;

public class ObstacleElementFactoryImpl implements ObstacleElementFactory {
	
	@Inject
	private PhysicPlayField playField;
	
	private PlayFieldObstacleElement element;

	@Override
	public void createAndPlacePlayFieldElement() {
		// TODO: implement
//		Vector2 position = new Vector2(element.getPosition().getX(), element
//				.getPosition().getY());
//		Bumper bumper = new Bumper(position, element.getRadius());
//		playField.placePhysicsObject(bumper);
	}

	@Override
	public void setPlayFieldElement(PlayFieldObstacleElement element) {
		this.element = element;
	}

	@Override
	public int getElementId() {
		return element.getId();
	}

}

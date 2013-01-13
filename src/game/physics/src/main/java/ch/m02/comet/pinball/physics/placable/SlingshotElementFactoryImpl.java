package ch.m02.comet.pinball.physics.placable;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldSlingshotElement;
import ch.m02.comet.pinball.core.presentation.playfield.SlingshotElementFactory;
import ch.m02.comet.pinball.physics.PhysicPlayField;

public class SlingshotElementFactoryImpl implements SlingshotElementFactory {
	
	@Inject
	private PhysicPlayField playField;
	
	private PlayFieldSlingshotElement element;

	@Override
	public void createAndPlacePlayFieldElement() {
		// TODO: implement
//		Vector2 position = new Vector2(element.getPosition().getX(), element
//				.getPosition().getY());
//		Bumper bumper = new Bumper(position, element.getRadius());
//		playField.placePhysicsObject(bumper);
	}

	@Override
	public void setPlayFieldElement(PlayFieldSlingshotElement element) {
		this.element = element;
	}

	@Override
	public int getElementId() {
		return element.getId();
	}

}

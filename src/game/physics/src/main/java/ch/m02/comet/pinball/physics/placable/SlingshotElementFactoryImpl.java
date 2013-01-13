package ch.m02.comet.pinball.physics.placable;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldSlingshotElement;
import ch.m02.comet.pinball.core.presentation.playfield.SlingshotElementFactory;
import ch.m02.comet.pinball.physics.PhysicPlayField;

import com.badlogic.gdx.math.Vector2;

public class SlingshotElementFactoryImpl implements SlingshotElementFactory {
	
	@Inject
	private PhysicPlayField playField;
	
	private PlayFieldSlingshotElement element;

	@Override
	public void createAndPlacePlayFieldElement() {
		Vector2 cornerPosition = new Vector2(element.getPosition().getX(), element
				.getPosition().getY());
		Vector2 cornerAVector = new Vector2(element.getCornerAVector().getX(), element
				.getCornerAVector().getY());
		Vector2 cornerBVector = new Vector2(element.getCornerBVector().getX(), element
				.getCornerBVector().getY());
		Slingshot slingshot = new Slingshot(cornerPosition, cornerAVector, cornerBVector);
		playField.placePhysicsObject(slingshot);
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

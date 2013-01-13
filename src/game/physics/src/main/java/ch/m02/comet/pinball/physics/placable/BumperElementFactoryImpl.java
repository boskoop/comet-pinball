package ch.m02.comet.pinball.physics.placable;

import javax.inject.Inject;

import com.badlogic.gdx.math.Vector2;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldBumperElement;
import ch.m02.comet.pinball.core.presentation.playfield.BumperElementFactory;
import ch.m02.comet.pinball.physics.PhysicPlayField;

public class BumperElementFactoryImpl implements BumperElementFactory {
	
	@Inject
	private PhysicPlayField playField;
	
	private PlayFieldBumperElement element;

	@Override
	public void createAndPlacePlayFieldElement() {
		Vector2 position = new Vector2(element.getPosition().getX(), element
				.getPosition().getY());
		Bumper bumper = new Bumper(position, element.getRadius());
		playField.placePhysicsObject(bumper);
	}

	@Override
	public void setPlayFieldElement(PlayFieldBumperElement element) {
		this.element = element;
	}

}
package ch.m02.comet.pinball.physics.placable;

import javax.inject.Inject;

import com.badlogic.gdx.math.Vector2;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.playfield.PlayFieldBumperElement;
import ch.m02.comet.pinball.core.presentation.playfield.BumperElementFactory;
import ch.m02.comet.pinball.physics.PhysicsPlayField;
import ch.m02.comet.pinball.physics.PhysicsDefinition;

public class BumperElementFactoryImpl implements BumperElementFactory {
	
	@Inject
	private PhysicsPlayField playField;
	
	@Inject
	private ApplicationContext context;
	
	private PlayFieldBumperElement element;

	@Override
	public void createAndPlacePlayFieldElement() {
		EventCreator creator = context.getComponentContainer().getComponent(EventCreator.class);
		creator.setId(element.getId());
		Vector2 position = new Vector2(element.getPosition().getX(), element
				.getPosition().getY());
		position.scl(PhysicsDefinition.METER_SCALE_FACTOR);
		Bumper bumper = new Bumper(creator, position, element.getRadius()
				* PhysicsDefinition.METER_SCALE_FACTOR);
		playField.placePhysicsObject(bumper);
	}

	@Override
	public void setPlayFieldElement(PlayFieldBumperElement element) {
		this.element = element;
	}

}

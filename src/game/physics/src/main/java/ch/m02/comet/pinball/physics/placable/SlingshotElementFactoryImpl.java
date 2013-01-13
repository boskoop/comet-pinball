package ch.m02.comet.pinball.physics.placable;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.playfield.PlayFieldSlingshotElement;
import ch.m02.comet.pinball.core.presentation.playfield.SlingshotElementFactory;
import ch.m02.comet.pinball.physics.PhysicsPlayField;
import ch.m02.comet.pinball.physics.PhysicsDefinition;

import com.badlogic.gdx.math.Vector2;

public class SlingshotElementFactoryImpl implements SlingshotElementFactory {
	
	@Inject
	private PhysicsPlayField playField;
	
	@Inject
	private ApplicationContext context;
	
	private PlayFieldSlingshotElement element;

	@Override
	public void createAndPlacePlayFieldElement() {
		EventCreator creator = context.getComponentContainer().getComponent(EventCreator.class);
		creator.setId(element.getId());
		Vector2 cornerPosition = new Vector2(element.getPosition().getX(), element
				.getPosition().getY());
		cornerPosition.mul(PhysicsDefinition.METER_SCALE_FACTOR);
		Vector2 cornerAVector = new Vector2(element.getCornerAVector().getX(), element
				.getCornerAVector().getY());
		cornerAVector.mul(PhysicsDefinition.METER_SCALE_FACTOR);
		Vector2 cornerBVector = new Vector2(element.getCornerBVector().getX(), element
				.getCornerBVector().getY());
		cornerBVector.mul(PhysicsDefinition.METER_SCALE_FACTOR);
		Slingshot slingshot = new Slingshot(creator, cornerPosition, cornerAVector, cornerBVector);
		playField.placePhysicsObject(slingshot);
	}

	@Override
	public void setPlayFieldElement(PlayFieldSlingshotElement element) {
		this.element = element;
	}

}

package ch.m02.comet.pinball.physics.placable;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.playfield.PlayFieldObstacleElement;
import ch.m02.comet.pinball.core.presentation.playfield.ObstacleElementFactory;
import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsPlayField;
import ch.m02.comet.pinball.physics.util.VectorUtil;

import com.badlogic.gdx.math.Vector2;

public class ObstacleElementFactoryImpl implements ObstacleElementFactory {
	
	@Inject
	private PhysicsPlayField playField;
	
	@Inject
	private ApplicationContext context;
	
	private PlayFieldObstacleElement element;

	@Override
	public void createAndPlacePlayFieldElement() {
		EventCreator creator = context.getComponentContainer().getComponent(EventCreator.class);
		creator.setId(element.getId());
		
		Vector2 position = new Vector2(element.getPosition().getX(), element
				.getPosition().getY());
		
		// convert vectors to libgdx vectors
		Vector2[] vertices = VectorUtil.convertToVertices(element.getVertices());
		
		// convert from cm's to meters
		for(int i =0; i < vertices.length; i++)
				vertices[i].scl(PhysicsDefinition.METER_SCALE_FACTOR);
		position.scl(PhysicsDefinition.METER_SCALE_FACTOR);
		
		// create and place obstacle
		Obstacle obstacle = new Obstacle(creator, position, vertices);
		playField.placePhysicsObject(obstacle);
	}

	@Override
	public void setPlayFieldElement(PlayFieldObstacleElement element) {
		this.element = element;
	}

}

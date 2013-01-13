package ch.m02.comet.pinball.physics.placable;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldObstacleElement;
import ch.m02.comet.pinball.core.presentation.playfield.ObstacleElementFactory;
import ch.m02.comet.pinball.physics.PhysicsDefinition;
import ch.m02.comet.pinball.physics.PhysicsPlayField;
import ch.m02.comet.pinball.physics.util.VectorUtil;

import com.badlogic.gdx.math.Vector2;

public class ObstacleElementFactoryImpl implements ObstacleElementFactory {
	
	@Inject
	private PhysicsPlayField playField;
	
	private PlayFieldObstacleElement element;

	@Override
	public void createAndPlacePlayFieldElement() {
		
		Vector2 position = new Vector2(element.getPosition().getX(), element
				.getPosition().getY());
		
		// convert vectors to libgdx vectors
		Vector2[] vertices = VectorUtil.convertToVertices(element.getVertices());
		
		// convert from cm's to meters
		for(int i =0; i < vertices.length; i++)
				vertices[i].mul(PhysicsDefinition.METER_SCALE_FACTOR);
		position.mul(PhysicsDefinition.METER_SCALE_FACTOR);
		
		// create and place obstacle
		Obstacle obstacle = new Obstacle(position, vertices);
		playField.placePhysicsObject(obstacle);
	}

	@Override
	public void setPlayFieldElement(PlayFieldObstacleElement element) {
		this.element = element;
	}

}

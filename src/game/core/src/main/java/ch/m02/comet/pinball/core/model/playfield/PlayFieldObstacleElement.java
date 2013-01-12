package ch.m02.comet.pinball.core.model.playfield;

import java.util.List;

public interface PlayFieldObstacleElement extends PlayFieldElement {

	public List<? extends Vector> getVertices();
	
}

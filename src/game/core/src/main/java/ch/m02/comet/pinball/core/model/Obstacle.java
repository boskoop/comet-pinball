package ch.m02.comet.pinball.core.model;

import java.io.Serializable;

public interface Obstacle extends Serializable {

	public ObstacleType getType();
	
	public Placement getPlacement();
	
}

package ch.m02.comet.pinball.core.model;

import java.io.Serializable;
import java.util.List;

public interface PlayField extends Serializable {
	
	public String getName();

	public List<Obstacle> getObstacles();
	
	public List<GameRule> getGameRules();
	
}

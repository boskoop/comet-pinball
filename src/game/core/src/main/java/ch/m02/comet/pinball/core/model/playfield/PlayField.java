package ch.m02.comet.pinball.core.model.playfield;

import java.util.List;

public interface PlayField {
	
	public String getName();

	public List<PlayFieldElement> getObstacles();
	
	public List<PlayFieldRule> getGameRules();
	
}

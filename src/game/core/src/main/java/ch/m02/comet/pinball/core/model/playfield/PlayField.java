package ch.m02.comet.pinball.core.model.playfield;

import java.util.List;

public interface PlayField {
	
	public String getName();

	public List<? extends PlayFieldElement> getElements();
	
	public List<? extends PlayFieldRule> getGameRules();
	
}

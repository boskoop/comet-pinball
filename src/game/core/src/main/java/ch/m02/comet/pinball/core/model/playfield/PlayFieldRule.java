package ch.m02.comet.pinball.core.model.playfield;

import java.util.List;

public interface PlayFieldRule {

	public Class<? extends Rule> getClassName();
	
	public List<Integer> getParameters();
	
}

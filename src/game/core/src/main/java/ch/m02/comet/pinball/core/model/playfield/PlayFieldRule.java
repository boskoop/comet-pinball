package ch.m02.comet.pinball.core.model.playfield;

import java.util.List;

public interface PlayFieldRule {

	public Class<?> getClassName();
	
	public List<Integer> getParameters();
	
}

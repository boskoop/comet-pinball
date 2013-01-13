package ch.m02.comet.pinball.core.model.playfield;

import java.util.List;

public interface Rule {

	public void handleHit(int id);
	
	public void init(List<Integer> paramters);
	
}

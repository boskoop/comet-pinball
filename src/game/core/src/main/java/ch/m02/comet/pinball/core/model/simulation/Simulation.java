package ch.m02.comet.pinball.core.model.simulation;

import java.io.Serializable;
import java.util.List;

public interface Simulation extends Serializable {

	public List<? extends Player> getPlayer();
	
	public PlayFieldId getPlayFieldId();
	
	public Score getScore();
	
	public List<? extends Game> getGames();
	
}
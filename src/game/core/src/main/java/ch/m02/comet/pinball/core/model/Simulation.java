package ch.m02.comet.pinball.core.model;

import java.io.Serializable;
import java.util.List;

public interface Simulation extends Serializable {

	public List<Player> getPlayer();
	
	public PlayField getPlayField();
	
	public Score getScore();
	
	public List<Game> getGames();
	
}
package ch.m02.comet.pinball.core.presentation;

import java.util.List;

import ch.m02.comet.pinball.core.model.simulation.Score;


public interface Display {

	public void displayScore(int score);
	
	public void dispayMessage(String message);
	
	public void setHighscores(List<? extends Score> list);

	public void setHighscore(int scoreValue);
	
}

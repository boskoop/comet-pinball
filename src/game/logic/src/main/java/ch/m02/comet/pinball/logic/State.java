package ch.m02.comet.pinball.logic;

public interface State {

	public void startNewSimulation();
	
	public void splashFinished();
	
	public void gameOver();
	
	public void showHighscores();
	
}

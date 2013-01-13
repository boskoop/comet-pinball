package ch.m02.comet.pinball.logic;

public interface State {

	public void startNewSimulation();
	
	public void showMainMenu();
	
	public void gameOver();

	public void handleBallHitsId(int id);
	
	public void showHighscores();
	
	public void ballPlunged();
	
}

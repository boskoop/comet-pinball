package ch.m02.comet.pinball.presentation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.model.simulation.Score;
import ch.m02.comet.pinball.presentation.graphics.DisplayObject;
import ch.m02.comet.pinball.presentation.graphics.GraphicsDisplay;
import ch.m02.comet.pinball.presentation.screens.HighscoreScreenImpl;

public class PinballDisplay implements GraphicsDisplay {
	
	private static final Logger log = LoggerFactory.getLogger(PinballDisplay.class);

	private DisplayObject scoreDisplay;
	private DisplayObject messageDisplay;
	private HighscoreScreenImpl highscoreScreen;

	@Override
	public void registerScoreDisplay(DisplayObject scoreDisplay) {
		this.scoreDisplay = scoreDisplay;
	}
	
	@Override
	public void registerMessageDisplay(DisplayObject messageDisplay) {
		this.messageDisplay = messageDisplay;
	}
	
	@Override
	public void registerHighscoreScreen(HighscoreScreenImpl highscoreScreen) {
		this.highscoreScreen = highscoreScreen;
	}
	
	@Override
	public void setHighscores(List<? extends Score> highscores) {
		if (highscoreScreen != null){
			highscoreScreen.setHighscores(highscores);
			} else {
				log.warn("No highscore screen registered, ignoring!");
			}
	}
	
	@Override
	public void displayScore(int score) {
		if (scoreDisplay != null){
		scoreDisplay.displayValue(Integer.toString(score));
		} else {
			log.warn("No score display registered, ignoring!");
		}
	}

	@Override
	public void dispayMessage(String message) {
		if (messageDisplay != null){
			messageDisplay.displayValue(message);
		} else {
			log.warn("No message display registered, ignoring!");
		}
	}

}

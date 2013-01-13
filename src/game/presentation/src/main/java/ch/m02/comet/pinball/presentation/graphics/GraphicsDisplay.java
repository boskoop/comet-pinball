package ch.m02.comet.pinball.presentation.graphics;

import ch.m02.comet.pinball.core.presentation.Display;
import ch.m02.comet.pinball.core.presentation.screen.PlayerNameScreen;
import ch.m02.comet.pinball.presentation.screens.HighscoreScreenImpl;

public interface GraphicsDisplay extends Display {

	public void registerScoreDisplay(DisplayObject scoreDisplay);

	public void registerMessageDisplay(DisplayObject messageDisplay);

	public void registerHighscoreScreen(HighscoreScreenImpl highscoreScreen);

	public void registerPlayerNameScreen(PlayerNameScreen playerNameScreen);
	
}

package ch.m02.comet.pinball.presentation.graphics;

import ch.m02.comet.pinball.core.presentation.Display;

public interface GraphicsDisplay extends Display {

	public void registerScoreDisplay(DisplayObject scoreDisplay);

	public void registerMessageDisplay(DisplayObject messageDisplay);
	
}

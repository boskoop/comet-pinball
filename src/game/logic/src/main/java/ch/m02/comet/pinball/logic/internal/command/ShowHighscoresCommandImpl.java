package ch.m02.comet.pinball.logic.internal.command;

import ch.m02.comet.pinball.core.logic.command.ShowHighscoresCommand;

public class ShowHighscoresCommandImpl extends AbstractCommand implements ShowHighscoresCommand{

	@Override
	public void execute() {
		getReceiver().getCurrentState().showHighscores();	
	}

}

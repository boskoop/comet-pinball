package ch.m02.comet.pinball.logic.command;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.logic.command.NewGameCommand;
import ch.m02.comet.pinball.logic.LogicManager;

public class NewGameCommandImpl implements NewGameCommand {

	@Inject
	private LogicManager receiver;
	
	@Override
	public void execute() {
		receiver.startNewGame();
	}

}

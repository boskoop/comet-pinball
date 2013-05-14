package ch.m02.comet.pinball.logic.command;

import ch.m02.comet.pinball.core.logic.command.BallResetCommand;

public class BallResetCommandImpl extends AbstractCommand implements BallResetCommand {

	@Override
	public void execute() {
		getReceiver().getCurrentState().ballReset();
	}

}

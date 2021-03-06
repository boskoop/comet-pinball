package ch.m02.comet.pinball.logic.command;

import ch.m02.comet.pinball.core.logic.command.BallDownCommand;

public class BallDownCommandImpl extends AbstractCommand implements BallDownCommand {
	
	@Override
	public void execute() {
		getReceiver().getCurrentState().ballDown();
	}

}

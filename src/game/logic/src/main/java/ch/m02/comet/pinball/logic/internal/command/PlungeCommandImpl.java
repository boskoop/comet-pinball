package ch.m02.comet.pinball.logic.internal.command;

import ch.m02.comet.pinball.core.logic.command.PlungeCommand;

public class PlungeCommandImpl extends AbstractCommand implements PlungeCommand {
	
	@Override
	public void execute() {
		getReceiver().getCurrentState().ballPlunged();
	}

}

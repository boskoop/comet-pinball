package ch.m02.comet.pinball.logic.internal.command;

import ch.m02.comet.pinball.core.logic.command.SplashFinishedCommand;

public class SplashFinishedCommandImpl extends AbstractCommand implements SplashFinishedCommand {

	@Override
	public void execute() {
		getReceiver().getCurrentState().showMainMenu();
	}

}

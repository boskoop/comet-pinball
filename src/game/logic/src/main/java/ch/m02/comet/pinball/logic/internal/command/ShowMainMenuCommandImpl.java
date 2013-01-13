package ch.m02.comet.pinball.logic.internal.command;

import ch.m02.comet.pinball.core.logic.command.ShowMainMenuCommand;

public class ShowMainMenuCommandImpl extends AbstractCommand implements ShowMainMenuCommand {

	@Override
	public void execute() {
		getReceiver().getCurrentState().showMainMenu();
	}

}

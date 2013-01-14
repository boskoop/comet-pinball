package ch.m02.comet.pinball.logic.internal.command;

import ch.m02.comet.pinball.core.logic.command.SavePlayerNameCommand;

public class SavePlayerNameCommandImpl extends AbstractCommand implements SavePlayerNameCommand {

	private String playerName;
	
	@Override
	public void execute() {
		getReceiver().getCurrentState().savePlayerName(playerName);
	}

	@Override
	public void setPlayerName(String name) {
		playerName = name;
	}

}

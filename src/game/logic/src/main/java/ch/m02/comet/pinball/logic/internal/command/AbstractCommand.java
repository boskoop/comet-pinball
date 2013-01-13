package ch.m02.comet.pinball.logic.internal.command;

import javax.inject.Inject;

import ch.m02.comet.pinball.core.logic.command.Command;
import ch.m02.comet.pinball.logic.LogicManager;

public abstract class AbstractCommand implements Command {

	@Inject
	private LogicManager receiver;
	
	protected LogicManager getReceiver() {
		return receiver;
	}

}

package ch.m02.comet.pinball.logic.internal.command;

import ch.m02.comet.pinball.core.logic.command.NewSimulationCommand;

public class NewSimulationCommandImpl extends AbstractCommand implements NewSimulationCommand {
	
	@Override
	public void execute() {
		getReceiver().getCurrentState().startNewSimulation();
	}

}

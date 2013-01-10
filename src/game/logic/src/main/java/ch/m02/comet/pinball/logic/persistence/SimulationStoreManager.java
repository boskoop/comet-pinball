package ch.m02.comet.pinball.logic.persistence;


public interface SimulationStoreManager {

	public SimulationStore getSimulationStore();

	public void saveSimulationStore(SimulationStore store);
	
}

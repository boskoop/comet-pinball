package ch.m02.comet.pinball.logic.persistence.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ch.m02.comet.pinball.logic.model.simulation.SimulationPdo;
import ch.m02.comet.pinball.logic.persistence.SimulationStore;

public class SimulationStoreManagerTest {

	private SimulationStoreManager em;

	@Before
	public void clean() {
		cleanStore();
		em = new SimulationStoreManager();
		em.initializePersistence();
	}

	@AfterClass
	public static void cleanStore() {
		File persistentFile = new File(
				SimulationStoreManager.PERSISTENT_FILE_NAME);
		if (persistentFile.exists()) {
			assertTrue(persistentFile.delete());
		}
		assertFalse(persistentFile.exists());
	}

	@Test
	public void testInitializePersistence() {
		SimulationStore store = em.getSimulationStore();
		assertTrue(store.getSimulations().isEmpty());
	}
	
	@Test
	public void testSaveStore() {
		SimulationStore store = new SimulationStore();
		store.addSimulation(new SimulationPdo());
		em.saveSimulationStore(store);
		
		SimulationStore managerStore = em.getSimulationStore();
		assertEquals(store, managerStore);
		assertEquals(1, managerStore.getSimulations().size());
	}
	
	@Test
	public void testSaveAndLoadStore() {
		SimulationStore store = new SimulationStore();
		store.addSimulation(new SimulationPdo());
		em.saveSimulationStore(store);

		SimulationStoreManager em2 = new SimulationStoreManager();
		em2.initializePersistence();
		
		SimulationStore managerStore = em2.getSimulationStore();
		assertEquals(1, managerStore.getSimulations().size());
	}

}

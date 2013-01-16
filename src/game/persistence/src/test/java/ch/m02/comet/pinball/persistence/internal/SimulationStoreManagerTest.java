package ch.m02.comet.pinball.persistence.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ch.m02.comet.pinball.persistence.internal.SimulationStoreManager;
import ch.m02.comet.pinball.persistence.model.simulation.SimulationPdo;
import ch.m02.comet.pinball.persistence.model.simulation.SimulationStorePdo;

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
		SimulationStorePdo store = em.getSimulationStore();
		assertTrue(store.getSimulations().isEmpty());
	}
	
	@Test
	public void testSaveStore() {
		SimulationStorePdo store = new SimulationStorePdo();
		store.addSimulation(new SimulationPdo());
		em.saveSimulationStore(store);
		
		SimulationStorePdo managerStore = em.getSimulationStore();
		assertEquals(store, managerStore);
		assertEquals(1, managerStore.getSimulations().size());
	}
	
	@Test
	public void testSaveAndLoadStore() {
		SimulationStorePdo store = new SimulationStorePdo();
		store.addSimulation(new SimulationPdo());
		em.saveSimulationStore(store);

		SimulationStoreManager em2 = new SimulationStoreManager();
		em2.initializePersistence();
		
		SimulationStorePdo managerStore = em2.getSimulationStore();
		assertEquals(1, managerStore.getSimulations().size());
	}

}

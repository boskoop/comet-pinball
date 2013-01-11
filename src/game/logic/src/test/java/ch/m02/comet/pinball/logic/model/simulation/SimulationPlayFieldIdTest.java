package ch.m02.comet.pinball.logic.model.simulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class SimulationPlayFieldIdTest {

	@Test
	public void testSetPlayFieldId() {
		PlayFieldIdPdo playFieldId = new PlayFieldIdPdo();
		SimulationPdo simulation = new SimulationPdo();

		assertTrue(playFieldId.getSimulations().isEmpty());
		assertNull(simulation.getScore());

		simulation.setPlayFieldId(playFieldId);

		assertEquals(1, playFieldId.getSimulations().size());
		assertTrue(playFieldId.getSimulations().contains(simulation));
		assertSame(playFieldId, simulation.getPlayFieldId());
	}

	@Test
	public void testUnsetPlayFieldId() {
		PlayFieldIdPdo playFieldId = new PlayFieldIdPdo();
		SimulationPdo simulation = new SimulationPdo();
		simulation.setPlayFieldId(playFieldId);

		simulation.setPlayFieldId(null);
		assertNull(simulation.getPlayFieldId());
		assertTrue(playFieldId.getSimulations().isEmpty());
	}

	@Test
	public void testChangePlayFieldId() {
		PlayFieldIdPdo playFieldId = new PlayFieldIdPdo();
		SimulationPdo simulation = new SimulationPdo();
		simulation.setPlayFieldId(playFieldId);

		PlayFieldIdPdo newPlayFieldId = new PlayFieldIdPdo();

		simulation.setPlayFieldId(newPlayFieldId);
		assertTrue(playFieldId.getSimulations().isEmpty());
		assertEquals(1, newPlayFieldId.getSimulations().size());
		assertTrue(newPlayFieldId.getSimulations().contains(simulation));
		assertSame(newPlayFieldId, simulation.getPlayFieldId());
	}

	@Test
	public void testAddTwoSimulations() {
		PlayFieldIdPdo playFieldId = new PlayFieldIdPdo();
		SimulationPdo simulation1 = new SimulationPdo();
		SimulationPdo simulation2 = new SimulationPdo();
		simulation1.setPlayFieldId(playFieldId);
		simulation2.setPlayFieldId(playFieldId);

		assertEquals(2, playFieldId.getSimulations().size());
		assertTrue(playFieldId.getSimulations().contains(simulation1));
		assertSame(playFieldId, simulation1.getPlayFieldId());
		assertTrue(playFieldId.getSimulations().contains(simulation2));
		assertSame(playFieldId, simulation2.getPlayFieldId());
	}

	@Test
	public void testSetPlayFieldTwice() {
		PlayFieldIdPdo playFieldId = new PlayFieldIdPdo();
		SimulationPdo simulation = new SimulationPdo();
		simulation.setPlayFieldId(playFieldId);
		simulation.setPlayFieldId(playFieldId);

		assertEquals(1, playFieldId.getSimulations().size());
		assertTrue(playFieldId.getSimulations().contains(simulation));
		assertSame(playFieldId, simulation.getPlayFieldId());
	}

	@Test
	public void testRemoveSimulation() {
		PlayFieldIdPdo playFieldId = new PlayFieldIdPdo();
		SimulationPdo simulation1 = new SimulationPdo();
		SimulationPdo simulation2 = new SimulationPdo();
		simulation1.setPlayFieldId(playFieldId);
		simulation2.setPlayFieldId(playFieldId);

		simulation1.setPlayFieldId(null);

		assertEquals(1, playFieldId.getSimulations().size());
		assertFalse(playFieldId.getSimulations().contains(simulation1));
		assertNull(simulation1.getPlayFieldId());
		assertTrue(playFieldId.getSimulations().contains(simulation2));
		assertSame(playFieldId, simulation2.getPlayFieldId());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSimulationsUnmodifiable() {
		PlayFieldIdPdo playFieldId = null;
		try {
			playFieldId = new PlayFieldIdPdo();
			SimulationPdo simulation = new SimulationPdo();
			simulation.setPlayFieldId(playFieldId);
		} catch (Exception e) {
			fail();
		}
		playFieldId.getSimulations().add(new SimulationPdo());
	}

}

package ch.m02.comet.pinball.persistence.model.simulation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.m02.comet.pinball.persistence.model.simulation.PlayerPdo;
import ch.m02.comet.pinball.persistence.model.simulation.SimulationPdo;

public class SimulationPlayerTest {

	@Test
	public void testSetPlayer() {
		PlayerPdo player = new PlayerPdo();
		SimulationPdo simulation = new SimulationPdo();

		assertTrue(player.getSimulations().isEmpty());
		assertNull(simulation.getScore());

		simulation.setPlayer(player);

		assertEquals(1, player.getSimulations().size());
		assertTrue(player.getSimulations().contains(simulation));
		assertSame(player, simulation.getPlayer());
	}

	@Test
	public void testUnsetPlayer() {
		PlayerPdo player = new PlayerPdo();
		SimulationPdo simulation = new SimulationPdo();
		simulation.setPlayer(player);

		simulation.setPlayer(null);
		assertNull(simulation.getPlayer());
		assertTrue(player.getSimulations().isEmpty());
	}

	@Test
	public void testChangePlayer() {
		PlayerPdo player = new PlayerPdo();
		SimulationPdo simulation = new SimulationPdo();
		simulation.setPlayer(player);

		PlayerPdo newPlayer = new PlayerPdo();

		simulation.setPlayer(newPlayer);
		assertTrue(player.getSimulations().isEmpty());
		assertEquals(1, newPlayer.getSimulations().size());
		assertTrue(newPlayer.getSimulations().contains(simulation));
		assertSame(newPlayer, simulation.getPlayer());
	}

	@Test
	public void testAddTwoSimulations() {
		PlayerPdo player = new PlayerPdo();
		SimulationPdo simulation1 = new SimulationPdo();
		SimulationPdo simulation2 = new SimulationPdo();
		simulation1.setPlayer(player);
		simulation2.setPlayer(player);

		assertEquals(2, player.getSimulations().size());
		assertTrue(player.getSimulations().contains(simulation1));
		assertSame(player, simulation1.getPlayer());
		assertTrue(player.getSimulations().contains(simulation2));
		assertSame(player, simulation2.getPlayer());
	}

	@Test
	public void testSetPlayerTwice() {
		PlayerPdo player = new PlayerPdo();
		SimulationPdo simulation = new SimulationPdo();
		simulation.setPlayer(player);
		simulation.setPlayer(player);

		assertEquals(1, player.getSimulations().size());
		assertTrue(player.getSimulations().contains(simulation));
		assertSame(player, simulation.getPlayer());
	}

	@Test
	public void testRemoveSimulation() {
		PlayerPdo player = new PlayerPdo();
		SimulationPdo simulation1 = new SimulationPdo();
		SimulationPdo simulation2 = new SimulationPdo();
		simulation1.setPlayer(player);
		simulation2.setPlayer(player);

		simulation1.setPlayer(null);

		assertEquals(1, player.getSimulations().size());
		assertFalse(player.getSimulations().contains(simulation1));
		assertNull(simulation1.getPlayer());
		assertTrue(player.getSimulations().contains(simulation2));
		assertSame(player, simulation2.getPlayer());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSimulationsUnmodifiable() {
		PlayerPdo player = null;
		try {
			player = new PlayerPdo();
			SimulationPdo simulation = new SimulationPdo();
			simulation.setPlayer(player);
		} catch (Exception e) {
			fail();
		}
		player.getSimulations().add(new SimulationPdo());
	}

}

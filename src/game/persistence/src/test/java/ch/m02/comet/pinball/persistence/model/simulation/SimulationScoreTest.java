package ch.m02.comet.pinball.persistence.model.simulation;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.m02.comet.pinball.persistence.model.simulation.ScorePdo;
import ch.m02.comet.pinball.persistence.model.simulation.SimulationPdo;

public class SimulationScoreTest {

	@Test
	public void testSetScore() {
		ScorePdo score = new ScorePdo();
		SimulationPdo simulation = new SimulationPdo();
		
		assertNull(score.getSimulation());
		assertNull(simulation.getScore());
		
		simulation.setScore(score);
		
		assertNotNull(score.getSimulation());
		assertSame(simulation, score.getSimulation());
		assertSame(score, simulation.getScore());
	}

	@Test
	public void testUnsetScore() {
		ScorePdo score = new ScorePdo();
		SimulationPdo simulation = new SimulationPdo();
		simulation.setScore(score);
		
		simulation.setScore(null);
		assertNull(simulation.getScore());
		assertNull(score.getSimulation());
	}

	@Test
	public void testChangeScore() {
		ScorePdo score = new ScorePdo();
		SimulationPdo simulation = new SimulationPdo();
		simulation.setScore(score);
		
		ScorePdo newScore = new ScorePdo();
		
		simulation.setScore(newScore);
		assertNull(score.getSimulation());
		assertNotNull(newScore.getSimulation());
		assertSame(simulation, newScore.getSimulation());
		assertSame(newScore, simulation.getScore());
	}

}

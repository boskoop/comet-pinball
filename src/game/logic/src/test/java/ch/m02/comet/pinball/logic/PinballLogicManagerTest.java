package ch.m02.comet.pinball.logic;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ch.m02.comet.pinball.logic.internal.PinballLogicManager;


public class PinballLogicManagerTest {

	@Test
	public void testPinballLogic() {
		PinballLogicManager logic = new PinballLogicManager();
		assertNotNull(logic);
	}
}

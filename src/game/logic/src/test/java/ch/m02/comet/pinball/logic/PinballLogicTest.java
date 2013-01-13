package ch.m02.comet.pinball.logic;

import static org.junit.Assert.assertNotNull;
import ch.m02.comet.pinball.logic.internal.PinballLogicManager;


public class PinballLogicTest {

	public void testPinballLogic() {
		PinballLogicManager logic = new PinballLogicManager();
		assertNotNull(logic);
	}
}

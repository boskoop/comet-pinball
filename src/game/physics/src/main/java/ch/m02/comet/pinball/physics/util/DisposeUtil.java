package ch.m02.comet.pinball.physics.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Shape;

public class DisposeUtil {

	private DisposeUtil() {
		// utility class
	}

	/**
	 * Safely disposes a Shape, catches any Exception which occurs and logs it.
	 * 
	 * @param s
	 *            the shape to dispose
	 */
	public static void safelyDispose(Shape s) {
		if (s != null) {
			try {
				s.dispose();
			} catch (RuntimeException e) {
				Gdx.app.error(DisposeUtil.class.getCanonicalName(),
						"Disposing shape failed", e);
			}
		}
	}

}

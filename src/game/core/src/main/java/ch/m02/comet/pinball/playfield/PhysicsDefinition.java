package ch.m02.comet.pinball.playfield;

import com.badlogic.gdx.math.MathUtils;

/**
 * Box2d works best if your moving objects are around the size of 1 unit, which
 * should equal 1m in real (it uses meters, seconds, kilograms). Since a pinball
 * is around the size of 2.7cm, we scale our world up by a factor of 100 and use
 * cm instead of m. Therefore we always should always scale derived units from
 * meters to cm.
 * <p>
 * Examples:<br>
 * <code>1m = 100cm<br>
 * gravity: 9.81m/s^2 = 981 cm/s^2
 * density: 8000 kg/m^3 = 0.008 kg/cm^3</code>
 */
public interface PhysicsDefinition {

	// field size: 76cm x 140cm
	public static final float FIELD_WIDTH = 76.0f;
	public static final float FIELD_HEIGHT = 140.0f;

	// typical diameter = 2.7cm
	// pinball radius: 1.35cm
	public static final float PINBALL_RADIUS = 1.35f;

	// typical value ~6.5-7 degrees
	public static final float RAMP_ANGLE_DEGREES = 7.0f;
	public static final float EARTH_GRAVITY = -981.0f;
	public static final float RAMP_GRAVITY = EARTH_GRAVITY
			* MathUtils.sinDeg(RAMP_ANGLE_DEGREES);

}

package ch.m02.comet.pinball.physics;

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

	public static final float METER_SCALE_FACTOR = 10f;
	public static final float SQUARE_METER_SCALE_FACTOR = METER_SCALE_FACTOR * METER_SCALE_FACTOR;
	public static final float CUBE_METER_SCALE_FACTOR = SQUARE_METER_SCALE_FACTOR * METER_SCALE_FACTOR;
	
	// field size: 76cm x 140cm
	public static final float FIELD_WIDTH = 0.76f * METER_SCALE_FACTOR;
	public static final float FIELD_HEIGHT = 1.40f * METER_SCALE_FACTOR;
	
	public static final float BOX2D_TO_WORLD = 60;

	// typical diameter = 2.7cm
	// pinball radius: 1.35cm
	public static final float PINBALL_RADIUS = 0.0135f * METER_SCALE_FACTOR;
	

	// Steel has a density of 8000 kg/m^3
	public static final float STEEL_DENSITY = 8000f / CUBE_METER_SCALE_FACTOR;
	
	// Steel restitution coefficient
	public static final float STEEL_RESTITUTION = 0.56f;
	
	// Bumper
	public static final float BUMPER_RADIUS = 0.03f * METER_SCALE_FACTOR;
	public static final float BUMPER_FORCE = 20f * METER_SCALE_FACTOR;
	public static final float BUMPER_RESTITUTION = STEEL_RESTITUTION;
	
	// Slingshot
	//public static final float SLINGSHOT_SIZE = 0.05f * METER_SCALE_FACTOR;
	public static final float SLINGSHOT_FORCE = 30f * METER_SCALE_FACTOR;
	public static final float SLINGSHOT_RESTITUTION = STEEL_RESTITUTION;
	public static final float SLINGSHOT_DENSITY = STEEL_DENSITY;
	public static final float SLINGSHOT_CORNER_SIZE = 0.005f * METER_SCALE_FACTOR;

	// typical value ~6.5-7 degrees
	public static final float RAMP_ANGLE_DEGREES = 7.0f;
	public static final float EARTH_GRAVITY = -9.81f * METER_SCALE_FACTOR;
	public static final float RAMP_GRAVITY = EARTH_GRAVITY
			* MathUtils.sinDeg(RAMP_ANGLE_DEGREES);

}

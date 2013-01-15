package ch.m02.comet.pinball.physics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.core.config.PhysicsProperties;
import ch.m02.comet.pinball.physics.internal.PhysicsDefinitionLoader;

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
public enum PhysicsDefinition {
	
	INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(PhysicsDefinition.class);
	
	private boolean initialized = false;
	
	public void initialize(PhysicsDefinitionLoader loader) {
		Configuration config = loader.getConfiguration();
		pinballRadius = Float.valueOf(config.getProperty(PhysicsProperties.PINBALL_RADIUS))
				* METER_SCALE_FACTOR;
		rampAngleDegrees = Float.valueOf(config.getProperty(PhysicsProperties.RAMP_ANGLE_DEGREES));
		earthGravity = Float.valueOf(config.getProperty(PhysicsProperties.EARTH_GRAVITY))
				* METER_SCALE_FACTOR;
		rampGravity = earthGravity * MathUtils.sinDeg(rampAngleDegrees);
		initialized = true;
	}

	public static final float METER_SCALE_FACTOR = 10f;
	public static final float SQUARE_METER_SCALE_FACTOR = METER_SCALE_FACTOR * METER_SCALE_FACTOR;
	public static final float CUBE_METER_SCALE_FACTOR = SQUARE_METER_SCALE_FACTOR * METER_SCALE_FACTOR;
	
	// field size: 76cm x 140cm
	public static final float FIELD_WIDTH = 0.76f * METER_SCALE_FACTOR;
	public static final float FIELD_HEIGHT = 1.40f * METER_SCALE_FACTOR;
	
	public static final float BOX2D_TO_WORLD = 60;

	// typical diameter = 2.7cm
	// pinball radius: 1.35cm
	private float pinballRadius;
	
	public float getPinballRadius() {
		checkInitialized();
		return pinballRadius;
	}
	
	// Steel has a density of 8000 kg/m^3
	public static final float STEEL_DENSITY = 8000f / CUBE_METER_SCALE_FACTOR;
	
	// Steel restitution coefficient
	public static final float STEEL_RESTITUTION = 0.56f;
	
	// Bumper
	public static final float BUMPER_RADIUS = 0.03f * METER_SCALE_FACTOR;
	public static final float BUMPER_FORCE = 20f * METER_SCALE_FACTOR;
	public static final float BUMPER_RESTITUTION = STEEL_RESTITUTION;
	public static final float BUMPER_DENSITY = STEEL_DENSITY;
	
	// Obstacle
	public static final float OBSTACLE_RESTITUTION = STEEL_RESTITUTION;
	public static final float OBSTACLE_DENSITY = STEEL_DENSITY;
	
	// Slingshot
	public static final float SLINGSHOT_FORCE = 30f * METER_SCALE_FACTOR;
	public static final float SLINGSHOT_RESTITUTION = STEEL_RESTITUTION;
	public static final float SLINGSHOT_DENSITY = STEEL_DENSITY;
	public static final float SLINGSHOT_CORNER_SIZE = 0.005f * METER_SCALE_FACTOR;

	private float earthGravity;
	
	public float getEarthGravity() {
		checkInitialized();
		return earthGravity;
	}

	// typical value ~6.5-7 degrees
	private float rampAngleDegrees;
	
	public float getRampAngleDegrees() {
		checkInitialized();
		return rampAngleDegrees;
	}
	
	private float rampGravity;
	
	public float getRampGravity() {
		checkInitialized();
		return rampGravity;
	}
	
	private void checkInitialized() {
		if (!initialized) {
			log.warn("Physics definition has been called without beeing initialized.");
			log.warn("Caller: {}", Thread.currentThread().getStackTrace()[3]);
		}
	}

}

package ch.m02.comet.pinball.core.config;

public enum PhysicsProperties implements PropertiesEnum {

	PINBALL_RADIUS("physics.pinball.radius"),
	EARTH_GRAVITY("physics.earth.gravity"),
	RAMP_ANGLE_DEGREES("physics.ramp.angle.degrees");
	
	private String propertyName;
	
	private PhysicsProperties(String propertyName) {
		this.propertyName = propertyName;
	}
	
	@Override
	public String getPropertyName() {
		return propertyName;
	}

}

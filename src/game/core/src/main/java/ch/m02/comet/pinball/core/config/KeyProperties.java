package ch.m02.comet.pinball.core.config;

public enum KeyProperties implements PropertiesEnum {

	EXIT_GAME("key.game.exit"),
	BALL_UP("key.ball.up"),
	BALL_LEFT("key.ball.left"),
	BALL_RIGHT("key.ball.right"),
	PLUNGE("key.ball.plunge"),
	BALL_RESET("key.ball.reset"),
	LEFT_FLIPPER("key.flipper.left"),
	RIGHT_FLIPPER("key.flipper.right");
	
	private String propertyName;
	
	private KeyProperties(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
}

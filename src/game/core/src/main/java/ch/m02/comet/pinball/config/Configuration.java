package ch.m02.comet.pinball.config;

import java.util.Properties;

import com.badlogic.gdx.Gdx;

public enum Configuration {
	
	INSTANCE;
	
	private static final String PROPERTIES_FILE_NAME = "pinball.properties";
	
	private Properties properties;
	
	private Configuration() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
		} catch (Exception e) {
			Gdx.app.error("Configuration", "could not load properties from file " + PROPERTIES_FILE_NAME, e);
		}
	}
	
	private static final String DEBUG = "pinball.debug";
	private static final String SKIP_SPLASHSCREEN = "pinball.skip.splashscreen";
	
	public boolean isDebugEnabled() {
		String debug = properties.getProperty(DEBUG);
		if ("true".equals(debug)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Skip the splash screen. Makes it easier to debug the application
	 */
	public boolean skipSplashscreen() {
		String debug = properties.getProperty(SKIP_SPLASHSCREEN);
		if ("true".equals(debug)) {
			return true;
		}
		return false;
	}
}

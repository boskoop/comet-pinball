package ch.m02.comet.pinball.core.internal;

import java.util.Properties;

import javax.annotation.PostConstruct;

import ch.m02.comet.pinball.core.Configuration;

public class ConfigurationImpl implements Configuration {
	
	private static final String PROPERTIES_FILE_NAME = "pinball.properties";
	
	private Properties properties;
	
	@PostConstruct
	public void init() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
		} catch (Exception e) {
			System.err.println("Configuration could not load properties from file " + PROPERTIES_FILE_NAME);
//			Gdx.app.error("Configuration", "could not load properties from file " + PROPERTIES_FILE_NAME, e);
		}
	}
	
	private static final String DEBUG = "pinball.debug";
	private static final String SKIP_SPLASHSCREEN = "pinball.skip.splashscreen";
	
	@Override
	public boolean isDebugEnabled() {
		String debug = properties.getProperty(DEBUG);
		if ("true".equals(debug)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean skipSplashscreen() {
		String debug = properties.getProperty(SKIP_SPLASHSCREEN);
		if ("true".equals(debug)) {
			return true;
		}
		return false;
	}
}

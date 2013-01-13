package ch.m02.comet.pinball.core.internal;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.core.config.KeyProperties;

public class ConfigurationImpl implements Configuration {

	private static final Logger log = LoggerFactory
			.getLogger(ConfigurationImpl.class);

	private static final String PROPERTIES_FILE_NAME = "pinball.properties";

	private Properties properties;

	@PostConstruct
	public void init() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(
					PROPERTIES_FILE_NAME));
		} catch (Exception e) {
			log.error("Configuration could not load properties from file {}",
							PROPERTIES_FILE_NAME);
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public Boolean getBooleanProperty(String booleanPropertyKey) {
		String value = properties.getProperty(booleanPropertyKey);
		if (value == null) {
			log.info(
					"Could not load boolean property {}, not found in properties, returning null",
					booleanPropertyKey);
			return null;
		} else if ("true".equals(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public String getStringProperty(String stringPropertyKey) {
		String value = properties.getProperty(stringPropertyKey);
		if (value == null) {
			log.info(
					"Could not load string property {}, not found in properties, returning null",
					stringPropertyKey);
			return null;
		}
		return value;
	}

	@Override
	public Integer getIntegerProperty(String integerPropertyKey) {
		String value = properties.getProperty(integerPropertyKey);
		if (value == null) {
			log.info(
					"Could not load integer property {}, not found in properties, returning null",
					integerPropertyKey);
			return null;
		}
		Integer intValue = null;
		try {
			intValue = Integer.valueOf(value);
		} catch (NumberFormatException e) {
			log.error(
					"value for integer property {} was '{}' and could not be parsed to int, returning null.",
					integerPropertyKey, value);
			log.error(e.getMessage(), e);
		}
		return intValue;
	}
	
	@Override
	public String getKeyProperty(KeyProperties keyPropertyKey) {
		return getStringProperty(keyPropertyKey.getPropertyName());
	}
}

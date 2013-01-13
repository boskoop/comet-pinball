package ch.m02.comet.pinball.core.config;

public interface Configuration {

	/**
	 * @param booleanPropertyKey
	 *            {@link BooleanProperties}
	 */
	public Boolean getBooleanProperty(String booleanPropertyKey);

	/**
	 * @param stringPropertyKey
	 *            {@link StringProperties}
	 */
	public String getStringProperty(String stringPropertyKey);

	/**
	 * @param integerPropertyKey
	 *            {@link IntegerProperties}
	 */
	public Integer getIntegerProperty(String integerPropertyKey);

	/**
	 * @param keyPropertyKey
	 *            {@link KeyProperties}
	 */
	public String getKeyProperty(KeyProperties keyPropertyKey);

}

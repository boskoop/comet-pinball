package ch.m02.comet.pinball.core.config;

public interface Configuration {

	public boolean isDebugEnabled();
	
	/**
	 * Skip the splash screen. Makes it easier to debug the application
	 */
	public boolean skipSplashscreen();
	
}

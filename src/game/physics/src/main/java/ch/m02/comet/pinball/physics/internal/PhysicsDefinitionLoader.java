package ch.m02.comet.pinball.physics.internal;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.physics.PhysicsDefinition;


public class PhysicsDefinitionLoader {

	@Inject
	private Configuration configuration;
	
	@PostConstruct
	public void loadPhysicsDefinition() {
		PhysicsDefinition.INSTANCE.initialize(this);
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}

}

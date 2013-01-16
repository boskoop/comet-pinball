package ch.m02.comet.pinball.persistence.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.persistence.model.simulation.SimulationStorePdo;

class SimulationStoreManager {

	private static final Logger log = LoggerFactory
			.getLogger(SimulationStoreDaoImpl.class);

	static final String PERSISTENT_FILE_NAME = "pinball.ser";
	
	private SimulationStorePdo store;

	public void initializePersistence() {
		File persistentFile = new File(PERSISTENT_FILE_NAME);
		if (!persistentFile.exists()) {
			createNewEntityStore();
		}
		loadEntities();
	}

	private void createNewEntityStore() {
		log.info("Creating new simulation store");
		store = new SimulationStorePdo();
		save();
	}

	private void loadEntities() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(
					PERSISTENT_FILE_NAME));
				store = (SimulationStorePdo) in.readObject();
		} catch (Exception e) {
			log.error("Could not load simulation store!", e);
			throw new RuntimeException("Could not load simulation store!", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
		log.info("Loaded simulation store");
	}

	private void save() {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(
					PERSISTENT_FILE_NAME));
			out.writeObject(store);
		} catch (IOException e) {
			log.error("Could not save simulation store!", e);
			throw new RuntimeException("Could not save simulation store!", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
		log.info("Saved simulation store");
	}

	public SimulationStorePdo getSimulationStore() {
		return store;
	}
	
	public void saveSimulationStore(SimulationStorePdo store) {
		this.store = store;
		save();
	}
	
}

package ch.m02.comet.pinball.logic.persistence.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.logic.persistence.SimulationStoreManager;
import ch.m02.comet.pinball.logic.persistence.SimulationStore;

public class SimulationStoreManagerImpl implements SimulationStoreManager {

	private static final Logger log = LoggerFactory
			.getLogger(SimulationStoreManagerImpl.class);

	static final String PERSISTENT_FILE_NAME = "data.ser";
	
	private SimulationStore store;

	@PostConstruct
	public void initializePersistence() {
		File persistentFile = new File(PERSISTENT_FILE_NAME);
		if (!persistentFile.exists()) {
			createNewEntityStore();
		}
		loadEntities();
	}

	private void createNewEntityStore() {
		log.info("Creating new simulation store");
		store = new SimulationStore();
		save();
	}

	private void loadEntities() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(
					PERSISTENT_FILE_NAME));
			try {
				store = (SimulationStore) in.readObject();
			} finally {
				in.close();
			}
		} catch (ClassNotFoundException e) {
			log.error("Could not load simulation store!", e);
		} catch (ClassCastException e) {
			log.error("Could not load simulation store!", e);
		} catch (IOException e) {
			log.error("Could not load simulation store!", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("Could not close stream while loading simulation store!",
							e);
				}
			}
		}
		log.info("Loaded simulation store");
	}

	private void save() {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(
					PERSISTENT_FILE_NAME));
			try {
				out.writeObject(store);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			log.error("Could not save simulation store!", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("Could not close stream while saving simulation store!",
							e);
				}
			}
		}
		log.info("Saved simulation store");
	}

	@Override
	public SimulationStore getSimulationStore() {
		return store;
	}
	
	@Override
	public void saveSimulationStore(SimulationStore store) {
		this.store = store;
		save();
	}

}

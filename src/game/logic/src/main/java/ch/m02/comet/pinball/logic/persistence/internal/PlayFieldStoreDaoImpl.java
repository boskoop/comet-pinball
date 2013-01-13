package ch.m02.comet.pinball.logic.persistence.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.logic.model.playfield.PlayFieldPdo;
import ch.m02.comet.pinball.logic.model.playfield.PlayFieldStore;
import ch.m02.comet.pinball.logic.persistence.PlayFieldStoreDao;

public class PlayFieldStoreDaoImpl implements PlayFieldStoreDao {

	private static final Logger log = LoggerFactory.getLogger(PlayFieldStoreDaoImpl.class);
	
	static final String PERSISTENT_FILE_NAME = "playfields.xml";

	@PostConstruct
	public void initializePersistence() {
		File persistentFile = new File(PERSISTENT_FILE_NAME);
		if (!persistentFile.exists()) {
			throw new RuntimeException("playfield configuration file does not exist!");
		}
	}
	
	@Override
	public List<PlayFieldPdo> findPlayFields() {
		PlayFieldStore store = null;
		InputStream playfieldStream = null;
		try {
			playfieldStream = new FileInputStream(new File(PERSISTENT_FILE_NAME));
			JAXBContext context = JAXBContext.newInstance(PlayFieldStore.class);
			Unmarshaller u = context.createUnmarshaller();

			store = (PlayFieldStore) u.unmarshal(playfieldStream);
		} catch (JAXBException e) {
			log.error("Could not load playfield", e);
			throw new RuntimeException("invalid playfield configuration file!");
		} catch (FileNotFoundException e) {
			log.error("Could not load playfield", e);
			throw new RuntimeException("playfield configuration file does not exist!");
		} finally {
			try {
				playfieldStream.close();
			} catch (IOException e) {
				log.error("Exception while closing playfieldStream", e);
			}
		}
		log.info("loaded {} play fields", store.getPlayFields().size());
		for (PlayFieldPdo field : store.getPlayFields()) {
			log.info("loaded play field {}", field.getName());
		}
		return store.getPlayFields();
	}

}

package ch.m02.comet.pinball.persistence;

import java.util.List;

import ch.m02.comet.pinball.persistence.model.playfield.PlayFieldPdo;

public interface PlayFieldStoreDao {

	public List<PlayFieldPdo> findPlayFields();
	
}

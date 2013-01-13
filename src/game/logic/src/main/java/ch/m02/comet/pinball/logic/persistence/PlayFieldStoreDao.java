package ch.m02.comet.pinball.logic.persistence;

import java.util.List;

import ch.m02.comet.pinball.logic.model.playfield.PlayFieldPdo;

public interface PlayFieldStoreDao {

	public List<PlayFieldPdo> findPlayFields();
	
}

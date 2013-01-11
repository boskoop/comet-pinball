package ch.m02.comet.pinball.logic.persistence;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import ch.m02.comet.pinball.logic.model.playfield.PlayFieldPdo;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "configuration", namespace = "ch.m02.comet.pinball.model")
public class PlayFieldStore {
	
	@XmlElementWrapper(name = "playfields")
	@XmlElement(name = "playfield", required = true)
	private List<PlayFieldPdo> playfields;
	
	public List<PlayFieldPdo> getPlayFields() {
		return playfields;
	}
	
	public void setPlayFields(List<PlayFieldPdo> playfields) {
		this.playfields = playfields;
	}
	
}

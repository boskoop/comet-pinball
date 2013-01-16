package ch.m02.comet.pinball.persistence.model.playfield;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "configuration")
@XmlType(name = "configuration")
public class PlayFieldStore {
	
	@XmlElementWrapper(name = "playfields", required = true)
	@XmlElement(name = "playfield", required = true)
	private List<PlayFieldPdo> playfields;
	
	public List<PlayFieldPdo> getPlayFields() {
		return playfields;
	}
	
	public void setPlayFields(List<PlayFieldPdo> playfields) {
		this.playfields = playfields;
	}
	
}

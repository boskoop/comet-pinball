package ch.m02.comet.pinball.persistence.model.playfield;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://comet.m02.ch/pinball/playfield", name = "element")
public abstract class PlayFieldElementPdo implements PlayFieldElement {

	@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "id", required = true)
	private int id;

	@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "position", required = true)
	private VectorPdo position;

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public VectorPdo getPosition() {
		return position;
	}

	public void setPosition(VectorPdo position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", id)
				.append("position", position)
				.build();
	}

}

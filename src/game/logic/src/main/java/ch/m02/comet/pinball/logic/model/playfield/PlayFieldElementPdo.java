package ch.m02.comet.pinball.logic.model.playfield;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ PlayFieldBumperElementPdo.class,
		PlayFieldObstacleElementPdo.class, PlayFieldSlingshotElementPdo.class })
public abstract class PlayFieldElementPdo implements PlayFieldElement {

	@XmlElement(name = "id", required = true)
	private int id;

	@XmlElement(name = "position", required = true)
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

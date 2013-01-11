package ch.m02.comet.pinball.logic.model.playfield;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldElement;
import ch.m02.comet.pinball.core.model.playfield.PlayFieldElementType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "element")
@XmlType
public class PlayFieldElementPdo implements PlayFieldElement {

	@XmlElement(name = "id", required = true)
	private int id;

	@XmlElement(name = "type", required = true)
	private PlayFieldElementType type;

	@XmlElement(name = "placement", required = true)
	private PlacementPdo placement;

	@Override
	public PlayFieldElementType getType() {
		return type;
	}

	@Override
	public PlacementPdo getPlacement() {
		return placement;
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPlacement(PlacementPdo placement) {
		this.placement = placement;
	}

	public void setType(PlayFieldElementType type) {
		this.type = type;
	}

}

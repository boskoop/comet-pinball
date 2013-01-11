package ch.m02.comet.pinball.logic.model.playfield;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ch.m02.comet.pinball.core.model.playfield.Placement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "placement")
@XmlType
public class PlacementPdo implements Placement {

	@XmlElement(name = "posx", required = true)
	private float positionX;

	@XmlElement(name = "posy", required = true)
	private float positionY;

	@XmlElement(name = "scale", required = true)
	private float scale;

	@XmlElement(name = "rotation", required = true)
	private float rotation;

	@Override
	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float x) {
		this.positionX = x;
	}

	@Override
	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float y) {
		this.positionY = y;
	}

	@Override
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

}

package ch.m02.comet.pinball.logic.model.playfield;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldBumperElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "element")
public class PlayFieldBumperElementPdo extends PlayFieldElementPdo implements
		PlayFieldBumperElement {

	@XmlElement(name = "radius", required = true)
	private float radius;
	
	@Override
	public float getRadius() {
		return radius;
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}

}

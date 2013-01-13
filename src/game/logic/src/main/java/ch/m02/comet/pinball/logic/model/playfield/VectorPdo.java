package ch.m02.comet.pinball.logic.model.playfield;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ch.m02.comet.pinball.core.model.playfield.Vector;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vector")
@XmlType(name = "vector")
public class VectorPdo implements Vector {

	@XmlElement(name = "x", required = true)
	private float x;
	
	@XmlElement(name = "y", required = true)
	private float y;
	
	public VectorPdo() {
	}
	
	public VectorPdo(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("x", x)
				.append("y", y)
				.build();
	}

}

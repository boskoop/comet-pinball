package ch.m02.comet.pinball.logic.model.playfield;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldSlingshotElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "element")
public class PlayFieldSlingshotElementPdo extends PlayFieldElementPdo implements
		PlayFieldSlingshotElement {
	
	@XmlElement(name = "a", required = true)
	private VectorPdo cornerA;

	@XmlElement(name = "b", required = true)
	private VectorPdo cornerB;
	
	@Override
	public VectorPdo getCornerAVector() {
		return cornerA;
	}

	@Override
	public VectorPdo getCornerBVector() {
		return cornerB;
	}
	
	public void setCornerA(VectorPdo cornerA) {
		this.cornerA = cornerA;
	}

	public void setCornerB(VectorPdo cornerB) {
		this.cornerB = cornerB;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("cornerA", cornerA)
				.append("cornerB", cornerB)
				.build();
	}

}

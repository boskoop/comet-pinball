package ch.m02.comet.pinball.persistence.model.playfield;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.playfield.PlayFieldSlingshotElement;
import ch.m02.comet.pinball.core.presentation.playfield.SlingshotElementFactory;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://comet.m02.ch/pinball/playfield", name = "slingshot")
public class PlayFieldSlingshotElementPdo extends PlayFieldElementPdo implements
		PlayFieldSlingshotElement {
	
	@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "corner.a", required = true)
	private VectorPdo cornerA;

	@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "corner.b", required = true)
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

	@Override
	public SlingshotElementFactory getElementFactory(ApplicationContext context) {
		SlingshotElementFactory factory = context.getComponentContainer()
				.getComponent(SlingshotElementFactory.class);
		factory.setPlayFieldElement(this);
		return factory;
	}
}

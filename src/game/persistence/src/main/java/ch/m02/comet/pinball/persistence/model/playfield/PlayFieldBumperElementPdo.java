package ch.m02.comet.pinball.persistence.model.playfield;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.playfield.PlayFieldBumperElement;
import ch.m02.comet.pinball.core.presentation.playfield.BumperElementFactory;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bumper")
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
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("radius", radius)
				.build();
	}

	@Override
	public BumperElementFactory getElementFactory(ApplicationContext context) {
		BumperElementFactory factory = context.getComponentContainer()
				.getComponent(BumperElementFactory.class);
		factory.setPlayFieldElement(this);
		return factory;
	}

}

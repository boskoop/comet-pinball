package ch.m02.comet.pinball.persistence.model.playfield;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.playfield.PlayFieldObstacleElement;
import ch.m02.comet.pinball.core.presentation.playfield.ObstacleElementFactory;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://comet.m02.ch/pinball/playfield", name = "obstacle")
public class PlayFieldObstacleElementPdo extends PlayFieldElementPdo implements
		PlayFieldObstacleElement {
	
	@XmlElementWrapper(namespace = "http://comet.m02.ch/pinball/playfield", name = "vertices", required = true)
	@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "vertice", required = true)
	private List<VectorPdo> vertices;
	
	@Override
	public List<VectorPdo> getVertices() {
		return vertices;
	}

	public void setVertices(List<VectorPdo> vertices) {
		this.vertices = vertices;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this)
				.appendSuper(super.toString());
		builder.append("vertices=[");
		for (VectorPdo v : vertices) {
			builder.append(v);
		}
		builder.append("]");
		return builder.build();
	}

	@Override
	public ObstacleElementFactory getElementFactory(ApplicationContext context) {
		ObstacleElementFactory factory = context.getComponentContainer()
				.getComponent(ObstacleElementFactory.class);
		factory.setPlayFieldElement(this);
		return factory;
	}

}

package ch.m02.comet.pinball.logic.model.playfield;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ch.m02.comet.pinball.core.ApplicationContext;
import ch.m02.comet.pinball.core.model.playfield.PlayFieldObstacleElement;
import ch.m02.comet.pinball.core.presentation.ElementFactory;
import ch.m02.comet.pinball.core.presentation.playfield.ObstacleElementFactory;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "element")
public class PlayFieldObstacleElementPdo extends PlayFieldElementPdo implements
		PlayFieldObstacleElement {
	
	@XmlElementWrapper(name = "vertices")
	@XmlElement(name = "vertice", required = true)
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
	public ElementFactory getElementFactory(ApplicationContext context) {
		return context.getComponentContainer().getComponent(ObstacleElementFactory.class);
	}

}

package ch.m02.comet.pinball.logic.model.playfield;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldObstacleElement;

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

}
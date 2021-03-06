package ch.m02.comet.pinball.persistence.model.playfield;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ToStringBuilder;

import ch.m02.comet.pinball.core.model.playfield.PlayField;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://comet.m02.ch/pinball/playfield", name = "playfield")
public class PlayFieldPdo implements PlayField {

	@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "name", required = true)
	private String name;

	@XmlElements({
			@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "bumper", type = PlayFieldBumperElementPdo.class),
			@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "slingshot", type = PlayFieldSlingshotElementPdo.class),
			@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "obstacle", type = PlayFieldObstacleElementPdo.class) })
	@XmlElementWrapper(namespace = "http://comet.m02.ch/pinball/playfield", name = "elements", required = true, nillable = false)
	private List<PlayFieldElementPdo> elements;

	@XmlElementWrapper(namespace = "http://comet.m02.ch/pinball/playfield", name = "rules", required = true)
	@XmlElement(namespace = "http://comet.m02.ch/pinball/playfield", name = "rule", required = true)
	private List<PlayFieldRulePdo> rules;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<PlayFieldElementPdo> getElements() {
		return elements;
	}

	@Override
	public List<PlayFieldRulePdo> getGameRules() {
		return rules;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setElements(List<PlayFieldElementPdo> elements) {
		this.elements = elements;
	}

	public void setRules(List<PlayFieldRulePdo> rules) {
		this.rules = rules;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this)
				.append("name", name);
		builder.append("elements=[");
		for (PlayFieldElementPdo e : elements) {
			builder.append(e);
		}
		builder.append("],rules=[");
		for (PlayFieldRulePdo r : rules) {
			builder.append(r);
		}
		builder.append("]");
		return builder.build();
	}

}

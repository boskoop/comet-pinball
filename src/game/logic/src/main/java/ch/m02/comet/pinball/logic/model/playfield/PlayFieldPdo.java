package ch.m02.comet.pinball.logic.model.playfield;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import ch.m02.comet.pinball.core.model.playfield.PlayField;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "playfield")
public class PlayFieldPdo implements PlayField {

	@XmlElement(name = "name", required = true)
	private String name;

	@XmlElementWrapper(name = "elements")
	@XmlElement(name = "element", required = true)
	private List<PlayFieldElementPdo> elements;

	@XmlElementWrapper(name = "rules")
	@XmlElement(name = "rule", required = true)
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

}

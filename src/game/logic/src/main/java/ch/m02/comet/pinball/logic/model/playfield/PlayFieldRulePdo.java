package ch.m02.comet.pinball.logic.model.playfield;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldRule;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rule")
@XmlType
public class PlayFieldRulePdo implements PlayFieldRule {

	@XmlElement(name = "class", required = true)
	private String className;

	@XmlElementWrapper(name = "parameters")
	@XmlElement(name = "parameter", required = true)
	private List<Integer> parameters;

	@Override
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public List<Integer> getParameters() {
		return parameters;
	}

	public void setParameters(List<Integer> parameters) {
		this.parameters = parameters;
	}

}

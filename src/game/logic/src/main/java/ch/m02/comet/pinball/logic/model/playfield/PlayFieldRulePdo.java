package ch.m02.comet.pinball.logic.model.playfield;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import ch.m02.comet.pinball.core.model.playfield.PlayFieldRule;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rule")
public class PlayFieldRulePdo implements PlayFieldRule {

	@XmlElement(name = "class", required = true)
	private Class<?> className;

	@XmlElementWrapper(name = "parameters")
	@XmlElement(name = "parameter", required = true)
	private List<Integer> parameters;

	@Override
	public Class<?> getClassName() {
		return className;
	}

	public void setClassName(Class<?> className) {
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

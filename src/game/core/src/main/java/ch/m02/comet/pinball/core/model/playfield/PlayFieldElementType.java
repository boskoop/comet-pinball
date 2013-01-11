package ch.m02.comet.pinball.core.model.playfield;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "playFieldElementType")
@XmlEnum
public enum PlayFieldElementType {

	BUMPER, SLINGSHOT, GOAL, GENERIC, LAMP;
	
}

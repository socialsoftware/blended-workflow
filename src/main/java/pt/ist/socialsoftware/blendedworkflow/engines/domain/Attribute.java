package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class Attribute extends Attribute_Base {

	public enum AttributeType {STRING, NUMBER, BOOLEAN};

	public Attribute(String name, AttributeType type, boolean isKeyAttribute) {
		setName(name);
		setType(type);
		setIsKeyAttribute(isKeyAttribute);
	}

}

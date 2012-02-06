package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class Attribute extends Attribute_Base {

	public enum AttributeType {STRING, NUMBER, BOOLEAN};

	public Attribute(String id, String name, AttributeType type, boolean isKeyAttribute) {
		setId(id);
		setName(name);
		setType(type);
		setIsKeyAttribute(isKeyAttribute);
	}

}

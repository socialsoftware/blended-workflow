package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class Attribute extends Attribute_Base {

	public enum AttributeType {BOOLEAN, NUMBER, STRING};

	public Attribute(DataModel dataModel, String name, Entity entity, AttributeType type, boolean isKeyAttribute) throws BlendedWorkflowException {
		checkUniqueAttributeName(entity,name);
		setDataModel(dataModel);
		setName(name);
		setEntity(entity);
		setType(type);
		setIsKeyAttribute(isKeyAttribute);
	}

	private void checkUniqueAttributeName(Entity entity, String name) throws BlendedWorkflowException {
		for (Attribute attribute : entity.getAttributes()) {
			if (attribute.getName().equals(name)) {
				throw new BlendedWorkflowException("Exception @Attribute: The Attribute name: " + name + "already exists.");
			}
		}
	}

	public void cloneAttribute(DataModelInstance dataModelInstance, Entity entity) throws BlendedWorkflowException {
		new Attribute(dataModelInstance, getName(), entity, getType(), getIsKeyAttribute());
	}


}
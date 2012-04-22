package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

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
				throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_ATTRIBUTE_NAME, name);
			}
		}
	}

	public void cloneAttribute(DataModelInstance dataModelInstance, Entity entity) throws BlendedWorkflowException {
		new Attribute(dataModelInstance, getName(), entity, getType(), getIsKeyAttribute());
	}
	
	/**
	 * Gets a string with an instance of the AttributeType. Used by the WorkletAdapter to create the tasks/goals cornerstone data
	 */
	public String getAttributeType() {
		String value = "string";
		if (this.getType().equals(AttributeType.BOOLEAN)) {
			value = "true";
		}
		else if (this.getType().equals(AttributeType.NUMBER)) {
			value = "0";
		}
		return value;
	}

}
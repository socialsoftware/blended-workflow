package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class Attribute extends Attribute_Base {

	public enum AttributeType {
		BOOLEAN, NUMBER, STRING
	};

	public Attribute(DataModel dataModel, String name, Entity entity,
			AttributeType type, boolean isKeyAttribute, boolean isSystem)
			throws BlendedWorkflowException {
		checkUniqueAttributeName(entity, name);
		setDataModel(dataModel);
		setName(name);
		setEntity(entity);
		setType(type);
		setIsKeyAttribute(isKeyAttribute);
		setIsSystem(isSystem);
	}

	private void checkUniqueAttributeName(Entity entity, String name)
			throws BlendedWorkflowException {
		for (Attribute attribute : entity.getAttributesSet()) {
			if (attribute.getName().equals(name)) {
				throw new BlendedWorkflowException(
						BlendedWorkflowError.INVALID_ATTRIBUTE_NAME, name);
			}
		}
	}

	public void cloneAttribute(DataModelInstance dataModelInstance,
			Entity entity) throws BlendedWorkflowException {
		new Attribute(dataModelInstance, getName(), entity, getType(),
				getIsKeyAttribute(), getIsSystem());
	}

	/**
	 * FIXME: Double/Boolean
	 */
	public String getYAWLAttributeType() {
		return "string";
	}

	public String getYAWLAttributeValue() {
		if (this.getType().equals(AttributeType.NUMBER)) {
			return "0";
		} else {
			return "string";
		}
	}

}
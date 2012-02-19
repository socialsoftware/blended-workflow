package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public class AttributeInstance extends AttributeInstance_Base {

	public AttributeInstance(Attribute attribute, EntityInstance entityInstance) {
		setEntityInstance(entityInstance);
		setAttribute(attribute);
		setId(getAttribute().getName() + "." + entityInstance.getNewAttributeInstanceId());
		setState(DataState.UNDEFINED);
	}

	@Override
	public void setValue(String value) {
		if (!getState().equals(DataState.DEFINED)) {
			setState(DataState.DEFINED);
		}
		super.setValue(value);
	}
}
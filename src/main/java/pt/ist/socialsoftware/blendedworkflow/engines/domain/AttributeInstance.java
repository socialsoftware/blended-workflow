package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public class AttributeInstance extends AttributeInstance_Base {

	public AttributeInstance(Attribute attribute, String id) {
		setAttribute(attribute);
		setId(getAttribute().getName() + "." + id);
		setState(DataState.UNDEFINED);
		setValue("");
	}

	@Override
	public void setValue(String value) {
		if (!getState().equals(DataState.DEFINED)) {
			setState(DataState.DEFINED);
		}
		super.setValue(value);
	}
}
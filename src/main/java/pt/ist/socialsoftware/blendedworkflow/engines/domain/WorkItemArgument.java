package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public class WorkItemArgument extends WorkItemArgument_Base {

	public WorkItemArgument(AttributeInstance attributeInstance, String value, DataState state) {
		setAttributeInstance(attributeInstance);
		setValue(value);
		setState(state);
	}

}

package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;

@Deprecated
public class OldWorkItemArgument extends OldWorkItemArgument_Base {

	public OldWorkItemArgument(OldAttributeInstance attributeInstance, String value, DataState state) {
		setAttributeInstance(attributeInstance);
		setValue(value);
		setState(state);
	}

}

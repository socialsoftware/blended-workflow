package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public class AttributeInstance extends AttributeInstance_Base {

	public AttributeInstance() {
		super();
	}

	public AttributeInstance(String id) {
		setId(id);
		setState(DataState.UNDEFINED);
		setValue("");
	}

	@Override
	public void setValue(String value) {
		setState(DataState.DEFINED);
		if (!value.equals(getValue())) {
			super.setValue(value);
			for (WorkItem workItem : getWorkItem()) {
				//System.out.println("ATTINSTANCE CLASS: NOTIFY Workitem id " + workItem.getId());
				workItem.notifyDataChange();
			}
		}
	}

	@Override
	public void setState(DataState value) {
		super.setState (value);
		if (getState().equals(DataState.SKIPPED)){
			for (WorkItem workItem : getWorkItem()) {
				workItem.notifyStateChange();
			}
		}
	}

}

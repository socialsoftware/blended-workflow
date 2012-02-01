package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.workletinterface.WorkletAdapter;

public class WorkItem extends WorkItem_Base {
	
	public enum WorkItemState {ENABLED, CONSTRAINT_VIOLATION, CHECKED_IN, COMPLETED};

	public WorkItem(String workItemID) {
		setId(workItemID);
		setState(WorkItemState.ENABLED);
	}
	
	public void setAttributeValues(HashMap<String, String> values) {
		for (AttributeInstance attributeInstance : getAttributeInstance()) {
			if (values.containsKey(attributeInstance.getId())) {
				attributeInstance.setValue(values.get(attributeInstance.getId()));
			}
		}
	}

	public void notifyDataChange() {
		if (getState() == WorkItemState.ENABLED) {
			setState(WorkItemState.CONSTRAINT_VIOLATION);
			WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
		}
	}

	public void notifyWorkItemCheckedIn() {
		if (getState() == WorkItemState.ENABLED) {
			WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
		}	
		super.setState(WorkItemState.CHECKED_IN);
	}
	
	// to be redefined for each kind of work item, either goal or activity
	public void notifyWorkItemEnabled() {
		
	}
	
}
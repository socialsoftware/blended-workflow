package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.workletinterface.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public class WorkItem extends WorkItem_Base {

	public enum WorkItemState {ENABLED, CONSTRAINT_VIOLATION, CHECKED_IN, SKIPPED, COMPLETED};

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
		}
		//System.out.println("WORKITEM CLASS: WorkItemID:" + getId());
		WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
	}

	public void notifyWorkItemCheckedIn() {
		if (getState() == WorkItemState.ENABLED) {
			WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
		}	
		setState(WorkItemState.CHECKED_IN);
	}

	// to be redefined for each kind of work item, either goal or activity
	public void notifyWorkItemEnabled() {

	}

	public void setAttributeState(DataState state) {
		for (AttributeInstance attributeInstance : getAttributeInstance()) {
			attributeInstance.setState(state);
		}
	}

	public void notifyWorkItemSkipped() {
		if (getState().equals(WorkItemState.ENABLED)){
			setState(WorkItemState.SKIPPED);
		}
	}

	public void notifyStateChange() {
		if (getState() == WorkItemState.ENABLED) {
			WorkletAdapter.getInstance().notifyWorkItemSkippedWorkItem(this);
		}	
		setState(WorkItemState.SKIPPED);
	}

}
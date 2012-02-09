package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.workletinterface.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public abstract class WorkItem extends WorkItem_Base {

	public enum WorkItemState {ENABLED, CONSTRAINT_VIOLATION, CHECKED_IN, SKIPPED, COMPLETED};
	
	public WorkItem() {
		super();
	}

	public WorkItem(String workItemID) {
		setId(workItemID);
		setState(WorkItemState.ENABLED);
	}

	public void notifyWorkItemCheckedIn(HashMap<String, String> values) {
		setAttributeValues(values);
		if (getState() == WorkItemState.ENABLED) {
			setState(WorkItemState.CHECKED_IN);
			WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
		}	
		notifyWorkItemDataChanged();
	}
	
	public void notifyWorkItemDataChanged() {
		Set<WorkItem> notifyWorkItems = new HashSet<WorkItem>();
		
		for (AttributeInstance attributeInstance : getAttributeInstance()) {
			for (WorkItem workItem : attributeInstance.getWorkItem()) {
				if (workItem != this) {
					notifyWorkItems.add(workItem);
				}
			}
		}
		
		for (WorkItem workItem : notifyWorkItems) {
			workItem.notifyDataChange();
		}
		
	}

	private void setAttributeValues(HashMap<String, String> values) {
		for (AttributeInstance attributeInstance : getAttributeInstance()) {
			if (values.containsKey(attributeInstance.getId())) {
				attributeInstance.setValue(values.get(attributeInstance.getId()));
			}
		}
	}

	public void notifyWorkItemSkipped() {
		setAttributesSkipped();
		if (getState() == WorkItemState.ENABLED) {
			setState(WorkItemState.SKIPPED);
			WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
		}
		notifyWorkItemDataChanged();
	}
	
	private void setAttributesSkipped() {
		for (AttributeInstance attributeInstance : getAttributeInstance()) {
			if (attributeInstance.getState() == DataState.UNDEFINED) {
				attributeInstance.setState(DataState.SKIPPED);
			}
		}
	}

	private void notifyDataChange() {
		if (getState() == WorkItemState.ENABLED) {
			setState(WorkItemState.CONSTRAINT_VIOLATION);
			WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
		}
		//System.out.println("WORKITEM CLASS: WorkItemID:" + getId());
	}

	// to be redefined for each kind of work item, either goal or activity
	public void notifyWorkItemEnabled() {

	}
	
	public abstract String getElementId();

}
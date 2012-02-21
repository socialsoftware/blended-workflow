package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.workletinterface.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public abstract class WorkItem extends WorkItem_Base {

	public enum WorkItemState {ENABLED, CONSTRAINT_VIOLATION, CHECKED_IN, SKIPPED, COMPLETED};


	public void notifyWorkItemCheckedIn(HashMap<String, String> values) {
		setAttributeValues(values);
		if (getState() == WorkItemState.ENABLED) {
			setState(WorkItemState.CHECKED_IN);
			WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
		}	
		notifyWorkItemDataChanged();
	}

	public void notifyWorkItemSkipped() {
		setAttributesSkipped();
		if (getState() == WorkItemState.ENABLED) {
			setState(WorkItemState.SKIPPED);
			WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
		}
		notifyWorkItemDataChanged();
	}

	public void notifyWorkItemDataChanged() {
		Set<WorkItem> notifyWorkItems = new HashSet<WorkItem>();
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			for (WorkItem workItem : attributeInstance.getWorkItems()) {
				if (workItem != this) {
					notifyWorkItems.add(workItem);
				}
			}
		}
		for (WorkItem workItem : notifyWorkItems) {
			workItem.notifyDataChange();
		}

	}

	private void notifyDataChange() {
		if (getState() == WorkItemState.ENABLED) {
			setState(WorkItemState.CONSTRAINT_VIOLATION);
			WorkletAdapter.getInstance().notifyWorkItemContraintViolation(this);
		}
	}

	private void setAttributeValues(HashMap<String, String> values) {
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			if (values.containsKey(attributeInstance.getId())) {
				attributeInstance.setValue(values.get(attributeInstance.getId()));
			}
		}
	}

	private void setAttributesSkipped() {
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			if (attributeInstance.getState() == DataState.UNDEFINED) {
				attributeInstance.setState(DataState.SKIPPED);
			}
		}
	}

	// to be redefined for each kind of work item, either goal or activity
	public abstract void notifyEnabled();

	public abstract void notifyCompleted();

	public abstract String getElementId();

}
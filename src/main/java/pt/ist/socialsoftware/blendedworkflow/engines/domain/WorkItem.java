package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public abstract class WorkItem extends WorkItem_Base {

	public enum WorkItemState {PRE_TASK, PRE_GOAL, PRE_FALSE, CONSTRAINT_VIOLATION, ENABLED, GOAL_PENDING, CHECKED_IN, SKIPPED, 
		COMPLETED, RE_ACTIVATED, ACTIVATED};

	/******************************
	 * State Change Notifications *
	 ******************************/
	
	/**
	 * Notify a TaskWorkItem if its PreConstraint = SKIPPED
	 */
	public abstract void notifyPreTask();
	
	/**
	 * Notify a TaskWorkItem if its PreConstraint = FALSE
	 */
	public abstract void notifyPreFalse();
	
	/**
	 * Notify a TaskWorkItem if its PreConstraint = TRUE
	 * Notify a GoalWorkItem when it is created
	 * Notify a WorkItem if there is a Data change event
	 */
	public abstract void notifyDataChanged();
	
	/**
	 * Notify a TaskWorkItem if its PostConstraint = FALSE
	 * Notify a GoalWorkItem if its Condition is False
	 */
	public abstract void notifyEnabled();
	
	/**
	 * Notify a Parent GoalWorkItem if a new subGoal is created
	 */
	public abstract void notifyPending();
	
	/**
	 * Notify a WorkItem if there is a CheckIn event
	 */
	public abstract void notifyCheckedIn(); 
	
	/**
	 * Notify a TaskWorkItem if its PostConstraint = TRUE
	 * Notify a GoalWorkItem if its Condition is TRUE
	 */
	public abstract void notifyCompleted();

	/**
	 * Notify a WorkItem if there is a Skip event
	 * Notify a TaskWorkItem if its PostConstraint = SKIPPED
	 * Notify a GoalWorkItem if its Condition is SKIPPED
	 */
	public abstract void notifySkipped();
	
	/**
	 * Notify a GoalWorkItem if it is ReActivated
	 */
	public abstract void notifyReActivated();
	
	/***********************************
	 * Commit WorkItemArguments values *
	 ***********************************/

	/**
	 * Commits the new data to the dataModel.
	 */
	public void setAttributeValues() {
		Boolean modified = false;
		Boolean isPreTask = false;
		
		//Add PreConstrain data
		for (WorkItemArgument workItemArgument : getInputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			if (!attributeInstance.getState().equals(DataState.DEFINED)) {
				workItemArgument.getAttributeInstance().setValue(workItemArgument.getValue());
				isPreTask = true;
				modified = true;
			}
			else if (attributeInstance.getValue() != workItemArgument.getValue()) {
				workItemArgument.getAttributeInstance().setValue(workItemArgument.getValue());
				isPreTask = true;
				modified = true;
			}
		}
		
		//Add ConstrainViolation data
		for (WorkItemArgument workItemArgument : getOutputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			if (!attributeInstance.getState().equals(DataState.DEFINED)) {
				workItemArgument.getAttributeInstance().setValue(workItemArgument.getValue());
				modified = true;
			}
			else if (attributeInstance.getValue() != workItemArgument.getValue()) {
				workItemArgument.getAttributeInstance().setValue(workItemArgument.getValue());
				modified = true;
			}
		}

		if (modified) {
			notifyWorkItemDataChanged(isPreTask);
		}
	}
	
	/**
	 * Commits the new data as SKIPPED to the dataModel.
	 */
	public void setAttributeSkipped() {
		Boolean isPreTask = false;
		Boolean modified = false;
		
		//Add PreConstrain data
		for (WorkItemArgument workItemArgument : getInputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			if (attributeInstance.getState() == DataState.UNDEFINED) {
				attributeInstance.setState(DataState.SKIPPED);
				isPreTask = true;
				modified = true;
			}
		}
		
		//Add ConstrainViolation data
		for (WorkItemArgument workItemArgument : getOutputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			if (attributeInstance.getState() == DataState.UNDEFINED) {
				attributeInstance.setState(DataState.SKIPPED);
				attributeInstance.setValue("$SKIPPED$");
				modified = true;
			}
		}

		if (modified) {
			notifyWorkItemDataChanged(isPreTask);
		}
	}
	
	/*****************************
	 * Data Change Notifications *
	 *****************************/

	/**
	 * Get all affected WorkItems an notifies then to reevaluate their conditions.
	 */
	public void notifyWorkItemDataChanged(Boolean isPreTask) {
		Set<WorkItem> notifyWorkItems = new HashSet<WorkItem>();
		//If PRE_TASK: Get WorkItems affected by PreConstraint attributesInstances new values
		if (isPreTask) {
			for (AttributeInstance attributeInstance : getInputAttributeInstances()) {
				//Check preconditions
				for (WorkItem workItem : attributeInstance.getPreConstraintWorkItems()) {
					if (!workItem.equals(this) && (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK) || workItem.getState().equals(WorkItemState.PRE_GOAL) || workItem.getState().equals(WorkItemState.PRE_FALSE))) {
						notifyWorkItems.add(workItem);
					}
				}
				//Goalconditions and pos conditions
				for (WorkItem workItem : attributeInstance.getContraintViolationWorkItems()) {
					if (!workItem.equals(this) & (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK) || workItem.getState().equals(WorkItemState.PRE_GOAL) || workItem.getState().equals(WorkItemState.PRE_FALSE))) {
						notifyWorkItems.add(workItem);
					}
				}
				
			}
		}
		
		//Get WorkItems affected by ConstraintViolation attributesInstances values
		for (AttributeInstance attributeInstance : getOutputAttributeInstances()) {
			//Check preconditions
			for (WorkItem workItem : attributeInstance.getPreConstraintWorkItems()) {
				if (!workItem.equals(this) & (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK) || workItem.getState().equals(WorkItemState.PRE_FALSE))) {
					notifyWorkItems.add(workItem);
				}
			}
			//Goalconditions and pos conditions
			for (WorkItem workItem : attributeInstance.getContraintViolationWorkItems()) {
				if (!workItem.equals(this) & (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK) || workItem.getState().equals(WorkItemState.PRE_FALSE))) {
					notifyWorkItems.add(workItem);
				}
			}
		}
		
		for (WorkItem workItem : notifyWorkItems) {
			workItem.updateInputWorkItemArguments();
			workItem.updateOutputWorkItemArguments();
			workItem.notifyDataChange();
		}
	}
	
	/**
	 * Notify a WorkItem of a Data change.
	 */
	public void notifyDataChange() {
		if (getState() == WorkItemState.ENABLED || getState() == WorkItemState.PRE_FALSE) {
			notifyDataChanged();
		} else if (getState() == WorkItemState.PRE_TASK) {
			BlendedWorkflow.getInstance().getWorkletAdapter().requestWorkItemPreCondition((TaskWorkItem) this);
		} else if (getState() == WorkItemState.PRE_GOAL) {
			((GoalWorkItem)this).evaluate(true);
		}
	}
	
	/**
	 * Copy the Task PreConstraint AttributesInstances to its PreConstraint WorkItem Arguments.
	 */
	public void createInputWorkItemArguments() {
		for (AttributeInstance attributeInstance : getInputAttributeInstances()) {
			WorkItemArgument workItemArgument = new WorkItemArgument(attributeInstance, attributeInstance.getValue(), attributeInstance.getState());
			addInputWorkItemArguments(workItemArgument);
		}
	}
	
	/**
	 * Update PreConstraint WorkItem Arguments with new Data.
	 */
	public void updateInputWorkItemArguments() {
		for (WorkItemArgument workItemArgument : getInputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			workItemArgument.setValue(attributeInstance.getValue());
			workItemArgument.setState(attributeInstance.getState());
		}
	}
	
	/**
	 * Copy the Goal AttributesInstances to its ConstrainViolation WorkItem Arguments.
	 */
	public void createOutputWorkItemArguments() {
		for (AttributeInstance attributeInstance : getOutputAttributeInstances()) {
			WorkItemArgument workItemArgument = new WorkItemArgument(attributeInstance, attributeInstance.getValue(), attributeInstance.getState());
			addOutputWorkItemArguments(workItemArgument);
		}
	}
	
	/**
	 * Update ConstrainViolation WorkItem Arguments with new Data.
	 */
	public void updateOutputWorkItemArguments() {
		for (WorkItemArgument workItemArgument : getOutputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			workItemArgument.setValue(attributeInstance.getValue());
			workItemArgument.setState(attributeInstance.getState());
		}
	}
}
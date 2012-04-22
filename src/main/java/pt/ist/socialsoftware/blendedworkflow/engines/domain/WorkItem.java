package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public abstract class WorkItem extends WorkItem_Base {

	public enum WorkItemState {PRE_TASK, PRE_FALSE, CONSTRAINT_VIOLATION, ENABLED, GOAL_PENDING, CHECKED_IN, SKIPPED, 
		COMPLETED, RE_ACTIVATED};

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
	public abstract void notifyConstrainViolation();
	
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
	public void notifyCheckedIn() {
		if (getState() == WorkItemState.ENABLED || getState() == WorkItemState.PRE_TASK) {
			setState(WorkItemState.CHECKED_IN);
		}
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}
	
	/**
	 * Notify a WorkItem if there is a Skip event
	 */
	public void notifySkip() {
		if (getState() == WorkItemState.ENABLED || getState() == WorkItemState.PRE_TASK) {
			setState(WorkItemState.CHECKED_IN);
		}
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}
	
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
	public abstract void setAttributeValues();
	
	/**
	 * Commits the new data as SKIPPED to the dataModel.
	 */
	public abstract void setAttributeSkipped();
	
	/*****************************
	 * Data Change Notifications *
	 *****************************/

	/**
	 * Get all affected WorkItems an notifies then to reevaluate their conditions.
	 */
	public abstract void notifyWorkItemDataChanged(Boolean isPreTask);
	
	/**
	 * Notify a WorkItem of a Data change.
	 */
	public void notifyDataChange() {
		if (getState() == WorkItemState.ENABLED) {
			notifyConstrainViolation();
		} else if (getState() == WorkItemState.PRE_TASK) {
			BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemPreConstraint((TaskWorkItem) this);
		}
	}
	
}
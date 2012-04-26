package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal.GoalState;

public class GoalWorkItem extends GoalWorkItem_Base {
	
	private Logger log = Logger.getLogger("GoalWorkItem");
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public GoalWorkItem(BWInstance bwInstance, Goal goal) {
		log.info("New GoalWorkitem for goal " + goal.getName());
		setBwInstance(bwInstance);
		setGoal(goal);
		setID(goal.getName() + "." + bwInstance.getNewWorkItemID()); // Id: GoalName.#
		goal.getCondition().assignAttributeInstances(this);
		createConstrainViolationWorkItemArguments();
		notifyConstrainViolation();
		
		setRole(goal.getRole());
		setUser(goal.getUser());
	}

	/**
	 * Copy the Goal AttributesInstances to its ConstrainViolation WorkItem Arguments.
	 */
	private void createConstrainViolationWorkItemArguments() {
		for (AttributeInstance attributeInstance : getContraintViolationAttributeInstances()) {
			WorkItemArgument workItemArgument = new WorkItemArgument(attributeInstance, attributeInstance.getValue(), attributeInstance.getState());
			addConstrainViolationWorkItemArguments(workItemArgument);
		}
	}
	
	/**
	 * Update ConstrainViolation WorkItem Arguments with new Data.
	 */
	public void updateConstrainViolationWorkItemArguments() {
		for (WorkItemArgument workItemArgument : getConstrainViolationWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			workItemArgument.setValue(attributeInstance.getValue());
			workItemArgument.setState(attributeInstance.getState());
		}
	}
	
	public void updatePreConstrainWorkItemArguments() {}
	
	/******************************
	 * State Change Notifications *
	 ******************************/
	
	@Override
	public void notifyPreTask() {}
	
	@Override
	public void notifyPreFalse() {}
	
	@Override
	public void notifyConstrainViolation() {
		log.info("GoalWorkitem " + getID() + " is now in ConstrainViolation state");
		setState(WorkItemState.CONSTRAINT_VIOLATION);
		updateConstrainViolationWorkItemArguments();
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}

	@Override
	public void notifyEnabled() {
		log.info("GoalWorkitem " + getID() + " is now in Enabled state");
		getGoal().setState(GoalState.ENABLED);
		setState(WorkItemState.ENABLED);
		BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
	}
	
	@Override
	public void notifyPending() {
		log.info("GoalWorkitem " + getID() + " is now in Pending state");
		setState(WorkItemState.GOAL_PENDING);
		getGoal().setState(GoalState.DEACTIVATED);
		BlendedWorkflow.getInstance().getWorkListManager().notifyPendingWorkItem(this);
	}

	@Override
	public void notifyCompleted() {
		log.info("GoalWorkitem " + getID() + " is now in Completed state");
		if (getState() == WorkItemState.CHECKED_IN || getState() == WorkItemState.CONSTRAINT_VIOLATION) {
			setState(WorkItemState.COMPLETED);
			getGoal().setState(GoalState.ACHIEVED);
		}	
		setAttributeValues();

		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date,"Completed", "[GOAL] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifyCompletedWorkItem(this); 
		
		getBwInstance().getGoalModelInstance().getEnabledWorkItems();
	}

	@Override
	public void notifySkipped() {
		log.info("GoalWorkitem " + getID() + " is now in Skipped state");
		if (getState() == WorkItemState.CHECKED_IN || getState() == WorkItemState.CONSTRAINT_VIOLATION) {
			setState(WorkItemState.SKIPPED);
			getGoal().setState(GoalState.SKIPPED);
		}	
		setAttributeSkipped();
		
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date, "Skipped", "[GOAL] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifySkippedWorkItem(this);
		
		getBwInstance().getGoalModelInstance().getEnabledWorkItems();
	}
	
	@Override
	public void notifyReActivated() {
		// TODO: notifyReActivated
	}
	
	/***********************************
	 * Commit WorkItemArguments values *
	 ***********************************/
	
	@Override
	public void setAttributeValues() {
		// Add ConstrainViolation data
		for (WorkItemArgument workItemArgument : getConstrainViolationWorkItemArguments()) {
			workItemArgument.getAttributeInstance().setValue(workItemArgument.getValue());
		}
		
		notifyWorkItemDataChanged(false);
	}
	
	@Override
	public void setAttributeSkipped() {
		// Add ConstrainViolation data
		for (WorkItemArgument workItemArgument : getConstrainViolationWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			if (attributeInstance.getState() == DataState.UNDEFINED) {
				attributeInstance.setState(DataState.SKIPPED);
			}
		}
		
		notifyWorkItemDataChanged(false);
	}
	
	@Override
	public void notifyWorkItemDataChanged(Boolean isPreTask) {
		Set<WorkItem> notifyWorkItems = new HashSet<WorkItem>();

		// Get WorkItems affected by ConstraintViolation attributesInstances values
		for (AttributeInstance attributeInstance : getContraintViolationAttributeInstances()) {
			for (WorkItem workItem : attributeInstance.getContraintViolationWorkItems()) {
				if (!workItem.equals(this) & (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK))) {
					notifyWorkItems.add(workItem);
				}
			}
		}
		
		// TODO: pre
		
		for (WorkItem workItem : notifyWorkItems) {
			workItem.updatePreConstrainWorkItemArguments();
			workItem.updateConstrainViolationWorkItemArguments();
			workItem.notifyDataChange();
		}
	}

}

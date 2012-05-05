package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task.TaskState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class TaskWorkItem extends TaskWorkItem_Base {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Logger log = Logger.getLogger("TaskWorkItem");

	public TaskWorkItem(BWInstance bwInstance, Task task) {
		log.info("New TaskWorkItem for task " + task.getName());
		setBwInstance(bwInstance);
		setTask(task);
		setID(task.getName() + "." + bwInstance.getNewWorkItemID()); //Id: TaskName.#
		setRole(task.getRole());
		setUser(task.getUser());
		
		task.getPreConstraint().assignAttributeInstances(this, "pre");
		task.getPostConstraint().assignAttributeInstances(this, "post");
		
		createPreConstrainWorkItemArguments();
		createConstrainViolationWorkItemArguments();
	}

	/**
	 * Copy the Task PreConstraint AttributesInstances to its PreConstraint WorkItem Arguments.
	 */
	private void createPreConstrainWorkItemArguments() {
		for (AttributeInstance attributeInstance : getPreConstraintAttributeInstances()) {
			WorkItemArgument workItemArgument = new WorkItemArgument(attributeInstance, attributeInstance.getValue(), attributeInstance.getState());
			addPreConstrainWorkItemArguments(workItemArgument);
		}
	}
	
	/**
	 * Update PreConstraint WorkItem Arguments with new Data.
	 */
	public void updatePreConstrainWorkItemArguments() {
		for (WorkItemArgument workItemArgument : getPreConstrainWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			workItemArgument.setValue(attributeInstance.getValue());
			workItemArgument.setState(attributeInstance.getState());
		}
	}
	
	/**
	 * Copy the Task PostConstraint AttributesInstances to its ConstrainViolation WorkItem Arguments.
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

	/******************************
	 * State Change Notifications *
	 ******************************/

	@Override
	public void notifyPreTask() {
		log.info("TaskWorkitem " + getID() + " is now in PreTask state");
		getTask().setState(TaskState.ENABLED);
		setState(WorkItemState.PRE_TASK);
		BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
	}

	@Override
	public void notifyPreFalse() {
		log.info("TaskWorkitem " + getID() + " is now in PreFalse state");
		setState(WorkItemState.PRE_FALSE);
		// Should not happen!
	}

	@Override
	public void notifyConstrainViolation() {
		log.info("TaskWorkitem " + getID() + " is now in ConstrainViolation state");
		setState(WorkItemState.CONSTRAINT_VIOLATION);
		updatePreConstrainWorkItemArguments();
		updateConstrainViolationWorkItemArguments();
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
		log.info("TaskWorkitem " + getID() + " is now in ConstrainViolation state -end");
	}

	@Override
	public void notifyEnabled() {
		log.info("TaskWorkitem " + getID() + " is now in ENABLED state");
		getTask().setState(TaskState.ENABLED);
		setState(WorkItemState.ENABLED);
		BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
	}

	@Override
	public void notifyPending() {}

	@Override
	public void notifyCompleted() {
		log.info("TaskWorkitem " + getID() + " is now in COMPLETED state");
		if (getState() == WorkItemState.CHECKED_IN || getState() == WorkItemState.CONSTRAINT_VIOLATION) {
			setState(WorkItemState.COMPLETED);
			getTask().setState(TaskState.ACHIEVED);
		}	
		setAttributeValues();
		
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date,"Completed", "[TASK] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifyCompletedWorkItem(this);
		
		// Test proposes only
		try {
			getBwInstance().getTaskModelInstance().getEnabledWorkItems();
		} catch (BlendedWorkflowException e) {
			log.info("notifyCompleted exc" + e.getMessage());
		}
	}

	@Override
	public void notifySkipped() {
		log.info("TaskWorkitem " + getID() + " is now in SKIPPED state");
		if (getState() == WorkItemState.CHECKED_IN || getState() == WorkItemState.CONSTRAINT_VIOLATION) {
			setState(WorkItemState.SKIPPED);
			getTask().setState(TaskState.SKIPPED);
		}	
		setAttributeSkipped();
		
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date,"Skipped", "[TASK] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifySkippedWorkItem(this);
		
		// Test proposes only
		try {
			getBwInstance().getTaskModelInstance().getEnabledWorkItems();
		} catch (BlendedWorkflowException e) {
			log.info("notifyCompleted exc" + e.getMessage());
		}
	}

	@Override
	public void notifyReActivated() {}
	
	/***********************************
	 * Commit WorkItemArguments values *
	 ***********************************/
	
	@Override
	public void setAttributeValues() {
		Boolean modified = false;
		Boolean isPreTask = false;
		// Add PreConstrain data
		for (WorkItemArgument workItemArgument : getPreConstrainWorkItemArguments()) {

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

		// Add ConstrainViolation data
		for (WorkItemArgument workItemArgument : getConstrainViolationWorkItemArguments()) {
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
	
	@Override
	public void setAttributeSkipped() {
		Boolean isPreTask = false;
		Boolean modified = false;
		// Add PreConstrain data
		for (WorkItemArgument workItemArgument : getPreConstrainWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			if (attributeInstance.getState() == DataState.UNDEFINED) {
				attributeInstance.setState(DataState.SKIPPED);
				isPreTask = true;
				modified = true;
			}
		}

		// Add ConstrainViolation data
		for (WorkItemArgument workItemArgument : getConstrainViolationWorkItemArguments()) {
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
	
	@Override
	public void notifyWorkItemDataChanged(Boolean isPreTask) {
		Set<WorkItem> notifyWorkItems = new HashSet<WorkItem>();
		// If PRE_TASK: Get WorkItems affected by PreConstraint attributesInstances new values
		if (isPreTask) {
			for (AttributeInstance attributeInstance : getPreConstraintAttributeInstances()) {
				// Check preconditions
				for (WorkItem workItem : attributeInstance.getPreConstraintTaskWorkItems()) {
					if (!workItem.equals(this) && (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK))) {
						notifyWorkItems.add(workItem);
					}
				}
				// Goalconditions and pos conditions
				for (WorkItem workItem : attributeInstance.getContraintViolationWorkItems()) {
					if (!workItem.equals(this) & (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK))) {
						notifyWorkItems.add(workItem);
					}
				}
				
			}
		}
		
		// Get WorkItems affected by ConstraintViolation attributesInstances values
		for (AttributeInstance attributeInstance : getContraintViolationAttributeInstances()) {
			// Check preconditions
			for (WorkItem workItem : attributeInstance.getPreConstraintTaskWorkItems()) {
				if (!workItem.equals(this) & (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK))) {
					notifyWorkItems.add(workItem);
				}
			}
			// Goalconditions and pos conditions
			for (WorkItem workItem : attributeInstance.getContraintViolationWorkItems()) {
				if (!workItem.equals(this) & (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK))) {
					notifyWorkItems.add(workItem);
				}
			}
		}
		
		for (WorkItem workItem : notifyWorkItems) {
			workItem.updatePreConstrainWorkItemArguments();
			workItem.updateConstrainViolationWorkItemArguments();
			workItem.notifyDataChange();
		}
	}
	
	@Override
	public void notifyCheckedIn() {
		if (getState() == WorkItemState.ENABLED || getState() == WorkItemState.PRE_TASK) {
			setState(WorkItemState.CHECKED_IN);
		}
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}

	@Override
	public void notifySkip() {
		if (getState() == WorkItemState.ENABLED || getState() == WorkItemState.PRE_TASK) {
			setState(WorkItemState.CHECKED_IN);
		}
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}

}
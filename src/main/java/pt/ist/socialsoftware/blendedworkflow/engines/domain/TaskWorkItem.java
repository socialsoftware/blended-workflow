package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task.TaskState;

public class TaskWorkItem extends TaskWorkItem_Base {

	public TaskWorkItem(BWInstance bwInstance, Task task) {
		setBwInstance(bwInstance);
		setTask(task);
		setID(task.getName() + "." + bwInstance.getNewWorkItemID()); //Id: TaskName.#
		setState(WorkItemState.ENABLED);
		task.setState(TaskState.ENABLED);
		task.getPreConstraint().assignAttributeInstances(this, "pre");
		task.getPostConstraint().assignAttributeInstances(this, "post");
		createPreConstrainWorkItemArguments();
		createConstrainViolationWorkItemArguments();
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
		addConstrainViolationWorkItemArguments();
	}
	
	private void createPreConstrainWorkItemArguments() {
		for (AttributeInstance attributeInstance : getPreConstraintAttributeInstances()) {
			WorkItemArgument workItemArgument = new WorkItemArgument(attributeInstance, null);
			addPreConstrainWorkItemArguments(workItemArgument);
		}
	}
	
	private void createConstrainViolationWorkItemArguments() {
		for (AttributeInstance attributeInstance : getContraintViolationAttributeInstances()) {
			WorkItemArgument workItemArgument = new WorkItemArgument(attributeInstance, null);
			addConstrainViolationWorkItemArguments(workItemArgument);
		}
	}
	
	private void addPreConstrainWorkItemArguments() {
		for (AttributeInstance attributeInstance : getPreConstraintAttributeInstances()) {
			if (attributeInstance.getState() == DataState.SKIPPED) {
				addPreConstrainWorkItemArguments(new WorkItemArgument(attributeInstance, ""));
			}
		}
	}
	
	private void addConstrainViolationWorkItemArguments() {
		for (AttributeInstance attributeInstance : getContraintViolationAttributeInstances()) {
			addConstrainViolationWorkItemArguments(new WorkItemArgument(attributeInstance, ""));
		}
	}

	@Override
	public void notifyEnabled() {
		getTask().setState(TaskState.ENABLED);
		setState(WorkItemState.ENABLED);
		BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
	}

	@Override
	public void notifyCompleted() {
		setState(WorkItemState.COMPLETED);
		
	}

	@Override
	public void notifySkipped() {
		setState(WorkItemState.SKIPPED);
	}
	
	public void notifyPreConstrain() {
		setState(WorkItemState.PRE_CONSTRAINT);
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}
	
	public void notifyPreTask() {
		setState(WorkItemState.PRE_TASK);
		addPreConstrainWorkItemArguments();
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}
	
	@Override
	public void notifyPending() {
		getTask().setState(TaskState.DEACTIVATED);
		setState(WorkItemState.PENDING);
	}

	@Override
	public void notifyConstrainViolation() {
		setState(WorkItemState.CONSTRAINT_VIOLATION);
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}

}
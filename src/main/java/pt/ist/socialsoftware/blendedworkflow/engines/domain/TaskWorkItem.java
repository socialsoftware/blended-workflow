package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task.TaskState;

public class TaskWorkItem extends TaskWorkItem_Base {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private final Logger log = Logger.getLogger("TaskWorkItem");

	public TaskWorkItem(BWInstance bwInstance, Task task) {
		log.info("New TaskWorkItem for task " + task.getName());
		setBwInstance(bwInstance);
		setTask(task);
		setID(task.getName() + "." + bwInstance.getNewWorkItemID()); //Id: TaskName.#
		setState(WorkItemState.NEW);
		
		setRole(task.getRole());
		setUser(task.getUser());
		
		task.getPreConstraint().assignAttributeInstances(this, "pre");
		task.getPostConstraint().assignAttributeInstances(this, "post");
		createInputWorkItemArguments();
		createOutputWorkItemArguments();
	}
	
	/******************************
	 * NEW STATE MACHINE          *
	 ******************************/
	public void notifyPreActivity(){
		log.info("ActivityWorkitem " + getID() + " is now in the PREACTIVITY state");
		setState(WorkItemState.PRE_ACTIVITY);
		if (getState().equals(WorkItemState.NEW)) {
			BlendedWorkflow.getInstance().getWorkletAdapter().requestWorkItemPostConditionEvaluation(this);
		} else if (getState().equals(WorkItemState.PRE_ACTIVITY)) {
			BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
		}
	}
	
	public void notifyEnabled1() {
		log.info("ActivityWorkitem " + getID() + " is now in ENABLED state");
		setState(WorkItemState.ENABLED);
		if (getState().equals(WorkItemState.NEW)) {
			BlendedWorkflow.getInstance().getWorkletAdapter().requestWorkItemPostConditionEvaluation(this);
		} else if (getState().equals(WorkItemState.PRE_ACTIVITY)) {
			BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
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
		log.error("TaskWorkitem " + getID() + " is now in PreFalse state");
		setState(WorkItemState.PRE_FALSE);
	}

	@Override
	public void notifyDataChanged() {
		log.info("TaskWorkitem " + getID() + " is re-evaluating due to changes in data");
//		setState(WorkItemState.CONSTRAINT_VIOLATION);
		updateInputWorkItemArguments();
		updateOutputWorkItemArguments();
		log.debug(this.getClass().getSimpleName());
		BlendedWorkflow.getInstance().getWorkletAdapter().requestWorkItemPostConditionEvaluation(this);
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
		getBwInstance().getLog().addLogRecords(new LogRecord(date,"Completed", "[ACTIVITY] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifyCompletedWorkItem(this);
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
		getBwInstance().getLog().addLogRecords(new LogRecord(date,"Skipped", "[ACTIVITY] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifySkippedWorkItem(this);
	}

	@Override
	public void notifyReActivated() {}
	
	@Override
	public void notifyCheckedIn() {
		if (getState() == WorkItemState.ENABLED || getState() == WorkItemState.PRE_TASK) {
			setState(WorkItemState.CHECKED_IN);
		}
		BlendedWorkflow.getInstance().getWorkletAdapter().requestWorkItemPostConditionEvaluation(this);
	}
}
package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task.TaskState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.shared.PropertiesManager;

public class TaskWorkItem extends TaskWorkItem_Base {
	
	protected Boolean yawlFlow = Boolean.parseBoolean(PropertiesManager.getProperty("yawl.Flow"));
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
		
		createInputWorkItemArguments();
		createOutputWorkItemArguments();
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
	public void notifyConstrainViolation() {
		log.info("TaskWorkitem " + getID() + " is now in ConstrainViolation state");
		setState(WorkItemState.CONSTRAINT_VIOLATION);
		updateInputWorkItemArguments();
		updateOutputWorkItemArguments();
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
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

		// FIXME: Test proposes only
		if (!yawlFlow) {
			try {
				getBwInstance().getTaskModelInstance().getEnabledWorkItems();
			} catch (BlendedWorkflowException e) {
				log.error(e.getMessage());
			}
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
		getBwInstance().getLog().addLogRecords(new LogRecord(date,"Skipped", "[ACTIVITY] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifySkippedWorkItem(this);
		
		// FIXME: Test proposes only
		if (!yawlFlow) {
			try {
				getBwInstance().getTaskModelInstance().getEnabledWorkItems();
			} catch (BlendedWorkflowException e) {
				log.error(e.getMessage());
			}
		}
	}

	@Override
	public void notifyReActivated() {}
	
	@Override
	public void notifyCheckedIn() {
		if (getState() == WorkItemState.ENABLED || getState() == WorkItemState.PRE_TASK) {
			setState(WorkItemState.CHECKED_IN);
		}
		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this);
	}
}
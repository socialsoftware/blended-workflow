package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;

public class WorkListManager {

	private Logger log = Logger.getLogger("WorklistManager");
	protected BWPresentation bwPresentation = null;

	public BWPresentation getBwPresentation() {
		return bwPresentation;
	}

	public void setBwPresentation(BWPresentation bwPresentation) {
		this.bwPresentation = bwPresentation;
	}

	/**
	 * Notify the BWPresentation of enabled WorkItems.
	 * @param workItem The enabled WorkItem (i.e. State = ENABLED or PRE_TASK).
	 */
	public void notifyEnabledWorkItem(WorkItem workItem) {
		log.info("WorkItem " + workItem.getID() + " is now enabled.");
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			getBwPresentation().addGoalWorkItem(workItem.getOID(), workItem.getID());
		}
		else {
			getBwPresentation().addTaskWorkItem(workItem.getOID(), workItem.getID());
		}
	}

	/**
	 * Notify the BWPresentation of skipped WorkItems.
	 * @param workItem The skipped WorkItem (i.e. State = SKIPPED).
	 */
	public void notifySkippedWorkItem(WorkItem workItem) {
		log.info("WorkItem " + workItem.getID() + " is now skipped.");
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			getBwPresentation().removeGoalWorkItem(workItem.getOID());
		}
		else {
			getBwPresentation().removeTaskWorkItem(workItem.getOID());
		}
	}

	/**
	 * Notify the BWPresentation of pending WorkItems.
	 * @param workItem The pending WorkItem (i.e. State = PENDING).
	 */
	public void notifyPendingWorkItem(WorkItem workItem) {
		log.info("WorkItem " + workItem.getID() + " is now pending.");
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			getBwPresentation().removeGoalWorkItem(workItem.getOID());
		}
		else {
			getBwPresentation().removeTaskWorkItem(workItem.getOID());
		}
	}

	/**
	 * Notify the BWPresentation of completed WorkItems.
	 * @param workItem The pending WorkItem (i.e. State = COMPLETED).
	 */
	public void notifyCompletedWorkItem(WorkItem workItem) {
		log.info("WorkItem " + workItem.getID() + " is now completed.");
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			getBwPresentation().removeGoalWorkItem(workItem.getOID());
		}
		else {
			getBwPresentation().removeTaskWorkItem(workItem.getOID());
		}
	}

	/**
	 * Update the BWPresentation with all the active WorkItems.
	 */
	public void updateBWPresentation() {
		log.info("Update BWPresentation.");
		for (BWSpecification bwSpecification : BlendedWorkflow.getInstance().getBwSpecifications()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstances()) {
				for (WorkItem workItem : bwInstance.getWorkItems()) {
					if (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK))
						notifyEnabledWorkItem(workItem);
				}
			}
		}
	}

}
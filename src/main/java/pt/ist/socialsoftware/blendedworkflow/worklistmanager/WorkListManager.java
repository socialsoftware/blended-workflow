package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;

public class WorkListManager {

	protected BWPresentation bwPresentation = null;

	public BWPresentation getBwPresentation() {
		return bwPresentation;
	}

	public void setBwPresentation(BWPresentation bwPresentation) {
		this.bwPresentation = bwPresentation;
	}

	public void notifyEnabledWorkItem(WorkItem workItem) {
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			getBwPresentation().addGoalWorkItem(workItem.getOID(), workItem.getID());
		}
		else {
			getBwPresentation().addTaskWorkItem(workItem.getOID(), workItem.getID());
		}
	}

	public void notifySkippedWorkItem(WorkItem workItem) {
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			getBwPresentation().removeGoalWorkItem(workItem.getOID());
		}
		else {
			getBwPresentation().removeTaskWorkItem(workItem.getOID());
		}
	}

	public void notifyPendingWorkItem(WorkItem workItem) {
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			getBwPresentation().removeGoalWorkItem(workItem.getOID());
		}
		else {
			getBwPresentation().removeTaskWorkItem(workItem.getOID());
		}
	}

	public void notifyCompletedWorkItem(WorkItem workItem) {
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			getBwPresentation().removeGoalWorkItem(workItem.getOID());
		}
		else {
			getBwPresentation().removeTaskWorkItem(workItem.getOID());
		}
	}
	
	public void updateBWPresentation() {
		for (BWSpecification bwSpecification : BlendedWorkflow.getInstance().getBwSpecifications())
			for (BWInstance bwInstance : bwSpecification.getBwInstances()) {
				for (WorkItem workItem : bwInstance.getWorkItems()) {
					notifyEnabledWorkItem(workItem);
				}
			}
	}

}
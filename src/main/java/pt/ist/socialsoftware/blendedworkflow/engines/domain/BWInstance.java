package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class BWInstance extends BWInstance_Base {

	public BWInstance(BWSpecification bwSpecification, String id, String name) throws BlendedWorkflowException {
		setId(id);
		setName(name);
		setBwSpecification(bwSpecification);
		setGoalModelInstance(bwSpecification.getGoalModel().cloneGoalModel());
	}
	
	public WorkItem getWorkItem(String WorkItemID) throws BlendedWorkflowException {
		for (WorkItem workItem : getWorkItem())
			if (workItem.getId().equals(WorkItemID))
				return workItem;
		throw new BlendedWorkflowException("Non existing workItem");
	}

}

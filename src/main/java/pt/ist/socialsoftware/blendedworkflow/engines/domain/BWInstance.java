package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class BWInstance extends BWInstance_Base {

	public BWInstance(BWSpecification bwSpecification) throws BlendedWorkflowException {
		setBwSpecification(bwSpecification);
		setId(getBwSpecification().getName() + "." + getBwSpecification().getNewBWInstanceId()); //Id: BWSpecificationName.#
		setDataModelInstance(bwSpecification.getDataModel().cloneDataModel());
		
		GoalModelInstance goalModelInstance = new GoalModelInstance();
		setGoalModelInstance(goalModelInstance);
		bwSpecification.getGoalModel().cloneGoalModel(goalModelInstance);
	}

	public WorkItem getWorkItem(String id) throws BlendedWorkflowException {
		for (WorkItem workItem : getWorkItems())
			if (workItem.getId().equals(id))
				return workItem;
		throw new BlendedWorkflowException("Exception @WorkItem: The WorkItem id: " + id + "does not exist.");
	}

}
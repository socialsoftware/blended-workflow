package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class BWInstance extends BWInstance_Base {

	public BWInstance(BWSpecification bwSpecification) throws BlendedWorkflowException {
		setBwSpecification(bwSpecification);
		setId(getBwSpecification().getName() + "." + getBwSpecification().getNewBWInstanceId()); //Id: BWSpecificationName.#
		setWorkItemCounter(0);
		setDataModelInstance(bwSpecification.getDataModel().cloneDataModel());
		
		GoalModelInstance goalModelInstance = new GoalModelInstance();
		setGoalModelInstance(goalModelInstance);
		bwSpecification.getGoalModel().cloneGoalModel(goalModelInstance);
		
		TaskModelInstance taskModelInstance = new TaskModelInstance();
		setTaskModelInstance(taskModelInstance);
		bwSpecification.getTaskModel().cloneTaskModel(taskModelInstance);
	}
	
	public Integer getNewWorkItemId() {
		setWorkItemCounter(getWorkItemCounter()+1);
		return getWorkItemCounter();
	}

	public WorkItem getWorkItem(String id) throws BlendedWorkflowException {
		for (WorkItem workItem : getWorkItems())
			if (workItem.getId().equals(id))
				return workItem;
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_WORKITEM_ID, id);
	}

}
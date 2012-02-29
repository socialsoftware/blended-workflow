package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class BWSpecification extends BWSpecification_Base {

	public BWSpecification(String name) throws BlendedWorkflowException {
		checkUniqueBWSpecificationName(name);
		setName(name);
		setDataModel(new DataModel());
		setTaskModel(new TaskModel());
		setGoalModel(new GoalModel());
		setBwInstanceCounter(0);
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		blendedWorkflow.addBwSpecifications(this);

	}

	private void checkUniqueBWSpecificationName(String name) throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		for (BWSpecification bwSpecification : blendedWorkflow.getBwSpecifications()) {
			if (bwSpecification.getName().equals(name)) {
				throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_SPECIFICATION_NAME, name);
			}
		}
	}

	public int getNewBWInstanceId() {
		setBwInstanceCounter(getBwInstanceCounter()+1);
		return getBwInstanceCounter();
	}

}
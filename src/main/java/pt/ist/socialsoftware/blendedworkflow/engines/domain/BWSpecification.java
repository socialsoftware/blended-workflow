package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class BWSpecification extends BWSpecification_Base {

	public BWSpecification(String name) throws BlendedWorkflowException {
		checkUniqueBWSpecificationName(name);
		setName(name);
		setDataModel(new DataModel());
		setGoalModel(new GoalModel());
		setBwInstanceCounter(0);
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		blendedWorkflow.addBwSpecifications(this);

	}

	private void checkUniqueBWSpecificationName(String name) throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		for (BWSpecification bwSpecification : blendedWorkflow.getBwSpecifications()) {
			if (bwSpecification.getName().equals(name)) {
				throw new BlendedWorkflowException("Exception @BWSpecification: The BWSpecification name: " + name + "already exists.");
			}
		}
	}

	public int getNewBWInstanceId() {
		setBwInstanceCounter(getBwInstanceCounter()+1);
		return getBwInstanceCounter();
	}

}
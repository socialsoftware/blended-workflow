package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.*;

public class BlendedWorkflow extends BlendedWorkflow_Base {

	public BlendedWorkflow() {
		super();
	}

	public static BlendedWorkflow getInstance() {
		return FenixFramework.getRoot();
	}

	public BWSpecification getBWSpecification(String name) throws BlendedWorkflowException {
		for (BWSpecification bwSpecification : getBwSpecifications()) {
			if (bwSpecification.getName().equals(name))
				return bwSpecification;
		}
		throw new BlendedWorkflowException("Exception @BlendedWorkflow: The BWSpecification name: " + name + " does not exist.");

	}

	public BWInstance getBWInstance(String id) throws BlendedWorkflowException {
		for (BWSpecification bwSpecification : getBwSpecificationsSet()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstancesSet()) {
				if (bwInstance.getId().equals(id))
					return bwInstance;
			}
		}
		throw new BlendedWorkflowException("Exception @BlendedWorkflow: The BWInstance id: " + id + " does not exist.");
	}

}
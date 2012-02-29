package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.*;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

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
		throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_SPECIFICATION_NAME, name);
	}

	public BWInstance getBWInstance(String id) throws BlendedWorkflowException {
		for (BWSpecification bwSpecification : getBwSpecificationsSet()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstancesSet()) {
				if (bwInstance.getId().equals(id))
					return bwInstance;
			}
		}
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_CASE_ID, id);
	}

}
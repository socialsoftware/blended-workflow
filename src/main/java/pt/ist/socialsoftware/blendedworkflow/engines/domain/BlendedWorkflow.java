package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.*;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class BlendedWorkflow extends BlendedWorkflow_Base {
	
	private YAWLAdapter yawlAdapter = null;
	private WorkletAdapter workletAdapter = null;

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

	public BWInstance getBWInstance(String ID) throws BlendedWorkflowException {
		for (BWSpecification bwSpecification : getBwSpecificationsSet()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstancesSet()) {
				if (bwInstance.getID().equals(ID))
					return bwInstance;
			}
		}
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_CASE_ID, ID);
	}
	
	public BWInstance getYAWLBWInstance(String yawlCaseID) throws BlendedWorkflowException {
		for (BWSpecification bwSpecification : getBwSpecificationsSet()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstancesSet()) {
				if (bwInstance.getYawlCaseID().equals(yawlCaseID))
					return bwInstance;
			}
		}
		throw new BlendedWorkflowException(BlendedWorkflowError.NON_EXISTENT_CASE_ID, yawlCaseID);
	}

	public YAWLAdapter getYawlAdapter() throws BlendedWorkflowException {
		if (yawlAdapter == null) {
			yawlAdapter = new YAWLAdapter();
		}
		return yawlAdapter;
	}

	public void setYawlAdapter(YAWLAdapter yawlAdapter) {
		this.yawlAdapter = yawlAdapter;
	}

	public WorkletAdapter getWorkletAdapter() {
		if (workletAdapter == null) {
			workletAdapter = new WorkletAdapter();
		}
		return workletAdapter;
	}

	public void setWorkletAdapter(WorkletAdapter workletAdapter) {
		this.workletAdapter = workletAdapter;
	}

}
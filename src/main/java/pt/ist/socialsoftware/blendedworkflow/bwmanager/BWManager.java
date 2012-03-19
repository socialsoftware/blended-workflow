package pt.ist.socialsoftware.blendedworkflow.bwmanager;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;

public class BWManager {

	protected BWPresentation bwPresentation = null;

	public BWPresentation getBwPresentation() {
		return bwPresentation;
	}

	public void setBwPresentation(BWPresentation bwPresentation) {
		this.bwPresentation = bwPresentation;
	}

	public void notifyLoadedBWSpecification(BWSpecification bwSpecification) {
		getBwPresentation().addBWSpecification(bwSpecification.getOID(), bwSpecification.getName());
	}

	public void notifyCreatedBWInstance(BWInstance bwInstance) {
		getBwPresentation().addBWInstance(bwInstance.getOID(), bwInstance.getID());
	}

	public void updateBWPresentation() {
		for (BWSpecification bwSpecification : BlendedWorkflow.getInstance().getBwSpecifications()) {
			notifyLoadedBWSpecification(bwSpecification);
			for (BWInstance bwInstance : bwSpecification.getBwInstances()) {
				notifyCreatedBWInstance(bwInstance);
			}
		}
	}

}

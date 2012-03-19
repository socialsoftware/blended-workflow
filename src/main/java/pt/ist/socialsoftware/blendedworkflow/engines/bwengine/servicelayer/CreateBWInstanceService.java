package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateBWInstanceService {

	private BWSpecification bwSpecification;

	public CreateBWInstanceService(long bwSpecificationOID) {
		this.bwSpecification = AbstractDomainObject.fromOID(bwSpecificationOID);
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		// Get BWSpecification and clone it
		BWInstance bwInstance = new BWInstance(this.bwSpecification);
		
		// Launch case on YAWL
//		BWSpecification bwSpecification = bwInstance.getBwSpecification();
//		String yawlSpecificationID = bwSpecification.getYawlSpecficationID();
//		String yawlCaseID = BlendedWorkflow.getInstance().getYawlAdapter().launchCase(yawlSpecificationID);
//		bwInstance.setYawlCaseID(yawlCaseID);
		
		// Get Enabled Workitems;
		BlendedWorkflow.getInstance().getBwManager().notifyCreatedBWInstance(bwInstance);

		bwInstance.getGoalModelInstance().getEnabledWorkItems();
		
		for (WorkItem workItem : bwInstance.getWorkItems()) {
			BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(workItem);
		}
	}
}
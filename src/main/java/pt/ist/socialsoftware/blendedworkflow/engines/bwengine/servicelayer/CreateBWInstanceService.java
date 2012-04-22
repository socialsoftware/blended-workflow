package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import org.apache.log4j.Logger;

import jvstm.Atomic;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateBWInstanceService {

	private static Logger log = Logger.getLogger("CreateBWInstanceService");
	private BWSpecification bwSpecification;
	private String name;

	public CreateBWInstanceService(long bwSpecificationOID, String name) {
		this.bwSpecification = AbstractDomainObject.fromOID(bwSpecificationOID);
		this.name = name;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		log.info("Create BWInstance for BWSpecification " + this.bwSpecification.getName() + " with name " + name);
		
		// Get BWSpecification and clone it
		BWInstance bwInstance = new BWInstance(this.bwSpecification, name);
		
		// Launch case on YAWL
		BWSpecification bwSpecification = bwInstance.getBwSpecification();
		String yawlSpecificationID = bwSpecification.getYawlSpecficationID();
		String yawlCaseID = BlendedWorkflow.getInstance().getYawlAdapter().launchCase(yawlSpecificationID);
		bwInstance.setYawlCaseID(yawlCaseID);
		
		BlendedWorkflow.getInstance().getBwManager().notifyCreatedBWInstance(bwInstance);
		bwInstance.getGoalModelInstance().getEnabledWorkItems();
		bwInstance.getTaskModelInstance().getEnabledWorkItems();
	}
}
package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateBWInstanceService {

	private static Logger log = Logger.getLogger("CreateBWInstanceService");
	private BWSpecification bwSpecification;
	private String name;
	private String userID;

	public CreateBWInstanceService(long bwSpecificationOID, String name, String userID) {
		this.bwSpecification = AbstractDomainObject.fromOID(bwSpecificationOID);
		this.name = name;
		this.userID = userID;
	}

	public void execute() throws BlendedWorkflowException {
		// Create BWInstance
		Transaction.begin();
//		ThreadPool.getThreadExecutor().execute(Thread.currentThread());
		log.info("Create BWInstance for BWSpecification " + this.bwSpecification.getName() + " with name " + name);
		
		// GetUser
		User user = BlendedWorkflow.getInstance().getOrganizationalModel().getUser(userID);
		
		// Get BWSpecification and clone it
		BWInstance bwInstance = new BWInstance(this.bwSpecification, name, user);
		
		// Launch case on YAWL
		BWSpecification bwSpecification = bwInstance.getBwSpecification();
		String yawlSpecificationID = bwSpecification.getYawlSpecficationID();
		String yawlCaseID = BlendedWorkflow.getInstance().getYawlAdapter().launchCase(yawlSpecificationID);
		bwInstance.setYawlCaseID(yawlCaseID);
		log.info("Created BWInstance:" + bwInstance.getID() + " YAWL:" + bwInstance.getYawlCaseID());
		Transaction.commit();
		
		// Create GoalWorkItems and TaskWorkItems
		Transaction.begin();
		BlendedWorkflow.getInstance().getBwManager().notifyCreatedBWInstance(bwInstance);
		bwInstance.getGoalModelInstance().getEnabledWorkItems();
//		BlendedWorkflow.getInstance().getWorkletAdapter().createNewTaskWorkItems();
		Transaction.commit();
		
		// Notify TaskWorkItems
//		Transaction.begin();
//		BlendedWorkflow.getInstance().getWorkletAdapter().processNewTaskWorkItems();
//		// Test proposes only
//		bwInstance.getTaskModelInstance().getEnabledWorkItems();
//		Transaction.commit();
	}
}
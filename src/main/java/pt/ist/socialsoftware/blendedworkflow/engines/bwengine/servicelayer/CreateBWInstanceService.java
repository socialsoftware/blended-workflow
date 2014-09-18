package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateBWInstanceService implements Callable<String> {

	private static Logger log = Logger.getLogger("CreateBWInstanceService");
	private final BWSpecification bwSpecification;
	private final String name;
	private final String userID;

	public CreateBWInstanceService(long bwSpecificationOID, String name,
			String userID) {
		this.bwSpecification = FenixFramework
				.getDomainObject(bwSpecificationOID);
		this.name = name;
		this.userID = userID;
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		try {
			// GetUser
			User user = BlendedWorkflow.getInstance().getOrganizationalModel()
					.getUser(userID);

			// Get BWSpecification and clone it
			BWInstance bwInstance = new BWInstance(this.bwSpecification, name,
					user);

			// Launch case on YAWL
			BWSpecification bwSpecification = bwInstance.getBwSpecification();
			String yawlSpecificationID = bwSpecification
					.getYawlSpecficationID();
			String yawlCaseID = BlendedWorkflow.getInstance().getYawlAdapter()
					.launchCase(yawlSpecificationID);
			bwInstance.setYawlCaseID(yawlCaseID);

			// Create GoalWorkItems and TaskWorkItems
			BlendedWorkflow.getInstance().getBwManager()
					.notifyCreatedBWInstance(bwInstance);

		} catch (BlendedWorkflowException bwe) {
			log.error(bwe.getError());
			BlendedWorkflow.getInstance().getBwManager()
					.notifyException(bwe.getError());
		}
		Transaction.commit();
		log.info("END");
		return "CreateBWInstanceService:Sucess";
	}
}
package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.EntityInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.User;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateBWInstanceService implements Callable<String> {

	private static Logger log = Logger.getLogger("CreateBWInstanceService");
	private BWSpecification bwSpecification;
	private String name;
	private String userID;

	public CreateBWInstanceService(long bwSpecificationOID, String name, String userID) {
		this.bwSpecification = AbstractDomainObject.fromOID(bwSpecificationOID);
		this.name = name;
		this.userID = userID;
	}

	@Override
	public String call() throws Exception {
		log.info("Start");
		Transaction.begin();
		try {
			// GetUser
			User user = BlendedWorkflow.getInstance().getOrganizationalModel().getUser(userID);

			// Get BWSpecification and clone it
			BWInstance bwInstance = new BWInstance(this.bwSpecification, name, user);

			// Launch case on YAWL
			BWSpecification bwSpecification = bwInstance.getBwSpecification();
			String yawlSpecificationID = bwSpecification.getYawlSpecficationID();
			String yawlCaseID = BlendedWorkflow.getInstance().getYawlAdapter().launchCase(yawlSpecificationID);
			bwInstance.setYawlCaseID(yawlCaseID);

			// Create GoalWorkItems and TaskWorkItems
			BlendedWorkflow.getInstance().getBwManager().notifyCreatedBWInstance(bwInstance);

			// Create Patients
			populatePatients(bwInstance);

			bwInstance.getTaskModelInstance().getEnabledWorkItems(); //FIXME:Test proposes only 

		} catch (BlendedWorkflowException bwe) {
			log.error(bwe.getError());
			BlendedWorkflow.getInstance().getBwManager().notifyException(bwe.getError());
		}
		Transaction.commit();
		log.info("END");
		return "CreateBWInstanceService:Sucess";
	}

	private void populatePatients(BWInstance bwInstance) {
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();

		//PatientType
		Entity patient = dataModelInstance.getEntity("Patient");
		Attribute name = patient.getAttribute("Name");
		Attribute age = patient.getAttribute("Age");
		Attribute heartProblems = patient.getAttribute("Cardiac Problems");

		//Patient.1
		EntityInstance patient1 = new EntityInstance(dataModelInstance, patient);
		AttributeInstance patient1Name = new AttributeInstance(name, patient1);
		patient1Name.setValue("Davide Passinhas");
		AttributeInstance patient1Age = new AttributeInstance(age, patient1);
		patient1Age.setValue("23");
		AttributeInstance patient1HeartProblems = new AttributeInstance(heartProblems, patient1);
		patient1HeartProblems.setValue("true");

		//Patient.2
		EntityInstance patient2 = new EntityInstance(dataModelInstance, patient);
		AttributeInstance patient2Name = new AttributeInstance(name, patient2);
		patient2Name.setValue("David Martinho");
		AttributeInstance patient2Age = new AttributeInstance(age, patient2);
		patient2Age.setValue("26");
		AttributeInstance patient2HeartProblems = new AttributeInstance(heartProblems, patient2);
		patient2HeartProblems.setValue("false");

	}
}
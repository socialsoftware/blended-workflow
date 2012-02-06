package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateGoalService {

	private String bwInstanceID;
	private String goalId;
	private String goalName;
	private String entityName;
	private String attributeName;
	private BWInstance bwInstance;
	private BWSpecification bwSpecification;
	private DataModel dataModel;

	public CreateGoalService (String bwInstanceID, String goalId, String goalName, String entityName, String attributeName) {
		this.bwInstanceID = bwInstanceID;
		this.goalId = goalId;
		this.goalName = goalName;
		this.entityName = entityName;
		this.attributeName = attributeName;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		this.bwInstance = blendedWorkflow.getBWInstance(this.bwInstanceID);
		bwSpecification = this.bwInstance.getBwSpecification();
		dataModel = this.bwSpecification.getDataModel();

		// TODO: create data
		// ids must be generated automatically
		// cycle to add several entities and attributes
		Entity entity = new Entity("Entity-ID",entityName); 
		Attribute attribute = new Attribute ("Attribute-ID",attributeName, AttributeType.STRING, true);
		dataModel.addEntity(entity);
		dataModel.addAttribute(attribute);
		entity.addAttribute(attribute);

		// TODO: create goal
	}

}
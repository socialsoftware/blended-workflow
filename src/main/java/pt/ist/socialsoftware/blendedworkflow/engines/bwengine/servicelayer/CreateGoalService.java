package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.*;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateGoalService {

	private String bwInstanceID;
	private String parentGoalId;
	private String goalName;
	private String entityName;
	private String attributeName;
	private String condition;
	private BWInstance bwInstance;
	private BWSpecification bwSpecification;
	private DataModel dataModel;

	public CreateGoalService (String bwInstanceID, String parentGoalId, String goalName, String entityName, String attributeName, String condition) {
		this.bwInstanceID = bwInstanceID;
		this.parentGoalId = parentGoalId;
		this.goalName = goalName;
		this.entityName = entityName;
		this.attributeName = attributeName;
		this.condition = condition;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		this.bwInstance = blendedWorkflow.getBWInstance(this.bwInstanceID);
		bwSpecification = this.bwInstance.getBwSpecification();
		dataModel = this.bwSpecification.getDataModel();

		// TODO: create data
		Entity entity = new Entity("Entity-ID",entityName); 
		Attribute attribute = new Attribute ("Attribute-ID",attributeName, AttributeType.STRING, true);
		dataModel.addEntity(entity);
		dataModel.addAttribute(attribute);
		entity.addAttribute(attribute);
		
		// create condition
//		String rawcondition;
//		Condition cond = null;
//		try {
//			cond = ConditionFactory.createCondition(rawcondition, "this.dataModelURI", "dataModelID");
//			//conditionInstances.put(dataModelID, cond);
//		} catch (BlendedWorkflowException e) {
//			System.out.println(e.getMessage());
//		}
//		

			
		// TODO: create goal
//		Goal goal = new Goal ("1", goalName);
//		goal.setCondition(cond);
	}

}
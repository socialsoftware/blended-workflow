package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.*;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateGoalService {

	private String bwInstanceID;
	private String parentGoalId;
	private String goalName;
	private String condition;
	private BWInstance bwInstance;
	private BWSpecification bwSpecification;
	private DataModel dataModel;

	public CreateGoalService (String bwInstanceID, String parentGoalId, String goalName, String condition) {
		this.bwInstanceID = bwInstanceID;
		this.parentGoalId = parentGoalId;
		this.goalName = goalName;
		this.condition = condition;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		// conversor();
		
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		this.bwInstance = blendedWorkflow.getBWInstance(this.bwInstanceID);
		bwSpecification = this.bwInstance.getBwSpecification();
		dataModel = this.bwSpecification.getDataModel();
		GoalModel goalModel = this.bwSpecification.getGoalModel();
		
		//
		
		// create condition
		Condition cond = null;
		try {
			cond = ConditionFactory.createCondition(condition, "this.dataModelURI", "dataModelID");
			//conditionInstances.put(dataModelID, cond);
		} catch (BlendedWorkflowException e) {
			System.out.println(e.getMessage());
		}
		
		// create goal
		// call goal engine service??
		Goal goal = new Goal (goalModel,goalName);
		goal.setCondition(cond);
		goalModel.addGoal(goal);
		
		// verify enabled goals -> create GoalWorkItems
	}
	
	private void conversor () {
	}

}
package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateGoalService {

	private String bwInstanceID;
	private String parentGoalName;
	private String goalName;
	private String condition;
	private BWInstance bwInstance;
	private GoalModelInstance goalModelInstance;
	private DataModelInstance dataModelInstance;
	private Goal parentGoal;

	public CreateGoalService (String bwInstanceID, String parentGoalName, String goalName, String condition) {
		this.bwInstanceID = bwInstanceID;
		this.parentGoalName = parentGoalName;
		this.goalName = goalName;
		this.condition = condition;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		BlendedWorkflow blendedWorkflow = BlendedWorkflow.getInstance();
		this.bwInstance = blendedWorkflow.getBWInstance(this.bwInstanceID);
		this.goalModelInstance = bwInstance.getGoalModelInstance();
		this.dataModelInstance = bwInstance.getDataModelInstance();
		this.parentGoal = this.goalModelInstance.getGoal(parentGoalName);

		System.out.println("Create new Goal: \"" + this.goalName + "\"");

		Condition newGoalCondition = ConditionFactory.createCondition(dataModelInstance, condition);
		new Goal(this.goalModelInstance, this.parentGoal, this.goalName, newGoalCondition);

		System.out.println("Actual Enabled WorkItems:");
		for (WorkItem workItem : this.bwInstance.getWorkItems()) {
			if (workItem.getState() == WorkItemState.ENABLED) {
				System.out.println(workItem.getId());
			}
		}
		System.out.println("ReCheck Enabled WorkItems:"); // TODO call this method after workitemState changed to achieved
		bwInstance.getGoalModelInstance().getEnabledWorkItems();
		for (WorkItem workItem : this.bwInstance.getWorkItems()) {
			if (workItem.getState() == WorkItemState.ENABLED) {
				System.out.println(workItem.getId());
			}
		}
	}

	//	private void converter () {
	//	}

}
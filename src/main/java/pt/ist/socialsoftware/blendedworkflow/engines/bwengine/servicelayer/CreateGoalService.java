package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.ConditionFactory;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateGoalService {

	private BWInstance bwInstance;
	private Goal parentGoal;
	private String name;
	private String condition;

	public CreateGoalService (BWInstance bwInstance, Goal parentGoal, String name, String condition) {
		this.bwInstance = bwInstance;
		this.parentGoal = parentGoal;
		this.name = name;
		this.condition = condition;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

		Condition goalCondition = ConditionFactory.createCondition(dataModelInstance, condition);
		new Goal(goalModelInstance, parentGoal, name, goalCondition);

		goalModelInstance.getEnabledWorkItems();
	}
}
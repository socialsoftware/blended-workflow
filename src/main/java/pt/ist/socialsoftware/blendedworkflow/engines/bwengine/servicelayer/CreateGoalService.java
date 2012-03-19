package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer;

import jvstm.Atomic;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.ConditionFactory;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class CreateGoalService {

	private BWInstance bwInstance;
	private Goal parentGoal;
	private String name;
	private String condition;

	public CreateGoalService (long bwInstanceOID, String name, long parentGoalOID,  String condition) {
		this.bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		this.parentGoal = AbstractDomainObject.fromOID(parentGoalOID);
		this.name = name;
		this.condition = condition;
	}

	@Atomic
	public void execute() throws BlendedWorkflowException {
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

		Condition goalCondition = ConditionFactory.createCondition(dataModelInstance, condition);
		Goal newGoal = new Goal(goalModelInstance, parentGoal, name, goalCondition);

		newGoal.updateParentGoal();
		
		goalModelInstance.getEnabledWorkItems();
		
		// FIXME;
		for (WorkItem workItem : bwInstance.getWorkItems()) {
			if (workItem.getID().equals(name+"."+ bwInstance.getWorkItemsCount())) {
				BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(workItem);
			}
		}
	}
}
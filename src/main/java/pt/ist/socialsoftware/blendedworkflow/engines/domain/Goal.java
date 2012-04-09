package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.ArrayList;
import java.util.List;

import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class Goal extends Goal_Base {

	public enum GoalState {DEACTIVATED, ENABLED, SKIPPED, ACHIEVED};

	public Goal(GoalModel goalModel, String name, String description, Condition condition) throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		setCondition(condition);
		setState(GoalState.DEACTIVATED);
	}

	public Goal(GoalModel goalModel, Goal parentGoal, String name,String description, Condition condition) throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		setCondition(condition);
		setParentGoal(parentGoal);
		setState(GoalState.DEACTIVATED);
	}

	private void checkUniqueGoalName(GoalModel goalModel, String name) throws BlendedWorkflowException {
		for (Goal goal : goalModel.getGoals()) {
			if (goal.getName().equals(name)) {
				throw new BlendedWorkflowException(BlendedWorkflowError.INVALID_GOAL_NAME, name);
			}
		}
	}

	public void cloneGoal(GoalModelInstance goalModelInstance) throws BlendedWorkflowException {
		Condition newCondition = null;
		Condition condition = getCondition();
		if (condition != null) {
			newCondition = condition.cloneCondition(goalModelInstance);
		}
		new Goal(goalModelInstance, getName(), getDescription(), newCondition);
	}

	public void checkState(BWInstance bwInstance) {
		int subgoalsAchievedCount = 0;

		if (getState() == GoalState.DEACTIVATED) {
			if (getSubGoalsCount() > 0) { 
				for (Goal goal : getSubGoals()) {
					if ( (goal.getState() == GoalState.ACHIEVED) && (goal.getState() == GoalState.SKIPPED)) {
						subgoalsAchievedCount++;
					}
				}
				if (getSubGoalsCount() == subgoalsAchievedCount) { // SubGoals achieved
					setState(GoalState.ENABLED);
					if (!getParentGoal().equals(null) && getParentGoal().getState().equals(GoalState.ENABLED)) {
						if (getParentGoal().getGoalWorkItem().getState().equals(WorkItemState.PENDING)) {
							getParentGoal().getGoalWorkItem().notifyConstrainViolation();
						}
					}
					else {
						new GoalWorkItem(bwInstance, this);
					}
				}
			}
			else { // No SubGoals
				if (getState() == GoalState.DEACTIVATED) {
					setState(GoalState.ENABLED);
					if (!getParentGoal().equals(null) && getParentGoal().getState().equals(GoalState.ENABLED)) {
						if (getParentGoal().getGoalWorkItem().getState().equals(WorkItemState.PENDING)) {
							getParentGoal().getGoalWorkItem().notifyConstrainViolation();
						}
					}
					else {
						new GoalWorkItem(bwInstance, this);
					}
				}
			}
		}
	}

	public void updateParentGoal() {
		Goal parentGoal = getParentGoal();
		if (parentGoal.getState().equals(GoalState.ENABLED)) {
			parentGoal.setState(GoalState.DEACTIVATED);
			parentGoal.getGoalWorkItem().notifyPending();
		}
	}
	
	public String getConditionData() {
		String dataString = getCondition().getData();
		String r ="";
		
		String[] elementArr = dataString.split("\\.");
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < elementArr.length ; i++) {
			String element = elementArr[i];
			if (!result.contains(element)) {
				result.add(element);
			}
		}
		
		if (result.size() == 1) {
			r += result.get(0);
		}
		else {
			for (int i = 0; i < result.size()-1 ; i++) {
				r += result.get(i) + ", ";
			}
			r += result.get(result.size()-1);
		}
		return r;
	}
	
	public String getSubGoalsData() {
		String r = "";
		Boolean first = true;
		for (Goal subGoal : getSubGoals()) {
			String subGoalDataString = subGoal.getConditionData();
			if (first) {
				r += subGoalDataString;
			}
			else {
				r += ", " + subGoalDataString;
			}
		}
		return r;
	}

	public List<Entity> getEntities() {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		
		String dataString = getCondition().getEntities();
		String[] elementArr = dataString.split("\\.");
		
		for (int i = 0; i < elementArr.length ; i++) {
			String element = elementArr[i];
			if (!entities.contains(element) && !element.equals("null")) {
				Entity entity = AbstractDomainObject.fromOID(new Long(element));
				entities.add(entity);
			}
		}
		return entities;
	}

	public List<Attribute> getAttributes() {
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		
		String dataString = getCondition().getAttributes();
		String[] elementArr = dataString.split("\\.");
		
		for (int i = 0; i < elementArr.length ; i++) {
			String element = elementArr[i];
			if (!attributes.contains(element) && !element.equals("null")) {
				Attribute entity = AbstractDomainObject.fromOID(new Long(element));
				attributes.add(entity);
			}
		}
		return attributes;
	}

}
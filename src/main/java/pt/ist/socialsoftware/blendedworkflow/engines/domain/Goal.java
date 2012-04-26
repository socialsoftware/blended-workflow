package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class Goal extends Goal_Base {

	public enum GoalState {DEACTIVATED, ENABLED, SKIPPED, ACHIEVED};

	/**
	 * Create the GoalTree root Goal.
	 */
	public Goal(GoalModel goalModel, String name, String description, Condition condition) throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		setCondition(condition);
		setState(GoalState.DEACTIVATED);
		setParentGoal(null);
		BlendedWorkflow.getInstance().getOrganizationalModel().setDefault(this);
	}

	/**
	 * Create a Goal.
	 */
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

	/**
	 * Get the Goal condition data to use in the use interface.
	 * @return a string with the condition data entities.
	 */
	public String getConstraintData() {
		Set<Entity> entities = getCondition().getEntities();
		Set<Attribute> attributes = getCondition().getAttributes();
		String dataString = "";

		// Add Attribute entities
		for (Attribute attribute : attributes) {
			entities.add(attribute.getEntity());
		}

		// Create String
		int count = 0;
		for (Entity entity : entities) {
			if (entities.size() == 1 || count < entities.size()-1) {
				dataString += entity.getName();
			} else {
				dataString += entity.getName() + ", ";
			}
			count++;
		}
		return dataString;
	}

	/**
	 * Get the subGoals conditions data to use in the use interface.
	 * @return a string with the condition data entities.
	 */
	public String getSubGoalsData() {
		String dataString = "";
		Boolean first = true;
		for (Goal subGoal : getSubGoals()) {
			String subGoalDataString = subGoal.getConstraintData();
			if (first) {
				dataString += subGoalDataString;
			}
			else {
				dataString += ", " + subGoalDataString;
			}
		}
		return dataString;
	}

	/**********************
	 * Check Goals to create new WorkItems.
	 **********************/
	public void checkState(BWInstance bwInstance) {
		int subgoalsAchievedCount = 0;

		if (getState() == GoalState.DEACTIVATED) {
			if (getSubGoalsCount() > 0) { 
				for (Goal goal : getSubGoals()) {
					if ( (goal.getState() == GoalState.ACHIEVED) || (goal.getState() == GoalState.SKIPPED)) {
						subgoalsAchievedCount++;
					}
				}
				if (getSubGoalsCount() == subgoalsAchievedCount) { // SubGoals achieved
					setState(GoalState.ENABLED);
					if (getParentGoal() != null && getParentGoal().getState().equals(GoalState.ENABLED)) {
						if (getParentGoal().getGoalWorkItem().getState().equals(WorkItemState.GOAL_PENDING)) {
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
						if (getParentGoal().getGoalWorkItem().getState().equals(WorkItemState.GOAL_PENDING)) {
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

}
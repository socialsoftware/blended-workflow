package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class AchieveGoal extends AchieveGoal_Base {

	/**
	 * Create the GoalTree root Goal.
	 */
	public AchieveGoal(GoalModel goalModel, String name, String description,
			Condition condition, Entity context)
			throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		setSucessCondition(condition);
		setParentGoal(null);
		setEntityContext(context);
	}

	/**
	 * Create a Goal.
	 */
	public AchieveGoal(GoalModel goalModel, AchieveGoal parentGoal,
			String name, String description, Condition condition, Entity context)
			throws BlendedWorkflowException {
		checkUniqueGoalName(goalModel, name);
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		setSucessCondition(condition);
		setParentGoal(parentGoal);
		setEntityContext(context);
	}

	private void checkUniqueGoalName(GoalModel goalModel, String name)
			throws BlendedWorkflowException {
		for (AchieveGoal goal : goalModel.getAchieveGoalsSet()) {
			if (goal.getName().equals(name)) {
				throw new BlendedWorkflowException(
						BlendedWorkflowError.INVALID_GOAL_NAME, name);
			}
		}
		for (MaintainGoal goal : goalModel.getMaintainGoalsSet()) {
			if (goal.getName().equals(name)) {
				throw new BlendedWorkflowException(
						BlendedWorkflowError.INVALID_GOAL_NAME, name);
			}
		}
	}

	public void cloneGoal(GoalModelInstance goalModelInstance)
			throws BlendedWorkflowException {
		Condition newSucessCondition = null;
		Condition condition = getSucessCondition();
		if (condition != null) {
			newSucessCondition = condition.cloneCondition(goalModelInstance);
		}

		// Get EntityTypeContext from Template
		BWInstance bwInstance = goalModelInstance.getBwInstance();
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		Entity newEntityContext = null;
		for (Entity entity : dataModelInstance.getEntitiesSet()) {
			if (getEntityContext().getName().equals(entity.getName())) {
				newEntityContext = entity;
			}
		}

		AchieveGoal newGoal = new AchieveGoal(goalModelInstance, getName(),
				getDescription(), newSucessCondition, newEntityContext);
		newGoal.setUser(getUser());
		newGoal.setRole(getRole());

		for (Condition activateCondition : getActivateConditionsSet()) {
			Condition newActivateCondition = activateCondition
					.cloneCondition(goalModelInstance);
			newGoal.addActivateConditions(newActivateCondition);
		}
	}

	/**
	 * Get the Goal condition data to use in the use interface.
	 * 
	 * @return a string with the condition data entities.
	 */
	public String getConstraintData() {
		Set<Entity> entities = getSucessCondition().getEntities();
		Set<Attribute> attributes = getSucessCondition().getAttributes();
		String dataString = "";

		// Add Attribute entities
		for (Attribute attribute : attributes) {
			entities.add(attribute.getEntity());
		}

		// Create String
		int count = 0;
		for (Entity entity : entities) {
			if (entities.size() == 1) {
				dataString += entity.getName();
			} else if (count < entities.size() - 1) {
				dataString += entity.getName() + ", ";
				;
			} else {
				dataString += entity.getName();
			}
			count++;
		}
		return dataString;
	}

	public String getPreConstraintData() {
		List<Condition> activateConditions = new ArrayList<Condition>(
				getActivateConditionsSet());
		Set<Entity> entities = activateConditions.get(0).getEntities(); // FIXME:
																		// Only
																		// First
																		// ActivateConditionData
		Set<Attribute> attributes = activateConditions.get(0).getAttributes();
		String dataString = "";

		// Add Attribute entities
		for (Attribute attribute : attributes) {
			entities.add(attribute.getEntity());
		}

		// Create String
		int count = 0;
		for (Entity entity : entities) {
			if (entities.size() == 1) {
				dataString += entity.getName();
			} else if (count < entities.size() - 1) {
				dataString += entity.getName() + ", ";
				;
			} else {
				dataString += entity.getName();
			}
			count++;
		}
		return dataString;
	}

	/**
	 * Check GoalWorkitems in the ACTIVATED state.
	 */
	public void checkActivated(BWInstance bwInstance) {
		if (!getGoalWorkItemsSet().isEmpty()) {
			for (GoalWorkItem goalWorkItem : getGoalWorkItemsSet()) {
				if (goalWorkItem.getState().equals(GoalState.ACTIVATED)) {
					goalWorkItem.notifyDataChanged();
				}
			}
		}
	}

	/**
	 * Check if exists a goalWorkitem for a given context
	 * 
	 * @param goal
	 * @param entityInstanceContext
	 * @return
	 */
	public Boolean goalWorkItemsExistForTheContext(
			EntityInstance entityInstanceContext) {
		Boolean alreadyExists = false;
		for (GoalWorkItem goalWorkItem : getGoalWorkItemsSet()) {
			if (goalWorkItem.getEntityInstanceContext().equals(
					entityInstanceContext)) {
				alreadyExists = true;
			}
		}
		return alreadyExists;
	}

	// TODO:
	public Set<Entity> getSubGoalsContext() {
		Set<Entity> result = new HashSet<Entity>();
		// result.add(getEntityContext());
		for (AchieveGoal subGoal : getSubGoalsSet()) {
			result.add(subGoal.getEntityContext());
		}

		return result;
	}

}
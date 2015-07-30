package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class Goal extends Goal_Base {

    @Override
    public void setName(String name) {
        checkUniqueGoalName(name);
        super.setName(name);
    }

    public Goal(BWGoalModel goalModel, String name) {
        setGoalModel(goalModel);
        setName(name);
    }

    /**
     * Create the GoalTree root Goal.
     */
    public Goal(BWGoalModel goalModel, String name, String description,
            Condition condition, BWEntity context) throws BWException {
        setGoalModel(goalModel);
        setName(name);
        setDescription(description);
        addSuccessCondition(condition);
        setParentGoal(null);
        setEntityContext(context);
    }

    /**
     * Create a Goal.
     */
    public Goal(BWGoalModel goalModel, Goal parentGoal, String name,
            String description, Condition condition, BWEntity context)
                    throws BWException {
        setGoalModel(goalModel);
        setName(name);
        setDescription(description);
        addSuccessCondition(condition);
        setParentGoal(parentGoal);
        setEntityContext(context);
    }

    private void checkUniqueGoalName(String name) throws BWException {
        for (Goal goal : this.getGoalModel().getGoalSet()) {
            if (goal.getName() != null && goal.getName().equals(name)) {
                throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
            }
        }
        for (MaintainGoal goal : this.getGoalModel().getMaintainGoalsSet()) {
            if (goal.getName() != null && goal.getName().equals(name)) {
                throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
            }
        }
    }

    public void cloneGoal(GoalModelInstance goalModelInstance)
            throws BWException {
        Condition newSucessCondition = null;
        Condition condition = getSuccessConditionSet().stream().findFirst()
                .get();
        if (condition != null) {
            newSucessCondition = condition.cloneCondition(goalModelInstance);
        }

        // Get EntityTypeContext from Template
        BWInstance bwInstance = goalModelInstance.getBwInstance();
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        BWEntity newEntityContext = null;
        for (BWEntity entity : dataModelInstance.getEntitiesSet()) {
            if (getEntityContext().getName().equals(entity.getName())) {
                newEntityContext = entity;
            }
        }

        Goal newGoal = new Goal(goalModelInstance, getName(), getDescription(),
                newSucessCondition, newEntityContext);
        newGoal.setUser(getUser());
        newGoal.setRole(getRole());

        for (Condition activateCondition : getActivationConditionSet()) {
            Condition newActivateCondition = activateCondition
                    .cloneCondition(goalModelInstance);
            newGoal.addActivationCondition(newActivateCondition);
        }
    }

    /**
     * Get the Goal condition data to use in the use interface.
     * 
     * @return a string with the condition data entities.
     */
    public String getConstraintData() {
        Set<BWEntity> entities = getSuccessConditionSet().stream().findFirst()
                .get().getEntities();
        Set<BWAttribute> attributes = getSuccessConditionSet().stream()
                .findFirst().get().getAttributes();
        String dataString = "";

        // Add Attribute entities
        for (BWAttribute attribute : attributes) {
            entities.add(attribute.getEntity());
        }

        // Create String
        int count = 0;
        for (BWEntity entity : entities) {
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
                getActivationConditionSet());
        Set<BWEntity> entities = activateConditions.get(0).getEntities(); // FIXME:
                                                                          // Only
                                                                          // First
                                                                          // ActivateConditionData
        Set<BWAttribute> attributes = activateConditions.get(0).getAttributes();
        String dataString = "";

        // Add Attribute entities
        for (BWAttribute attribute : attributes) {
            entities.add(attribute.getEntity());
        }

        // Create String
        int count = 0;
        for (BWEntity entity : entities) {
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
            if (goalWorkItem.getEntityInstanceContext()
                    .equals(entityInstanceContext)) {
                alreadyExists = true;
            }
        }
        return alreadyExists;
    }

    // TODO:
    public Set<BWEntity> getSubGoalsContext() {
        Set<BWEntity> result = new HashSet<BWEntity>();
        // result.add(getEntityContext());
        for (Goal subGoal : getSubGoalsSet()) {
            result.add(subGoal.getEntityContext());
        }

        return result;
    }

    public void delete() {
        setGoalModel(null);

        deleteDomainObject();
    }

}
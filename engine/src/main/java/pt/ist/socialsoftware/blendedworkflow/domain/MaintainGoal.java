package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class MaintainGoal extends MaintainGoal_Base {

    public enum MaintainGoalState {
        DEACTIVATED, ENABLED
    };

    public MaintainGoal(BWGoalModel goalModel, String name, String description,
            Condition condition, BWEntity context) throws BWException {
        checkUniqueGoalName(goalModel, name);
        setGoalModel(goalModel);
        setName(name);
        setDescription(description);
        setMaintainCondition(condition);
        setMaintainGoalEntityContext(context);
        setState(MaintainGoalState.ENABLED);
    }

    private void checkUniqueGoalName(BWGoalModel goalModel, String name)
            throws BWException {
        for (AchieveGoal goal : goalModel.getAchieveGoalsSet()) {
            if (goal.getName().equals(name)) {
                throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
            }
        }
        for (MaintainGoal goal : goalModel.getMaintainGoalsSet()) {
            if (goal.getName().equals(name)) {
                throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
            }
        }
    }

    public void cloneGoal(GoalModelInstance goalModelInstance)
            throws BWException {
        Condition newMaintainCondition = null;
        Condition condition = getMaintainCondition();
        if (condition != null) {
            newMaintainCondition = condition.cloneCondition(goalModelInstance);
        }

        // Get EntityTypeContext from Template
        BWInstance bwInstance = goalModelInstance.getBwInstance();
        DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
        BWEntity newEntityContext = null;
        for (BWEntity entity : dataModelInstance.getEntitiesSet()) {
            if (getMaintainGoalEntityContext().getName()
                    .equals(entity.getName())) {
                newEntityContext = entity;
            }
        }

        new MaintainGoal(goalModelInstance, getName(), getDescription(),
                newMaintainCondition, newEntityContext);
    }
}

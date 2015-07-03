package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class GoalModel extends GoalModel_Base {

    /**
     * Clone the GoalModel tree
     */
    public void cloneGoalModel(GoalModelInstance newGoalModelInstance)
            throws BWException {
        for (MaintainGoal maintainGoal : getMaintainGoalsSet()) {
            maintainGoal.cloneGoal(newGoalModelInstance);
        }

        for (AchieveGoal goal : getAchieveGoalsSet()) {
            goal.cloneGoal(newGoalModelInstance);
        }

        for (AchieveGoal goal : getAchieveGoalsSet()) {
            for (AchieveGoal subgoal : goal.getSubGoalsSet()) {
                newGoalModelInstance.getGoal(goal.getName()).addSubGoals(
                        newGoalModelInstance.getGoal(subgoal.getName()));
            }
        }
    }

    public AchieveGoal getGoal(String name) throws BWException {
        for (AchieveGoal goal : getAchieveGoalsSet()) {
            if (goal.getName().equals(name)) {
                return goal;
            }
        }
        throw new BWException(BlendedWorkflowError.NON_EXISTENT_GOAL_NAME,
                name);
    }

    public MaintainGoal getMaintainGoal(String name) throws BWException {
        for (MaintainGoal goal : getMaintainGoalsSet()) {
            if (goal.getName().equals(name)) {
                return goal;
            }
        }
        throw new BWException(BlendedWorkflowError.NON_EXISTENT_GOAL_NAME,
                name);
    }

    public void delete() {
        setSpecification(null);
        deleteDomainObject();
    }
}
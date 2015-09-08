package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWGoalModel extends BWGoalModel_Base {

    /**
     * Clone the GoalModel tree
     */
    public void cloneGoalModel(GoalModelInstance newGoalModelInstance)
            throws BWException {
        for (MaintainGoal maintainGoal : getMaintainGoalsSet()) {
            maintainGoal.cloneGoal(newGoalModelInstance);
        }

        for (Goal goal : getGoalSet()) {
            goal.cloneGoal(newGoalModelInstance);
        }

        for (Goal goal : getGoalSet()) {
            for (Goal subgoal : goal.getSubGoalsSet()) {
                newGoalModelInstance.getGoal(goal.getName()).addSubGoals(
                        newGoalModelInstance.getGoal(subgoal.getName()));
            }
        }
    }

    public Goal getGoal(String name) throws BWException {
        for (Goal goal : getGoalSet()) {
            if (goal.getName().equals(name)) {
                return goal;
            }
        }
        throw new BWException(BWErrorType.NON_EXISTENT_GOAL_NAME, name);
    }

    public MaintainGoal getMaintainGoal(String name) throws BWException {
        for (MaintainGoal goal : getMaintainGoalsSet()) {
            if (goal.getName().equals(name)) {
                return goal;
            }
        }
        throw new BWException(BWErrorType.NON_EXISTENT_GOAL_NAME, name);
    }

    public void clean() {
        getGoalSet().stream().forEach(goal -> goal.delete());
    }

    public void delete() {
        clean();
        setSpecification(null);

        deleteDomainObject();
    }

    public Goal merge(Goal goalOne, Goal goalTwo) {
        // TODO Auto-generated method stub
        return null;
    }

}
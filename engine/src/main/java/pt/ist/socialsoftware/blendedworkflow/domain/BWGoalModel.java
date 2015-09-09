package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.stream.Stream;

import pt.ist.socialsoftware.blendedworkflow.domain.Goal.GoalRelation;
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
            for (Goal subgoal : goal.getSubGoalSet()) {
                newGoalModelInstance.getGoal(goal.getName()).addSubGoal(
                        newGoalModelInstance.getGoal(subgoal.getName()));
            }
        }
    }

    public boolean existsGoal(String name) {
        return getGoalSet().stream()
                .anyMatch(goal -> goal.getName().equals(name));
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

    public Goal mergeGoals(Goal goalOne, Goal goalTwo) {
        Goal result = null;

        GoalRelation relation = goalOne.getGoalRelation(goalTwo);

        if (relation == GoalRelation.OTHER) {
            throw new BWException(BWErrorType.UNMERGEABLE_GOALS);
        } else if (relation == GoalRelation.SIBLING) {
            result = mergeSiblingGoals(goalOne, goalTwo);
        } else if (relation == GoalRelation.PARENT) {
            result = mergeParentChildGoals(goalTwo, goalOne);
        } else if (relation == GoalRelation.CHILD) {
            result = mergeParentChildGoals(goalOne, goalTwo);
        }

        return result;
    }

    private Goal mergeSiblingGoals(Goal goalOne, Goal goalTwo) {
        Goal result;
        String name = goalOne.getName() + "-" + goalTwo.getName();
        while (existsGoal(name))
            name = name + ".1";

        result = new Goal(this, name);
        result.setParentGoal(goalOne.getParentGoal());

        Stream.concat(goalOne.getSubGoalSet().stream(),
                goalTwo.getSubGoalSet().stream())
                .forEach((goal) -> result.addSubGoal(goal));

        Stream.concat(goalOne.getSuccessConditionSet().stream(),
                goalTwo.getSuccessConditionSet().stream())
                .forEach((cond) -> result.addSuccessCondition(cond));

        Stream.concat(goalOne.getActivationConditionSet().stream(),
                goalTwo.getActivationConditionSet().stream())
                .forEach((cond) -> result.addActivationCondition(cond));
        result.purgeActivationCondition();

        Stream.concat(goalOne.getEntityInvariantConditionSet().stream(),
                goalTwo.getEntityInvariantConditionSet().stream())
                .forEach((cond) -> result.addEntityInvariantCondition(cond));

        Stream.concat(goalOne.getAttributeInvariantConditionSet().stream(),
                goalTwo.getAttributeInvariantConditionSet().stream())
                .forEach((rule) -> result.addAttributeInvariantCondition(rule));

        goalOne.delete();
        goalTwo.delete();
        return result;
    }

    private Goal mergeParentChildGoals(Goal parentGoal, Goal childGoal) {
        parentGoal.checkCanMergeChild(childGoal);

        childGoal.getSubGoalSet().stream()
                .forEach((goal) -> parentGoal.addSubGoal(goal));

        childGoal.getSuccessConditionSet().stream()
                .forEach((cond) -> parentGoal.addSuccessCondition(cond));

        childGoal.getActivationConditionSet().stream()
                .forEach((cond) -> parentGoal.addActivationCondition(cond));
        parentGoal.purgeActivationCondition();

        childGoal.getEntityInvariantConditionSet().stream()
                .forEach((mul) -> parentGoal.addEntityInvariantCondition(mul));

        childGoal.getAttributeInvariantConditionSet().stream().forEach(
                (rule) -> parentGoal.addAttributeInvariantCondition(rule));

        childGoal.delete();

        return parentGoal;
    }

}
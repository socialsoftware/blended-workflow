package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GoalModelInstance extends GoalModelInstance_Base {

    @Override
    public Goal getGoal(String name) throws BWException {
        for (Goal goal : getGoalSet()) {
            if (goal.getName().equals(name)) {
                return goal;
            }
        }
        throw new BWException(BWErrorType.NON_EXISTENT_GOAL_NAME, name);
    }

    /**
     * Check all GoalWorkItems in the ACTIVATED state, due to the achievement or
     * skipping of a goalWorkItem.
     */
    public void checkActivatedWorkItems() {
        for (Goal goal : getGoalSet()) {
            goal.checkActivated(getBwInstance());
        }
    }

    /**
     * Given an AchieveGoal, gets all related MaintainGoals.
     * 
     * @param achieveGoal
     *            An AchieveGoal.
     * @return a set containing all the related MaintainGoals.
     */
    public Set<MaintainGoal> getAchieveGoalAssociatedMaintainGoals(
            Goal achieveGoal) {
        Set<MaintainGoal> maintainGoals = new HashSet<MaintainGoal>();

        for (MaintainGoal maintainGoal : getMaintainGoalsSet()) {
            // Get AchieveGoal Conditions Data
            Set<Attribute> achieveAttributes = new HashSet<Attribute>();
            Set<Entity> achieveEntities = new HashSet<Entity>();
            for (Condition ac : achieveGoal.getActivationConditionSet()) {
                achieveAttributes.addAll(ac.getAttributeSet());
                achieveEntities.addAll(ac.getEntities());
            }
            achieveAttributes.addAll(achieveGoal.getSuccessConditionSet()
                    .stream().findFirst().get().getAttributeSet());
            achieveEntities.addAll(achieveGoal.getSuccessConditionSet().stream()
                    .findFirst().get().getEntities());

            // Get MaintainGoal Condition Data
            Set<Attribute> maintainAttributes = new HashSet<Attribute>();
            Set<Entity> maintainEntities = new HashSet<Entity>();
            maintainAttributes.addAll(
                    maintainGoal.getMaintainCondition().getAttributeSet());
            maintainEntities
                    .addAll(maintainGoal.getMaintainCondition().getEntities());

            achieveAttributes.retainAll(maintainAttributes);
            achieveEntities.retainAll(maintainEntities);

            if (!achieveAttributes.isEmpty() || !achieveEntities.isEmpty()) {
                maintainGoals.add(maintainGoal);
            }
        }

        return maintainGoals;
    }

    /**
     * Given an AchieveGoal, gets all related MaintainGoals.
     * 
     * @param achieveGoal
     *            An AchieveGoal.
     * @return a set containing all the related MaintainGoals.
     */
    public Set<Goal> getAchieveGoalAssociatedAchieveGoals(Goal achieveGoal) {
        Set<Goal> achieveGoalsRESULT = new HashSet<Goal>();

        for (Goal ag : getGoalSet()) {
            if (!achieveGoal.equals(ag)) {
                // Given AchieveGoal Conditions Data
                Set<Attribute> achieveAttributes = new HashSet<Attribute>();
                Set<Entity> achieveEntities = new HashSet<Entity>();
                achieveAttributes.addAll(achieveGoal.getSuccessConditionSet()
                        .stream().findFirst().get().getAttributeSet());
                achieveEntities.addAll(achieveGoal.getSuccessConditionSet()
                        .stream().findFirst().get().getEntities());

                // For AchieveGoal Conditions Data
                Set<Attribute> agAttributes = new HashSet<Attribute>();
                Set<Entity> agEntities = new HashSet<Entity>();
                for (Condition ac : ag.getActivationConditionSet()) {
                    agAttributes.addAll(ac.getAttributeSet());
                    agEntities.addAll(ac.getEntities());
                }
                agAttributes.addAll(ag.getSuccessConditionSet().stream()
                        .findFirst().get().getAttributeSet());
                agEntities.addAll(ag.getSuccessConditionSet().stream()
                        .findFirst().get().getEntities());

                achieveAttributes.retainAll(agAttributes);
                achieveEntities.retainAll(agEntities);

                if (!achieveAttributes.isEmpty()
                        || !achieveEntities.isEmpty()) {
                    achieveGoalsRESULT.add(ag);
                }
            }
        }
        return achieveGoalsRESULT;
    }

    public Set<RelationBW> getSubGoalsRelations(Goal topGoal) {
        Set<RelationBW> result = new HashSet<RelationBW>();

        DataModelInstance dataModelInstance = getBwInstance()
                .getDataModelInstance();
        for (Goal subGoal : topGoal.getSubGoalSet()) {
            result.addAll(dataModelInstance.getRelations(
                    topGoal.getEntityContext(), subGoal.getEntityContext()));
        }

        return result;
    }
}
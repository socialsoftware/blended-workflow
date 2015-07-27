package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class GoalModelInstance extends GoalModelInstance_Base {

    @Override
    public AchieveGoal getGoal(String name) throws BWException {
        for (AchieveGoal goal : getAchieveGoalsSet()) {
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
        for (AchieveGoal goal : getAchieveGoalsSet()) {
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
            AchieveGoal achieveGoal) {
        Set<MaintainGoal> maintainGoals = new HashSet<MaintainGoal>();

        for (MaintainGoal maintainGoal : getMaintainGoalsSet()) {
            // Get AchieveGoal Conditions Data
            Set<BWAttribute> achieveAttributes = new HashSet<BWAttribute>();
            Set<BWEntity> achieveEntities = new HashSet<BWEntity>();
            for (Condition ac : achieveGoal.getActivateConditionsSet()) {
                achieveAttributes.addAll(ac.getAttributes());
                achieveEntities.addAll(ac.getEntities());
            }
            achieveAttributes
                    .addAll(achieveGoal.getSuccessCondition().getAttributes());
            achieveEntities
                    .addAll(achieveGoal.getSuccessCondition().getEntities());

            // Get MaintainGoal Condition Data
            Set<BWAttribute> maintainAttributes = new HashSet<BWAttribute>();
            Set<BWEntity> maintainEntities = new HashSet<BWEntity>();
            maintainAttributes.addAll(
                    maintainGoal.getMaintainCondition().getAttributes());
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
    public Set<AchieveGoal> getAchieveGoalAssociatedAchieveGoals(
            AchieveGoal achieveGoal) {
        Set<AchieveGoal> achieveGoalsRESULT = new HashSet<AchieveGoal>();

        for (AchieveGoal ag : getAchieveGoalsSet()) {
            if (!achieveGoal.equals(ag)) {
                // Given AchieveGoal Conditions Data
                Set<BWAttribute> achieveAttributes = new HashSet<BWAttribute>();
                Set<BWEntity> achieveEntities = new HashSet<BWEntity>();
                achieveAttributes.addAll(
                        achieveGoal.getSuccessCondition().getAttributes());
                achieveEntities.addAll(
                        achieveGoal.getSuccessCondition().getEntities());

                // For AchieveGoal Conditions Data
                Set<BWAttribute> agAttributes = new HashSet<BWAttribute>();
                Set<BWEntity> agEntities = new HashSet<BWEntity>();
                for (Condition ac : ag.getActivateConditionsSet()) {
                    agAttributes.addAll(ac.getAttributes());
                    agEntities.addAll(ac.getEntities());
                }
                agAttributes.addAll(ag.getSuccessCondition().getAttributes());
                agEntities.addAll(ag.getSuccessCondition().getEntities());

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

    public Set<BWRelation> getSubGoalsRelations(AchieveGoal topGoal) {
        Set<BWRelation> result = new HashSet<BWRelation>();

        DataModelInstance dataModelInstance = getBwInstance()
                .getDataModelInstance();
        for (AchieveGoal subGoal : topGoal.getSubGoalsSet()) {
            result.addAll(dataModelInstance.getRelations(
                    topGoal.getEntityContext(), subGoal.getEntityContext()));
        }

        return result;
    }
}
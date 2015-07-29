package org.blended.goal.repository;

import org.blended.common.common.AttributeAchieveCondition;
import org.blended.common.common.AttributeInvariantCondition;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityInvariantCondition;
import org.blended.common.common.Specification;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalModel;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWError;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;

public class GoalInterface {
    private static Logger log = LoggerFactory.getLogger(GoalInterface.class);

    private static GoalInterface instance = null;

    public static GoalInterface getInstance() {
        if (instance == null) {
            instance = new GoalInterface();
        }
        return instance;
    }

    private AtomicDesignInterface adi = null;

    private GoalInterface() {
        adi = AtomicDesignInterface.getInstance();
    }

    public BWNotification loadGoalModel(String specId, GoalModel eGoalModel) {
        log.debug("loadGoalModel: {}", specId);

        BWNotification notification = new BWNotification();

        Specification eSpec = eGoalModel.getSpecification();
        log.debug("Specification: {}", eSpec.getName());

        try {
            // load goal specification
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
            log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        }

        for (Goal goal : eGoalModel.getGoals()) {
            log.debug("Goal: {}", goal.getName());

            log.debug("Subgoals");
            for (Goal subGoal : goal.getChildrenGoals()) {
                log.debug("Subgoal: {}", subGoal.getName());
            }

            log.debug("Activation Conditions");
            for (EObject eObj : goal.getActivationConditions()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("ACT({})", eac.getName());

                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("ACT({})", aac.getConditions());

                }
                assert(false);
            }

            log.debug("Success Conditions");
            for (EObject eObj : goal.getSuccessConditions()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("SUC({})", eac.getName());

                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("SUC({})", aac.getConditions());

                }
                assert(false);
            }

            log.debug("Invariant Conditions");
            for (EObject eObj : goal.getInvariantConditions()) {
                if (eObj instanceof EntityInvariantCondition) {
                    EntityInvariantCondition eic = (EntityInvariantCondition) eObj;
                    log.debug("INV({})", eic.getName());

                } else if (eObj instanceof AttributeInvariantCondition) {
                    AttributeInvariantCondition aic = (AttributeInvariantCondition) eObj;
                    log.debug("INV({})", aic.getName());

                }
                assert(false);

            }
        }

        return notification;
    }

}

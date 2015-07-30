package org.blended.goal.repository;

import java.util.stream.Collectors;

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
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecificationDTO;

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
            adi.loadGoalSpecification(new SpecificationDTO(specId));
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
            log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        }

        for (Goal eGoal : eGoalModel.getGoals()) {
            log.debug("Goal: {}", eGoal.getName());

            try {
                adi.createGoal(specId, eGoal.getName());
            } catch (BWException bwe) {
                notification.addError(
                        new BWError(bwe.getError(), bwe.getMessage()));
                log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
            }

            log.debug("Activation Conditions");
            for (EObject eObj : eGoal.getActivationConditions()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("ACT({})", eac.getName());
                    adi.associateEntityAchieveConditionToGoalAtivationCondition(
                            specId, eGoal.getName(), eac.getName());
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("ACT({})", aac.getConditions());
                    adi.associateAttributeAchieveConditionToGoalActivationCondition(
                            specId, eGoal.getName(), aac.getConditions()
                                    .stream().collect(Collectors.toSet()));
                }
                assert(false);
            }

            log.debug("Success Conditions");
            for (EObject eObj : eGoal.getSuccessConditions()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("SUC({})", eac.getName());
                    adi.associateEntityAchieveConditionToGoalSuccessCondition(
                            specId, eGoal.getName(), eac.getName());
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("SUC({})", aac.getConditions());
                    adi.associateAttributeAchieveConditionToGoalSuccessCondition(
                            specId, eGoal.getName(), aac.getConditions()
                                    .stream().collect(Collectors.toSet()));
                }
                assert(false);
            }

            log.debug("Invariant Conditions");
            for (EObject eObj : eGoal.getInvariantConditions()) {
                if (eObj instanceof EntityInvariantCondition) {
                    EntityInvariantCondition eic = (EntityInvariantCondition) eObj;
                    log.debug("MUL({},{})", eic.getName(),
                            eic.getCardinality());
                    adi.associateMulConditionToGoalEntityInvariantCondition(
                            specId, eGoal.getName(), eic.getName(),
                            eic.getCardinality());
                } else if (eObj instanceof AttributeInvariantCondition) {
                    AttributeInvariantCondition aic = (AttributeInvariantCondition) eObj;
                    log.debug("RULE({})", aic.getName());
                    adi.associateRuleConditionToGoalAttributeInvariantCondition(
                            specId, eGoal.getName(), aic.getName());
                }
                assert(false);
            }
        }

        for (Goal eGoal : eGoalModel.getGoals()) {
            log.debug("Subgoals");
            for (Goal subGoal : eGoal.getChildrenGoals()) {
                log.debug("Subgoal: {}", subGoal.getName());
                try {
                    adi.addSubGoal(specId, eGoal.getName(), subGoal.getName());
                } catch (BWException bwe) {
                    notification.addError(
                            new BWError(bwe.getError(), bwe.getMessage()));
                    log.debug("Error: {}, {}", bwe.getError(),
                            bwe.getMessage());
                }
            }
        }

        adi.printSpecificationModels(specId);

        return notification;
    }

}

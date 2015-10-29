package org.blended.goal.repository;

import org.blended.common.common.AttributeAchieveCondition;
import org.blended.common.common.AttributeInvariantCondition;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityInvariantCondition;
import org.blended.common.common.Specification;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.BWNotification;
import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.dto.GoalDTO;
import org.blended.common.repository.resttemplate.dto.MulConditionDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalModel;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoalInterface {
    private static Logger log = LoggerFactory.getLogger(GoalInterface.class);

    private static GoalInterface instance = null;

    public static GoalInterface getInstance() {
        if (instance == null) {
            instance = new GoalInterface();
        }
        return instance;
    }

    private CommonInterface ci = null;

    private GoalInterface() {
        ci = CommonInterface.getInstance();
    }

    public BWNotification loadGoalModel(String specId, GoalModel eGoalModel) {
        log.debug("loadGoalModel: {}", specId);

        BWNotification notification = new BWNotification();

        Specification eSpec = eGoalModel.getSpecification();
        log.debug("Specification: {}", eSpec.getName());

        try {
            ci.getSpecBySpecId(specId);

            ci.cleanGoalModel(specId);
        } catch (RepositoryException re) {
            log.debug("loadGoalModel: {}", re.getMessage());
            // data and condition models are required
            notification.addError(re.getError());
            return notification;
        }

        for (Goal eGoal : eGoalModel.getGoals()) {
            log.debug("Goal: {}", eGoal.getName());
            String goalName = eGoal.getName();
            try {
                ci.createGoal(new GoalDTO(specId, eGoal.getName()));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }

            log.debug("Activation Conditions");
            for (EObject eObj : eGoal.getActivationConditions()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("ACT({})", eac.getName());
                    try {
                        ci.associateEntityToGoalActivation(specId, goalName,
                                eac.getName());
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("ACT({})", aac.getAttribute());
                    try {
                        ci.associateAttributeToGoalActivation(specId, goalName,
                                aac.getAttribute());
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                }
                assert (false);
            }

            log.debug("Success Conditions");
            for (EObject eObj : eGoal.getSuccessConditions()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("SUC({})", eac.getName());
                    try {
                        ci.associateEntityToGoalSuccess(specId, goalName,
                                eac.getName());
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("SUC({})", aac.getAttribute());
                    try {
                        ci.associateAttributeToGoalSuccess(specId, goalName,
                                aac.getAttribute());
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                }
                assert (false);
            }

            log.debug("Invariant Conditions");
            for (EObject eObj : eGoal.getInvariantConditions()) {
                if (eObj instanceof EntityInvariantCondition) {
                    EntityInvariantCondition eic = (EntityInvariantCondition) eObj;
                    log.debug("MUL({},{})", eic.getName(),
                            eic.getCardinality());
                    try {
                        ci.associateMulToGoalInvariant(specId, goalName,
                                new MulConditionDTO(specId, eic.getName(),
                                        eic.getCardinality()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof AttributeInvariantCondition) {
                    AttributeInvariantCondition aic = (AttributeInvariantCondition) eObj;
                    log.debug("RULE({})", aic.getName());
                    try {
                        ci.associateRuleToGoalInvariant(specId, goalName,
                                new RuleDTO(specId, aic.getName()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                }
                assert (false);
            }
        }

        for (Goal eGoal : eGoalModel.getGoals()) {
            log.debug("Subgoals");
            for (Goal subGoal : eGoal.getChildrenGoals()) {
                log.debug("Subgoal: {}", subGoal.getName());
                try {
                    ci.addSubGoal(eGoal.getName(),
                            new GoalDTO(specId, subGoal.getName()));
                } catch (RepositoryException re) {
                    notification.addError(re.getError());
                    log.debug("Error: {}", re.getMessage());
                }
            }
        }

        ci.printSpecificationModels(specId);

        return notification;
    }

}

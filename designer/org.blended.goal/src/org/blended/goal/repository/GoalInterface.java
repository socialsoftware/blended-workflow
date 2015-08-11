package org.blended.goal.repository;

import java.util.stream.Collectors;

import org.blended.common.common.AttributeAchieveCondition;
import org.blended.common.common.AttributeInvariantCondition;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityInvariantCondition;
import org.blended.common.common.Specification;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.BWNotification;
import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.vo.GoalVO;
import org.blended.common.repository.resttemplate.vo.MulConditionVO;
import org.blended.common.repository.resttemplate.vo.RuleVO;
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
            String goalExtId = null;
            try {
                GoalVO goalVO = ci
                        .createGoal(new GoalVO(specId, eGoal.getName()));
                goalExtId = goalVO.getExtId();
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
                        ci.associateEntityAchieveConditionToGoalActCondition(
                                specId, goalExtId, eac.getName());
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("ACT({})", aac.getConditions());
                    try {
                        ci.associateAttributeAchieveConditionToGoalActCondition(
                                specId, goalExtId, aac.getConditions().stream()
                                        .collect(Collectors.toSet()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                }
                assert(false);
            }

            log.debug("Success Conditions");
            for (EObject eObj : eGoal.getSuccessConditions()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("SUC({})", eac.getName());
                    try {
                        ci.associateEntityAchieveConditionToGoalSucCondition(
                                specId, goalExtId, eac.getName());
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("SUC({})", aac.getConditions());
                    try {
                        ci.associateAttributeAchieveConditionToGoalSucCondition(
                                specId, goalExtId, aac.getConditions().stream()
                                        .collect(Collectors.toSet()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                }
                assert(false);
            }

            log.debug("Invariant Conditions");
            for (EObject eObj : eGoal.getInvariantConditions()) {
                if (eObj instanceof EntityInvariantCondition) {
                    EntityInvariantCondition eic = (EntityInvariantCondition) eObj;
                    log.debug("MUL({},{})", eic.getName(),
                            eic.getCardinality());
                    try {
                        ci.associateMulConditionToGoalEntityInvariantCondition(
                                specId, goalExtId, new MulConditionVO(specId,
                                        eic.getName(), eic.getCardinality()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof AttributeInvariantCondition) {
                    AttributeInvariantCondition aic = (AttributeInvariantCondition) eObj;
                    log.debug("RULE({})", aic.getName());
                    try {
                        ci.associateRuleConditionToGoalAttributeInvariantCondition(
                                specId, goalExtId,
                                new RuleVO(specId, aic.getName()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                }
                assert(false);
            }
        }

        for (Goal eGoal : eGoalModel.getGoals()) {
            log.debug("Subgoals");
            GoalVO goalVO = ci.getGoalByName(specId, eGoal.getName());
            for (Goal subGoal : eGoal.getChildrenGoals()) {
                log.debug("Subgoal: {}", subGoal.getName());
                try {
                    ci.addSubGoal(new GoalVO(specId, goalVO.getExtId(),
                            subGoal.getName()));
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

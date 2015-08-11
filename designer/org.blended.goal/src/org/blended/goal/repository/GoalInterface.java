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
import org.blended.common.repository.resttemplate.vo.SpecVO;
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

        SpecVO specVO = null;
        String conditionModelExtId = null;
        String dataModelExtId = null;
        String goalModelExtId = null;
        try {
            specVO = ci.getSpecBySpecId(specId);

            conditionModelExtId = specVO.getConditionModelExtId();
            dataModelExtId = specVO.getDataModelExtId();
            goalModelExtId = specVO.getGoalModelExtId();
            ci.cleanGoalModel(goalModelExtId);
        } catch (RepositoryException re) {
            log.debug("loadGoalModel: {}", re.getMessage());
            // data and condition models are required
            return notification;
        }

        for (Goal eGoal : eGoalModel.getGoals()) {
            log.debug("Goal: {}", eGoal.getName());
            String goalExtId = null;
            try {
                GoalVO goalVO = ci.createGoal(
                        new GoalVO(goalModelExtId, eGoal.getName()));
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
                    ci.associateEntityAchieveConditionToGoalActCondition(
                            goalModelExtId, goalExtId, eac.getName());
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("ACT({})", aac.getConditions());
                    ci.associateAttributeAchieveConditionToGoalActCondition(
                            goalModelExtId, goalExtId, aac.getConditions()
                                    .stream().collect(Collectors.toSet()));
                }
                assert(false);
            }

            log.debug("Success Conditions");
            for (EObject eObj : eGoal.getSuccessConditions()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("SUC({})", eac.getName());
                    ci.associateEntityAchieveConditionToGoalSucCondition(
                            goalModelExtId, goalExtId, eac.getName());
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("SUC({})", aac.getConditions());
                    ci.associateAttributeAchieveConditionToGoalSucCondition(
                            goalModelExtId, goalExtId, aac.getConditions()
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
                    ci.associateMulConditionToGoalEntityInvariantCondition(
                            goalModelExtId, goalExtId,
                            new MulConditionVO(conditionModelExtId,
                                    eic.getName(), eic.getCardinality()));
                } else if (eObj instanceof AttributeInvariantCondition) {
                    AttributeInvariantCondition aic = (AttributeInvariantCondition) eObj;
                    log.debug("RULE({})", aic.getName());
                    ci.associateRuleConditionToGoalAttributeInvariantCondition(
                            goalModelExtId, goalExtId,
                            new RuleVO(conditionModelExtId, aic.getName()));
                }
                assert(false);
            }
        }

        for (Goal eGoal : eGoalModel.getGoals()) {
            log.debug("Subgoals");
            GoalVO goalVO = ci.getGoalByName(goalModelExtId, eGoal.getName());
            for (Goal subGoal : eGoal.getChildrenGoals()) {
                log.debug("Subgoal: {}", subGoal.getName());
                try {
                    ci.addSubGoal(new GoalVO(goalVO.getExtId(), goalModelExtId,
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

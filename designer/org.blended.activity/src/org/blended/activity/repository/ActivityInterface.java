package org.blended.activity.repository;

import java.util.stream.Collectors;

import org.blended.activity.activity.Activity;
import org.blended.activity.activity.ActivityModel;
import org.blended.common.common.AttributeAchieveCondition;
import org.blended.common.common.AttributeInvariantCondition;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityInvariantCondition;
import org.blended.common.common.Specification;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.BWNotification;
import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.dto.MulConditionDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityInterface {
    private static Logger log = LoggerFactory
            .getLogger(ActivityInterface.class);

    private static ActivityInterface instance = null;

    public static ActivityInterface getInstance() {
        if (instance == null) {
            instance = new ActivityInterface();
        }
        return instance;
    }

    private CommonInterface ci = null;

    private ActivityInterface() {
        ci = CommonInterface.getInstance();
    }

    public BWNotification loadActivityModel(String specId,
            ActivityModel eActivityModel) {
        log.debug("loadActivityModel: {}", specId);

        BWNotification notification = new BWNotification();

        Specification eSpec = eActivityModel.getSpecification();
        log.debug("Specification: {}", eSpec.getName());

        try {
            ci.getSpecBySpecId(specId);

            ci.cleanActivityModel(specId);
        } catch (RepositoryException re) {
            log.debug("loadActivityModel: {}", re.getMessage());
            // data and condition models are required
            notification.addError(re.getError());
            return notification;
        }

        for (Activity eActivity : eActivityModel.getActivities()) {
            log.debug("Activity specId:{}, name:{}, description:{}", specId,
                    eActivity.getName(), eActivity.getDescription());

            ci.createActivity(specId, eActivity.getName(),
                    eActivity.getDescription());

            for (EObject eObj : eActivity.getPre()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("PRE({})", eac.getName());
                    try {
                        ci.associateEntityToActivityPre(specId,
                                eActivity.getName(), eac.getName());
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("PRE({})", aac.getConditions());
                    try {
                        ci.associateAttributeToActivityPre(specId,
                                eActivity.getName(), aac.getConditions()
                                        .stream().collect(Collectors.toSet()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                }
                assert(false);

            }

            for (EObject eObj : eActivity.getPost()) {
                if (eObj instanceof EntityAchieveCondition) {
                    EntityAchieveCondition eac = (EntityAchieveCondition) eObj;
                    log.debug("POST({})", eac.getName());
                    try {
                        ci.associateEntityToActivityPost(specId,
                                eActivity.getName(), eac.getName());
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof AttributeAchieveCondition) {
                    AttributeAchieveCondition aac = (AttributeAchieveCondition) eObj;
                    log.debug("POST({})", aac.getConditions());
                    try {
                        ci.associateAttributeToActivityPost(specId,
                                eActivity.getName(), aac.getConditions()
                                        .stream().collect(Collectors.toSet()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof EntityInvariantCondition) {
                    EntityInvariantCondition eic = (EntityInvariantCondition) eObj;
                    log.debug("POST(MUL({},{}))", eic.getName(),
                            eic.getCardinality());
                    try {
                        ci.associateMulToActivityPost(specId,
                                eActivity.getName(), new MulConditionDTO(specId,
                                        eic.getName(), eic.getCardinality()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                } else if (eObj instanceof AttributeInvariantCondition) {
                    AttributeInvariantCondition aic = (AttributeInvariantCondition) eObj;
                    log.debug("POST(RULE({}))", aic.getName());
                    try {
                        ci.associateRuleToActivityPost(specId,
                                eActivity.getName(),
                                new RuleDTO(specId, aic.getName()));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                }
                assert(false);
            }
        }

        try {
            ci.checkActivityModelConsistency(specId);
        } catch (RepositoryException re) {
            notification.addError(re.getError());
            log.debug("Error: {}", re.getMessage());
        }

        ci.printSpecificationModels(specId);

        return notification;
    }

}

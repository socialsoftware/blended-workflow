package org.blended.condition.repinterface;

import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityAchieveConditionExist;
import org.blended.common.common.EntityDependenceCondition;
import org.blended.common.common.EntityInvariantCondition;
import org.blended.common.common.Specification;
import org.blended.condition.condition.ConditionModel;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWError;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityAchieveConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulInvariantDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecificationDTO;

public class ConditionInterface {
    private static Logger log = LoggerFactory
            .getLogger(ConditionInterface.class);

    private static ConditionInterface instance = null;

    public static ConditionInterface getInstance() {
        if (instance == null) {
            instance = new ConditionInterface();
        }
        return instance;
    }

    // to be invoked by tests only
    public void deleteSpecification(SpecificationDTO specDTO) {
        adi.deleteSpecification(specDTO);
    }

    private AtomicDesignInterface adi = null;

    private ConditionInterface() {
        adi = AtomicDesignInterface.getInstance();
    }

    public BWNotification loadConditionModel(String specId,
            ConditionModel eConditionModel) {
        BWNotification notification = new BWNotification();

        Specification eSpec = eConditionModel.getSpecification();
        log.debug("Specification: {}", eSpec.getName());

        try {
            adi.loadConditionSpecification(
                    new SpecificationDTO(specId, eSpec.getName()));
        } catch (BWException bwe) {
            notification
                    .addError(new BWError(bwe.getError(), bwe.getMessage()));
            log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        }

        for (EObject eObj : eConditionModel.getEntityAchieveConditions()) {
            if (eObj instanceof EntityAchieveCondition) {
                EntityAchieveCondition eEac = (EntityAchieveCondition) eObj;
                log.debug("EntityAchieveCondition Name:{} ", eEac.getName());
                try {
                    adi.createEntityAchieveCondition(
                            new EntityAchieveConditionDTO(specId,
                                    eEac.getName(), false));
                } catch (BWException bwe) {
                    notification.addError(
                            new BWError(bwe.getError(), bwe.getMessage()));
                    log.debug("Error: {}, {}", bwe.getError(),
                            bwe.getMessage());
                }

            } else if (eObj instanceof EntityAchieveConditionExist) {
                EntityAchieveConditionExist eEace = (EntityAchieveConditionExist) eObj;
                log.debug("EntityAchieveConditionExists Name:{} ",
                        eEace.getName());
                try {
                    adi.createEntityAchieveCondition(
                            new EntityAchieveConditionDTO(specId,
                                    eEace.getName(), true));
                } catch (BWException bwe) {
                    notification.addError(
                            new BWError(bwe.getError(), bwe.getMessage()));
                    log.debug("Error: {}, {}", bwe.getError(),
                            bwe.getMessage());
                }

            }
        }

        for (EntityDependenceCondition eEpc : eConditionModel
                .getEntityDependenceConditions()) {
            log.debug("EntityDependenceCondition Entity1:{}, Entity2:{}",
                    eEpc.getEntity1(), eEpc.getEntity2());
            // try {
            // adi.createEntityDependenceCondition(new DependenceDTO(specId,
            // eEpc.getEntity1(), eEpc.getEntity2()));
            // } catch (BWException bwe) {
            // notification.addError(
            // new BWError(bwe.getError(), bwe.getMessage()));
            // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
            // }
        }

        for (EntityInvariantCondition eEic : eConditionModel
                .getEntityInvariantConditions()) {
            log.debug("EntityInvariantCondition Name:{}, Cardinality:{}",
                    eEic.getName(), eEic.getCardinality());
            try {
                adi.createEntityInvariantCondition(new MulInvariantDTO(specId,
                        eEic.getName(), eEic.getCardinality()));
            } catch (BWException bwe) {
                notification.addError(
                        new BWError(bwe.getError(), bwe.getMessage()));
                log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
            }
        }

        // for (AttributeAchieveCondition eAac : eConditionModel
        // .getAttributeAchieveConditions()) {
        // log.debug("AttributeAchieveCondition Conditions:{} ",
        // eAac.getConditions());
        // try {
        //
        // } catch (BWException bwe) {
        // notification.addError(
        // new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        // }
        // }

        // for (AttributeDependenceCondition eApc : eConditionModel
        // .getAttributeDependenceConditions()) {
        // log.debug(
        // "AttributeDependenceCondition Attributes1:{}, Attributes2:{}",
        // eApc.getAttributes1(), eApc.getAttributes2());
        // try {
        //
        // } catch (BWException bwe) {
        // notification.addError(
        // new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        // }
        // }
        //
        // for (AttributeInvariantCondition eAic : eConditionModel
        // .getAttributeInvariantConditions()) {
        // log.debug("AttributeInvariantCondition Name:{}", eAic.getName());
        // try {
        //
        // } catch (BWException bwe) {
        // notification.addError(
        // new BWError(bwe.getError(), bwe.getMessage()));
        // log.debug("Error: {}, {}", bwe.getError(), bwe.getMessage());
        // }
        // }

        adi.writeLoadedConditionModel(specId);

        return notification;
    }

}

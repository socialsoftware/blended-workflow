package org.blended.condition.repinterface;

import org.blended.common.common.AttributeAchieveCondition;
import org.blended.common.common.AttributeDependenceCondition;
import org.blended.common.common.AttributeInvariantCondition;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityDependenceCondition;
import org.blended.common.common.EntityInvariantCondition;
import org.blended.common.common.Specification;
import org.blended.condition.condition.ConditionModel;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWNotification;
import pt.ist.socialsoftware.blendedworkflow.service.design.AtomicDesignInterface;
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

        for (EObject eObj : eConditionModel.getEntityAchieveConditions()) {
            EntityAchieveCondition eEac = (EntityAchieveCondition) eObj;
            log.debug("EntityAchieveCondition Name:{} ", eEac.getName());
        }

        for (EntityDependenceCondition eEpc : eConditionModel
                .getEntityDependenceConditions()) {
            log.debug("EntityDependenceCondition Entity1:{}, Entity2:{}",
                    eEpc.getEntity1(), eEpc.getEntity2());
        }

        for (EntityInvariantCondition eEic : eConditionModel
                .getEntityInvariantConditions()) {
            log.debug("EntityInvariantCondition Name:{}, Cardinality:{}",
                    eEic.getName(), eEic.getCardinality());
        }

        for (AttributeAchieveCondition eAac : eConditionModel
                .getAttributeAchieveConditions()) {
            log.debug("AttributeAchieveCondition Conditions:{} ",
                    eAac.getConditions());
        }

        for (AttributeDependenceCondition eApc : eConditionModel
                .getAttributeDependenceConditions()) {
            log.debug(
                    "AttributeDependenceCondition Attributes1:{}, Attributes2:{}",
                    eApc.getAttributes1(), eApc.getAttributes2());
        }

        for (AttributeInvariantCondition eAic : eConditionModel
                .getAttributeInvariantConditions()) {
            log.debug("AttributeInvariantCondition Expression:{}",
                    eAic.getExpression());

        }

        return notification;
    }

}

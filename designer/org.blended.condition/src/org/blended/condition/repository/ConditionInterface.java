package org.blended.condition.repository;

import org.blended.common.common.AttributeAchieveCondition;
import org.blended.common.common.AttributeDependenceCondition;
import org.blended.common.common.AttributeInvariantCondition;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.EntityAchieveConditionExist;
import org.blended.common.common.EntityDependenceCondition;
import org.blended.common.common.EntityInvariantCondition;
import org.blended.common.common.NotMandatoryAttributeAchieveCondition;
import org.blended.common.common.Specification;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.BWNotification;
import org.blended.common.repository.resttemplate.RepositoryException;
import org.blended.common.repository.resttemplate.dto.DefAttributeConditionDTO;
import org.blended.common.repository.resttemplate.dto.DefEntityConditionDTO;
import org.blended.common.repository.resttemplate.dto.DependenceDTO;
import org.blended.common.repository.resttemplate.dto.EntityDTO;
import org.blended.common.repository.resttemplate.dto.MulConditionDTO;
import org.blended.common.repository.resttemplate.dto.ProductDTO;
import org.blended.common.repository.resttemplate.dto.RuleDTO;
import org.blended.condition.condition.ConditionModel;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public void deleteSpecification(String specId) {
        ci.deleteSpecification(specId);
    }

    private CommonInterface ci = null;

    private ConditionInterface() {
        ci = CommonInterface.getInstance();
    }

    public BWNotification loadConditionModel(String specId,
            ConditionModel eConditionModel) {
        BWNotification notification = new BWNotification();

        Specification eSpec = eConditionModel.getSpecification();
        log.debug("Specification: {}", eSpec.getName());

        try {
            ci.getSpecBySpecId(specId);

            ci.cleanConditionModel(specId);
        } catch (RepositoryException re) {
            log.debug("loadConditionModel: {}", re.getMessage());
            // a data model is required
            return notification;
        }

        for (EObject eObj : eConditionModel.getEntityAchieveConditions()) {
            String entityName = null;
            boolean exists = false;
            if (eObj instanceof EntityAchieveCondition) {
                EntityAchieveCondition eEac = (EntityAchieveCondition) eObj;
                entityName = eEac.getName();
                exists = false;
            } else if (eObj instanceof EntityAchieveConditionExist) {
                EntityAchieveConditionExist eEace = (EntityAchieveConditionExist) eObj;
                entityName = eEace.getName();
                exists = true;
            }
            try {
                log.debug("EntityAchieveConditionExists Name:{} ", entityName);
                ci.createEntityAchieveCondition(
                        new DefEntityConditionDTO(specId, entityName, exists));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        for (EntityDependenceCondition eEpc : eConditionModel
                .getEntityDependenceConditions()) {
            log.debug("EntityDependenceCondition Entity1:{}, Entity2:{}",
                    eEpc.getEntity1(), eEpc.getEntity2());
            try {
                EntityDTO entityDTO = ci.getEntityByName(specId,
                        eEpc.getEntity1());
                ci.createEntityDependenceCondition(new DependenceDTO(specId,
                        entityDTO.getExtId(), eEpc.getEntity2()));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        for (EntityInvariantCondition eEic : eConditionModel
                .getEntityInvariantConditions()) {
            log.debug("EntityInvariantCondition Name:{}, Cardinality:{}",
                    eEic.getName(), eEic.getCardinality());
            try {
                ci.createEntityInvariantCondition(new MulConditionDTO(specId,
                        eEic.getName(), eEic.getCardinality()));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        for (AttributeAchieveCondition eAac : eConditionModel
                .getAttributeAchieveConditions()) {
            log.debug("AttributeAchieveCondition Conditions:{} ",
                    eAac.getAttribute());
            boolean mandatory;
            if (eAac instanceof NotMandatoryAttributeAchieveCondition) {
                mandatory = false;
            } else {
                mandatory = true;
            }
            String path = eAac.getAttribute();
            try {
                ci.createAttributeAchieveCondition(
                        new DefAttributeConditionDTO(specId, path, mandatory));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        for (AttributeDependenceCondition eApc : eConditionModel
                .getAttributeDependenceConditions()) {
            log.debug(
                    "AttributeDependenceCondition Attribute1:{}, Attribute2:{}",
                    eApc.getAttribute1(), eApc.getAttribute2());
            try {
                ProductDTO productDTO = ci.getProduct(specId,
                        eApc.getAttribute1());
                ci.createAttributeDependenceCondition(new DependenceDTO(specId,
                        productDTO.getExtId(), eApc.getAttribute2()));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        for (AttributeInvariantCondition eAic : eConditionModel
                .getAttributeInvariantConditions()) {
            log.debug("AttributeInvariantCondition Name:{}", eAic.getName());
            try {
                ci.createAttributeInvariantCondition(
                        new RuleDTO(specId, eAic.getName()));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        ci.printSpecificationModels(specId);

        return notification;
    }

}

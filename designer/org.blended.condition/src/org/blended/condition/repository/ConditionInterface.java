package org.blended.condition.repository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.blended.common.repository.resttemplate.vo.DEFEntityConditionVO;
import org.blended.common.repository.resttemplate.vo.DefAttributeConditionVO;
import org.blended.common.repository.resttemplate.vo.DependenceVO;
import org.blended.common.repository.resttemplate.vo.MulConditionVO;
import org.blended.common.repository.resttemplate.vo.ProductVO;
import org.blended.common.repository.resttemplate.vo.RuleVO;
import org.blended.common.repository.resttemplate.vo.SpecVO;
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

        SpecVO specVO = null;
        String conditionModelExtId = null;
        String dataModelExtId = null;
        try {
            specVO = ci.getSpecBySpecId(specId);

            conditionModelExtId = specVO.getConditionModelExtId();
            dataModelExtId = specVO.getDataModelExtId();
            ci.cleanConditionModel(conditionModelExtId);
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
                ci.createEntityAchieveCondition(new DEFEntityConditionVO(
                        conditionModelExtId, entityName, exists));
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
                Set<String> ent = new HashSet<String>();
                ent.add(eEpc.getEntity1());
                ci.createEntityDependenceCondition(
                        new DependenceVO(conditionModelExtId, eEpc.getEntity1(),
                                eEpc.getEntity2()));
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
                ci.createEntityInvariantCondition(
                        new MulConditionVO(conditionModelExtId, eEic.getName(),
                                eEic.getCardinality()));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        for (AttributeAchieveCondition eAac : eConditionModel
                .getAttributeAchieveConditions()) {
            log.debug("AttributeAchieveCondition Conditions:{} ",
                    eAac.getConditions());
            boolean mandatory;
            if (eAac instanceof NotMandatoryAttributeAchieveCondition) {
                mandatory = false;
            } else {
                mandatory = true;
            }
            Set<String> paths = new HashSet<String>();
            for (String path : eAac.getConditions()) {
                paths.add(path);
            }
            try {
                ci.createAttributeAchieveCondition(new DefAttributeConditionVO(
                        conditionModelExtId, paths, mandatory));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        for (AttributeDependenceCondition eApc : eConditionModel
                .getAttributeDependenceConditions()) {
            log.debug(
                    "AttributeDependenceCondition Attributes1:{}, Attributes2:{}",
                    eApc.getAttributes1(), eApc.getAttributes2());
            try {
                Set<String> sourceAtts = eApc.getAttributes1().stream()
                        .collect(Collectors.toSet());
                ProductVO productVO = ci.getProduct(dataModelExtId, sourceAtts);
                for (String path : eApc.getAttributes2().stream()
                        .collect(Collectors.toSet())) {
                    try {
                        ci.createAttributeDependenceCondition(new DependenceVO(
                                productVO.getProductExtId(), path));
                    } catch (RepositoryException re) {
                        notification.addError(re.getError());
                        log.debug("Error: {}", re.getMessage());
                    }
                }
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
                        new RuleVO(conditionModelExtId, eAic.getName()));
            } catch (RepositoryException re) {
                notification.addError(re.getError());
                log.debug("Error: {}", re.getMessage());
            }
        }

        ci.printSpecificationModels(specId);

        return notification;
    }

}

package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeBoolCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.BWAttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWBinaryExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWBinaryExpression.BinaryOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.BWConditionModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDependence;
import pt.ist.socialsoftware.blendedworkflow.domain.BWEntity;
import pt.ist.socialsoftware.blendedworkflow.domain.BWExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BWGoalModel;
import pt.ist.socialsoftware.blendedworkflow.domain.BWNumberLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct;
import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct.ProductType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRelation.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.BWRule;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.BWStringLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.BoolComparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DEFEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.FalseCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MULCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.NotCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.OrCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeGroupDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DEFEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO.Type;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GoalDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ProductDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RelationDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.RuleDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.SpecDTO;

public class AtomicDesignInterface {
    private static Logger log = LoggerFactory
            .getLogger(AtomicDesignInterface.class);

    private static AtomicDesignInterface instance;

    public static AtomicDesignInterface getInstance() {
        if (instance == null) {
            instance = new AtomicDesignInterface();
        }
        return instance;
    }

    private AtomicDesignInterface() {
    }

    private BlendedWorkflow getBlendedWorkflow() {
        return BlendedWorkflow.getInstance();
    }

    // to be invoked by tests only
    @Atomic(mode = TxMode.WRITE)
    public void deleteSpecification(String specId) {
        getSpecBySpecId(specId).delete();
    }

    public BWSpecification getSpecBySpecId(String specId) {
        BWSpecification spec = getBlendedWorkflow().getSpecById(specId)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_SPECIFICATION_ID, specId));
        return spec;
    }

    @Atomic(mode = TxMode.WRITE)
    public BWSpecification createSpecification(SpecDTO specDTO) {
        return getBlendedWorkflow().createSpecification(specDTO.getSpecId(),
                specDTO.getName());
    }

    @Atomic(mode = TxMode.WRITE)
    public void cleanDataModel(String extId) {
        BWDataModel dataModel = getDataModelByExtId(extId);

        dataModel.clean();
    }

    @Atomic(mode = TxMode.WRITE)
    public BWEntity createEntity(EntityDTO entDTO) {
        log.debug("createEntity dataModelExtId:{}, name:{}, exists:{}",
                entDTO.getDataModelExtId(), entDTO.getName(),
                entDTO.getExists());

        BWDataModel dataModel = getDataModelByExtId(entDTO.getDataModelExtId());

        return dataModel.createEntity(entDTO.getName(), entDTO.getExists());
    }

    @Atomic(mode = TxMode.WRITE)
    public BWAttribute createAttribute(AttributeDTO attDTO) {
        log.debug("createAttribute entityExtId:{}", attDTO.getEntityExtId());
        BWEntity ent = getEntityByExtId(attDTO.getEntityExtId());

        BWAttributeGroup attGroup = null;
        if (attDTO.getGroupExtId() != null) {
            attGroup = getAttributeGroupByExtId(attDTO.getGroupExtId());
        }

        BWAttribute attribute = ent.createAttribute(attGroup, attDTO.getName(),
                AttributeType.parseAttributeType(attDTO.getType()),
                attDTO.getIsMandatory());

        return attribute;
    }

    @Atomic(mode = TxMode.WRITE)
    public BWRelation createRelation(RelationDTO relDTO) {
        log.debug("createRelation {}, entityOneName:{}, entityTwoName:{}",
                relDTO.getDataModelExtId(), relDTO.getEntOneName(),
                relDTO.getEntTwoName());

        BWDataModel dataModel = getDataModelByExtId(relDTO.getDataModelExtId());

        BWEntity entityOne;
        if (relDTO.getEntOneExtId() != null) {
            entityOne = getEntityByExtId(relDTO.getEntOneExtId());
        } else {
            entityOne = getEntityByName(dataModel, relDTO.getEntOneName());
        }

        BWEntity entityTwo;
        if (relDTO.getEntTwoExtId() != null) {
            entityTwo = getEntityByExtId(relDTO.getEntTwoExtId());
        } else {
            entityTwo = getEntityByName(dataModel, relDTO.getEntTwoName());
        }

        if (entityOne.getDataModel() != entityTwo.getDataModel())
            throw new BWException(BWErrorType.INVALID_RELATION,
                    entityOne.getDataModel().getSpecification().getSpecId()
                            + "!=" + entityTwo.getDataModel().getSpecification()
                                    .getSpecId());

        BWRelation relation = entityOne.createRelation(relDTO.getName(),
                relDTO.getRolenameOne(),
                Cardinality.parseCardinality(relDTO.getCardinalityOne()),
                entityTwo, relDTO.getRolenameTwo(),
                Cardinality.parseCardinality(relDTO.getCardinalityTwo()));

        return relation;
    }

    @Atomic(mode = TxMode.WRITE)
    public BWAttributeGroup createAttributeGroup(
            AttributeGroupDTO attGroupDTO) {
        BWEntity entity = getEntityByExtId(attGroupDTO.getEntityExtId());

        BWAttributeGroup group = entity.createAttributeGroup(
                attGroupDTO.getName(), attGroupDTO.isMandatory());

        return group;
    }

    @Atomic(mode = TxMode.WRITE)
    public BWDependence createDependence(DependenceDTO productDTO) {
        BWProduct product = getProductByExtId(productDTO.getProductExtId());

        return product.createDependence(productDTO.getPath());
    }

    public Set<BWDependence> getDependencies(String dataModelExtId) {
        BWDataModel dataModel = getDataModelByExtId(dataModelExtId);

        Set<BWDependence> deps = new HashSet<BWDependence>();

        for (BWDependence dependence : dataModel.getDependenceSet()) {
            deps.add(dependence);
        }

        return deps;
    }

    public boolean checkDependence(String extId) {
        BWDependence dependence = getDependenceByExtId(extId);
        return dependence.check();
    }

    @Atomic(mode = TxMode.WRITE)
    public void deleteDependence(String extId) {
        BWDependence dependence = getDependenceByExtId(extId);
        dependence.delete();
    }

    @Atomic(mode = TxMode.WRITE)
    public BWRule createRule(RuleDTO ruleDTO) {
        BWDataModel dataModel = getDataModelByExtId(
                ruleDTO.getDataModelExtId());

        BWRule rule = dataModel.createRule(ruleDTO.getName(),
                buildCondition(dataModel, ruleDTO.getExpression()));

        log.debug("createRule expression:{}", rule.getCondition().getSubPath());

        return rule;
    }

    @Atomic(mode = TxMode.WRITE)
    public void cleanConditionModel(String extId) {
        BWConditionModel conditionModel = getConditionModelByExtId(extId);

        conditionModel.clean();
    }

    @Atomic(mode = TxMode.WRITE)
    public void cleanGoalModel(String extId) {
        BWGoalModel goalModel = getGoalModelByExtId(extId);

        goalModel.clean();
    }

    @Atomic(mode = TxMode.WRITE)
    public DEFEntityCondition createEntityAchieveCondition(
            DEFEntityConditionDTO eacDTO) {
        log.debug("createEntityAchieveCondition Entity:{}, Value:{}",
                eacDTO.getEntityName(), eacDTO.isExists());
        BWConditionModel conditionModel = getConditionModelByExtId(
                eacDTO.getConditionModelExtId());

        BWEntity entity = getEntityByName(
                conditionModel.getSpecification().getDataModel(),
                eacDTO.getEntityName());

        if (entity.getExists() != eacDTO.isExists()) {
            throw new BWException(BWErrorType.INVALID_ENTITY,
                    eacDTO.getEntityName() + " exists=" + entity.getExists());
        }

        DEFEntityCondition defCondition = DEFEntityCondition
                .getDEFEntity(conditionModel.getSpecification(), entity);
        conditionModel.addEntityAchieveCondition(defCondition);

        return defCondition;
    }

    public BWEntity getEntityByName(String dataModelExtId, String entityName) {
        BWDataModel dataModel = getDataModelByExtId(dataModelExtId);

        return getEntityByName(dataModel, entityName);
    }

    @Atomic(mode = TxMode.WRITE)
    public BWDependence createEntityDependenceCondition(
            DependenceDTO dependenceDTO) {
        log.debug("createEntityDependenceCondition entityExtId:{}, Path:{}",
                dependenceDTO.getProductExtId(), dependenceDTO.getPath());

        BWEntity entity = getEntityByExtId(dependenceDTO.getProductExtId());

        BWDependence dependence = getDependence(entity,
                dependenceDTO.getPath());

        BWConditionModel conditionModel = entity.getDataModel()
                .getSpecification().getConditionModel();
        conditionModel.addEntityDependenceCondition(dependence);

        return dependence;
    }

    @Atomic(mode = TxMode.WRITE)
    public MULCondition createEntityInvariantCondition(MulConditionDTO miDTO) {
        log.debug("createEntityInvariantCondition Entity:{}, Cardinality:{}",
                miDTO.getRolePath(), miDTO.getCardinality());
        BWConditionModel conditionModel = getConditionModelByExtId(
                miDTO.getConditionModelExtId());

        MULCondition mulCondition = getMULCondition(
                conditionModel.getSpecification(), miDTO.getRolePath());

        if (!mulCondition.getCardinality().equals(miDTO.getCardinality()))
            new BWException(BWErrorType.INVALID_CARDINALITY,
                    miDTO.getCardinality());

        conditionModel.addEntityInvariantCondition(mulCondition);

        return mulCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public DEFAttributeCondition createAttributeAchieveCondition(
            DefAttributeConditionDTO aacDTO) {
        log.debug("createAttributeAchieveCondition Paths:{}, Mandatory:{}",
                aacDTO.getPaths(), aacDTO.isMandatory());
        BWConditionModel conditionModel = getConditionModelByExtId(
                aacDTO.getConditionModelExtId());

        Set<BWAttribute> attributes = getAttributes(
                conditionModel.getSpecification(), aacDTO.getPaths());

        if (attributes.size() == 0)
            throw new BWException(BWErrorType.INVALID_PATH,
                    aacDTO.getPaths().toString());

        DEFAttributeCondition defAttributeCondition = getDefAttributeCondition(
                attributes);

        if (defAttributeCondition.getAttribute() != null
                && defAttributeCondition.getAttribute()
                        .getIsMandatory() != aacDTO.isMandatory())
            throw new BWException(BWErrorType.INCONSISTENT_ATTRIBUTE_MANDATORY,
                    aacDTO.getPaths().toString());

        if (defAttributeCondition.getAttributeGroup() != null
                && defAttributeCondition.getAttributeGroup()
                        .getIsMandatory() != aacDTO.isMandatory())
            throw new BWException(BWErrorType.INCONSISTENT_ATTRIBUTE_MANDATORY,
                    aacDTO.getPaths().toString());

        conditionModel.addAttributeAchieveCondition(defAttributeCondition);

        return defAttributeCondition;
    }

    public BWProduct getProduct(String dataModelExtId,
            List<String> attributeNames) {
        BWDataModel dataModel = getDataModelByExtId(dataModelExtId);
        Set<BWAttribute> attributes = getAttributes(
                dataModel.getSpecification(),
                attributeNames.stream().collect(Collectors.toSet()));

        BWProduct product = null;
        if (attributes.size() == 0)
            throw new BWException(BWErrorType.INVALID_PATH,
                    attributeNames.toString());
        else if (attributes.size() == 1) {
            product = attributes.stream().findFirst().get();
        } else {
            BWEntity entity = attributes.stream().findFirst().get().getEntity();
            product = getAttributeGroup(entity, attributes);
        }

        return product;
    }

    @Atomic(mode = TxMode.WRITE)
    public BWDependence createAttributeDependenceCondition(
            DependenceDTO dependenceDTO) {
        log.debug("createAttributeDependenceCondition productExtId:{}, path:{}",
                dependenceDTO.getProductExtId(), dependenceDTO.getPath());
        BWProduct product = getProductByExtId(dependenceDTO.getProductExtId());

        BWDependence dependence = getDependence(product,
                dependenceDTO.getPath());

        BWConditionModel conditionModel = product.getEntity().getDataModel()
                .getSpecification().getConditionModel();
        conditionModel.addAttributeDependenceCondition(dependence);

        return dependence;
    }

    @Atomic(mode = TxMode.WRITE)
    public BWRule createAttributeInvariantCondition(RuleDTO ruleDTO) {
        BWConditionModel conditionModel = getConditionModelByExtId(
                ruleDTO.getConditionModelExtId());

        BWRule rule = getRule(conditionModel.getSpecification(),
                ruleDTO.getName());

        conditionModel.addAttributeInvariantCondition(rule);

        return rule;
    }

    @Atomic(mode = TxMode.WRITE)
    public Goal createGoal(GoalDTO goalDTO) {
        BWGoalModel goalModel = getGoalModelByExtId(
                goalDTO.getGoalModelExtId());

        return new Goal(goalModel, goalDTO.getName());
    }

    public Goal getGoalByName(String goalModelExtId, String goalName) {
        BWGoalModel goalModel = getGoalModelByExtId(goalModelExtId);

        return getGoalByName(goalModel.getSpecification(), goalName);
    }

    @Atomic(mode = TxMode.WRITE)
    public DEFEntityCondition associateEntityAchieveConditionToGoalSuccessCondition(
            String goalModelExtId, String goalExtId, String path) {
        BWGoalModel goalModel = getGoalModelByExtId(goalModelExtId);
        Goal goal = getGoalByExtId(goalExtId);

        BWProduct product = getTargetOfPath(goalModel.getSpecification(), path);
        if (product.getProductType() != ProductType.ENTITY)
            throw new BWException(BWErrorType.INVALID_PATH, path);

        DEFEntityCondition defEntityCondition = ((BWEntity) product)
                .getDefEntityCondition();
        goal.addSuccessCondition(defEntityCondition);

        return defEntityCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public DEFEntityCondition associateEntityAchieveConditionToGoalAtivationCondition(
            String goalModelExtId, String goalExtId, String path) {
        BWGoalModel goalModel = getGoalModelByExtId(goalModelExtId);
        Goal goal = getGoalByExtId(goalExtId);

        BWProduct product = getTargetOfPath(goalModel.getSpecification(), path);
        if (product.getProductType() != ProductType.ENTITY)
            throw new BWException(BWErrorType.INVALID_PATH, path);

        DEFEntityCondition defEntityCondition = ((BWEntity) product)
                .getDefEntityCondition();
        goal.addActivationCondition(defEntityCondition);

        return defEntityCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public DEFAttributeCondition associateAttributeAchieveConditionToGoalActivationCondition(
            String goalModelExtId, String goalExtId, Set<String> paths) {
        BWGoalModel goalModel = getGoalModelByExtId(goalModelExtId);
        Goal goal = getGoalByExtId(goalExtId);
        Set<BWAttribute> attributes = getAttributes(
                goalModel.getSpecification(), paths);

        if (attributes.size() == 0)
            throw new BWException(BWErrorType.INVALID_PATH, paths.toString());

        DEFAttributeCondition defAttributeCondition = getDefAttributeCondition(
                attributes);

        goal.addActivationCondition(defAttributeCondition);

        return defAttributeCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public DEFAttributeCondition associateAttributeAchieveConditionToGoalSuccessCondition(
            String goalModelExtId, String goalExtId, Set<String> paths) {
        BWGoalModel goalModel = getGoalModelByExtId(goalModelExtId);
        Goal goal = getGoalByExtId(goalExtId);
        Set<BWAttribute> attributes = getAttributes(
                goalModel.getSpecification(), paths);

        if (attributes.size() == 0)
            throw new BWException(BWErrorType.INVALID_PATH, paths.toString());

        DEFAttributeCondition defAttributeCondition = getDefAttributeCondition(
                attributes);

        goal.addSuccessCondition(defAttributeCondition);

        return defAttributeCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public void associateMulConditionToGoalEntityInvariantCondition(
            String goalModelExtId, String goalExtId, String path,
            String cardinality) {
        BWGoalModel goalModel = getGoalModelByExtId(goalModelExtId);
        Goal goal = getGoalByExtId(goalExtId);

        MULCondition mulCondition = getMULCondition(
                goalModel.getSpecification(), path);

        goal.addEntityInvariantCondition(mulCondition);
    }

    @Atomic(mode = TxMode.WRITE)
    public void associateRuleConditionToGoalAttributeInvariantCondition(
            String goalModelExtId, String goalExtId, String ruleName) {
        BWGoalModel goalModel = getGoalModelByExtId(goalModelExtId);
        Goal goal = getGoalByExtId(goalExtId);

        BWRule rule = getRule(goalModel.getSpecification(), ruleName);

        goal.addAttributeInvariantCondition(rule);
    }

    @Atomic(mode = TxMode.WRITE)
    public Goal addSubGoal(String goalModelExtId, String extId,
            String subGoalName) {
        BWGoalModel goalModel = getGoalModelByExtId(goalModelExtId);
        Goal goal = getGoalByExtId(extId);
        Goal subGoal = getGoalByName(goalModel.getSpecification(), subGoalName);
        goal.addSubGoals(subGoal);

        return subGoal;
    }

    public ProductDTO getSourceOfPath(String specId, String path) {
        BWSpecification spec = getSpecBySpecId(specId);

        BWEntity entity = getEntityByName(spec.getDataModel(),
                path.split("\\.")[0]);

        return new ProductDTO(entity.getExternalId(),
                spec.getDataModel().getExternalId(), ProductType.ENTITY.name());
    }

    public ProductDTO getTargetOfPath(String specId, String path) {
        BWSpecification spec = getSpecBySpecId(specId);

        BWProduct product = getTargetOfPath(spec, path);

        return new ProductDTO(product.getExternalId(),
                spec.getDataModel().getExternalId(),
                product.getProductType().name());

    }

    public Set<String> getDependencePaths(String specId,
            Set<String> sucConditions) {
        Set<String> paths = new HashSet<String>();
        BWSpecification spec = getSpecBySpecId(specId);

        for (String sucCond : sucConditions) {
            BWProduct product = getTargetOfPath(spec, sucCond);

            paths.addAll(product.getDependenceSet().stream()
                    .map(dep -> dep.getPath()).collect(Collectors.toSet()));

            if (product instanceof BWAttribute) {
                BWAttribute attribute = (BWAttribute) product;
                if (attribute.getAttributeGroup() != null) {
                    paths.addAll(attribute.getDependenceSet().stream()
                            .map(dep -> dep.getPath())
                            .collect(Collectors.toSet()));
                }
            }
        }

        return paths;
    }

    public void printSpecificationModels(String specId) {
        BWSpecification spec = getSpecBySpecId(specId);

        System.out.println("SPECIFICATION: " + spec.getName());

        System.out.println("Specification Data Model: " + spec.getName());
        System.out.println(
                "-------------------------------------------------------");

        for (BWEntity entity : spec.getDataModel().getEntitiesSet()) {
            System.out.println("Entity " + entity.getName() + " Exists:"
                    + entity.getExists());
        }

        spec.getDataModel().getDependenceSet().stream()
                .map(dep -> dep.getProduct().getName() + ":" + dep.getPath())
                .forEach(System.out::println);

        spec.getDataModel().getRuleSet().stream()
                .map(rule -> rule.getName() + ":"
                        + rule.getCondition().getSubPath())
                .forEach(System.out::println);

        System.out.println("Specification Condition Model: " + spec.getName());
        System.out.println(
                "-------------------------------------------------------");

        spec.getConditionModel().getEntityAchieveConditionSet().stream()
                .map(def -> def.getEntity().getName() + "-"
                        + def.getEntity().getExists())
                .forEach(System.out::println);

        spec.getConditionModel().getEntityDependenceConditionSet().stream()
                .map(dep -> dep.getProduct().getName() + "-" + dep.getPath())
                .forEach(System.out::println);

        spec.getConditionModel().getEntityInvariantConditionSet().stream()
                .map(mul -> mul.getExpression()).forEach(System.out::println);

        spec.getConditionModel().getAttributeAchieveConditionSet().stream()
                .map(def -> (def.getAttribute() != null)
                        ? (def.getAttribute().getEntity().getName() + "."
                                + def.getAttribute().getName())
                        : (def.getAttributeGroup().getEntity().getName() + "."
                                + def.getAttributeGroup().getName()))
                .forEach(System.out::println);

        spec.getConditionModel().getAttributeDependenceConditionSet().stream()
                .map(dep -> dep.getProduct().getName() + "-" + dep.getPath())
                .forEach(System.out::println);

        spec.getConditionModel().getAttributeInvariantConditionSet().stream()
                .map(rule -> rule.getName() + "-"
                        + rule.getCondition().getSubPath())
                .forEach(System.out::println);

        System.out.println("Specification Goal Model: " + spec.getName());
        System.out.println(
                "-------------------------------------------------------");

        for (Goal goal : spec.getGoalModel().getGoalSet()) {
            System.out.println("Goal name:" + goal.getName());

            for (Goal sub : goal.getSubGoalsSet()) {
                System.out.println("Sub goal name:" + sub.getName());
            }
            for (Condition act : goal.getActivationConditionSet()) {
                System.out.println("ACT(" + act.getSubPath() + ")");
            }
            for (Condition suc : goal.getSuccessConditionSet()) {
                System.out.println("SUC(" + suc.getSubPath() + ")");
            }
            for (MULCondition mul : goal.getEntityInvariantConditionSet()) {
                System.out.println(mul.getExpression());
            }
            for (BWRule rule : goal.getAttributeInvariantConditionSet()) {
                System.out.println("RUL(" + rule.getName() + ")");
            }
        }

    }

    private BWDataModel getDataModelByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        BWDataModel dataModel = FenixFramework.getDomainObject(externalId);
        if (dataModel == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return dataModel;
    }

    private BWConditionModel getConditionModelByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        BWConditionModel conditionModel = FenixFramework
                .getDomainObject(externalId);
        if (conditionModel == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return conditionModel;
    }

    private BWGoalModel getGoalModelByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        BWGoalModel goalModel = FenixFramework.getDomainObject(externalId);
        if (goalModel == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return goalModel;
    }

    private BWProduct getProductByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        BWProduct product = FenixFramework.getDomainObject(externalId);
        if (product == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return product;
    }

    private BWEntity getEntityByName(BWDataModel dataModel, String name) {
        return dataModel.getEntity(name).orElseThrow(
                () -> new BWException(BWErrorType.INVALID_ENTITY_NAME, name));
    }

    private BWEntity getEntityByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        BWEntity entity = FenixFramework.getDomainObject(externalId);
        if (entity == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return entity;
    }

    private BWAttributeGroup getAttributeGroupByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        BWAttributeGroup attributeGroup = FenixFramework
                .getDomainObject(externalId);
        if (attributeGroup == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return attributeGroup;
    }

    private BWAttributeGroup getAttributeGroup(BWEntity entity,
            Set<BWAttribute> attributes) {
        return entity.getAttributeGroupSet().stream()
                .filter(attGroup -> attGroup.getAttributeSet()
                        .equals(attributes))
                .findFirst()
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_ATTRIBUTE_GROUP,
                        attributes.stream().map(att -> att.getName())
                                .collect(Collectors.joining(","))));
    }

    private Set<BWAttribute> getAttributes(BWSpecification spec,
            Set<String> paths) {
        log.debug("getAttributes paths:{}", paths);
        BWEntity entity = null;
        Set<BWAttribute> attributes = new HashSet<BWAttribute>();
        for (String path : paths) {
            String entityName = path.split("\\.")[0];
            BWEntity tmp = getEntityByName(spec.getDataModel(), entityName);
            if ((entity != null) && (entity != tmp))
                throw new BWException(BWErrorType.INVALID_ATTRIBUTE_GROUP,
                        paths.toString());
            entity = tmp;
            attributes.add((BWAttribute) getTargetOfPath(spec, path));
        }
        return attributes;
    }

    private BWDependence getDependenceByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        BWDependence dependence = FenixFramework.getDomainObject(externalId);
        if (dependence == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return dependence;
    }

    private BWDependence getDependence(BWProduct product, String path) {
        return product.getDependenceSet().stream()
                .filter(dep -> dep.getPath().equals(path)).findFirst()
                .orElseThrow(() -> new BWException(
                        BWErrorType.DEPENDENCE_NOT_EXISTS, path));

    }

    private BWProduct getTargetOfPath(BWSpecification spec, String path) {
        List<String> pathLeft = Arrays.stream(path.split("\\."))
                .collect(Collectors.toList());
        BWEntity entity = getEntityByName(spec.getDataModel(), pathLeft.get(0));
        pathLeft.remove(0);
        return entity.getNext(pathLeft, path);
    }

    private Goal getGoalByName(BWSpecification spec, String goalName) {
        return spec.getGoalModel().getGoalSet().stream()
                .filter(g -> g.getName().equals(goalName)).findFirst()
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_GOAL_NAME, goalName));
    }

    private Goal getGoalByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        Goal goal = FenixFramework.getDomainObject(externalId);
        if (goal == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return goal;
    }

    private DEFAttributeCondition getDefAttributeCondition(
            Set<BWAttribute> attributes) {
        DEFAttributeCondition defAttributeCondition = null;
        if (attributes.size() == 1) {
            BWAttribute att = attributes.stream().findFirst().get();
            defAttributeCondition = DEFAttributeCondition
                    .getDEFAttributeCondition(att);
        } else {
            BWEntity entity = attributes.stream().findFirst().get().getEntity();
            BWAttributeGroup attributeGroup = getAttributeGroup(entity,
                    attributes);
            defAttributeCondition = DEFAttributeCondition
                    .getDEFAttributeCondition(attributeGroup);
        }

        return defAttributeCondition;
    }

    private BWRule getRule(BWSpecification spec, String ruleName) {
        return spec.getDataModel().getRuleSet().stream()
                .filter(r -> r.getName().equals(ruleName)).findFirst()
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_RULE_NAME, ruleName));

    }

    private MULCondition getMULCondition(BWSpecification spec, String path) {
        String entityName = path.split("\\.")[0];
        String rolename = path.split("\\.")[1];

        BWRelation relation = spec.getDataModel().getRelationsSet().stream()
                .filter(rel -> (rel.getEntityOne().getName().equals(entityName)
                        && rel.getRoleNameTwo().equals(rolename))
                        || (rel.getEntityTwo().getName().equals(entityName)
                                && rel.getRoleNameOne().equals(rolename)))
                .findFirst().orElseThrow(
                        () -> new BWException(BWErrorType.INVALID_PATH, path));

        return MULCondition.getMulCondition(relation, rolename);
    }

    private Condition buildCondition(BWDataModel dataModel,
            ExpressionDTO expression) {
        switch (Type.valueOf(expression.getType())) {
        case AND:
            return new AndCondition(
                    buildCondition(dataModel, expression.getLeftExpression()),
                    buildCondition(dataModel, expression.getRightExpression()));
        case OR:
            return new OrCondition(
                    buildCondition(dataModel, expression.getLeftExpression()),
                    buildCondition(dataModel, expression.getRightExpression()));
        case NOT:
            return new NotCondition(
                    buildCondition(dataModel, expression.getUnaryExpression()));
        case ATT_DEF:
            // TODO: remove the cast
            BWProduct product = getTargetOfPath(dataModel.getSpecification(),
                    expression.getValue());
            if (product instanceof BWAttribute)
                return DEFAttributeCondition
                        .getDEFAttributeCondition((BWAttribute) product);
            else if (product instanceof BWAttributeGroup)
                return DEFAttributeCondition
                        .getDEFAttributeCondition((BWAttributeGroup) product);
            assert(true);
            return null;
        case ATT_VALUE:
            // TODO: remove cast
            BWAttribute att = (BWAttribute) getTargetOfPath(
                    dataModel.getSpecification(), expression.getValue());
            return new BWAttributeBoolCondition(att);
        case EQUAL:
            if (ExpressionDTO.isBoolExp(
                    Type.valueOf(expression.getLeftExpression().getType())))
                return new BoolComparison(
                        buildCondition(dataModel,
                                expression.getLeftExpression()),
                        buildCondition(dataModel,
                                expression.getRightExpression()),
                        ComparisonOperator.EQUAL);
            else
                return new Comparison(
                        buildExpression(dataModel,
                                expression.getLeftExpression()),
                        buildExpression(dataModel,
                                expression.getRightExpression()),
                        ComparisonOperator.EQUAL);
        case NOT_EQUAL:
            if (ExpressionDTO.isBoolExp(
                    Type.valueOf(expression.getLeftExpression().getType())))
                return new BoolComparison(
                        buildCondition(dataModel,
                                expression.getLeftExpression()),
                        buildCondition(dataModel,
                                expression.getRightExpression()),
                        ComparisonOperator.NOT_EQUAL);
            else
                return new Comparison(
                        buildExpression(dataModel,
                                expression.getLeftExpression()),
                        buildExpression(dataModel,
                                expression.getRightExpression()),
                        ComparisonOperator.NOT_EQUAL);
        case GREATER:
            return new Comparison(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    ComparisonOperator.GREATER);
        case GREATER_EQUAL:
            return new Comparison(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    ComparisonOperator.GREATER_EQUAL);
        case SMALLER:
            return new Comparison(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    ComparisonOperator.SMALLER);
        case SMALLER_EQUAL:
            return new Comparison(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    ComparisonOperator.SMALLER_EQUAL);
        case BOOL:
            if (expression.getValue().toLowerCase().equals("true"))
                return new TrueCondition();
            if (expression.getValue().toLowerCase().equals("false"))
                return new FalseCondition();
            assert(false);
            return null;
        default:
            assert(false);
            return null;
        }
    }

    private BWExpression buildExpression(BWDataModel dataModel,
            ExpressionDTO expression) {
        switch (Type.valueOf(expression.getType())) {
        case PLUS:
            return new BWBinaryExpression(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    BinaryOperator.PLUS);
        case MINUS:
            return new BWBinaryExpression(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    BinaryOperator.MINUS);
        case MUL:
            return new BWBinaryExpression(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    BinaryOperator.MUL);
        case DIV:
            return new BWBinaryExpression(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    BinaryOperator.DIV);
        case ATT_VALUE:
            // TODO: remove cast
            BWAttribute attribute = (BWAttribute) getTargetOfPath(
                    dataModel.getSpecification(), expression.getValue());
            return new BWAttributeValueExpression(attribute);
        case INT:
            return new BWNumberLiteral(Integer.parseInt(expression.getValue()));
        case STRING:
            return new BWStringLiteral(expression.getValue());
        default:
            assert(false);
            return null;
        }
    }

}

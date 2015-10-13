package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeGroup;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeValueExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BinaryExpression;
import pt.ist.socialsoftware.blendedworkflow.domain.BinaryExpression.BinaryOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.BoolComparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison;
import pt.ist.socialsoftware.blendedworkflow.domain.Comparison.ComparisonOperator;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.ConditionModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Expression;
import pt.ist.socialsoftware.blendedworkflow.domain.FalseCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.NotCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.NumberLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.OrCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW.Cardinality;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.StringLiteral;
import pt.ist.socialsoftware.blendedworkflow.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeGroupDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefConditionSetDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefEntityConditionDTO;
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
import pt.ist.socialsoftware.blendedworkflow.service.req.AddActivityReq;

public class DesignInterface {
    private static Logger log = LoggerFactory.getLogger(DesignInterface.class);

    private static DesignInterface instance;

    public static DesignInterface getInstance() {
        if (instance == null) {
            instance = new DesignInterface();
        }
        return instance;
    }

    private DesignInterface() {
    }

    private BlendedWorkflow getBlendedWorkflow() {
        return BlendedWorkflow.getInstance();
    }

    // to be invoked by tests only
    @Atomic(mode = TxMode.WRITE)
    public void deleteSpecification(String specId) {
        getSpecBySpecId(specId).delete();
    }

    @Atomic(mode = TxMode.WRITE)
    public Specification getSpecBySpecId(String specId) {
        Specification spec = getBlendedWorkflow().getSpecById(specId)
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_SPECIFICATION_ID, specId));
        return spec;
    }

    @Atomic(mode = TxMode.WRITE)
    public Specification createSpecification(SpecDTO specDTO) {
        return getBlendedWorkflow().createSpecification(specDTO.getSpecId(),
                specDTO.getName());
    }

    @Atomic(mode = TxMode.WRITE)
    public void cleanDataModel(String specId) {
        Specification spec = getSpecBySpecId(specId);

        spec.getDataModel().clean();
    }

    @Atomic(mode = TxMode.WRITE)
    public Entity createEntity(EntityDTO entDTO) {
        log.debug("createEntity specId:{}, name:{}, exists:{}",
                entDTO.getSpecId(), entDTO.getName(), entDTO.getExists());

        Specification spec = getSpecBySpecId(entDTO.getSpecId());

        return spec.getDataModel().createEntity(entDTO.getName(),
                entDTO.getExists());
    }

    @Atomic(mode = TxMode.WRITE)
    public AttributeBasic createAttribute(AttributeDTO attDTO) {
        log.debug("createAttribute entityExtId:{}", attDTO.getEntityExtId());
        Entity ent = getEntityByExtId(attDTO.getEntityExtId());

        AttributeGroup attGroup = null;
        if (attDTO.getGroupExtId() != null) {
            attGroup = getAttributeGroupByExtId(attDTO.getGroupExtId());
        }

        AttributeBasic attribute = ent.createAttribute(attGroup,
                attDTO.getName(),
                AttributeType.parseAttributeType(attDTO.getType()),
                attDTO.getIsMandatory());

        return attribute;
    }

    public Attribute getAttribute(String specId, Set<String> paths) {
        return getAttribute(getSpecBySpecId(specId), paths);
    }

    @Atomic(mode = TxMode.WRITE)
    public RelationBW createRelation(RelationDTO relDTO) {
        log.debug("createRelation {}, entityOneName:{}, entityTwoName:{}",
                relDTO.getSpecId(), relDTO.getEntOneName(),
                relDTO.getEntTwoName());

        Specification spec = getSpecBySpecId(relDTO.getSpecId());

        Entity entityOne;
        if (relDTO.getEntOneExtId() != null) {
            entityOne = getEntityByExtId(relDTO.getEntOneExtId());
        } else {
            entityOne = getEntityByName(spec.getDataModel(),
                    relDTO.getEntOneName());
        }

        Entity entityTwo;
        if (relDTO.getEntTwoExtId() != null) {
            entityTwo = getEntityByExtId(relDTO.getEntTwoExtId());
        } else {
            entityTwo = getEntityByName(spec.getDataModel(),
                    relDTO.getEntTwoName());
        }

        if (entityOne.getDataModel() != entityTwo.getDataModel())
            throw new BWException(BWErrorType.INVALID_RELATION,
                    entityOne.getDataModel().getSpecification().getSpecId()
                            + "!=" + entityTwo.getDataModel().getSpecification()
                                    .getSpecId());

        RelationBW relation = entityOne.createRelation(relDTO.getName(),
                relDTO.getRolenameOne(),
                Cardinality.parseCardinality(relDTO.getCardinalityOne()),
                entityTwo, relDTO.getRolenameTwo(),
                Cardinality.parseCardinality(relDTO.getCardinalityTwo()));

        return relation;
    }

    @Atomic(mode = TxMode.WRITE)
    public AttributeGroup createAttributeGroup(AttributeGroupDTO attGroupDTO) {
        Entity entity = getEntityByExtId(attGroupDTO.getEntityExtId());

        AttributeGroup group = entity.createAttributeGroup(
                attGroupDTO.getName(), attGroupDTO.isMandatory());

        return group;
    }

    @Atomic(mode = TxMode.WRITE)
    public Dependence createDependence(DependenceDTO productDTO) {
        Product product = getProductByExtId(productDTO.getProductExtId());

        return product.createDependence(productDTO.getPath());
    }

    public Set<Dependence> getDependencies(String specId) {
        Specification spec = getSpecBySpecId(specId);

        Set<Dependence> deps = new HashSet<Dependence>();

        for (Dependence dependence : spec.getDataModel().getDependenceSet()) {
            deps.add(dependence);
        }

        return deps;
    }

    public boolean checkDependence(String extId) {
        Dependence dependence = getDependenceByExtId(extId);
        return dependence.check();
    }

    @Atomic(mode = TxMode.WRITE)
    public void deleteDependence(String extId) {
        Dependence dependence = getDependenceByExtId(extId);
        dependence.delete();
    }

    @Atomic(mode = TxMode.WRITE)
    public Rule createRule(RuleDTO ruleDTO) {
        Specification spec = getSpecBySpecId(ruleDTO.getSpecId());

        Rule rule = spec.getDataModel().createRule(ruleDTO.getName(),
                buildCondition(spec.getDataModel(), ruleDTO.getExpression()));

        log.debug("createRule expression:{}", rule.getCondition().getSubPath());

        return rule;
    }

    @Atomic(mode = TxMode.WRITE)
    public void cleanConditionModel(String specId) {
        Specification spec = getSpecBySpecId(specId);

        if (spec.getDataModel().getEntitySet().size() == 0)
            throw new BWException(BWErrorType.NO_DATA_MODEL, specId);

        spec.getConditionModel().clean();
    }

    @Atomic(mode = TxMode.WRITE)
    public boolean generateConditionModel(String specId) {
        Specification spec = getSpecBySpecId(specId);

        if (spec.getDataModel().getEntitySet().size() == 0)
            throw new BWException(BWErrorType.NO_DATA_MODEL, specId);

        spec.getConditionModel().generateConditions();

        return true;
    }

    @Atomic(mode = TxMode.WRITE)
    public void cleanGoalModel(String specId) {
        Specification spec = getSpecBySpecId(specId);

        if (spec.getDataModel().getEntitySet().size() == 0)
            throw new BWException(BWErrorType.NO_DATA_MODEL, specId);

        if (spec.getConditionModel().getEntityAchieveConditionSet().size() == 0)
            throw new BWException(BWErrorType.NO_CONDITION_MODEL, specId);

        spec.getGoalModel().clean();
    }

    @Atomic(mode = TxMode.WRITE)
    public void cleanActivityModel(String specId) {
        Specification spec = getSpecBySpecId(specId);

        if (spec.getDataModel().getEntitySet().size() == 0)
            throw new BWException(BWErrorType.NO_DATA_MODEL, specId);

        if (spec.getConditionModel().getEntityAchieveConditionSet().size() == 0)
            throw new BWException(BWErrorType.NO_CONDITION_MODEL, specId);

        spec.getTaskModel().clean();
    }

    @Atomic(mode = TxMode.WRITE)
    public DefEntityCondition createEntityAchieveCondition(
            DefEntityConditionDTO eacDTO) {
        log.debug("createEntityAchieveCondition Entity:{}, Value:{}",
                eacDTO.getEntityName(), eacDTO.isExists());
        Specification spec = getSpecBySpecId(eacDTO.getSpecId());

        Entity entity = getEntityByName(spec.getDataModel(),
                eacDTO.getEntityName());

        if (entity.getExists() != eacDTO.isExists()) {
            throw new BWException(BWErrorType.INVALID_ENTITY,
                    eacDTO.getEntityName() + " exists=" + entity.getExists());
        }

        DefEntityCondition defCondition = DefEntityCondition
                .getDefEntity(entity);
        spec.getConditionModel().addEntityAchieveCondition(defCondition);

        return defCondition;
    }

    public Entity getEntityByName(String specId, String entityName) {
        Specification spec = getSpecBySpecId(specId);

        return getEntityByName(spec.getDataModel(), entityName);
    }

    public AttributeBasic getAttributeByExtId(String extId) {
        return FenixFramework.getDomainObject(extId);
    }

    @Atomic(mode = TxMode.WRITE)
    public Dependence createEntityDependenceCondition(
            DependenceDTO dependenceDTO) {
        log.debug("createEntityDependenceCondition entityExtId:{}, Path:{}",
                dependenceDTO.getProductExtId(), dependenceDTO.getPath());

        Entity entity = getEntityByExtId(dependenceDTO.getProductExtId());

        Dependence dependence = getDependence(entity, dependenceDTO.getPath());

        ConditionModel conditionModel = entity.getDataModel().getSpecification()
                .getConditionModel();
        conditionModel.addEntityDependenceCondition(dependence);

        return dependence;
    }

    @Atomic(mode = TxMode.WRITE)
    public MulCondition createEntityInvariantCondition(MulConditionDTO miDTO) {
        log.debug("createEntityInvariantCondition Entity:{}, Cardinality:{}",
                miDTO.getRolePath(), miDTO.getCardinality());
        Specification spec = getSpecBySpecId(miDTO.getSpecId());

        MulCondition mulCondition = getMULCondition(spec, miDTO.getRolePath());

        if (!mulCondition.getCardinality().equals(miDTO.getCardinality()))
            new BWException(BWErrorType.INVALID_CARDINALITY,
                    miDTO.getCardinality());

        spec.getConditionModel().addEntityInvariantCondition(mulCondition);

        return mulCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public DefAttributeCondition createAttributeAchieveCondition(
            DefAttributeConditionDTO aacDTO) {
        log.debug("createAttributeAchieveCondition Paths:{}, Mandatory:{}",
                aacDTO.getPaths(), aacDTO.isMandatory());

        Specification spec = getSpecBySpecId(aacDTO.getSpecId());

        DefAttributeCondition defAttributeCondition = DefAttributeCondition
                .getDefAttribute(getAttribute(spec, aacDTO.getPaths()));

        if (defAttributeCondition.getAttributeOfDef() != null
                && defAttributeCondition.getAttributeOfDef()
                        .getIsMandatory() != aacDTO.isMandatory())
            throw new BWException(BWErrorType.INCONSISTENT_ATTRIBUTE_MANDATORY,
                    aacDTO.getPaths().toString());

        spec.getConditionModel()
                .addAttributeAchieveCondition(defAttributeCondition);

        return defAttributeCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public Dependence createAttributeDependence(DependenceDTO dependenceDTO) {
        log.debug("createAttributeDependenceCondition productExtId:{}, path:{}",
                dependenceDTO.getProductExtId(), dependenceDTO.getPath());
        Product product = getProductByExtId(dependenceDTO.getProductExtId());

        Dependence dependence = getDependence(product, dependenceDTO.getPath());

        ConditionModel conditionModel = product.getEntity().getDataModel()
                .getSpecification().getConditionModel();
        conditionModel.addAttributeDependenceCondition(dependence);

        return dependence;
    }

    @Atomic(mode = TxMode.WRITE)
    public Rule createAttributeInvariant(RuleDTO ruleDTO) {
        Specification spec = getSpecBySpecId(ruleDTO.getSpecId());

        Rule rule = getRule(spec, ruleDTO.getName());

        spec.getConditionModel().addAttributeInvariantCondition(rule);

        return rule;
    }

    @Atomic(mode = TxMode.WRITE)
    public Goal createGoal(GoalDTO goalDTO) {
        Specification spec = getSpecBySpecId(goalDTO.getSpecId());

        return new Goal(spec.getGoalModel(), goalDTO.getName());
    }

    public Goal getGoalByName(String specId, String goalName) {
        Specification spec = getSpecBySpecId(specId);

        return getGoalByName(spec, goalName);
    }

    public Set<DefEntityCondition> getGoalSuccessEntitySet(String specId,
            String goalName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        return goal.getSuccessConditionSet().stream()
                .filter(DefEntityCondition.class::isInstance)
                .map(DefEntityCondition.class::cast)
                .collect(Collectors.toSet());
    }

    @Atomic(mode = TxMode.WRITE)
    public DefEntityCondition associateEntityToGoalSuccess(String specId,
            String goalName, String path) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        Product product = spec.getDataModel().getTargetOfPath(path);
        if (product.getProductType() != ProductType.ENTITY)
            throw new BWException(BWErrorType.INVALID_PATH, path);

        DefEntityCondition defEntityCondition = ((Entity) product)
                .getDefEntityCondition();
        goal.addSuccessCondition(defEntityCondition);

        return defEntityCondition;
    }

    public Set<DefAttributeCondition> getGoalSuccessAttributeSet(String specId,
            String goalName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        return goal.getSuccessConditionSet().stream()
                .filter(DefAttributeCondition.class::isInstance)
                .map(DefAttributeCondition.class::cast)
                .collect(Collectors.toSet());
    }

    @Atomic(mode = TxMode.WRITE)
    public DefAttributeCondition associateAttributeToGoalSuccess(String specId,
            String goalName, Set<String> paths) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);
        DefAttributeCondition defAttributeCondition = DefAttributeCondition
                .getDefAttribute(getAttribute(spec, paths));

        goal.addSuccessCondition(defAttributeCondition);

        return defAttributeCondition;
    }

    public Set<DefEntityCondition> getGoalActivationEntitySet(String specId,
            String goalName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        return goal.getActivationConditionSet().stream()
                .filter(DefEntityCondition.class::isInstance)
                .map(DefEntityCondition.class::cast)
                .collect(Collectors.toSet());
    }

    @Atomic(mode = TxMode.WRITE)
    public DefEntityCondition associateEntityToGoalAtivation(String specId,
            String goalName, String path) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        Product product = spec.getDataModel().getTargetOfPath(path);
        if (product.getProductType() != ProductType.ENTITY)
            throw new BWException(BWErrorType.INVALID_PATH, path);

        DefEntityCondition defEntityCondition = ((Entity) product)
                .getDefEntityCondition();
        goal.addActivationCondition(defEntityCondition);

        return defEntityCondition;
    }

    public Set<DefAttributeCondition> getGoalActivationAttributeSet(
            String specId, String goalName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        return goal.getActivationConditionSet().stream()
                .filter(DefAttributeCondition.class::isInstance)
                .map(DefAttributeCondition.class::cast)
                .collect(Collectors.toSet());
    }

    @Atomic(mode = TxMode.WRITE)
    public DefAttributeCondition associateAttributeToGoalActivation(
            String specId, String goalName, Set<String> paths) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);
        DefAttributeCondition defAttributeCondition = DefAttributeCondition
                .getDefAttribute(getAttribute(spec, paths));

        goal.addActivationCondition(defAttributeCondition);

        return defAttributeCondition;
    }

    public Set<MulCondition> getGoalMulInvSet(String specId, String goalName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        return goal.getEntityInvariantConditionSet();
    }

    @Atomic(mode = TxMode.WRITE)
    public void associateMulToGoalInvariant(String specId, String goalName,
            String path, String cardinality) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        MulCondition mulCondition = getMULCondition(spec, path);

        goal.addEntityInvariantCondition(mulCondition);
    }

    public Set<Rule> getGoalRuleInvSet(String specId, String goalName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        return goal.getAttributeInvariantConditionSet();
    }

    @Atomic(mode = TxMode.WRITE)
    public void associateRuleToGoalInvariant(String specId, String goalName,
            String ruleName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        Rule rule = getRule(spec, ruleName);

        goal.addAttributeInvariantCondition(rule);
    }

    public Set<Goal> getSubGoals(String specId, String goalName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        return goal.getSubGoalSet();
    }

    public Goal getParentGoal(String specId, String goalName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);

        return goal.getParentGoal();
    }

    @Atomic(mode = TxMode.WRITE)
    public Goal addSubGoal(String specId, String goalName, String subGoalName) {
        Specification spec = getSpecBySpecId(specId);
        Goal goal = getGoalByName(spec, goalName);
        Goal subGoal = getGoalByName(spec, subGoalName);
        goal.addSubGoal(subGoal);

        return subGoal;
    }

    @Atomic(mode = TxMode.WRITE)
    public Goal mergeGoals(String specId, String newGoalName,
            String goalNameOne, String goalNameTwo) {
        Specification spec = getSpecBySpecId(specId);
        Goal goalOne = getGoalByName(spec, goalNameOne);
        Goal goalTwo = getGoalByName(spec, goalNameTwo);

        return spec.getGoalModel().mergeGoals(newGoalName, goalOne, goalTwo);
    }

    @Atomic(mode = TxMode.WRITE)
    public Goal extractChildGoal(String specId, String newGoalName,
            String sourceGoalName, DefConditionSetDTO successConditionDTO) {
        Specification spec = getSpecBySpecId(specId);
        Goal parentGoal = getGoalByName(spec, sourceGoalName);

        Set<Condition> successConditions = getConditionSet(spec,
                successConditionDTO);

        return parentGoal.extractChild(newGoalName, successConditions);
    }

    @Atomic(mode = TxMode.WRITE)
    public Goal extractSiblingGoal(String specId, String newGoalName,
            String sourceGoalName, DefConditionSetDTO successConditionDTO) {
        Specification spec = getSpecBySpecId(specId);
        Goal sourceGoal = getGoalByName(spec, sourceGoalName);

        Set<Condition> successConditions = getConditionSet(spec,
                successConditionDTO);

        return sourceGoal.extractSibling(newGoalName, successConditions);
    }

    @Atomic(mode = TxMode.WRITE)
    public Task createActivity(ActivityDTO activityDTO) {
        Specification spec = getSpecBySpecId(activityDTO.getSpecId());

        return spec.getTaskModel().createTask(activityDTO.getName(),
                activityDTO.getDescription());
    }

    @Atomic(mode = TxMode.WRITE)
    public Task addActivity(String specId, AddActivityReq request) {
        Specification spec = getSpecBySpecId(specId);

        return spec.getTaskModel().addTask(request.getActivityName(),
                request.getDescription(),
                getConditionSet(spec, request.getPostConditionSet()));
    }

    public Set<Condition> getActivityPreCondition(String specId,
            String activityName) {
        Specification spec = getSpecBySpecId(specId);
        Task task = getTaskByName(spec, activityName);

        return task.getPreConditionSet();
    }

    @Atomic(mode = TxMode.WRITE)
    public DefEntityCondition associateEntityToActivityPre(String specId,
            String activityName, String path) {
        Specification spec = getSpecBySpecId(specId);
        Task task = getTaskByName(spec, activityName);

        Product product = spec.getDataModel().getTargetOfPath(path);
        if (product.getProductType() != ProductType.ENTITY)
            throw new BWException(BWErrorType.INVALID_PATH, path);

        DefEntityCondition defEntityCondition = ((Entity) product)
                .getDefEntityCondition();

        task.addPreCondition(defEntityCondition);

        return defEntityCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public DefAttributeCondition associateAttributeToActivityPre(String specId,
            String activityName, Set<String> paths) {
        Specification spec = getSpecBySpecId(specId);
        Task task = getTaskByName(spec, activityName);
        DefAttributeCondition defAttributeCondition = DefAttributeCondition
                .getDefAttribute(getAttribute(spec, paths));

        task.addPreCondition(defAttributeCondition);

        return defAttributeCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public DefEntityCondition associateEntityToActivityPost(String specId,
            String activityName, String path) {
        Specification spec = getSpecBySpecId(specId);
        Task task = getTaskByName(spec, activityName);

        Product product = spec.getDataModel().getTargetOfPath(path);
        if (product.getProductType() != ProductType.ENTITY)
            throw new BWException(BWErrorType.INVALID_PATH, path);

        DefEntityCondition defEntityCondition = ((Entity) product)
                .getDefEntityCondition();

        task.addPostCondition(defEntityCondition);

        return defEntityCondition;
    }

    @Atomic(mode = TxMode.WRITE)
    public DefAttributeCondition associateAttributeToActivityPost(String specId,
            String activityName, Set<String> paths) {
        Specification spec = getSpecBySpecId(specId);
        Task task = getTaskByName(spec, activityName);
        DefAttributeCondition defAttributeCondition = DefAttributeCondition
                .getDefAttribute(getAttribute(spec, paths));

        task.addPostCondition(defAttributeCondition);

        return defAttributeCondition;
    }

    public Set<MulCondition> getActivityMulConditions(String specId,
            String activityName) {
        Specification spec = getSpecBySpecId(specId);
        Task task = getTaskByName(spec, activityName);

        return task.getMultiplicityInvariantSet();
    }

    @Atomic(mode = TxMode.WRITE)
    public MulCondition associateMulToActivityPost(String specId,
            String activityName, String path, String cardinality) {
        Specification spec = getSpecBySpecId(specId);
        Task task = getTaskByName(spec, activityName);

        MulCondition mulCondition = getMULCondition(spec, path);

        task.addMultiplicityInvariant(mulCondition);

        return mulCondition;
    }

    public Set<Rule> getActivityRuleConditions(String specId,
            String activityName) {
        Specification spec = getSpecBySpecId(specId);
        Task task = getTaskByName(spec, activityName);

        return task.getRuleInvariantSet();
    }

    @Atomic(mode = TxMode.WRITE)
    public Rule associateRuleToActivityPost(String specId, String activityName,
            String ruleName) {
        Specification spec = getSpecBySpecId(specId);
        Task task = getTaskByName(spec, activityName);

        Rule rule = getRule(spec, ruleName);

        task.addRuleInvariant(rule);

        return rule;
    }

    public boolean checkActivityModel(String specId) {
        Specification spec = getSpecBySpecId(specId);

        return spec.getTaskModel().checkModel();
    }

    public ProductDTO getSourceOfPath(String specId, String path) {
        Specification spec = getSpecBySpecId(specId);

        Entity entity = getEntityByName(spec.getDataModel(),
                path.split("\\.")[0]);

        return new ProductDTO(specId, entity.getExternalId(),
                ProductType.ENTITY.name());
    }

    public ProductDTO getTargetOfPath(String specId, String path) {
        Specification spec = getSpecBySpecId(specId);

        Product product = spec.getDataModel().getTargetOfPath(path);

        return new ProductDTO(specId, product.getExternalId(),
                product.getProductType().name());

    }

    public Set<String> getDependencePaths(String specId,
            Set<String> sucConditions) {
        Set<String> paths = new HashSet<String>();
        Specification spec = getSpecBySpecId(specId);

        for (String sucCond : sucConditions) {
            Product product = spec.getDataModel().getTargetOfPath(sucCond);

            paths.addAll(product.getDependenceSet().stream()
                    .map(dep -> dep.getPath()).collect(Collectors.toSet()));

            if (product instanceof AttributeBasic) {
                AttributeBasic attribute = (AttributeBasic) product;
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
        Specification spec = getSpecBySpecId(specId);

        System.out.println("SPECIFICATION: " + spec.getName());

        System.out.println(
                "-------------------------------------------------------");
        System.out.println(
                "-------------------------------------------------------");
        System.out.println("Specification Data Model: " + spec.getName());
        System.out.println(
                "-------------------------------------------------------");

        for (Entity entity : spec.getDataModel().getEntitySet()) {
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

        System.out.println(
                "-------------------------------------------------------");
        System.out.println(
                "-------------------------------------------------------");
        System.out.println("Specification Condition Model: " + spec.getName());
        System.out.println(
                "-------------------------------------------------------");

        spec.getConditionModel().getEntityAchieveConditionSet().stream()
                .map(def -> "DEF(" + def.getEntity().getName() + ") "
                        + def.getEntity().getExists())
                .forEach(System.out::println);

        spec.getConditionModel().getEntityDependenceConditionSet().stream()
                .map(dep -> dep.getProduct().getName() + "-" + dep.getPath())
                .forEach(System.out::println);

        spec.getConditionModel().getEntityInvariantConditionSet().stream()
                .map(mul -> mul.getExpression()).forEach(System.out::println);

        spec.getConditionModel().getAttributeAchieveConditionSet().stream()
                .map(def -> "DEF("
                        + def.getAttributeOfDef().getEntity().getName() + "."
                        + def.getAttributeOfDef().getName() + ")")
                .forEach(System.out::println);

        spec.getConditionModel().getAttributeDependenceConditionSet().stream()
                .map(dep -> dep.getProduct().getName() + "-" + dep.getPath())
                .forEach(System.out::println);

        spec.getConditionModel().getAttributeInvariantConditionSet().stream()
                .map(rule -> rule.getName() + "-"
                        + rule.getCondition().getSubPath())
                .forEach(System.out::println);

        System.out.println(
                "-------------------------------------------------------");
        System.out.println(
                "-------------------------------------------------------");
        System.out.println("Specification Goal Model: " + spec.getName());
        System.out.println(
                "-------------------------------------------------------");

        for (Goal goal : spec.getGoalModel().getGoalSet()) {
            System.out.println("Goal name:" + goal.getName());

            for (Goal sub : goal.getSubGoalSet()) {
                System.out.println("Sub goal name:" + sub.getName());
            }
            for (Condition act : goal.getActivationConditionSet()) {
                System.out.println("ACT(" + act.getSubPath() + ")");
            }
            for (Condition suc : goal.getSuccessConditionSet()) {
                System.out.println("SUC(" + suc.getSubPath() + ")");
            }
            for (MulCondition mul : goal.getEntityInvariantConditionSet()) {
                System.out.println(mul.getExpression());
            }
            for (Rule rule : goal.getAttributeInvariantConditionSet()) {
                System.out.println("RUL(" + rule.getName() + ")");
            }
        }

        System.out.println(
                "-------------------------------------------------------");
        System.out.println(
                "-------------------------------------------------------");
        System.out.println("Specification Activity Model: " + spec.getName());
        System.out.println(
                "-------------------------------------------------------");

        for (Task task : spec.getTaskModel().getTasksSet()) {
            System.out.println("Task name:" + task.getName() + ", description:"
                    + task.getDescription());

            task.getPreConditionSet().stream().forEach((c) -> System.out
                    .println("PreCondition:" + c.getSubPath()));

            task.getPostConditionSet().stream().forEach((c) -> System.out
                    .println("PostCondition:" + c.getSubPath()));

            task.getMultiplicityInvariantSet().stream()
                    .forEach((m) -> System.out.println(
                            "MultiplicityInvariant:" + m.getExpression()));

            task.getRuleInvariantSet().stream().forEach((r) -> System.out
                    .println("MultiplicityInvariant:" + r.getName()));
        }

    }

    private Product getProductByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        Product product = FenixFramework.getDomainObject(externalId);
        if (product == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return product;
    }

    private Entity getEntityByName(DataModel dataModel, String name) {
        return dataModel.getEntity(name).orElseThrow(
                () -> new BWException(BWErrorType.INVALID_ENTITY_NAME, name));
    }

    private Entity getEntityByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        Entity entity = FenixFramework.getDomainObject(externalId);
        if (entity == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return entity;
    }

    private AttributeGroup getAttributeGroupByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        AttributeGroup attributeGroup = FenixFramework
                .getDomainObject(externalId);
        if (attributeGroup == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return attributeGroup;
    }

    private Attribute getAttribute(Specification spec, Set<String> paths) {
        log.debug("getAttributes paths:{}", paths);
        Entity entity = null;
        Set<AttributeBasic> attributes = new HashSet<AttributeBasic>();
        for (String path : paths) {
            String entityName = path.split("\\.")[0];
            Entity tmp = getEntityByName(spec.getDataModel(), entityName);
            if ((entity != null) && (entity != tmp))
                throw new BWException(BWErrorType.INVALID_ATTRIBUTE_GROUP,
                        paths.toString());
            entity = tmp;
            attributes.add(
                    (AttributeBasic) spec.getDataModel().getTargetOfPath(path));
        }

        if (attributes.size() == 0)
            throw new BWException(BWErrorType.INVALID_PATH, paths.toString());
        else if (attributes.size() == 1)
            return attributes.stream().findFirst().get();
        else
            return getAttributeGroup(entity, attributes);
    }

    private AttributeGroup getAttributeGroup(Entity entity,
            Set<AttributeBasic> attributes) {
        return entity.getAttributeGroupSet().stream()
                .filter(attGroup -> attGroup.getAttributeSet()
                        .equals(attributes))
                .findFirst()
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_ATTRIBUTE_GROUP,
                        attributes.stream().map(att -> att.getName())
                                .collect(Collectors.joining(","))));
    }

    private Dependence getDependenceByExtId(String externalId) {
        if (externalId == null || externalId.equals(""))
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        Dependence dependence = FenixFramework.getDomainObject(externalId);
        if (dependence == null)
            throw new BWException(BWErrorType.NOT_FOUND, externalId);
        return dependence;
    }

    private Dependence getDependence(Product product, String path) {
        return product.getDependenceSet().stream()
                .filter(dep -> dep.getPath().equals(path)).findFirst()
                .orElseThrow(() -> new BWException(
                        BWErrorType.DEPENDENCE_NOT_EXISTS, path));

    }

    private Goal getGoalByName(Specification spec, String goalName) {
        return spec.getGoalModel().getGoalSet().stream()
                .filter(g -> g.getName().equals(goalName)).findFirst()
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_GOAL_NAME, goalName));
    }

    private Rule getRule(Specification spec, String ruleName) {
        return spec.getDataModel().getRuleSet().stream()
                .filter(r -> r.getName().equals(ruleName)).findFirst()
                .orElseThrow(() -> new BWException(
                        BWErrorType.INVALID_RULE_NAME, ruleName));
    }

    private MulCondition getMULCondition(Specification spec, String path) {
        String entityName = path.split("\\.")[0];
        String rolename = path.split("\\.")[1];

        RelationBW relation = spec.getDataModel().getRelationBWSet().stream()
                .filter(rel -> (rel.getEntityOne().getName().equals(entityName)
                        && rel.getRoleNameTwo().equals(rolename))
                        || (rel.getEntityTwo().getName().equals(entityName)
                                && rel.getRoleNameOne().equals(rolename)))
                .findFirst().orElseThrow(
                        () -> new BWException(BWErrorType.INVALID_PATH, path));

        return MulCondition.getMulCondition(relation, rolename);
    }

    private Set<Condition> getConditionSet(Specification spec,
            DefConditionSetDTO defConditionSetDTO) {
        Set<Condition> conditions = defConditionSetDTO.getDefEnts().stream()
                .map((def) -> DefEntityCondition.getDefEntity(getEntityByName(
                        spec.getDataModel(), def.getEntityName())))
                .collect(Collectors.toSet());

        conditions.addAll(defConditionSetDTO.getDefAtts().stream()
                .map((def) -> DefAttributeCondition
                        .getDefAttribute(getAttribute(spec, def.getPaths())))
                .collect(Collectors.toSet()));
        return conditions;
    }

    private Task getTaskByName(Specification spec, String name) {
        return spec.getTaskModel().getTasksSet().stream()
                .filter(t -> t.getName().equals(name)).findFirst().orElseThrow(
                        () -> new BWException(BWErrorType.INVALID_TASK_NAME,
                                name));
    }

    private Condition buildCondition(DataModel dataModel,
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
            Product product = dataModel.getTargetOfPath(expression.getValue());

            return product.getDefCondition();
        // if (product instanceof AttributeBasic)
        // return DefAttributeCondition
        // .getDefAttribute((AttributeBasic) product);
        // else if (product instanceof AttributeGroup)
        // return DefAttributeCondition
        // .getDefAttribute((AttributeGroup) product);
        // assert(true);
        // return null;
        case ATT_VALUE:
            // TODO: remove cast
            // AttributeBasic att = (AttributeBasic) dataModel
            // .getTargetOfPath(expression.getValue());
            // return new AttributeBoolCondition(att);
            return dataModel.getTargetOfPath(expression.getValue())
                    .getDefCondition();
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

    private Expression buildExpression(DataModel dataModel,
            ExpressionDTO expression) {
        switch (Type.valueOf(expression.getType())) {
        case PLUS:
            return new BinaryExpression(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    BinaryOperator.PLUS);
        case MINUS:
            return new BinaryExpression(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    BinaryOperator.MINUS);
        case MUL:
            return new BinaryExpression(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    BinaryOperator.MUL);
        case DIV:
            return new BinaryExpression(
                    buildExpression(dataModel, expression.getLeftExpression()),
                    buildExpression(dataModel, expression.getRightExpression()),
                    BinaryOperator.DIV);
        case ATT_VALUE:
            // TODO: remove cast
            AttributeBasic attribute = (AttributeBasic) dataModel
                    .getTargetOfPath(expression.getValue());
            return new AttributeValueExpression(attribute);
        case INT:
            return new NumberLiteral(Integer.parseInt(expression.getValue()));
        case STRING:
            return new StringLiteral(expression.getValue());
        default:
            assert(false);
            return null;
        }
    }

}

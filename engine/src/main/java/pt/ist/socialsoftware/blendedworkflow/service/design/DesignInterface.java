package pt.ist.socialsoftware.blendedworkflow.service.design;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.ConditionModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel;
import pt.ist.socialsoftware.blendedworkflow.domain.DefAttributeCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefEntityCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefPathCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.DefProductCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Dependence;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.domain.MulCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.domain.RelationBW;
import pt.ist.socialsoftware.blendedworkflow.domain.Rule;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.AttributeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefAttributeConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefEntityConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DefPathConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.DependenceDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EntityDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GoalDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GraphDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.MulConditionDTO;
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

	public Set<Specification> getSpecs() {
		return getBlendedWorkflow().getSpecificationSet();
	}

	public Specification getSpecBySpecId(String specId) {
		Specification spec = getBlendedWorkflow().getSpecById(specId)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_SPECIFICATION_ID, specId));
		return spec;
	}

	@Atomic(mode = TxMode.WRITE)
	public Specification createSpecification(SpecDTO specDTO) {
		return getBlendedWorkflow().createSpecification(specDTO.getSpecId(), specDTO.getName());
	}

	@Atomic(mode = TxMode.WRITE)
	public void cleanDataModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		spec.getDataModel().clean();
	}

	public void checkDataModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		spec.getDataModel().check();
	}

	public Set<Entity> getEntities(String specId) {
		log.debug("getEntities specId:{}", specId);

		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getEntitySet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Entity createEntity(EntityDTO entDTO) {
		log.debug("createEntity specId:{}, name:{}, exists:{}", entDTO.getSpecId(), entDTO.getName(),
				entDTO.getExists());

		Specification spec = getSpecBySpecId(entDTO.getSpecId());

		return spec.getDataModel().createEntity(entDTO.getName(), entDTO.getExists());
	}

	public Set<Attribute> getAttributes(String specId) {
		log.debug("getAttributes specId:{}", specId);
		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getAttributeSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Attribute createAttribute(AttributeDTO attDTO) {
		log.debug("createAttribute entityExtId:{}", attDTO.getEntityExtId());
		Entity ent = getEntityByExtId(attDTO.getEntityExtId());

		Attribute attribute = ent.createAttribute(attDTO.getName(), AttributeType.parseAttributeType(attDTO.getType()),
				attDTO.isMandatory());

		return attribute;
	}

	public Product getProduct(String specId, String path) {
		return getSpecBySpecId(specId).getDataModel().getTargetOfPath(path);
	}

	@Atomic(mode = TxMode.WRITE)
	public RelationBW createRelation(RelationDTO relDTO) {
		log.debug("createRelation {}, entityOneName:{}, entityTwoName:{}", relDTO.getSpecId(), relDTO.getEntOneName(),
				relDTO.getEntTwoName());

		Specification spec = getSpecBySpecId(relDTO.getSpecId());

		Entity entityOne;
		if (relDTO.getEntOneExtId() != null) {
			entityOne = getEntityByExtId(relDTO.getEntOneExtId());
		} else {
			entityOne = getEntityByName(spec.getDataModel(), relDTO.getEntOneName());
		}

		Entity entityTwo;
		if (relDTO.getEntTwoExtId() != null) {
			entityTwo = getEntityByExtId(relDTO.getEntTwoExtId());
		} else {
			entityTwo = getEntityByName(spec.getDataModel(), relDTO.getEntTwoName());
		}

		if (entityOne.getDataModel() != entityTwo.getDataModel())
			throw new BWException(BWErrorType.INVALID_RELATION, entityOne.getDataModel().getSpecification().getSpecId()
					+ "!=" + entityTwo.getDataModel().getSpecification().getSpecId());

		RelationBW relation = entityOne.createRelation(relDTO.getName(), relDTO.getRolenameOne(),
				relDTO.getCardinalityOne(), entityTwo, relDTO.getRolenameTwo(), relDTO.getCardinalityTwo());

		return relation;
	}

	public Set<RelationBW> getRelations(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getRelationBWSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Dependence createDependence(DependenceDTO productDTO) {
		Product product = getProductByName(productDTO.getSpecId(), productDTO.getProduct());

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
		return dependence.checkPath();
	}

	@Atomic(mode = TxMode.WRITE)
	public void deleteDependence(String extId) {
		Dependence dependence = getDependenceByExtId(extId);
		dependence.delete();
	}

	@Atomic(mode = TxMode.WRITE)
	public Rule createRule(RuleDTO ruleDTO) {
		Specification spec = getSpecBySpecId(ruleDTO.getSpecId());
		Entity entity = spec.getDataModel().getEntity(ruleDTO.getEntityName())
				.orElseThrow(() -> new BWException(BWErrorType.NON_EXISTENT_ENTITY, ruleDTO.getEntityName()));

		Rule rule = entity.createRule(ruleDTO.getName(), ruleDTO.getExpression().buildCondition(spec));

		return rule;
	}

	public Set<Rule> getRules(String specId) {
		log.debug("getRules specId:{}", specId);

		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getRules();
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

		spec.getConditionModel().clean();
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
	public boolean generateGoalModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		if (spec.getDataModel().getEntitySet().size() == 0)
			throw new BWException(BWErrorType.NO_DATA_MODEL, specId);

		if (spec.getConditionModel().getEntityAchieveConditionSet().size() == 0)
			throw new BWException(BWErrorType.NO_CONDITION_MODEL, specId);

		spec.getGoalModel().clean();
		spec.getGoalModel().generateGoals();

		return true;
	}

	@Atomic(mode = TxMode.WRITE)
	public void cleanActivityModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		if (spec.getDataModel().getEntitySet().size() == 0)
			throw new BWException(BWErrorType.NO_DATA_MODEL, specId);

		if (spec.getConditionModel().getEntityAchieveConditionSet().size() == 0)
			throw new BWException(BWErrorType.NO_CONDITION_MODEL, specId);

		spec.getActivityModel().clean();
	}

	@Atomic(mode = TxMode.WRITE)
	public boolean generateActivityModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		if (spec.getDataModel().getEntitySet().size() == 0)
			throw new BWException(BWErrorType.NO_DATA_MODEL, specId);

		if (spec.getConditionModel().getEntityAchieveConditionSet().size() == 0)
			throw new BWException(BWErrorType.NO_CONDITION_MODEL, specId);

		spec.getActivityModel().clean();
		spec.getActivityModel().generateActivities();

		return true;
	}

	public Set<DefEntityCondition> getEntityAchieveConditionSet(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getEntityAchieveConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public DefEntityCondition createEntityAchieveCondition(DefEntityConditionDTO eacDTO) {
		log.debug("createEntityAchieveCondition Entity:{}, Value:{}", eacDTO.getEntityName(), eacDTO.isExists());
		Specification spec = getSpecBySpecId(eacDTO.getSpecId());

		Entity entity = getEntityByName(spec.getDataModel(), eacDTO.getEntityName());

		if (entity.getExists() != eacDTO.isExists()) {
			throw new BWException(BWErrorType.INVALID_ENTITY, eacDTO.getEntityName() + " exists=" + entity.getExists());
		}

		DefEntityCondition defCondition = DefEntityCondition.getDefEntity(entity);
		spec.getConditionModel().addEntityAchieveCondition(defCondition);

		return defCondition;
	}

	public Entity getEntityByName(String specId, String entityName) {
		Specification spec = getSpecBySpecId(specId);

		return getEntityByName(spec.getDataModel(), entityName);
	}

	public Attribute getAttributeByExtId(String extId) {
		return FenixFramework.getDomainObject(extId);
	}

	public Set<Dependence> getEntityDependenceConditionSet(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getEntityDependenceConditionSet();

	}

	@Atomic(mode = TxMode.WRITE)
	public Dependence createEntityDependenceCondition(DependenceDTO dependenceDTO) {
		log.debug("createEntityDependenceCondition entity:{}, Path:{}", dependenceDTO.getProduct(),
				dependenceDTO.getPath());

		Entity entity = getEntityByName(dependenceDTO.getSpecId(), dependenceDTO.getProduct());

		Dependence dependence = getDependence(entity, dependenceDTO.getPath());

		ConditionModel conditionModel = entity.getDataModel().getSpecification().getConditionModel();
		conditionModel.addEntityDependenceCondition(dependence);

		return dependence;
	}

	public Set<MulCondition> getEntityInvariantConditionSet(String specId) {
		log.debug("getEntityInvariantConditionSet specId:{}", specId);

		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getEntityInvariantConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public MulCondition createEntityInvariantCondition(MulConditionDTO miDTO) {
		log.debug("createEntityInvariantCondition Entity:{}, Cardinality:{}", miDTO.getRolePath(),
				miDTO.getCardinality());
		Specification spec = getSpecBySpecId(miDTO.getSpecId());

		MulCondition mulCondition = getMULCondition(spec, miDTO.getRolePath());

		if (!mulCondition.getTargetCardinality().equals(miDTO.getCardinality()))
			new BWException(BWErrorType.INVALID_CARDINALITY, miDTO.getCardinality());

		spec.getConditionModel().addEntityInvariantCondition(mulCondition);

		return mulCondition;
	}

	public Set<DefAttributeCondition> getAttributeAchieveConditionSet(String specId) {
		log.debug("getAttributeAchieveConditionSet specId:{}", specId);

		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getAttributeAchieveConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public DefAttributeCondition createAttributeAchieveCondition(DefAttributeConditionDTO aacDTO) {
		log.debug("createAttributeAchieveCondition path:{}, mandatory:{}", aacDTO.getPath(), aacDTO.isMandatory());

		Specification spec = getSpecBySpecId(aacDTO.getSpecId());

		DefAttributeCondition defAttributeCondition = DefAttributeCondition.getDefAttribute(spec, aacDTO.getPath());

		if (defAttributeCondition.getAttributeOfDef() != null
				&& defAttributeCondition.getAttributeOfDef().getIsMandatory() != aacDTO.isMandatory())
			throw new BWException(BWErrorType.INCONSISTENT_ATTRIBUTE_MANDATORY, aacDTO.getPath().toString());

		spec.getConditionModel().addAttributeAchieveCondition(defAttributeCondition);

		return defAttributeCondition;
	}

	public Set<Dependence> getAttributeDependenceConditionSet(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getAttributeDependenceConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Dependence createAttributeDependenceCondition(DependenceDTO dependenceDTO) {
		log.debug("createAttributeDependenceCondition product:{}, path:{}", dependenceDTO.getProduct(),
				dependenceDTO.getPath());
		Product product = getProductByName(dependenceDTO.getSpecId(), dependenceDTO.getProduct());

		Dependence dependence = getDependence(product, dependenceDTO.getPath());

		ConditionModel conditionModel = product.getEntity().getDataModel().getSpecification().getConditionModel();
		conditionModel.addAttributeDependenceCondition(dependence);

		return dependence;
	}

	public Set<Rule> getAttributeInvariantConditionSet(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getAttributeInvariantConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Rule createAttributeInvariant(RuleDTO ruleDTO) {
		Specification spec = getSpecBySpecId(ruleDTO.getSpecId());
		Entity entity = getEntityByName(spec.getDataModel(), ruleDTO.getEntityName());

		Rule rule = getRule(entity, ruleDTO.getName());

		spec.getConditionModel().addAttributeInvariantCondition(rule);

		return rule;
	}

	public Set<Goal> getGoals(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getGoalModel().getGoalSet();
	}

	public GraphDTO getGoalModelGraph(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getGoalModel().getGoalGraph();
	}

	@Atomic(mode = TxMode.WRITE)
	public Goal createGoal(GoalDTO goalDTO) {
		Specification spec = getSpecBySpecId(goalDTO.getSpecId());

		return new Goal(spec.getGoalModel(), goalDTO.getName());
	}

	@Atomic(mode = TxMode.WRITE)
	public void updateGoalName(String specId, String goalName, String newName) {
		Specification spec = getSpecBySpecId(specId);

		spec.getGoalModel().getGoal(goalName).setName(newName);
	}

	public Goal getGoalByName(String specId, String goalName) {
		Specification spec = getSpecBySpecId(specId);

		return getGoalByName(spec, goalName);
	}

	public Set<DefEntityCondition> getGoalSuccessEntitySet(String specId, String goalName) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);

		return goal.getSuccessConditionSet().stream().filter(DefEntityCondition.class::isInstance)
				.map(DefEntityCondition.class::cast).collect(Collectors.toSet());
	}

	@Atomic(mode = TxMode.WRITE)
	public DefEntityCondition associateEntityToGoalSuccess(String specId, String goalName, String path) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);

		Product product = spec.getDataModel().getTargetOfPath(path);
		if (product.getProductType() != ProductType.ENTITY)
			throw new BWException(BWErrorType.INVALID_PATH, path);

		DefEntityCondition defEntityCondition = ((Entity) product).getDefEntityCondition();
		goal.addSuccessCondition(defEntityCondition);

		return defEntityCondition;
	}

	public Set<DefAttributeCondition> getGoalSuccessAttributeSet(String specId, String goalName) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);

		return goal.getSuccessConditionSet().stream().filter(DefAttributeCondition.class::isInstance)
				.map(DefAttributeCondition.class::cast).collect(Collectors.toSet());
	}

	@Atomic(mode = TxMode.WRITE)
	public DefAttributeCondition associateAttributeToGoalSuccess(String specId, String goalName, String path) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);
		DefAttributeCondition defAttributeCondition = DefAttributeCondition.getDefAttribute(spec, path);

		goal.addSuccessCondition(defAttributeCondition);

		return defAttributeCondition;
	}

	public Set<DefPathCondition> getGoalActivationDefPathConditionSet(String specId, String goalName) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);

		return goal.getActivationConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public DefPathCondition associateDefPathConditionToGoalActivation(String specId, String goalName, String path) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);

		DefPathCondition defPathCondition = DefPathCondition.getDefPathCondition(spec, path);

		goal.addActivationCondition(defPathCondition);

		return defPathCondition;
	}

	public Set<MulCondition> getGoalMulInvSet(String specId, String goalName) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);

		return goal.getEntityInvariantConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public void associateMulToGoalInvariant(String specId, String goalName, String path, String cardinality) {
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
	public void associateRuleToGoalInvariant(RuleDTO ruleDTO, String goalName) {
		Specification spec = getSpecBySpecId(ruleDTO.getSpecId());
		Entity entity = getEntityByName(spec.getDataModel(), ruleDTO.getEntityName());

		Rule rule = getRule(entity, ruleDTO.getName());

		Goal goal = getGoalByName(spec, goalName);
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
	public Goal mergeGoals(String specId, String newGoalName, String goalNameOne, String goalNameTwo) {
		Specification spec = getSpecBySpecId(specId);
		Goal goalOne = getGoalByName(spec, goalNameOne);
		Goal goalTwo = getGoalByName(spec, goalNameTwo);

		return spec.getGoalModel().mergeGoals(newGoalName, goalOne, goalTwo);
	}

	@Atomic(mode = TxMode.WRITE)
	public Goal extractChildGoal(String specId, String newGoalName, String sourceGoalName,
			Set<DefPathConditionDTO> successConditionDTO) {
		Specification spec = getSpecBySpecId(specId);
		Goal parentGoal = getGoalByName(spec, sourceGoalName);

		Set<DefProductCondition> successConditions = getConditionSet(spec, successConditionDTO);

		return spec.getGoalModel().extractChild(parentGoal, newGoalName, successConditions);
	}

	@Atomic(mode = TxMode.WRITE)
	public Goal extractParentGoal(String specId, String newGoalName, String sourceGoalName,
			Set<DefPathConditionDTO> successConditionDTO) {
		Specification spec = getSpecBySpecId(specId);
		Goal childGoal = getGoalByName(spec, sourceGoalName);

		Set<DefProductCondition> successConditions = getConditionSet(spec, successConditionDTO);

		return spec.getGoalModel().extractParent(childGoal, newGoalName, successConditions);
	}

	@Atomic(mode = TxMode.WRITE)
	public Goal extractSiblingGoal(String specId, String newGoalName, String sourceGoalName,
			Set<DefPathConditionDTO> successConditionDTO) {
		Specification spec = getSpecBySpecId(specId);
		Goal sourceGoal = getGoalByName(spec, sourceGoalName);

		Set<DefProductCondition> successConditions = getConditionSet(spec, successConditionDTO);

		return spec.getGoalModel().extractSibling(sourceGoal, newGoalName, successConditions);
	}

	@Atomic(mode = TxMode.WRITE)
	public Activity createActivity(ActivityDTO activityDTO) {
		Specification spec = getSpecBySpecId(activityDTO.getSpecId());

		return spec.getActivityModel().createActivity(activityDTO.getName(), activityDTO.getDescription());
	}

	@Atomic(mode = TxMode.WRITE)
	public Activity addActivity(String specId, AddActivityReq request) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getActivityModel().addActivity(request.getActivityName(), request.getDescription(),
				getConditionSet(spec, request.getPostConditionSet()));
	}

	@Atomic(mode = TxMode.WRITE)
	public void updateActivityName(String specId, String activityName, String newName) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		activity.setName(newName);
	}

	public Set<Activity> getActivities(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getActivityModel().getActivitySet();
	}

	public Set<DefPathCondition> getActivityPreCondition(String specId, String activityName) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		return activity.getPreConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public DefPathCondition associateDefPathToActivityPre(String specId, String activityName, String path) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		spec.getDataModel().getTargetOfPath(path);

		DefPathCondition defPathCondition = DefPathCondition.getDefPathCondition(spec, path);

		activity.addPreCondition(defPathCondition);

		return defPathCondition;
	}

	public Set<DefProductCondition> getActivityPostCondition(String specId, String activityName) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		return activity.getPostConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public DefEntityCondition associateEntityToActivityPost(String specId, String activityName, String path) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		Product product = spec.getDataModel().getTargetOfPath(path);
		if (product.getProductType() != ProductType.ENTITY)
			throw new BWException(BWErrorType.INVALID_PATH, path);

		DefEntityCondition defEntityCondition = ((Entity) product).getDefEntityCondition();

		activity.addPostCondition(defEntityCondition);

		return defEntityCondition;
	}

	@Atomic(mode = TxMode.WRITE)
	public DefAttributeCondition associateAttributeToActivityPost(String specId, String activityName, String path) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);
		DefAttributeCondition defAttributeCondition = DefAttributeCondition.getDefAttribute(spec, path);

		activity.addPostCondition(defAttributeCondition);

		return defAttributeCondition;
	}

	public Set<MulCondition> getActivityMulConditions(String specId, String activityName) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		return activity.getMultiplicityInvariantSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public MulCondition associateMulToActivityPost(String specId, String activityName, String path,
			String cardinality) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		MulCondition mulCondition = getMULCondition(spec, path);

		activity.addMultiplicityInvariant(mulCondition);

		return mulCondition;
	}

	public Set<Rule> getActivityRuleConditions(String specId, String activityName) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		return activity.getRuleInvariantSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Rule associateRuleToActivityPost(RuleDTO ruleDTO, String activityName) {
		Specification spec = getSpecBySpecId(ruleDTO.getSpecId());
		Entity entity = getEntityByName(spec.getDataModel(), ruleDTO.getEntityName());

		Activity activity = getActivityByName(spec, activityName);

		Rule rule = getRule(entity, ruleDTO.getName());

		activity.addRuleInvariant(rule);

		return rule;
	}

	public void checkActivityModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		spec.getActivityModel().checkModel();
	}

	@Atomic(mode = TxMode.WRITE)
	public Activity mergeActivities(String specId, String newActivityName, String description, String activityNameOne,
			String activityNameTwo) {
		Specification spec = getSpecBySpecId(specId);
		Activity activityOne = getActivityByName(spec, activityNameOne);
		Activity activityTwo = getActivityByName(spec, activityNameTwo);

		return spec.getActivityModel().mergeActivities(newActivityName, description, activityOne, activityTwo);
	}

	@Atomic(mode = TxMode.WRITE)
	public Activity extractActivity(String specId, String newActivityName, String description,
			String sourceActivityName, Set<DefPathConditionDTO> successCondition) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, sourceActivityName);

		return spec.getActivityModel().extractActivity(activity, newActivityName, description,
				getConditionSet(spec, successCondition));
	}

	@Atomic(mode = TxMode.WRITE)
	public void addSequenceConditionToActivity(String specId, String activityName, String path) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		activity.addSequenceCondition(DefPathCondition.getDefPathCondition(spec, path));
	}

	@Atomic(mode = TxMode.WRITE)
	public void removeSequenceConditionToActivity(String specId, String activityName, String path) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		if (!activity.getSequenceConditionSet().stream().filter(d -> d.getPath().getValue().equals(path)).findFirst()
				.isPresent()) {
			throw new BWException(BWErrorType.UNKNOWN_SEQUENCE_CONDITION, path);
		}

		activity.getSequenceConditionSet().stream().filter(d -> d.getPath().getValue().equals(path))
				.forEach(d -> activity.removeSequenceCondition(d));

		spec.getActivityModel().applyRules();
	}

	public Set<DefPathCondition> getActivitySeqCondition(String specId, String activityName) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		return activity.getSequenceConditionSet();
	}

	public GraphDTO getActivityGraph(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getActivityModel().getActivityGraph();
	}

	public void printSpecificationModels(String specId) {
		Specification spec = getSpecBySpecId(specId);

		System.out.println("SPECIFICATION: " + spec.getName());

		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("Specification Data Model: " + spec.getName());
		System.out.println("-------------------------------------------------------");

		for (Entity entity : spec.getDataModel().getEntitySet()) {
			System.out.println("Entity " + entity.getName() + " Exists:" + entity.getExists());

			entity.getAttributeSet().stream().map(a -> "Attribute: " + a.getName()).forEach(System.out::println);

			entity.getRuleSet().stream().map(rule -> "Rule: " + rule.getName() + ":" + rule.getCondition().toString())
					.forEach(System.out::println);
		}

		spec.getDataModel().getDependenceSet().stream().map(dep -> dep.getProduct().getFullPath() + ":" + dep.getPath())
				.forEach(System.out::println);

		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("Specification Condition Model: " + spec.getName());
		System.out.println("-------------------------------------------------------");

		spec.getConditionModel().getEntityAchieveConditionSet().stream()
				.map(def -> "DEF(" + def.getEntity().getName() + ") " + def.getEntity().getExists())
				.forEach(System.out::println);

		spec.getConditionModel().getEntityDependenceConditionSet().stream()
				.map(dep -> dep.getProduct().getName() + "-" + dep.getPath()).forEach(System.out::println);

		spec.getConditionModel().getEntityInvariantConditionSet().stream().map(mul -> mul.getExpression())
				.forEach(System.out::println);

		spec.getConditionModel().getAttributeAchieveConditionSet().stream().map(def -> "DEF(" + def.getPath() + ")")
				.forEach(System.out::println);

		spec.getConditionModel().getAttributeDependenceConditionSet().stream()
				.map(dep -> dep.getProduct().getName() + "-" + dep.getPath().getValue()).forEach(System.out::println);

		spec.getConditionModel().getAttributeInvariantConditionSet().stream()
				.map(rule -> rule.getName() + "-" + rule.getCondition().getSubPath()).forEach(System.out::println);

		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("Specification Goal Model: " + spec.getName());
		System.out.println("-------------------------------------------------------");

		for (Goal goal : spec.getGoalModel().getGoalSet()) {
			System.out.println("Goal name:" + goal.getName());

			for (Goal sub : goal.getSubGoalSet()) {
				System.out.println("Sub goal name:" + sub.getName());
			}
			for (DefProductCondition act : goal.getActivationConditionSet()) {
				System.out.println("ACT(" + act.getSubPath() + ")");
			}
			for (DefProductCondition suc : goal.getSuccessConditionSet()) {
				System.out.println("SUC(" + suc.getSubPath() + ")");
			}
			for (MulCondition mul : goal.getEntityInvariantConditionSet()) {
				System.out.println(mul.getExpression());
			}
			for (Rule rule : goal.getAttributeInvariantConditionSet()) {
				System.out.println("RUL(" + rule.getName() + ")");
			}
		}

		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("Specification Activity Model: " + spec.getName());
		System.out.println("-------------------------------------------------------");

		for (Activity activity : spec.getActivityModel().getActivitySet()) {
			System.out.println("Activity name:" + activity.getName() + ", description:" + activity.getDescription());

			activity.getPreConditionSet().stream().forEach((c) -> System.out.println("PreCondition:" + c.getSubPath()));

			activity.getPostConditionSet().stream()
					.forEach((c) -> System.out.println("PostCondition:" + c.getSubPath()));

			activity.getMultiplicityInvariantSet().stream()
					.forEach((m) -> System.out.println("MultiplicityInvariant:" + m.getExpression()));

			activity.getRuleInvariantSet().stream()
					.forEach((r) -> System.out.println("MultiplicityInvariant:" + r.getName()));
		}

	}

	private Product getProductByName(String specId, String productPath) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getTargetOfPath(productPath);
	}

	private Entity getEntityByName(DataModel dataModel, String name) {
		return dataModel.getEntity(name).orElseThrow(() -> new BWException(BWErrorType.INVALID_ENTITY_NAME, name));
	}

	private Entity getEntityByExtId(String externalId) {
		if (externalId == null || externalId.equals(""))
			throw new BWException(BWErrorType.NOT_FOUND, externalId);
		Entity entity = FenixFramework.getDomainObject(externalId);
		if (entity == null)
			throw new BWException(BWErrorType.NOT_FOUND, externalId);
		return entity;
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
		return product.getDependenceSet().stream().filter(dep -> dep.getPath().getValue().equals(path)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.DEPENDENCE_NOT_EXISTS, path));

	}

	private Goal getGoalByName(Specification spec, String goalName) {
		return spec.getGoalModel().getGoalSet().stream().filter(g -> g.getName().equals(goalName)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_GOAL_NAME, goalName));
	}

	private Rule getRule(Entity entity, String ruleName) {
		return entity.getRuleSet().stream().filter(r -> r.getName().equals(ruleName)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_RULE_NAME, ruleName));
	}

	private MulCondition getMULCondition(Specification spec, String path) {
		String entityName = path.split("\\.")[0];
		String rolename = path.split("\\.")[1];

		RelationBW relation = spec.getDataModel().getRelationBWSet().stream()
				.filter(rel -> (rel.getEntityOne().getName().equals(entityName)
						&& rel.getRoleNameTwo().equals(rolename))
						|| (rel.getEntityTwo().getName().equals(entityName) && rel.getRoleNameOne().equals(rolename)))
				.findFirst().orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH, path));

		return MulCondition.getMulCondition(relation, rolename);
	}

	private Set<DefProductCondition> getConditionSet(Specification spec, Set<DefPathConditionDTO> defConditionSetDTO) {
		Set<DefProductCondition> conditions = new HashSet<DefProductCondition>();
		for (DefPathConditionDTO defPathCondition : defConditionSetDTO) {
			if (spec.getDataModel().getTargetOfPath(defPathCondition.getPath()).getProductType()
					.equals(ProductType.ENTITY)) {
				conditions.add(DefEntityCondition
						.getDefEntity(getEntityByName(spec.getDataModel(), defPathCondition.getPath())));
			} else if (spec.getDataModel().getTargetOfPath(defPathCondition.getPath()).getProductType()
					.equals(ProductType.ATTRIBUTE)) {
				conditions.add(DefAttributeCondition.getDefAttribute(spec, defPathCondition.getPath()));
			} else {
				assert false : "error in path";
			}
		}

		return conditions;
	}

	private Activity getActivityByName(Specification spec, String name) {
		return spec.getActivityModel().getActivitySet().stream().filter(t -> t.getName().equals(name)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ACTIVITY_NAME, name));
	}

}

package pt.ist.socialsoftware.blendedworkflow.core.service.design;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.core.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product.ProductType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.*;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.AddActivityDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.ExtractGoalDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.req.MergeOperationDto;
import pt.ist.socialsoftware.blendedworkflow.core.xml.SpecXmlExport;

public class DesignInterface {
	private static Logger logger = LoggerFactory.getLogger(DesignInterface.class);

	private static DesignInterface instance;

	public static DesignInterface getInstance() {
		if (instance == null) {
			instance = new DesignInterface();
		}
		return instance;
	}

	protected DesignInterface() {
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
	public Specification createSpecification(SpecDto specDTO) {
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

	public DataModelDto getDataModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return new DataModelDto(spec.getDataModel());
	}

	public Set<Entity> getEntities(String specId) {
		logger.debug("getEntities specId:{}", specId);

		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getEntitySet();
	}

	public Entity getMandatoryEntity(String specId) {
		return getSpecBySpecId(specId).getDataModel().getEntitySet().stream().filter(e -> e.getMandatory()).findFirst()
				.get();
	}

	@Atomic(mode = TxMode.WRITE)
	public Entity createEntity(EntityDto entDTO) {
		logger.debug("createEntity specId:{}, name:{}, exists:{}, mandatory:{}", entDTO.getSpecId(), entDTO.getName(),
				entDTO.getExists(), entDTO.isMandatory());

		Specification spec = getSpecBySpecId(entDTO.getSpecId());

		return spec.getDataModel().createEntity(entDTO.getName(), entDTO.getExists(), entDTO.isMandatory());
	}

	public Set<Attribute> getAttributes(String specId) {
		logger.debug("getAttributes specId:{}", specId);
		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getAttributeSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Attribute createAttribute(AttributeDto attDTO) {
		logger.debug("createAttribute entityExtId:{}", attDTO.getEntityExtId());
		Entity ent = getEntityByExtId(attDTO.getEntityExtId());

		Attribute attribute = ent.createAttribute(attDTO.getName(), AttributeType.parseAttributeType(attDTO.getType()),
				attDTO.isMandatory());

		return attribute;
	}

	public Product getProduct(String specId, String path) {
		return getSpecBySpecId(specId).getDataModel().getTargetOfPath(path);
	}

	@Atomic(mode = TxMode.WRITE)
	public RelationBW createRelation(RelationDto relDTO) {
		logger.debug("createRelation specId:{}, entityOneName:{}, entOneExtId:{}, entityTwoName:{}, entTwoExtId:{}",
				relDTO.getSpecId(), relDTO.getEntOneName(), relDTO.getEntOneExtId(), relDTO.getEntTwoName(),
				relDTO.getEntTwoExtId());

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

		if (entityOne.getDataModel() != entityTwo.getDataModel()) {
			throw new BWException(BWErrorType.INVALID_RELATION, entityOne.getDataModel().getSpecification().getSpecId()
					+ "!=" + entityTwo.getDataModel().getSpecification().getSpecId());
		}

		RelationBW relation = entityOne.createRelation(relDTO.getName(), relDTO.getRolenameOne(),
				relDTO.getCardinalityOne(), entityTwo, relDTO.getRolenameTwo(), relDTO.getCardinalityTwo());

		return relation;
	}

	public Set<RelationBW> getRelations(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getRelationBWSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Dependence createDependence(DependenceDto productDTO) {
		Product product = getProductByName(productDTO.getSpecId(), productDTO.getProduct());

		return product.createDependence(productDTO.getPath());
	}

	public Set<Dependence> getDependencies(String specId) {
		Specification spec = getSpecBySpecId(specId);

		Set<Dependence> deps = new HashSet<>();

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
	public Rule createRule(RuleDto ruleDTO) {
		Specification spec = getSpecBySpecId(ruleDTO.getSpecId());
		Entity entity = spec.getDataModel().getEntityByName(ruleDTO.getEntityName())
				.orElseThrow(() -> new BWException(BWErrorType.NON_EXISTENT_ENTITY, ruleDTO.getEntityName()));

		Rule rule = entity.createRule(ruleDTO.getName(), ruleDTO.getExpression().buildCondition(spec));

		return rule;
	}

	public Set<Rule> getRules(String specId) {
		logger.debug("getRules specId:{}", specId);

		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getRules();
	}

	@Atomic(mode = TxMode.WRITE)
	public void cleanConditionModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		if (spec.getDataModel().getEntitySet().size() == 0) {
			throw new BWException(BWErrorType.NO_DATA_MODEL, specId);
		}

		spec.getConditionModel().clean();
	}

	@Atomic(mode = TxMode.WRITE)
	public boolean generateConditionModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		if (spec.getDataModel().getEntitySet().size() == 0) {
			throw new BWException(BWErrorType.NO_DATA_MODEL, specId);
		}

		spec.getConditionModel().clean();
		spec.getConditionModel().generateConditions();

		return true;
	}

	@Atomic(mode = TxMode.WRITE)
	public void cleanGoalModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		if (spec.getDataModel().getEntitySet().size() == 0) {
			throw new BWException(BWErrorType.NO_DATA_MODEL, specId);
		}

		if (spec.getConditionModel().getEntityAchieveConditionSet().size() == 0) {
			throw new BWException(BWErrorType.NO_CONDITION_MODEL, specId);
		}

		spec.getGoalModel().clean();
	}

	@Atomic(mode = TxMode.WRITE)
	public boolean generateGoalModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		if (spec.getDataModel().getEntitySet().size() == 0) {
			throw new BWException(BWErrorType.NO_DATA_MODEL, specId);
		}

		if (spec.getConditionModel().getEntityAchieveConditionSet().size() == 0) {
			throw new BWException(BWErrorType.NO_CONDITION_MODEL, specId);
		}

		spec.getGoalModel().clean();
		spec.getGoalModel().generateGoals();

		return true;
	}

	@Atomic(mode = TxMode.WRITE)
	public void updateView(String specId, GoalModelDto goalModelDto) {
		GoalModel goalModel = getSpecBySpecId(specId).getGoalModel();

		goalModelDto.getGoals().stream().forEach(goalDto -> {
			Goal goal = goalModel.getGoal(goalDto.getName());
			String x = goalDto.getPosition().getX();
			String y = goalDto.getPosition().getY();
			if (goal.getView() == null) {
				Position position = new Position(x, y);
				new GoalView(goal, position);
			} else {
				goal.getView().getPosition().setX(x);
				goal.getView().getPosition().setY(y);
			}
		});
	}

	@Atomic(mode = TxMode.WRITE)
	public void cleanActivityModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		if (spec.getDataModel().getEntitySet().size() == 0) {
			throw new BWException(BWErrorType.NO_DATA_MODEL, specId);
		}

		if (spec.getConditionModel().getEntityAchieveConditionSet().size() == 0) {
			throw new BWException(BWErrorType.NO_CONDITION_MODEL, specId);
		}

		spec.getActivityModel().clean();
	}

	@Atomic(mode = TxMode.WRITE)
	public boolean generateActivityModel(String specId) {
		Specification spec = getSpecBySpecId(specId);

		if (spec.getDataModel().getEntitySet().size() == 0) {
			throw new BWException(BWErrorType.NO_DATA_MODEL, specId);
		}

		if (spec.getConditionModel().getEntityAchieveConditionSet().size() == 0) {
			throw new BWException(BWErrorType.NO_CONDITION_MODEL, specId);
		}

		spec.getActivityModel().clean();
		spec.getActivityModel().generateActivities();

		return true;
	}

	public Set<DefEntityCondition> getEntityAchieveConditionSet(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getEntityAchieveConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public DefEntityCondition createEntityAchieveCondition(DefEntityConditionDto eacDTO) {
		logger.debug("createEntityAchieveCondition Entity:{}, Value:{}", eacDTO.getEntityName(), eacDTO.isExists());
		Specification spec = getSpecBySpecId(eacDTO.getSpecId());

		Entity entity = getEntityByName(spec.getDataModel(), eacDTO.getEntityName());

		if (entity.getExists() != eacDTO.isExists()) {
			throw new BWException(BWErrorType.INVALID_ENTITY, eacDTO.getEntityName() + " exists=" + entity.getExists());
		}

		DefEntityCondition defCondition = DefEntityCondition.getDefEntityCondition(entity);
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
	public Dependence createEntityDependenceCondition(DependenceDto dependenceDTO) {
		logger.debug("createEntityDependenceCondition entity:{}, Path:{}", dependenceDTO.getProduct(),
				dependenceDTO.getPath());

		Entity entity = getEntityByName(dependenceDTO.getSpecId(), dependenceDTO.getProduct());

		Dependence dependence = getDependence(entity, dependenceDTO.getPath());

		ConditionModel conditionModel = entity.getDataModel().getSpecification().getConditionModel();
		conditionModel.addEntityDependenceCondition(dependence);

		return dependence;
	}

	public Set<MulCondition> getEntityInvariantConditionSet(String specId) {
		logger.debug("getEntityInvariantConditionSet specId:{}", specId);

		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getEntityInvariantConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public MulCondition createEntityInvariantCondition(String specId, MulConditionDto miDTO) {
		logger.debug("createEntityInvariantCondition Entity:{}, Cardinality:{}", miDTO.getRolePath(),
				miDTO.getCardinality());
		Specification spec = getSpecBySpecId(specId);

		MulCondition mulCondition = getMulCondition(spec, miDTO.getRolePath());

		if (!mulCondition.getCardinality().getExp().equals(miDTO.getCardinality())) {
			throw new BWException(BWErrorType.INVALID_CARDINALITY, miDTO.getCardinality());
		}

		spec.getConditionModel().addEntityInvariantCondition(mulCondition);

		return mulCondition;
	}

	public Set<DefAttributeCondition> getAttributeAchieveConditionSet(String specId) {
		logger.debug("getAttributeAchieveConditionSet specId:{}", specId);

		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getAttributeAchieveConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public DefAttributeCondition createAttributeAchieveCondition(DefAttributeConditionDto aacDTO) {
		logger.debug("createAttributeAchieveCondition path:{}, mandatory:{}", aacDTO.getPath(), aacDTO.isMandatory());

		Specification spec = getSpecBySpecId(aacDTO.getSpecId());

		DefAttributeCondition defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(spec,
				aacDTO.getPath());

		if (defAttributeCondition.getAttributeOfDef() != null
				&& defAttributeCondition.getAttributeOfDef().getIsMandatory() != aacDTO.isMandatory()) {
			throw new BWException(BWErrorType.INCONSISTENT_ATTRIBUTE_MANDATORY, aacDTO.getPath().toString());
		}

		spec.getConditionModel().addAttributeAchieveCondition(defAttributeCondition);

		return defAttributeCondition;
	}

	public Set<Dependence> getAttributeDependenceConditionSet(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getConditionModel().getAttributeDependenceConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Dependence createAttributeDependenceCondition(DependenceDto dependenceDTO) {
		logger.debug("createAttributeDependenceCondition product:{}, path:{}", dependenceDTO.getProduct(),
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
	public Rule createAttributeInvariant(RuleDto ruleDTO) {
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

	public Set<ProductGoal> getProductGoals(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getGoalModel().getProductGoalSet();
	}

	public Set<AssociationGoal> getAssociationGoals(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getGoalModel().getAssociationGoalSet();
	}

	public GraphDto getGoalModelGraph(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getGoalModel().getGoalGraph();
	}

	public GraphVisDto getGoalModelGraphVis(String specId) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getGoalModel().getGoalGraphVis();
	}

	@Atomic(mode = TxMode.WRITE)
	public ProductGoal createProductGoal(GoalDto goalDTO) {

		Specification spec = getSpecBySpecId(goalDTO.getSpecId());

		return new ProductGoal(spec.getGoalModel(), goalDTO.getName(), new HashSet<>());
	}

	@Atomic(mode = TxMode.WRITE)
	public AssociationGoal createAssociationGoal(GoalDto goalDTO) {

		Specification spec = getSpecBySpecId(goalDTO.getSpecId());

		return new AssociationGoal(spec.getGoalModel(), goalDTO.getName(), new HashSet<>());
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
		if (product.getProductType() != ProductType.ENTITY) {
			throw new BWException(BWErrorType.INVALID_PATH, path);
		}

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
		DefAttributeCondition defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(spec, path);

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

	public Set<RelationBW> getGoalRelations(String specId, String goalName) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);

		logger.debug("getGoalRelations: {}", goal.getEntityInvariantConditionSet().stream()
				.map(m -> m.getRelationBW().getName()).collect(Collectors.joining()));

		return goal.getEntityInvariantConditionSet().stream().map(m -> m.getRelationBW()).collect(Collectors.toSet());
	}

	@Atomic(mode = TxMode.WRITE)
	public void associateMulToGoalInvariant(String specId, String goalName, String path, String cardinality) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);

		MulCondition mulCondition = getMulCondition(spec, path);

		goal.addEntityInvariantCondition(mulCondition);
	}

	public Set<Rule> getGoalRuleInvSet(String specId, String goalName) {
		Specification spec = getSpecBySpecId(specId);
		Goal goal = getGoalByName(spec, goalName);

		return goal.getAttributeInvariantConditionSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public void associateRuleToGoalInvariant(RuleDto ruleDTO, String goalName) {
		Specification spec = getSpecBySpecId(ruleDTO.getSpecId());
		Entity entity = getEntityByName(spec.getDataModel(), ruleDTO.getEntityName());

		Rule rule = getRule(entity, ruleDTO.getName());

		Goal goal = getGoalByName(spec, goalName);
		goal.addAttributeInvariantCondition(rule);
	}

	@Atomic(mode = TxMode.WRITE)
	public Goal mergeGoals(MergeOperationDto mergeOperationDto) {
		Specification spec = getSpecBySpecId(mergeOperationDto.getSpecId());
		Goal goalOne = getGoalByName(spec, mergeOperationDto.getNameOne());
		Goal goalTwo = getGoalByName(spec, mergeOperationDto.getNameTwo());

		return spec.getGoalModel().mergeGoals(mergeOperationDto.getNewName(), goalOne, goalTwo);
	}

	@Atomic(mode = TxMode.WRITE)
	public Goal extractGoal(String specId, ExtractGoalDto extractGoalDto) {
		Specification spec = getSpecBySpecId(specId);

		Goal sourceGoal = getGoalByName(spec, extractGoalDto.getSourceGoalName());

		if (extractGoalDto.getSuccessConditions() != null) {
			Set<DefProductCondition> successConditions = getConditionSet(spec, extractGoalDto.getSuccessConditions());
			return spec.getGoalModel().extractProductGoal((ProductGoal) sourceGoal, extractGoalDto.getNewGoalName(),
					successConditions);
		} else {
			Set<MulCondition> mulCondition = getRelationSet(spec, extractGoalDto.getRelations()).stream()
					.flatMap(r -> r.getMulConditionSet().stream()).collect(Collectors.toSet());
			return spec.getGoalModel().extractAssociationGoal((AssociationGoal) sourceGoal,
					extractGoalDto.getNewGoalName(), mulCondition);
		}
	}

	@Atomic(mode = TxMode.WRITE)
	public Activity createActivity(ActivityDto activityDTO) {
		Specification spec = getSpecBySpecId(activityDTO.getSpecId());

		return spec.getActivityModel().createActivity(activityDTO.getName(), activityDTO.getDescription());
	}

	@Atomic(mode = TxMode.WRITE)
	public Activity addActivity(String specId, AddActivityDto request) {
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
		if (product.getProductType() != ProductType.ENTITY) {
			throw new BWException(BWErrorType.INVALID_PATH, path);
		}

		DefEntityCondition defEntityCondition = ((Entity) product).getDefEntityCondition();

		activity.addPostCondition(defEntityCondition);

		return defEntityCondition;
	}

	@Atomic(mode = TxMode.WRITE)
	public DefAttributeCondition associateAttributeToActivityPost(String specId, String activityName, String path) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);
		DefAttributeCondition defAttributeCondition = DefAttributeCondition.getDefAttributeCondition(spec, path);

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

		MulCondition mulCondition = getMulCondition(spec, path);

		activity.addMultiplicityInvariant(mulCondition);

		return mulCondition;
	}

	public Set<Rule> getActivityRuleConditions(String specId, String activityName) {
		Specification spec = getSpecBySpecId(specId);
		Activity activity = getActivityByName(spec, activityName);

		return activity.getRuleInvariantSet();
	}

	@Atomic(mode = TxMode.WRITE)
	public Rule associateRuleToActivityPost(RuleDto ruleDTO, String activityName) {
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
	public Activity mergeActivities(MergeOperationDto mergeOperationDto) {
		Specification spec = getSpecBySpecId(mergeOperationDto.getSpecId());
		Activity activityOne = getActivityByName(spec, mergeOperationDto.getNameOne());
		Activity activityTwo = getActivityByName(spec, mergeOperationDto.getNameTwo());

		return spec.getActivityModel().mergeActivities(mergeOperationDto.getNewName(),
				"Merge of " + mergeOperationDto.getNameOne() + " and " + mergeOperationDto.getNameTwo(), activityOne,
				activityTwo);
	}

	@Atomic(mode = TxMode.WRITE)
	public Activity extractActivity(String specId, String newActivityName, String description,
			String sourceActivityName, Set<DefPathConditionDto> successCondition) {
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

	public GraphDto getActivityGraph(String specId) {
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

	public String export(String specId) {
		Specification spec = getSpecBySpecId(specId);

		SpecXmlExport exporter = new SpecXmlExport();

		return exporter.export(spec);
		// logger.debug(exporter.export(spec));
	}

	private Product getProductByName(String specId, String productPath) {
		Specification spec = getSpecBySpecId(specId);

		return spec.getDataModel().getTargetOfPath(productPath);
	}

	private Entity getEntityByName(DataModel dataModel, String name) {
		return dataModel.getEntityByName(name)
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ENTITY_NAME, name));
	}

	private Entity getEntityByExtId(String externalId) {
		if (externalId == null || externalId.equals("")) {
			throw new BWException(BWErrorType.NOT_FOUND, externalId);
		}
		Entity entity = FenixFramework.getDomainObject(externalId);
		if (entity == null) {
			throw new BWException(BWErrorType.NOT_FOUND, externalId);
		}
		return entity;
	}

	private Dependence getDependenceByExtId(String externalId) {
		if (externalId == null || externalId.equals("")) {
			throw new BWException(BWErrorType.NOT_FOUND, externalId);
		}
		Dependence dependence = FenixFramework.getDomainObject(externalId);
		if (dependence == null) {
			throw new BWException(BWErrorType.NOT_FOUND, externalId);
		}
		return dependence;
	}

	private Dependence getDependence(Product product, String path) {
		return product.getDependenceSet().stream().filter(dep -> dep.getPath().getValue().equals(path)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.DEPENDENCE_NOT_EXISTS, path));

	}

	protected Goal getGoalByName(Specification spec, String goalName) {
		return spec.getGoalModel().getGoalSet().stream().filter(g -> g.getName().equals(goalName)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_GOAL_NAME, goalName));
	}

	private Rule getRule(Entity entity, String ruleName) {
		return entity.getRuleSet().stream().filter(r -> r.getName().equals(ruleName)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_RULE_NAME, ruleName));
	}

	private MulCondition getMulCondition(Specification spec, String path) {
		String entityName = path.split("\\.")[0];
		String rolename = path.split("\\.")[1];

		RelationBW relation = spec.getDataModel().getRelationBWSet().stream()
				.filter(rel -> rel.getEntityOne().getName().equals(entityName) && rel.getRolenameTwo().equals(rolename)
						|| rel.getEntityTwo().getName().equals(entityName) && rel.getRolenameOne().equals(rolename))
				.findFirst().orElseThrow(() -> new BWException(BWErrorType.INVALID_PATH, path));

		return MulCondition.getMulCondition(relation, rolename);
	}

	private Set<DefProductCondition> getConditionSet(Specification spec, Set<DefPathConditionDto> defConditionSetDTO) {
		Set<DefProductCondition> conditions = new HashSet<>();
		for (DefPathConditionDto defPathCondition : defConditionSetDTO) {
			if (spec.getDataModel().getTargetOfPath(defPathCondition.getPath()).getProductType()
					.equals(ProductType.ENTITY)) {
				conditions.add(DefEntityCondition
						.getDefEntityCondition(getEntityByName(spec.getDataModel(), defPathCondition.getPath())));
			} else if (spec.getDataModel().getTargetOfPath(defPathCondition.getPath()).getProductType()
					.equals(ProductType.ATTRIBUTE)) {
				conditions.add(DefAttributeCondition.getDefAttributeCondition(spec, defPathCondition.getPath()));
			} else {
				assert false : "error in path";
			}
		}

		return conditions;
	}

	private Set<RelationBW> getRelationSet(Specification spec, Set<RelationDto> relationDtoSet) {
		Set<RelationBW> relations = new HashSet<>();
		for (RelationDto relation : relationDtoSet) {
			relations.add(spec.getDataModel().getRelation(relation.getName()));
		}
		return relations;
	}

	protected Activity getActivityByName(Specification spec, String name) {
		return spec.getActivityModel().getActivitySet().stream().filter(t -> t.getName().equals(name)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.INVALID_ACTIVITY_NAME, name));
	}

}

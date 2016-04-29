package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityDTO;

public class Activity extends Activity_Base {
	private static Logger logger = LoggerFactory.getLogger(Activity.class);

	@Override
	public void setName(String name) {
		checkUniqueTaskName(name);
		super.setName(name);
	}

	@Override
	public void addSequenceCondition(DefPathCondition sequenceCondition) {
		checkSequenceConditon(sequenceCondition);

		super.addSequenceCondition(sequenceCondition);

		checkCycles(getActivityModel().getActivitySequences());

		getActivityModel().applyRules();
	}

	public Activity(ActivityModel activityModel, String name, String description) {
		setActivityModel(activityModel);
		setName(name);
		setDescription(description);
	}

	public Activity(ActivityModel activityModel, String name, String description, Set<DefPathCondition> preConditions,
			Set<DefProductCondition> postCondition, String previous, String joinCode, String splitCode)
			throws BWException {
		setActivityModel(activityModel);
		setName(name);
		getPreConditionSet().addAll(preConditions);
		getPostConditionSet().addAll(postCondition);
		setDescription(description);
		setPrevious(previous);
		setJoinCode(joinCode);
		setSplitCode(splitCode);
	}

	private void checkSequenceConditon(DefPathCondition sequenceCondition) {
		if (!getPostProducts().stream().map(p -> p.getEntity())
				.anyMatch(e -> e == sequenceCondition.getPath().getSource())) {
			throw new BWException(BWErrorType.SEQUENCE_CONDITION_INVALID, sequenceCondition.getPath().getValue());
		}
	}

	private void checkUniqueTaskName(String name) throws BWException {
		if (getActivityModel().getActivitySet().stream().filter(t -> t != this && t.getName().equals(name)).findFirst()
				.isPresent())
			throw new BWException(BWErrorType.INVALID_ACTIVITY_NAME, name);
	}

	public void cloneTask(OldTaskModelInstance activityModelInstance) throws BWException {
		Set<DefPathCondition> newPreConditions = null;
		Set<DefPathCondition> preConditions = getPreConditionSet();
		Set<DefProductCondition> newPostCondition = null;
		Set<DefProductCondition> postCondition = getPostConditionSet();
		if (!preConditions.isEmpty() && !getPostConditionSet().isEmpty()) {
			newPreConditions = preConditions.stream()
					.map((cond) -> (DefPathCondition) cond.cloneCondition(activityModelInstance))
					.collect(Collectors.toSet());
			newPostCondition = postCondition.stream()
					.map((cond) -> (DefProductCondition) cond.cloneCondition(activityModelInstance))
					.collect(Collectors.toSet());
		}
		Activity newTask = new Activity(activityModelInstance, getName(), getDescription(), newPreConditions,
				newPostCondition, getPrevious(), getJoinCode(), getSplitCode());
		newTask.setUser(getUser());
		newTask.setRole(getRole());
	}

	/**
	 * Get the Task condition data to use in the use interface.
	 * 
	 * @return a string with the condition data entities.
	 */
	public String getConstraintData(Boolean isPreConstraint) {
		Set<Entity> entities;
		Set<Attribute> attributes;
		String dataString = "";

		// Get Condition Data
		if (isPreConstraint) {
			entities = getPreConditionSet().stream().flatMap((cond) -> cond.getEntities().stream())
					.collect(Collectors.toSet());
			attributes = getPreConditionSet().stream().flatMap((cond) -> cond.getAttributeSet().stream())
					.collect(Collectors.toSet());
		} else {
			entities = getPostConditionSet().stream().flatMap((cond) -> cond.getEntities().stream())
					.collect(Collectors.toSet());
			attributes = getPostConditionSet().stream().flatMap((cond) -> cond.getAttributeSet().stream())
					.collect(Collectors.toSet());
		}

		// Add Attribute entities
		for (Attribute attribute : attributes) {
			entities.add(attribute.getEntity());
		}

		// Create String
		int count = 0;
		for (Entity entity : entities) {
			if (entities.size() == 1) {
				dataString += entity.getName();
			} else if (count < entities.size() - 1) {
				dataString += entity.getName() + ", ";
				;
			} else {
				dataString += entity.getName();
			}
			count++;
		}
		return dataString;
	}

	public void delete() {
		setActivityModel(null);

		getWorkItemSet().stream().forEach(wi -> wi.delete());

		getPreConditionSet().forEach(c -> removePreCondition(c));
		getPostConditionSet().forEach(c -> removePostCondition(c));
		getMultiplicityInvariantSet().forEach(m -> removeMultiplicityInvariant(m));
		getRuleInvariantSet().forEach(r -> removeRuleInvariant(r));
		getSequenceConditionSet().forEach(d -> d.delete());

		deleteDomainObject();
	}

	public ActivityDTO getDTO() {
		return new ActivityDTO(getActivityModel().getSpecification().getSpecId(), getName(), getDescription());
	}

	public void checkConsistency() {
		checkPostConditionContainsAtLeastOneDef();
		checkEntityOfDefAttributeIsDefined();
		checkDependenceConstraint();
		checkMultiplicityConstraint();
		checkRuleConstraint();
		checkCycles(getActivityModel().getActivitySequences());
	}

	public void checkCycles(Map<Activity, Set<Activity>> activityDependencies) {
		goThroughAcyclicPath(this, activityDependencies, new HashSet<Activity>());
	}

	private void goThroughAcyclicPath(Activity activity, Map<Activity, Set<Activity>> activityDependencies,
			Set<Activity> visitedTasks) {
		Set<Activity> nextTasks = activityDependencies.get(this);
		visitedTasks.add(this);

		if (nextTasks.contains(activity)) {
			throw new BWException(BWErrorType.DEPENDENCE_CIRCULARITY, getName());
		} else {
			for (Activity nextTask : nextTasks) {
				if (!visitedTasks.contains(nextTask))
					nextTask.goThroughAcyclicPath(activity, activityDependencies, visitedTasks);
			}
		}
	}

	private void checkRuleConstraint() {
		// at least one of the attributes in the rule is defined in the activity
		Set<Attribute> postAttributes = getPostConditionSet().stream().map(d -> d.getTargetOfPath())
				.filter(Attribute.class::isInstance).map(Attribute.class::cast).collect(Collectors.toSet());
		for (Rule rule : getRuleInvariantSet())
			if (!rule.getAttributeSet().stream().anyMatch(a -> postAttributes.contains(a)))
				throw new BWException(BWErrorType.INCONSISTENT_RULE_CONDITION, getName() + ":" + rule.getName());
	}

	private void checkMultiplicityConstraint() {
		Set<Entity> allEntities = getPreEntities();
		Set<Entity> postEntities = getPostEntities();
		allEntities.addAll(postEntities);

		Optional<MulCondition> oMul = getMultiplicityInvariantSet().stream()
				.filter(m -> !allEntities.contains(m.getRelationBW().getEntityOne())
						|| !allEntities.contains(m.getRelationBW().getEntityTwo()))
				.findFirst();

		if (oMul.isPresent())
			throw new BWException(BWErrorType.INCONSISTENT_MUL_CONDITION,
					getName() + ":" + oMul.get().getSourceEntity().getName() + "." + oMul.get().getTargetRolename());

		// at least one entity definition is done here
		oMul = getMultiplicityInvariantSet().stream()
				.filter(m -> !(postEntities.contains(m.getRelationBW().getEntityOne())
						|| postEntities.contains(m.getRelationBW().getEntityTwo())))
				.findFirst();

		if (oMul.isPresent())
			throw new BWException(BWErrorType.INCONSISTENT_MUL_CONDITION,
					getName() + ":" + oMul.get().getSourceEntity().getName() + "." + oMul.get().getTargetRolename());

	}

	private void checkDependenceConstraint() {
		Set<Product> preProducts = getPreProducts();
		Set<Product> postProducts = getPostProducts();

		Optional<Dependence> oDep = getActivityModel().getSpecification().getDataModel().getDependenceSet().stream()
				.filter(d -> postProducts.contains(d.getProduct()) && !preProducts.contains(d.getTarget())
						&& !postProducts.contains(d.getTarget()))
				.findFirst();

		if (oDep.isPresent())
			throw new BWException(BWErrorType.MISSING_DEF_IN_PRE, getName() + ":" + oDep.get().getProduct().getName());
	}

	private void checkEntityOfDefAttributeIsDefined() {
		Set<Entity> entities = Stream.concat(getPreEntities().stream(), getPostEntities().stream())
				.collect(Collectors.toSet());

		Optional<DefAttributeCondition> oDef = ConditionModel.getDefAttributeConditions(getPostConditionSet()).stream()
				.filter(d -> !entities.contains(d.getAttributeOfDef().getEntity())).findFirst();

		if (oDef.isPresent())
			throw new BWException(BWErrorType.MISSING_DEF_IN_PRE,
					getName() + ":" + oDef.get().getAttributeOfDef().getEntity().getName() + "."
							+ oDef.get().getAttributeOfDef().getName());
	}

	private void checkPostConditionContainsAtLeastOneDef() {
		if (getPostProducts().size() > 0)
			return;

		throw new BWException(BWErrorType.NO_DEF_CONDITION_IN_POST, getName());
	}

	public void removePreCondition(String path) {
		for (DefPathCondition defPathCondition : getPreConditionSet()) {
			if (defPathCondition.getPath().getValue().equals(path)) {
				removePreCondition(defPathCondition);
			}
		}
	}

	public Set<Attribute> getPostAttributes() {
		return getPostConditionSet().stream().map(d -> d.getPath().getTarget()).filter(Attribute.class::isInstance)
				.map(Attribute.class::cast).collect(Collectors.toSet());
	}

	public Set<Entity> getPostEntities() {
		return getPostConditionSet().stream().map(d -> d.getPath().getTarget()).filter(Entity.class::isInstance)
				.map(Entity.class::cast).collect(Collectors.toSet());
	}

	public Set<Entity> getPreEntities() {
		return getPreConditionSet().stream().map(d -> d.getPath().getTarget()).filter(Entity.class::isInstance)
				.map(Entity.class::cast).collect(Collectors.toSet());
	}

	public Set<Product> getPostProducts() {
		return getPostConditionSet().stream().map(d -> d.getPath().getTarget()).collect(Collectors.toSet());
	}

	public Set<Product> getPreProducts() {
		return getPreConditionSet().stream().map(d -> d.getPath().getTarget()).collect(Collectors.toSet());
	}

	public Set<Entity> getCreationDependentAdjacentEntities() {
		return getPreConditionSet().stream().filter(d -> getPostEntities().contains(d.getPath().getSource()))
				.map(d -> d.getPath().getAdjacent()).collect(Collectors.toSet());
	}

	public List<Entity> getContextEntities() {
		return getPostConditionSet().stream().map(d -> d.getPath().getSource()).collect(Collectors.toList());
	}

	public Set<DefPathCondition> getExecutionDependencies() {
		Set<DefPathCondition> defPathConditions = new HashSet<DefPathCondition>(getPreConditionSet());
		defPathConditions.addAll(getSequenceConditionSet());
		return defPathConditions;
	}

	public void applyMultiplicities(RelationBW relation) {
		getMultiplicityInvariantSet().addAll(relation.getMulConditionSet());

		relation.getEntitySet().stream().filter(e -> !getPostEntities().contains(e)).forEach(e -> addPreCondition(
				DefPathCondition.getDefPathCondition(getActivityModel().getSpecification(), relation.getPath(e))));
	}

	public Set<Entity> getEntityContext() {
		Set<Entity> entityContext = new HashSet<Entity>();

		// the entity already exist
		for (DefPathCondition defPath : getPreConditionSet()) {
			if (defPath.getTargetOfPath() == defPath.getSourceOfPath()) {
				entityContext.add(defPath.getSourceOfPath());
			}
		}

		// the entity is going to be defined
		for (DefPathCondition defPath : getPreConditionSet()) {
			if (!entityContext.contains(defPath.getSourceOfPath())) {
				entityContext.add(defPath.getPath().getAdjacent());
			}
		}

		return entityContext;
	}

	public Set<EntityInstance> getInstanceContext(WorkflowInstance workflowInstance, Entity entity) {
		// pre-conditions hold
		Set<EntityInstance> instanceContext = workflowInstance.getEntityInstanceSet(entity).stream()
				.filter(ei -> ei.holdsDefPathConditions(getPreConditionSet())).collect(Collectors.toSet());

		// post-conditions do not hold
		instanceContext = instanceContext.stream().filter(ei -> postConditionDoesNotHold(ei))
				.collect(Collectors.toSet());

		// finally E.e1,n must be ensured, there are enough in the instance
		// context, MAYBE IN ANOTHER METHOD isEnabled()

		return instanceContext;
	}

	public boolean postConditionDoesNotHold(EntityInstance entityInstance) {
		// for every def attribute in post-condition, if the attribute belongs
		// to the entity instance type, then it should not be defined for the
		// entity instance
		if (!getPostAttributes().stream().filter(a -> a.getEntity() == entityInstance.getEntity())
				.noneMatch(a -> entityInstance.isDefined(a))) {
			return false;
		}
		// for every def entity in post-condition, if the entity is in relation
		// with then entity instance type, then it should be possible to create
		// another relation instance associated with the entity instance
		return getMultiplicityInvariantSet().stream().filter(m -> m.getSourceEntity() == entityInstance.getEntity())
				.noneMatch(m -> entityInstance.isInMaxCardinality(m));
	}

}

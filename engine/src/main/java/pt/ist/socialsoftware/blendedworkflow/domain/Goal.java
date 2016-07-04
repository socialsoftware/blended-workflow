package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.OldGoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GoalDTO;

public class Goal extends Goal_Base {
	private static Logger logger = LoggerFactory.getLogger(Goal.class);

	public enum GoalRelation {
		CHILD, PARENT, SIBLING, OTHER;
	}

	@Override
	public void setName(String name) {
		checkUniqueGoalName(name);
		super.setName(name);
	}

	public Goal(GoalModel goalModel, String name) {
		setGoalModel(goalModel);
		setName(name);
		setParentGoal(null);
	}

	/**
	 * Create the GoalTree root Goal.
	 */
	public Goal(GoalModel goalModel, String name, String description, DefProductCondition condition, Entity context)
			throws BWException {
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		addSuccessCondition(condition);
		setParentGoal(null);
		setOldEntityContext(context);
	}

	/**
	 * Create a Goal.
	 */
	public Goal(GoalModel goalModel, Goal parentGoal, String name, String description, DefProductCondition condition,
			Entity context) throws BWException {
		setGoalModel(goalModel);
		setName(name);
		setDescription(description);
		addSuccessCondition(condition);
		setParentGoal(parentGoal);
		setOldEntityContext(context);
	}

	private void checkUniqueGoalName(String name) throws BWException {
		for (Goal goal : this.getGoalModel().getGoalSet()) {
			if (goal.getName() != null && goal != this && goal.getName().equals(name)) {
				throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
			}
		}
		for (OldMaintainGoal goal : this.getGoalModel().getMaintainGoalsSet()) {
			if (goal.getName() != null && goal.getName().equals(name)) {
				throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
			}
		}
	}

	public void cloneGoal(OldGoalModelInstance goalModelInstance) throws BWException {
		DefProductCondition newSucessCondition = null;
		DefProductCondition condition = getSuccessConditionSet().stream().findFirst().get();
		if (condition != null) {
			newSucessCondition = (DefProductCondition) condition.cloneCondition(goalModelInstance);
		}

		// Get EntityTypeContext from Template
		OldBWInstance bwInstance = goalModelInstance.getBwInstance();
		OldDataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		Entity newEntityContext = null;
		for (Entity entity : dataModelInstance.getEntitySet()) {
			if (getOldEntityContext().getName().equals(entity.getName())) {
				newEntityContext = entity;
			}
		}

		Goal newGoal = new Goal(goalModelInstance, getName(), getDescription(), newSucessCondition, newEntityContext);
		newGoal.setUser(getUser());
		newGoal.setRole(getRole());

		for (DefPathCondition activateCondition : getActivationConditionSet()) {
			DefPathCondition newActivateCondition = (DefPathCondition) activateCondition
					.cloneCondition(goalModelInstance);
			newGoal.addActivationCondition(newActivateCondition);
		}
	}

	/**
	 * Get the Goal condition data to use in the use interface.
	 * 
	 * @return a string with the condition data entities.
	 */
	public String getConstraintData() {
		Set<Entity> entities = getSuccessConditionSet().stream().findFirst().get().getEntities();
		Set<Attribute> attributes = getSuccessConditionSet().stream().findFirst().get().getAttributeSet();
		String dataString = "";

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

	public String getPreConstraintData() {
		List<Condition> activateConditions = new ArrayList<Condition>(getActivationConditionSet());
		Set<Entity> entities = activateConditions.get(0).getEntities(); // FIXME:
																		// Only
																		// First
																		// ActivateConditionData
		Set<Attribute> attributes = activateConditions.get(0).getAttributeSet();
		String dataString = "";

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

	/**
	 * Check GoalWorkitems in the ACTIVATED state.
	 */
	public void checkActivated(OldBWInstance bwInstance) {
		if (!getOldGoalWorkItemSet().isEmpty()) {
			for (OldGoalWorkItem goalWorkItem : getOldGoalWorkItemSet()) {
				if (goalWorkItem.getState().equals(GoalState.ACTIVATED)) {
					goalWorkItem.notifyDataChanged();
				}
			}
		}
	}

	/**
	 * Check if exists a goalWorkitem for a given context
	 * 
	 * @param goal
	 * @param entityInstanceContext
	 * @return
	 */
	public Boolean goalWorkItemsExistForTheContext(OldEntityInstance entityInstanceContext) {
		Boolean alreadyExists = false;
		for (OldGoalWorkItem goalWorkItem : getOldGoalWorkItemSet()) {
			if (goalWorkItem.getEntityInstanceContext().equals(entityInstanceContext)) {
				alreadyExists = true;
			}
		}
		return alreadyExists;
	}

	// TODO:
	public Set<Entity> getSubGoalsContext() {
		Set<Entity> result = new HashSet<Entity>();
		// result.add(getEntityContext());
		for (Goal subGoal : getSubGoalSet()) {
			result.add(subGoal.getOldEntityContext());
		}

		return result;
	}

	public void delete() {
		setGoalModel(null);
		setParentGoal(null);

		getWorkItemSet().stream().forEach(wi -> wi.delete());

		getSubGoalSet().stream().forEach(sub -> removeSubGoal(sub));
		getSuccessConditionSet().stream().forEach(suc -> removeSuccessCondition(suc));
		getActivationConditionSet().stream().forEach(act -> removeActivationCondition(act));
		getEntityInvariantConditionSet().stream().forEach(mul -> removeEntityInvariantCondition(mul));
		getAttributeInvariantConditionSet().stream().forEach(rule -> removeAttributeInvariantCondition(rule));

		deleteDomainObject();
	}

	public GoalDTO getDTO() {
		GoalDTO goalDTO = new GoalDTO();
		goalDTO.setSpecId(getSpecification().getSpecId());
		goalDTO.setExtId(getExternalId());
		goalDTO.setName(getName());

		return goalDTO;
	}

	public GoalRelation getGoalRelation(Goal goalTwo) {
		if (getSubGoalSet().contains(goalTwo))
			return GoalRelation.CHILD;

		if (goalTwo.getSubGoalSet().contains(this))
			return GoalRelation.PARENT;

		if ((getParentGoal() != null) && (getParentGoal().getSubGoalSet().contains(goalTwo)))
			return GoalRelation.SIBLING;

		return GoalRelation.OTHER;
	}

	public void applyConditions() {
		applyActivationConditions();
		applyMultiplicityConditions();
		applyRuleConditions();
	}

	private void applyActivationConditions() {
		Set<String> paths = getProducedProducts().stream().flatMap(p -> p.getDependenceSet().stream())
				.map(d -> d.getPath().getValue()).collect(Collectors.toSet());

		for (String path : paths) {
			if (!getProducedProducts().contains(getDataModel().getTargetOfPath(path)))
				addActivationCondition(DefPathCondition.getDefPathCondition(getSpecification(), path));
		}
	}

	private void applyMultiplicityConditions() {
		getProducedEntities().stream().flatMap(e -> e.getMultConditions().stream())
				.forEach(m -> addEntityInvariantCondition(m));
	}

	private void applyRuleConditions() {
		getProducedEntities().stream().flatMap(e -> e.getRuleSet().stream())
				.forEach(rule -> addAttributeInvariantCondition(rule));
	}

	private DataModel getDataModel() {
		return getSpecification().getDataModel();
	}

	private Specification getSpecification() {
		return getGoalModel().getSpecification();
	}

	public void checkConditionsExistSucc(Set<DefProductCondition> successConditions) {
		Optional<DefProductCondition> oCond = successConditions.stream()
				.filter((def) -> !getSuccessConditionSet().contains(def)).findFirst();

		if (oCond.isPresent())
			throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL,
					"checkConditionsExistInSource:" + oCond.get().getSubPath());

	}

	public void shrinkGoal(Set<DefProductCondition> successConditions) {
		successConditions.stream().forEach(def -> removeSuccessCondition(def));
		getActivationConditionSet().stream().forEach(def -> removeActivationCondition(def));
		getEntityInvariantConditionSet().stream().forEach(mul -> removeEntityInvariantCondition(mul));
		getAttributeInvariantConditionSet().stream().forEach(rul -> removeAttributeInvariantCondition(rul));
	}

	public void checkAttributeChildEntityParentConstraint() {
		Set<Attribute> attributes = getProducedAttributes();

		if (!attributes.isEmpty()) {
			Set<Goal> goals = getTransitiveParentGoals();
			goals.add(this);
			Set<Entity> entities = goals.stream().flatMap(g -> g.getProducedEntities().stream())
					.collect(Collectors.toSet());
			if (!attributes.stream().allMatch(a -> entities.contains(a.getEntity()))) {
				throw new BWException(BWErrorType.INCONSISTENT_GOALMODEL,
						"Attributes of " + getName() + " do not have its Entity in a parent goal");
			}
		}
	}

	public void checkActivationConditionDependenciesConstraint() {
		Set<Goal> subGoals = flattened().collect(Collectors.toSet());
		subGoals.remove(this);

		for (DefPathCondition defPath : getActivationConditionSet()) {
			Product defProduct = defPath.getTargetOfPath();
			if (subGoals.stream().anyMatch(g -> g.getProducedProducts().contains(defProduct))) {
				throw new BWException(BWErrorType.INCONSISTENT_GOALMODEL, "Subgoals of " + getName()
						+ " produce product " + defProduct.getFullPath() + " which is in its activation condition");
			}
		}
	}

	private Stream<Goal> flattened() {
		return Stream.concat(Stream.of(this), getSubGoalSet().stream().flatMap(Goal::flattened));
	}

	private Set<Product> getProducedProducts() {
		return getSuccessConditionSet().stream().map(d -> d.getPath().getTarget()).collect(Collectors.toSet());
	}

	private Set<Entity> getProducedEntities() {
		return getSuccessConditionSet().stream().map(d -> d.getPath().getTarget()).filter(Entity.class::isInstance)
				.map(Entity.class::cast).collect(Collectors.toSet());
	}

	private Set<Attribute> getProducedAttributes() {
		return getSuccessConditionSet().stream().map(d -> d.getPath().getTarget()).filter(Attribute.class::isInstance)
				.map(Attribute.class::cast).collect(Collectors.toSet());
	}

	private Set<Goal> getTransitiveParentGoals() {
		Set<Goal> parents = new HashSet<Goal>();

		if (getParentGoal() != null) {
			parents.add(getParentGoal());
			parents.addAll(getParentGoal().getTransitiveParentGoals());
		}

		return parents;
	}

	public Boolean isEnabledForExecution(WorkflowInstance workflowInstance) {
		// get entity context
		Set<Entity> entityContext = getEntityContext();
		if (entityContext.isEmpty()) {
			return true;
		}

		// for each entity, in entity context, get instance context
		for (Entity entity : entityContext) {
			if (getInstanceContext(workflowInstance, entity).isEmpty() && entityRequiresInstances(entity)) {
				return false;
			}
		}

		return true;
	}

	// there is a mul condition which target is entity and the mul condition
	// symmetric does not to the goal entity invariant conditions to top
	private boolean entityRequiresInstances(Entity entity) {
		for (MulCondition mulCondition : getEntityInvariantConditionSet()) {
			if (mulCondition.getTargetEntity() == entity
					&& !getEntityInvariantConditionsToTop().contains(mulCondition.getSymmetricMulCondition())) {
				return false;
			}
		}

		return true;
	}

	public Set<Entity> getEntityContext() {
		Set<Entity> entityContext = new HashSet<Entity>();

		// some may not be defined in activation conditions because of parent
		// goals
		for (DefProductCondition defProductCondition : getSuccessConditionSet()) {
			// attribute is defined but not its entity
			if (defProductCondition.isAttribute()
					&& !getSuccessConditionSet().contains(defProductCondition.getSourceOfPath())) {
				entityContext.add(defProductCondition.getSourceOfPath());
			}
			// create contexts for entities which are not defined in the goal
			// subtree
			if (defProductCondition.isEntity()) {
				for (MulCondition mulCondition : getEntityInvariantConditionSet()) {
					if (mulCondition.getSourceEntity() == defProductCondition.getTargetOfPath()
							&& !isInSubTree(mulCondition.getSymmetricMulCondition()))
						entityContext.add(mulCondition.getTargetEntity());
				}
			}
		}

		// the entity is already defined
		for (DefPathCondition defPathCondition : getActivationConditionSet()) {
			if (defPathCondition.isEntity()) {
				entityContext.add(defPathCondition.getSourceOfPath());
			}
		}

		// the entity is going to be defined
		for (DefPathCondition defPathCondition : getActivationConditionSet()) {
			if (!entityContext.contains(defPathCondition.getSourceOfPath())) {
				entityContext.add(defPathCondition.getPath().getAdjacent());
			}
		}

		return entityContext;
	}

	public Set<Entity> getEntityContext(Entity entity) {
		Set<Entity> entityContext = new HashSet<Entity>();

		// some may not be defined in activation conditions because of parent
		// goals
		for (DefProductCondition defProductCondition : getSuccessConditionSet().stream()
				.filter(d -> d.getSourceOfPath() == entity).collect(Collectors.toSet())) {
			// attribute is defined but not its entity
			if (defProductCondition.isAttribute()
					&& !getSuccessEntities().contains(defProductCondition.getSourceOfPath())) {
				entityContext.add(defProductCondition.getSourceOfPath());
			}
			// create contexts for entities which are not defined in the goal
			// subtree
			if (defProductCondition.isEntity()) {
				for (MulCondition mulCondition : getEntityInvariantConditionSet()) {
					if (mulCondition.getSourceEntity() == defProductCondition.getTargetOfPath()
							&& !isInSubTree(mulCondition.getSymmetricMulCondition()))
						entityContext.add(mulCondition.getTargetEntity());
				}
			}
		}

		Set<DefPathCondition> activationDefPathConditions = getActivationConditionSet().stream()
				.filter(d -> d.getSourceOfPath() == entity).collect(Collectors.toSet());

		// the entity already exist
		for (DefPathCondition defPathCondition : activationDefPathConditions) {
			if (defPathCondition.isEntity()) {
				entityContext.add(defPathCondition.getSourceOfPath());
			}
		}

		// the entity is going to be defined
		for (DefPathCondition defPathCondition : activationDefPathConditions) {
			if (!entityContext.contains(defPathCondition.getSourceOfPath())) {
				entityContext.add(defPathCondition.getPath().getAdjacent());
			}
		}

		return entityContext;
	}

	private boolean isInSubTree(MulCondition mulCondition) {
		if (getEntityInvariantConditionSet().contains(mulCondition))
			return true;

		for (Goal subGoal : getSubGoalSet()) {
			if (subGoal.isInSubTree(mulCondition))
				return true;
		}

		return false;
	}

	public Map<Entity, Set<EntityInstance>> getInstanceContext(WorkflowInstance workflowInstance) {
		Map<Entity, Set<EntityInstance>> instanceContext = new HashMap<Entity, Set<EntityInstance>>();

		for (Entity entity : getEntityContext()) {
			instanceContext.put(entity, getInstanceContext(workflowInstance, entity));
		}

		return instanceContext;
	}

	public Set<EntityInstance> getInstanceContext(WorkflowInstance workflowInstance, Entity contextEntity) {
		// activation conditions hold
		Set<EntityInstance> instanceContext = workflowInstance.getEntityInstanceSet(contextEntity).stream()
				.filter(ei -> ei.holdsDefPathConditions(getActivationConditionSet())).collect(Collectors.toSet());

		// none of activation conditions attributes are defined
		instanceContext = instanceContext.stream().filter(ei -> ei.attributesNotDefined(getSuccessAttributes()))
				.collect(Collectors.toSet());

		// entity instance can be associated due to invariant mulconditions
		instanceContext = instanceContext.stream()
				.filter(ei -> ei.canBeAssociatedWithNewEntityInstance(getEntityInvariantConditionSet()))
				.collect(Collectors.toSet());

		// there are enough instances in the context to enable the activity
		int instanceContextSize = instanceContext.size();
		if (!getEntityInvariantConditionSet().stream().filter(m -> m.getTargetEntity() == contextEntity)
				.allMatch(m -> m.getCardinality().getMinValue() <= instanceContextSize)) {
			instanceContext.clear();
		}

		return instanceContext;
	}

	public Set<MulCondition> getMulConditionsThatShouldHold(Product product) {
		Set<MulCondition> entityInvariantConditionsToTop = getEntityInvariantConditionsToTop();
		return getEntityInvariantConditionSet().stream()
				.filter(m -> m.getSourceEntity() == product
						&& entityInvariantConditionsToTop.contains(m.getSymmetricMulCondition()))
				.collect(Collectors.toSet());
	}

	private Set<Entity> getSuccessEntities() {
		return getSuccessConditionSet().stream().map(d -> d.getTargetOfPath()).filter(Entity.class::isInstance)
				.map(Entity.class::cast).collect(Collectors.toSet());
	}

	private Set<Attribute> getSuccessAttributes() {
		return getSuccessConditionSet().stream().map(d -> d.getTargetOfPath()).filter(Attribute.class::isInstance)
				.map(Attribute.class::cast).collect(Collectors.toSet());
	}

	private Set<Entity> getParentsSuccessEntities() {
		Set<Entity> successEntities = new HashSet<Entity>();

		if (getParentGoal() != null) {
			successEntities.addAll(getParentGoal().getSuccessEntitiesToTop());
		}

		return successEntities;
	}

	private Set<Entity> getSuccessEntitiesToTop() {
		Set<Entity> successEntities = new HashSet<Entity>(getSuccessEntities());

		if (getParentGoal() != null) {
			successEntities.addAll(getParentGoal().getSuccessEntitiesToTop());
		}

		return successEntities;
	}

	private Set<MulCondition> getEntityInvariantConditionsToTop() {
		Set<MulCondition> mulConditions = new HashSet<MulCondition>(getEntityInvariantConditionSet());

		if (getParentGoal() != null) {
			mulConditions.addAll(getParentGoal().getEntityInvariantConditionsToTop());
		}

		return mulConditions;
	}

	public Set<MulCondition> getMulConditionFromEntityToEntity(Entity fromEntity, Entity toEntity) {
		return getEntityInvariantConditionSet().stream()
				.filter(m -> m.getSourceEntity() == fromEntity && m.getTargetEntity() == toEntity)
				.collect(Collectors.toSet());
	}

	public Set<MulCondition> getInnerMulConditions(Entity entity) {
		Set<Entity> postEntities = getSuccessEntities();
		return getEntityInvariantConditionSet()
				.stream().filter(m -> postEntities.contains(m.getSourceEntity())
						&& postEntities.contains(m.getTargetEntity()) && m.getSourceEntity() == entity)
				.collect(Collectors.toSet());
	}

}
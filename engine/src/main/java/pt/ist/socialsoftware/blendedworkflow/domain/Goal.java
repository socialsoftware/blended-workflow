package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem.GoalState;
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
		setEntityContext(context);
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
		setEntityContext(context);
	}

	private void checkUniqueGoalName(String name) throws BWException {
		for (Goal goal : this.getGoalModel().getGoalSet()) {
			if (goal.getName() != null && goal != this && goal.getName().equals(name)) {
				throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
			}
		}
		for (MaintainGoal goal : this.getGoalModel().getMaintainGoalsSet()) {
			if (goal.getName() != null && goal.getName().equals(name)) {
				throw new BWException(BWErrorType.INVALID_GOAL_NAME, name);
			}
		}
	}

	public void cloneGoal(GoalModelInstance goalModelInstance) throws BWException {
		DefProductCondition newSucessCondition = null;
		DefProductCondition condition = getSuccessConditionSet().stream().findFirst().get();
		if (condition != null) {
			newSucessCondition = (DefProductCondition) condition.cloneCondition(goalModelInstance);
		}

		// Get EntityTypeContext from Template
		BWInstance bwInstance = goalModelInstance.getBwInstance();
		DataModelInstance dataModelInstance = bwInstance.getDataModelInstance();
		Entity newEntityContext = null;
		for (Entity entity : dataModelInstance.getEntitySet()) {
			if (getEntityContext().getName().equals(entity.getName())) {
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
	public void checkActivated(BWInstance bwInstance) {
		if (!getGoalWorkItemsSet().isEmpty()) {
			for (GoalWorkItem goalWorkItem : getGoalWorkItemsSet()) {
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
	public Boolean goalWorkItemsExistForTheContext(EntityInstance entityInstanceContext) {
		Boolean alreadyExists = false;
		for (GoalWorkItem goalWorkItem : getGoalWorkItemsSet()) {
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
			result.add(subGoal.getEntityContext());
		}

		return result;
	}

	public void delete() {
		setGoalModel(null);
		setParentGoal(null);
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

}
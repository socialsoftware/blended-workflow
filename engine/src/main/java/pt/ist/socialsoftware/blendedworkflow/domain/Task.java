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

public class Task extends Task_Base {
	private static Logger logger = LoggerFactory.getLogger(Task.class);

	@Override
	public void setName(String name) {
		checkUniqueTaskName(name);
		super.setName(name);
	}

	@Override
	public void addSequenceCondition(DefPathCondition sequenceCondition) {
		checkSequenceConditon(sequenceCondition);

		super.addSequenceCondition(sequenceCondition);

		checkCycles(getTaskModel().getTaskSequences());

		getTaskModel().applyRules();
	}

	public Task(TaskModel taskModel, String name, String description) {
		setTaskModel(taskModel);
		setName(name);
		setDescription(description);
	}

	public Task(TaskModel taskModel, String name, String description, Set<DefPathCondition> preConditions,
			Set<DefProductCondition> postCondition, String previous, String joinCode, String splitCode)
			throws BWException {
		setTaskModel(taskModel);
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
		if (getTaskModel().getTasksSet().stream().filter(t -> t != this && t.getName().equals(name)).findFirst()
				.isPresent())
			throw new BWException(BWErrorType.INVALID_TASK_NAME, name);
	}

	public void cloneTask(TaskModelInstance taskModelInstance) throws BWException {
		Set<DefPathCondition> newPreConditions = null;
		Set<DefPathCondition> preConditions = getPreConditionSet();
		Set<DefProductCondition> newPostCondition = null;
		Set<DefProductCondition> postCondition = getPostConditionSet();
		if (!preConditions.isEmpty() && !getPostConditionSet().isEmpty()) {
			newPreConditions = preConditions.stream()
					.map((cond) -> (DefPathCondition) cond.cloneCondition(taskModelInstance))
					.collect(Collectors.toSet());
			newPostCondition = postCondition.stream()
					.map((cond) -> (DefProductCondition) cond.cloneCondition(taskModelInstance))
					.collect(Collectors.toSet());
		}
		Task newTask = new Task(taskModelInstance, getName(), getDescription(), newPreConditions, newPostCondition,
				getPrevious(), getJoinCode(), getSplitCode());
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
		setTaskModel(null);
		getPreConditionSet().forEach(c -> removePreCondition(c));
		getPostConditionSet().forEach(c -> removePostCondition(c));
		getMultiplicityInvariantSet().forEach(m -> removeMultiplicityInvariant(m));
		getRuleInvariantSet().forEach(r -> removeRuleInvariant(r));
		getSequenceConditionSet().forEach(d -> d.delete());

		deleteDomainObject();
	}

	public ActivityDTO getDTO() {
		return new ActivityDTO(getTaskModel().getSpecification().getSpecId(), getName(), getDescription());
	}

	public void checkConsistency() {
		checkPostConditionContainsAtLeastOneDef();
		checkEntityOfDefAttributeIsDefined();
		checkDependenceConstraint();
		checkMultiplicityConstraint();
		checkRuleConstraint();
		checkCycles(getTaskModel().getTaskSequences());
	}

	public void checkCycles(Map<Task, Set<Task>> taskDependencies) {
		goThroughAcyclicPath(this, taskDependencies, new HashSet<Task>());
	}

	private void goThroughAcyclicPath(Task task, Map<Task, Set<Task>> taskDependencies, Set<Task> visitedTasks) {
		Set<Task> nextTasks = taskDependencies.get(this);
		visitedTasks.add(this);

		if (nextTasks.contains(task)) {
			throw new BWException(BWErrorType.DEPENDENCE_CIRCULARITY, getName());
		} else {
			for (Task nextTask : nextTasks) {
				if (!visitedTasks.contains(nextTask))
					nextTask.goThroughAcyclicPath(task, taskDependencies, visitedTasks);
			}
		}
	}

	private void checkRuleConstraint() {
		// at least one of the attributes in the rule is defined in the task
		Set<Attribute> postAttributes = getPostConditionSet().stream().map(d -> d.getTargetOfPath())
				.filter(Attribute.class::isInstance).map(Attribute.class::cast).collect(Collectors.toSet());
		for (Rule rule : getRuleInvariantSet())
			if (!rule.getAttributeSet().stream().anyMatch(a -> postAttributes.contains(a)))
				throw new BWException(BWErrorType.INCONSISTENT_RULE_CONDITION, getName() + ":" + rule.getName());
	}

	private void checkMultiplicityConstraint() {
		Set<Entity> allEntities = ConditionModel.getEntitiesOfDefConditionSet(getPreConditionSet());
		Set<Entity> postEntities = ConditionModel.getEntitiesOfDefConditionSet(getPostConditionSet());
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

		Optional<Dependence> oDep = getTaskModel().getSpecification().getDataModel().getDependenceSet().stream()
				.filter(d -> postProducts.contains(d.getProduct()) && !preProducts.contains(d.getTarget())
						&& !postProducts.contains(d.getTarget()))
				.findFirst();

		if (oDep.isPresent())
			throw new BWException(BWErrorType.MISSING_DEF_IN_PRE, getName() + ":" + oDep.get().getProduct().getName());
	}

	private void checkEntityOfDefAttributeIsDefined() {
		Set<Entity> entities = Stream
				.concat(ConditionModel.getEntitiesOfDefConditionSet(getPreConditionSet()).stream(),
						ConditionModel.getEntitiesOfDefConditionSet(getPostConditionSet()).stream())
				.collect(Collectors.toSet());

		Optional<DefAttributeCondition> oDef = ConditionModel.getDefAttributeConditions(getPostConditionSet()).stream()
				.filter(d -> !entities.contains(d.getAttributeOfDef().getEntity())).findFirst();

		if (oDef.isPresent())
			throw new BWException(BWErrorType.MISSING_DEF_IN_PRE,
					getName() + ":" + oDef.get().getAttributeOfDef().getEntity().getName() + "."
							+ oDef.get().getAttributeOfDef().getName());
	}

	private void checkPostConditionContainsAtLeastOneDef() {
		if (ConditionModel.getDefAttributeConditions(getPostConditionSet()).size() > 0)
			return;

		if (ConditionModel.getDefEntityConditions(getPostConditionSet()).size() > 0)
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

	public Set<Entity> getPostEntities() {
		return getPostConditionSet().stream().map(d -> d.getPath().getTarget()).filter(Entity.class::isInstance)
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

}

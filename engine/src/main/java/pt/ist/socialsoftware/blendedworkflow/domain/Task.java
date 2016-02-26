package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
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
		Set<AttributeBasic> attributes;
		String dataString = "";

		// Get Condition Data
		if (isPreConstraint) {
			entities = getPreConditionSet().stream().flatMap((cond) -> cond.getEntities().stream())
					.collect(Collectors.toSet());
			attributes = getPreConditionSet().stream().flatMap((cond) -> cond.getAttributeBasicSet().stream())
					.collect(Collectors.toSet());
		} else {
			entities = getPostConditionSet().stream().flatMap((cond) -> cond.getEntities().stream())
					.collect(Collectors.toSet());
			attributes = getPostConditionSet().stream().flatMap((cond) -> cond.getAttributeBasicSet().stream())
					.collect(Collectors.toSet());
		}

		// Add Attribute entities
		for (AttributeBasic attribute : attributes) {
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

		deleteDomainObject();
	}

	public ActivityDTO getDTO() {
		return new ActivityDTO(getTaskModel().getSpecification().getSpecId(), getName(), getDescription());
	}

	public boolean checkConsistency() {
		checkPostConditionContainsAtLeastOneDef();
		checkEntityOfDefAttributeIsDefined();
		checkDependenceConstraint();
		checkMultiplicityConstraint();
		checkRuleConstraint();
		checkCircularity();

		return true;
	}

	public void checkCircularity() {
		Set<Product> visitedProducts = new HashSet<Product>();
		goThroughAcyclicPath(this, visitedProducts);
	}

	private void goThroughAcyclicPath(Task task, Set<Product> visitedProducts) {
		Set<Product> nextProducts = getPreConditionSet().stream().map(p -> p.getPath().getTargetOfPath())
				.filter(p -> !p.isEntityAndExists() && !visitedProducts.contains(p)).collect(Collectors.toSet());

		Set<Task> nextTasks = nextProducts.stream()
				.map(p -> getTaskModel().getTaskPostConditionContains(p.getFullPath())).collect(Collectors.toSet());

		if (nextTasks.contains(task)) {
			throw new BWException(BWErrorType.DEPENDENCE_CIRCULARITY, getName());
		} else {
			visitedProducts.addAll(nextProducts);
			for (Task nextTask : nextTasks) {
				nextTask.goThroughAcyclicPath(task, visitedProducts);
			}
		}
	}

	private void checkRuleConstraint() {
		Set<AttributeBasic> attributes = getTaskModel().getDefinedAttributes();

		Set<AttributeBasic> postAttributes = ConditionModel.getBasicAtributesOfDefConditionSet(getPostConditionSet());

		Optional<Rule> oRule = getRuleInvariantSet().stream().filter(r -> !attributes.containsAll(r.getAttributeSet()))
				.findFirst();

		if (oRule.isPresent())
			throw new BWException(BWErrorType.INCONSISTENT_RULE_CONDITION, getName() + ":" + oRule.get().getName());

		// at least an attribute is defined in the task
		for (Rule rule : getRuleInvariantSet())
			if (!rule.getAttributeSet().stream().anyMatch((a) -> postAttributes.contains(a)))
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
					getName() + ":" + oMul.get().getEntity().getName() + "." + oMul.get().getRolename());

		// at least one entity definition is done here
		oMul = getMultiplicityInvariantSet().stream()
				.filter(m -> !(postEntities.contains(m.getRelationBW().getEntityOne())
						|| postEntities.contains(m.getRelationBW().getEntityTwo())))
				.findFirst();

		if (oMul.isPresent())
			throw new BWException(BWErrorType.INCONSISTENT_MUL_CONDITION,
					getName() + ":" + oMul.get().getEntity().getName());

	}

	private void checkDependenceConstraint() {
		Set<Product> postProducts = ConditionModel.getProductsOfDefConditions(getPostConditionSet());

		Set<Product> preProducts = ConditionModel.getProductsOfDefConditions(getPreConditionSet());

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

	public void removePreCondition(String entityTwoName) {
		for (DefPathCondition defPathCondition : getPreConditionSet()) {
			if (defPathCondition.getPath().getValue().equals(entityTwoName)) {
				removePreCondition(defPathCondition);
			}
		}

	}

}

package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class TaskModel extends TaskModel_Base {
	private static Logger logger = LoggerFactory.getLogger(TaskModel.class);

	/**
	 * Clone the TaskModel
	 */
	public void cloneTaskModel(TaskModelInstance newTaskModelInstance) throws BWException {
		for (Task task : getTasksSet()) {
			task.cloneTask(newTaskModelInstance);
		}
	}

	public Task createTask(String name, String description) {
		return new Task(this, name, description);
	}

	public Task getTask(String name) throws BWException {
		for (Task task : getTasksSet()) {
			if (task.getName().equals(name)) {
				return task;
			}
		}
		throw new BWException(BWErrorType.NON_EXISTENT_TASK_NAME, name);
	}

	public void clean() {
		getTasksSet().stream().forEach(t -> t.delete());
	}

	public void delete() {
		clean();
		setSpecification(null);

		deleteDomainObject();
	}

	public void generateActivities() {
		ConditionModel conditionModel = getSpecification().getConditionModel();

		int activityCounter = 1;

		for (DefEntityCondition defEntityCondition : conditionModel.getEntityAchieveConditionSet()) {
			if (!defEntityCondition.getEntity().getExists()) {
				HashSet<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
				postConditionSet.add(defEntityCondition);
				addTask("a" + activityCounter, "Activity number " + activityCounter, postConditionSet);
				activityCounter++;
			}
		}

		for (DefAttributeCondition defEntityCondition : conditionModel.getAttributeAchieveConditionSet()) {
			HashSet<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
			postConditionSet.add(defEntityCondition);
			addTask("a" + activityCounter, "Activity number " + activityCounter, postConditionSet);
			activityCounter++;
		}

		checkModel();
	}

	public Task addTask(String taskName, String taskDescription, Set<DefProductCondition> postConditionSet) {
		checkNonEmptyPostCondition(postConditionSet);

		checkModelNotCompletelyCreated();

		checkPostConditionsNotUsed(postConditionSet);

		Task task = createTask(taskName, taskDescription);
		postConditionSet.stream().forEach(c -> task.addPostCondition(c));

		applyAttributeEntityDependenceToPre(task);

		applyDependenceConditionsToPre(task);

		applyMultiplicityToPostAndPre(task);

		applyRuleConditionToPostAndPre(task);

		return task;
	}

	public Task mergeTasks(String taskName, String taskDescription, Task taskOne, Task taskTwo) {
		Task newTask = createTask(taskName, taskDescription);

		newTask.getPreConditionSet().addAll(taskOne.getPreConditionSet());
		newTask.getPreConditionSet().addAll(taskTwo.getPreConditionSet());

		newTask.getPostConditionSet().addAll(taskOne.getPostConditionSet());
		newTask.getPostConditionSet().addAll(taskTwo.getPostConditionSet());

		Set<Product> postProducts = getConditionModel().getProductsOfDefConditions(newTask.getPostConditionSet());

		for (DefPathCondition defPathCondition : newTask.getPreConditionSet()) {
			if (postProducts.contains(defPathCondition.getPathObject().getTargetOfPath()))
				newTask.removePreCondition(defPathCondition);
		}

		newTask.getMultiplicityInvariantSet().addAll(taskOne.getMultiplicityInvariantSet());
		newTask.getMultiplicityInvariantSet().addAll(taskTwo.getMultiplicityInvariantSet());

		newTask.getRuleInvariantSet().addAll(taskOne.getRuleInvariantSet());
		newTask.getRuleInvariantSet().addAll(taskTwo.getRuleInvariantSet());

		taskOne.delete();
		taskTwo.delete();

		return newTask;
	}

	public Task extractTask(Task fromTask, String taskName, String taskDescription,
			Set<DefProductCondition> postConditionSet) {
		String fromTaskName = fromTask.getName();
		String fromTaskDescription = fromTask.getDescription();
		Set<DefProductCondition> fromTaskPostCondition = new HashSet<DefProductCondition>(
				fromTask.getPostConditionSet());
		fromTaskPostCondition.removeAll(postConditionSet);

		fromTask.delete();

		addTask(fromTaskName, fromTaskDescription, fromTaskPostCondition);

		return addTask(taskName, taskDescription, postConditionSet);
	}

	private void applyRuleConditionToPostAndPre(Task task) {
		Set<AttributeBasic> definedAttributes = getDefinedAttributes();

		getSpecification().getDataModel().getRuleSet().stream()
				.filter(r -> r.getTaskWithRule() == null && definedAttributes.containsAll(r.getAttributeBasicSet()))
				.forEach(r -> task.addRuleInvariant(r));

		Set<AttributeBasic> postAttributes = getConditionModel()
				.getBasicAtributesOfDefAttributeSet(task.getPostConditionSet());
		Set<Product> preAttributes = task.getPreConditionSet().stream().map(d -> d.getPathObject().getTargetOfPath())
				.collect(Collectors.toSet());
		preAttributes.addAll(getDataModel().getEntitySet().stream().filter(e -> e.getExists())
				.flatMap(e -> e.getAttributeBasicSet().stream()).collect(Collectors.toSet()));

		task.getRuleInvariantSet().stream().flatMap(r -> r.getPathSet().stream())
				.filter(path -> !postAttributes.contains(getDataModel().getTargetOfPath(path))
						&& !preAttributes.contains(getDataModel().getTargetOfPath(path)))
				.forEach(path -> task.addPreCondition(DefPathCondition.getDefPathCondition(getSpecification(), path)));
	}

	public Set<AttributeBasic> getDefinedAttributes() {
		Set<AttributeBasic> definedAttributes = getTasksSet().stream()
				.flatMap(t -> getConditionModel().getDefAttributeConditions(t.getPostConditionSet()).stream())
				.flatMap(def -> def.getAttributeOfDef().getAttributeBasicSet().stream()).collect(Collectors.toSet());

		definedAttributes.addAll(getDataModel().getEntitySet().stream().filter(e -> e.getExists())
				.flatMap(e -> e.getAttributeBasicSet().stream()).collect(Collectors.toSet()));

		return definedAttributes;
	}

	private void applyMultiplicityToPostAndPre(Task task) {
		Set<Entity> definedEntities = getDefinedEntities();

		// System.out.println("definedEntities");
		// definedEntities.stream().forEach(e ->
		// System.out.println(e.getName()));

		Set<MulCondition> definedMulConditions = getTasksSet().stream()
				.flatMap(t -> t.getMultiplicityInvariantSet().stream()).collect(Collectors.toSet());

		// System.out.println("definedMulConditions");
		// definedMulConditions.stream().forEach(e ->
		// System.out.println(e.getRolename()));

		Set<MulCondition> undefinedMulConditions = new HashSet<MulCondition>(
				getConditionModel().getEntityInvariantConditionSet());
		undefinedMulConditions.removeAll(definedMulConditions);

		// System.out.println("undefinedMulConditions");
		// undefinedMulConditions.stream().forEach(e ->
		// System.out.println(e.getRolename()));

		Set<MulCondition> mulConditionsToDefine = undefinedMulConditions.stream()
				.filter(m -> definedEntities.containsAll(m.getRelationBW().getEntitySet())).collect(Collectors.toSet());

		mulConditionsToDefine.forEach(m -> task.getMultiplicityInvariantSet().add(m));

		mulConditionsToDefine.stream()
				.filter(m -> !getConditionModel().getEntitiesOfDefEntitySet(task.getPostConditionSet())
						.contains(m.getEntity()))
				.forEach(m -> task.addPreCondition(
						DefPathCondition.getDefPathCondition(getSpecification(), m.getEntity().getName())));
	}

	public Set<Entity> getDefinedEntities() {
		Set<Entity> definedEntities = getTasksSet().stream()
				.flatMap(t -> getConditionModel().getEntitiesOfDefEntitySet(t.getPostConditionSet()).stream())
				.collect(Collectors.toSet());

		Set<Entity> existEntities = getConditionModel().getEntityAchieveConditionSet().stream()
				.map(eac -> eac.getEntity()).filter(e -> e.getEntity().getExists()).collect(Collectors.toSet());

		definedEntities.addAll(existEntities);

		return definedEntities;
	}

	private void applyDependenceConditionsToPre(Task task) {
		Set<Product> postProducts = getConditionModel().getProductsOfDefConditions(task.getPostConditionSet());
		Set<Product> preProducts = getConditionModel().getProductsOfDefConditions(task.getPreConditionSet());

		getSpecification().getDataModel().getDependenceSet().stream()
				.filter(d -> postProducts.contains(d.getProduct()) && !postProducts.contains(d.getTarget())
						&& !preProducts.contains(d.getTarget()))
				.forEach(d -> task.addPreCondition(
						DefPathCondition.getDefPathCondition(getSpecification(), d.getPath().getValue())));

	}

	private void applyAttributeEntityDependenceToPre(Task task) {
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>(task.getPostConditionSet());
		getConditionModel().getDefAttributeConditions(postConditionSet).stream()
				.filter(def -> !getConditionModel().getEntitiesOfDefEntitySet(postConditionSet)
						.contains(def.getAttributeOfDef().getEntity()))
				.forEach(def -> task.addPreCondition(DefPathCondition.getDefPathCondition(getSpecification(),
						def.getAttributeOfDef().getEntity().getName())));
	}

	private void checkPostConditionsNotUsed(Set<DefProductCondition> postConditionSet) {
		if (getTasksSet().stream().flatMap(t -> t.getPostConditionSet().stream())
				.anyMatch(c -> postConditionSet.contains(c)))
			throw new BWException(BWErrorType.CANNOT_ADD_TASK, "condition already used");
	}

	private void checkModelNotCompletelyCreated() {
		ConditionModel conditionModel = getSpecification().getConditionModel();
		if (!conditionModel.getEntityAchieveConditionSet().stream().anyMatch(c -> c.getTaskWithPostCondition() == null)
				&& !conditionModel.getAttributeAchieveConditionSet().stream()
						.anyMatch(c -> c.getTaskWithPostCondition() == null))
			throw new BWException(BWErrorType.CANNOT_ADD_TASK,
					"all achieve conditions already belong to a post condition");
	}

	private void checkNonEmptyPostCondition(Set<DefProductCondition> postConditionSet) {
		if (postConditionSet.isEmpty())
			throw new BWException(BWErrorType.CANNOT_ADD_TASK, "empty post condition set");
	}

	public boolean checkModel() {
		checkAllConditionsAreUsedInPost();

		return getTasksSet().stream().map(t -> t.checkConsistency()).reduce(true, (b1, b2) -> b1 && b2);
	}

	private void checkAllConditionsAreUsedInPost() {
		ConditionModel conditionModel = getSpecification().getConditionModel();

		Set<DefProductCondition> allDefConditions = new HashSet<DefProductCondition>(
				conditionModel.getEntityAchieveConditionSet().stream().filter(d -> !d.getEntity().getExists())
						.collect(Collectors.toSet()));

		allDefConditions.addAll(conditionModel.getAttributeAchieveConditionSet().stream()
				.filter(d -> !d.getAttributeOfDef().getEntity().getExists()).collect(Collectors.toSet()));
		allDefConditions.removeAll(
				getTasksSet().stream().flatMap(t -> t.getPostConditionSet().stream()).collect(Collectors.toSet()));
		if (!allDefConditions.isEmpty())
			throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED,
					allDefConditions.stream().map(c -> "DEF(" + c.getPath() + ")").collect(Collectors.joining(",")));

		Set<MulCondition> allMulConditions = new HashSet<MulCondition>(conditionModel.getEntityInvariantConditionSet());
		allMulConditions.removeAll(getTasksSet().stream().flatMap(t -> t.getMultiplicityInvariantSet().stream())
				.collect(Collectors.toSet()));
		if (!allMulConditions.isEmpty())
			throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED,
					allMulConditions.stream().map(c -> c.getSubPath()).collect(Collectors.joining(",")));

		Set<Rule> allRules = new HashSet<Rule>(conditionModel.getAttributeInvariantConditionSet());
		allRules.removeAll(
				getTasksSet().stream().flatMap(t -> t.getRuleInvariantSet().stream()).collect(Collectors.toSet()));
		if (!allRules.isEmpty())
			throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED,
					allRules.stream().map(r -> r.getName()).collect(Collectors.joining(",")));
	}

	private ConditionModel getConditionModel() {
		return getSpecification().getConditionModel();
	}

	private DataModel getDataModel() {
		return getSpecification().getDataModel();
	}

}

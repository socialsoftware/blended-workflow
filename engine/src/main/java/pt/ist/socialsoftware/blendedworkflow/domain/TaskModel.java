package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

	public Task getTaskPostConditionContains(String path) {
		Product product = getDataModel().getTargetOfPath(path);
		if (product.isEntityAndExists()) {
			return null;
		}

		for (Task task : getTasksSet()) {
			if (task.getPostConditionSet().stream().map(d -> d.getPath().getTarget()).anyMatch(p -> p == product)) {
				return task;
			}
		}

		throw new BWException(BWErrorType.TASK_UNKNOWN_POST_CONDITION_DEF, path);
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

		for (DefAttributeCondition defAttributeCondition : conditionModel.getAttributeAchieveConditionSet()) {
			HashSet<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>();
			postConditionSet.add(defAttributeCondition);
			addTask("a" + activityCounter, "Activity number " + activityCounter, postConditionSet);
			activityCounter++;
		}

		for (RelationBW relation : getDataModel().getRelationBWSet()) {
			applyMultiplicityToPostAndPre(relation);
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

		applyRulesToPost(task);

		return task;
	}

	public Task mergeTasks(String taskName, String taskDescription, Task taskOne, Task taskTwo) {
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>(taskOne.getPostConditionSet());
		postConditionSet.addAll(taskTwo.getPostConditionSet());

		taskOne.delete();
		taskTwo.delete();

		Task newTask = addTask(taskName, taskDescription, postConditionSet);

		Set<RelationBW> relations = postConditionSet.stream().map(d -> d.getTargetOfPath())
				.filter(Entity.class::isInstance).map(Entity.class::cast).flatMap(e -> e.getRelationSet().stream())
				.collect(Collectors.toSet());
		for (RelationBW relation : relations) {
			applyMultiplicityToPostAndPre(relation);
		}

		checkModel();

		// Task newTask = createTask(taskName, taskDescription);
		//
		// newTask.getPreConditionSet().addAll(taskOne.getPreConditionSet());
		// newTask.getPreConditionSet().addAll(taskTwo.getPreConditionSet());
		//
		// newTask.getPostConditionSet().addAll(taskOne.getPostConditionSet());
		// newTask.getPostConditionSet().addAll(taskTwo.getPostConditionSet());
		//
		// Set<Product> postProducts =
		// ConditionModel.getProductsOfDefConditions(newTask.getPostConditionSet());
		//
		// for (DefPathCondition defPathCondition :
		// newTask.getPreConditionSet()) {
		// if (postProducts.contains(defPathCondition.getPath().getTarget()))
		// newTask.removePreCondition(defPathCondition);
		// }
		//
		// newTask.getMultiplicityInvariantSet().addAll(taskOne.getMultiplicityInvariantSet());
		// newTask.getMultiplicityInvariantSet().addAll(taskTwo.getMultiplicityInvariantSet());
		//
		// newTask.getRuleInvariantSet().addAll(taskOne.getRuleInvariantSet());
		// newTask.getRuleInvariantSet().addAll(taskTwo.getRuleInvariantSet());
		//
		// taskOne.delete();
		// taskTwo.delete();

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

	private void applyRulesToPost(Task task) {
		for (Rule rule : getConditionModel().getAttributeInvariantConditionSet()) {
			Set<Attribute> ruleAttributes = rule.getAttributeSet();
			if (task.getPostConditionSet().stream().anyMatch(d -> ruleAttributes.contains(d.getTargetOfPath()))) {
				task.getRuleInvariantSet().add(rule);
			}
		}
	}

	private Set<Task> getRuleTasks(Rule rule) {
		Set<Attribute> ruleAttributes = rule.getAttributeSet();

		// logger.debug("getRuleTasks attributes:{}",
		// ruleAttributes.stream().map(a ->
		// a.getName()).collect(Collectors.joining(",")));

		Set<Task> ruleTasks = new HashSet<Task>();
		for (Task task : getTasksSet()) {
			if (task.getPostConditionSet().stream().anyMatch(d -> ruleAttributes.contains(d.getTargetOfPath()))) {
				// logger.debug("getRuleTasks task to add:{}", task.getName());
				ruleTasks.add(task);
			}
		}

		return ruleTasks;
	}

	private void applyMultiplicityToPostAndPre(RelationBW relation) {
		List<Task> tasks = relation.getEntitySet().stream()
				.map(e -> DefEntityCondition.getDefEntity(e).getTaskWithPostCondition()).filter(t -> t != null)
				.collect(Collectors.toList());

		// the relation has an exists entity
		if (tasks.size() == 1) {
			tasks.get(0).getMultiplicityInvariantSet().addAll(relation.getMulConditionSet());
			addPreConditionsDueToMulConditions(tasks.get(0), relation);
		} else { // the two entities in the relation do no exist
			Map<Task, Set<Task>> taskDependencies = getTaskDependencies();

			if (taskDependencies.get(tasks.get(0)).contains(tasks.get(1))
					&& taskDependencies.get(tasks.get(1)).contains(tasks.get(0))) {
				assert false : "there is already a circularity when applying multiplicities to the task model";
			} else if (taskDependencies.get(tasks.get(0)).contains(tasks.get(1))) {
				tasks.get(0).getMultiplicityInvariantSet().addAll(relation.getMulConditionSet());
				addPreConditionsDueToMulConditions(tasks.get(0), relation);
			} else if (taskDependencies.get(tasks.get(1)).contains(tasks.get(0))) {
				tasks.get(1).getMultiplicityInvariantSet().addAll(relation.getMulConditionSet());
				addPreConditionsDueToMulConditions(tasks.get(1), relation);
			} else {
				tasks.get(0).getMultiplicityInvariantSet().addAll(relation.getMulConditionSet());
				addPreConditionsDueToMulConditions(tasks.get(0), relation);
			}
		}

	}

	private void addPreConditionsDueToMulConditions(Task task, RelationBW relation) {
		relation.getEntitySet().stream()
				.filter(e -> !ConditionModel.getEntitiesOfDefConditionSet(task.getPostConditionSet()).contains(e))
				.forEach(e -> task.addPreCondition(
						DefPathCondition.getDefPathCondition(getSpecification(), relation.getPath(e))));
	}

	private void applyDependenceConditionsToPre(Task task) {
		Set<Product> postProducts = ConditionModel.getProductsOfDefConditions(task.getPostConditionSet());
		Set<Product> preProducts = ConditionModel.getProductsOfDefConditions(task.getPreConditionSet());

		getSpecification().getDataModel().getDependenceSet().stream()
				.filter(d -> postProducts.contains(d.getProduct()) && !postProducts.contains(d.getTarget())
						&& !preProducts.contains(d.getTarget()))
				.forEach(d -> task.addPreCondition(
						DefPathCondition.getDefPathCondition(getSpecification(), d.getPath().getValue())));

	}

	private void applyAttributeEntityDependenceToPre(Task task) {
		Set<DefProductCondition> postConditionSet = new HashSet<DefProductCondition>(task.getPostConditionSet());
		ConditionModel.getDefAttributeConditions(postConditionSet).stream()
				.filter(def -> !ConditionModel.getEntitiesOfDefConditionSet(postConditionSet)
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

	public void checkModel() {
		checkAllConditionsAreUsedInPost();

		getTasksSet().stream().forEach(t -> t.checkConsistency());

		checkCycles();
		checkRules();
	}

	private void checkRules() {
		for (Rule rule : getConditionModel().getAttributeInvariantConditionSet()) {
			if (!getRuleTasks(rule).stream().allMatch(t -> t.getRuleInvariantSet().contains(rule))) {
				throw new BWException(BWErrorType.INCONSISTENT_RULE_CONDITION, rule.getName());
			}
		}
	}

	private void checkCycles() {
		Map<Task, Set<Task>> taskDependencies = getTaskDependencies();

		checkCycles(taskDependencies);
	}

	private void checkCycles(Map<Task, Set<Task>> taskDependencies) {
		for (Task task : taskDependencies.keySet()) {
			task.checkCycles(taskDependencies);
		}
	}

	public Map<Task, Set<Task>> getTaskDependencies() {
		Map<Task, Set<Task>> taskDependencies = new HashMap<Task, Set<Task>>();
		for (Task task : getTasksSet()) {
			Set<Task> tasks = task.getPreConditionSet().stream().flatMap(d -> d.getPath().getProductsInPath().stream())
					.map(p -> p.getDefCondition()).filter(d -> d.getTaskWithPostCondition() != null)
					.map(d -> d.getTaskWithPostCondition()).filter(t -> t != task).collect(Collectors.toSet());

			taskDependencies.put(task, tasks);
		}

		logger.debug("getTaskDependencies {}",
				taskDependencies.entrySet().stream()
						.map(e -> e.getKey().getName() + ":"
								+ e.getValue().stream().map(t -> t.getName()).collect(Collectors.joining(",")))
				.collect(Collectors.joining(";")));
		return taskDependencies;
	}

	private void checkAllConditionsAreUsedInPost() {
		ConditionModel conditionModel = getConditionModel();

		Set<DefProductCondition> allDefConditions = new HashSet<DefProductCondition>(
				conditionModel.getEntityAchieveConditionSet().stream().filter(d -> !d.getEntity().getExists())
						.collect(Collectors.toSet()));

		allDefConditions.addAll(conditionModel.getAttributeAchieveConditionSet().stream()
				.filter(d -> !d.getAttributeOfDef().getEntity().getExists()).collect(Collectors.toSet()));
		allDefConditions.removeAll(
				getTasksSet().stream().flatMap(t -> t.getPostConditionSet().stream()).collect(Collectors.toSet()));
		if (!allDefConditions.isEmpty())
			throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, allDefConditions.stream()
					.map(c -> "DEF(" + c.getPath().getValue() + ")").collect(Collectors.joining(",")));

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

package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.ArrayList;
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
import pt.ist.socialsoftware.blendedworkflow.service.dto.ActivityGraphDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EdgeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.NodeDTO;

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

		Set<DefPathCondition> sequenceConditions = new HashSet<DefPathCondition>(taskOne.getSequenceConditionSet());
		sequenceConditions.addAll(taskTwo.getSequenceConditionSet());

		Set<Product> products = postConditionSet.stream().map(d -> d.getTargetOfPath()).collect(Collectors.toSet());

		Set<String> sequenceConditionValues = sequenceConditions.stream()
				.filter(d -> !products.contains(d.getTargetOfPath())).map(d -> d.getPath().getValue())
				.collect(Collectors.toSet());

		taskOne.delete();
		taskTwo.delete();

		Task newTask = addTask(taskName, taskDescription, postConditionSet);
		sequenceConditionValues.stream().forEach(
				v -> newTask.addSequenceCondition(DefPathCondition.getDefPathCondition(getSpecification(), v)));

		for (RelationBW relation : getDataModel().getRelationBWSet()) {
			applyMultiplicityToPostAndPre(relation);
		}

		checkModel();

		return newTask;
	}

	public Task extractTask(Task fromTask, String taskName, String taskDescription,
			Set<DefProductCondition> postConditionSet) {
		if (postConditionSet.size() == 0 || fromTask.getPostConditionSet().size() - postConditionSet.size() <= 0
				|| fromTask.getPostConditionSet().containsAll(postConditionSet)) {
			new BWException(BWErrorType.CANNOT_EXTRACT_ACTIVITY, fromTask.getName());
		}

		String fromTaskName = fromTask.getName();
		String fromTaskDescription = fromTask.getDescription();
		Set<DefProductCondition> fromTaskPostCondition = new HashSet<DefProductCondition>(
				fromTask.getPostConditionSet());
		fromTaskPostCondition.removeAll(postConditionSet);

		Set<String> sequenceConditionValues = fromTask.getSequenceConditionSet().stream()
				.map(d -> d.getPath().getValue()).collect(Collectors.toSet());

		fromTask.delete();

		Task newFromTask = addTask(fromTaskName, fromTaskDescription, fromTaskPostCondition);

		Task newExtratedTask = addTask(taskName, taskDescription, postConditionSet);

		for (String path : sequenceConditionValues) {
			if (newFromTask.getPostConditionProducts().contains(getDataModel().getSourceOfPath(path))) {
				newFromTask.addSequenceCondition(DefPathCondition.getDefPathCondition(getSpecification(), path));
			} else {
				newExtratedTask.addSequenceCondition(DefPathCondition.getDefPathCondition(getSpecification(), path));
			}
		}

		for (RelationBW relation : getDataModel().getRelationBWSet()) {
			applyMultiplicityToPostAndPre(relation);
		}

		checkModel();

		return newExtratedTask;
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
				// it can occur when a merge or a split is tried
				throw new BWException(BWErrorType.DEPENDENCE_CIRCULARITY,
						tasks.get(0).getName() + "--" + tasks.get(1).getName());
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

		Set<Dependence> dependencies = getDefPathDependencies();

		dependencies.stream()
				.filter(d -> postProducts.contains(d.getProduct()) && !postProducts.contains(d.getTarget())
						&& !preProducts.contains(d.getTarget()))
				.forEach(d -> task.addPreCondition(
						DefPathCondition.getDefPathCondition(getSpecification(), d.getPath().getValue())));
	}

	private Set<Dependence> getDefPathDependencies() {
		Set<Dependence> dependencies = new HashSet<Dependence>(getConditionModel().getEntityDependenceConditionSet());
		dependencies.addAll(getConditionModel().getAttributeDependenceConditionSet());
		return dependencies;
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
			Set<Task> tasks = task.getExecutionDependencies().stream()
					.flatMap(d -> d.getPath().getProductsInPath().stream()).map(p -> p.getDefCondition())
					.filter(d -> d.getTaskWithPostCondition() != null).map(d -> d.getTaskWithPostCondition())
					.filter(t -> t != task).collect(Collectors.toSet());

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

	public ActivityGraphDTO getActivityGraph() {
		ActivityGraphDTO graph = new ActivityGraphDTO();

		Map<Task, Set<Task>> dependencies = getTaskDependencies();

		List<NodeDTO> nodes = new ArrayList<NodeDTO>();
		for (Task task : dependencies.keySet()) {
			String description = "PRE(" + task.getPreConditionSet().stream().map(d -> d.getPath().getValue())
					.collect(Collectors.joining(",")) + ")";
			if (!task.getSequenceConditionSet().isEmpty()) {
				description = description + ", " + "SEQ(" + task.getSequenceConditionSet().stream()
						.map(d -> d.getPath().getValue()).collect(Collectors.joining(",")) + ")";
			}
			if (!task.getPostConditionSet().isEmpty()) {
				description = description + ", " + "POST(" + task.getPostConditionSet().stream()
						.map(d -> d.getPath().getValue()).collect(Collectors.joining(",")) + ")";
			}
			if (!task.getMultiplicityInvariantSet().isEmpty()) {
				description = description + ", " + "MUL("
						+ task.getMultiplicityInvariantSet().stream().map(m -> m.getSourceEntity().getName() + "."
								+ m.getTargetRolename() + "," + m.getTargetCardinality().name())
								.collect(Collectors.joining(";"))
						+ ")";
			}
			if (!task.getRuleInvariantSet().isEmpty()) {
				description = description + ", " + "RULE("
						+ task.getRuleInvariantSet().stream().map(r -> r.getName()).collect(Collectors.joining(","))
						+ ")";
			}

			nodes.add(new NodeDTO(task.getExternalId(), task.getName(), description));
		}

		List<EdgeDTO> edges = new ArrayList<EdgeDTO>();
		for (Task task : dependencies.keySet()) {
			dependencies.get(task).stream()
					.forEach(t -> edges.add(new EdgeDTO(t.getExternalId(), task.getExternalId())));
		}

		graph.setNodes(nodes.stream().toArray(NodeDTO[]::new));
		graph.setEdges(edges.stream().toArray(EdgeDTO[]::new));

		return graph;
	}

	private ConditionModel getConditionModel() {
		return getSpecification().getConditionModel();
	}

	private DataModel getDataModel() {
		return getSpecification().getDataModel();
	}

}

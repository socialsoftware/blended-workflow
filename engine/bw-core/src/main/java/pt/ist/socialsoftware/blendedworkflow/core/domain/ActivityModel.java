package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EdgeDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GraphDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.NodeDTO;

public class ActivityModel extends ActivityModel_Base {
	private static Logger logger = LoggerFactory.getLogger(ActivityModel.class);

	public Activity createActivity(String name, String description) {
		return new Activity(this, name, description);
	}

	public Activity getActivity(String name) throws BWException {
		for (Activity activity : getActivitySet()) {
			if (activity.getName().equals(name)) {
				return activity;
			}
		}
		throw new BWException(BWErrorType.NON_EXISTENT_ACTIVITY_NAME, name);
	}

	public void clean() {
		getActivitySet().stream().forEach(t -> t.delete());
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
				HashSet<DefProductCondition> postConditionSet = new HashSet<>();
				postConditionSet.add(defEntityCondition);
				String name = postConditionSet.stream().map(d -> d.getPath().getValue())
						.collect(Collectors.joining(","));
				addActivity(name, "Activity number: " + activityCounter, postConditionSet);
				activityCounter++;
			}
		}

		for (DefAttributeCondition defAttributeCondition : conditionModel.getAttributeAchieveConditionSet()) {
			HashSet<DefProductCondition> postConditionSet = new HashSet<>();
			postConditionSet.add(defAttributeCondition);
			String name = postConditionSet.stream().map(d -> d.getPath().getValue()).collect(Collectors.joining(","));
			addActivity(name, "Activity number: " + activityCounter, postConditionSet);
			activityCounter++;
		}

		applyMultipicityConditions();
		applyRules();

		checkModel();
	}

	private void applyRuleDueToDependencies(Rule rule) {
		Map<Activity, Set<Activity>> activitySequences = getActivitySequences();
		Set<Activity> ruleActivities = getRuleActivities(rule);

		for (Activity activity : ruleActivities) {
			Set<Activity> reachable = new HashSet<>();
			reachable.add(activity);
			reachable = activitiesReachableFrom(reachable, activitySequences, new HashSet<Activity>());

			reachable.remove(activity);
			logger.debug("applyRuleDueToDependencies rule:{}, disjoint:{}", rule.getName(),
					Collections.disjoint(reachable, ruleActivities));
			if (Collections.disjoint(reachable, ruleActivities)) {
				// it is the last in the sequence that sets the attributes in
				// the rule
				activity.addRuleInvariant(rule);
			} else {
				// another activity, setting one of the attributes, occurs after
				activity.removeRuleInvariant(rule);
			}
		}

	}

	public Activity addActivity(String activityName, String activityDescription,
			Set<DefProductCondition> postConditionSet) {
		checkNonEmptyPostCondition(postConditionSet);

		checkModelNotCompletelyCreated();

		checkPostConditionsNotUsed(postConditionSet);

		Activity activity = createActivity(activityName, activityDescription);
		postConditionSet.stream().forEach(c -> activity.addPostCondition(c));

		applyAttributeEntityDependenceToPre(activity);

		applyDependenceConditionsToPre(activity);

		return activity;
	}

	public Activity mergeActivities(String activityName, String activityDescription, Activity activityOne,
			Activity activityTwo) {
		Set<DefProductCondition> postConditionSet = new HashSet<>(activityOne.getPostConditionSet());
		postConditionSet.addAll(activityTwo.getPostConditionSet());

		Set<DefPathCondition> sequenceConditions = new HashSet<>(activityOne.getSequenceConditionSet());
		sequenceConditions.addAll(activityTwo.getSequenceConditionSet());

		Set<Product> products = postConditionSet.stream().map(d -> d.getTargetOfPath()).collect(Collectors.toSet());

		Set<String> sequenceConditionValues = sequenceConditions.stream()
				.filter(d -> !products.contains(d.getTargetOfPath())).map(d -> d.getPath().getValue())
				.collect(Collectors.toSet());

		activityOne.delete();
		activityTwo.delete();

		Activity newActivity = addActivity(activityName, activityDescription, postConditionSet);
		sequenceConditionValues.stream().forEach(
				v -> newActivity.addSequenceCondition(DefPathCondition.getDefPathCondition(getSpecification(), v)));

		applyMultipicityConditions();
		applyRules();

		checkModel();

		return newActivity;
	}

	public Activity extractActivity(Activity fromActivity, String activityName, String activityDescription,
			Set<DefProductCondition> postConditionSet) {
		if (postConditionSet.size() == 0 || fromActivity.getPostConditionSet().size() - postConditionSet.size() <= 0
				|| !fromActivity.getPostConditionSet().containsAll(postConditionSet)) {
			throw new BWException(BWErrorType.CANNOT_EXTRACT_ACTIVITY, fromActivity.getName());
		}

		String fromActivityName = fromActivity.getName();
		String fromActivityDescription = fromActivity.getDescription();
		Set<DefProductCondition> fromActivityPostCondition = new HashSet<>(fromActivity.getPostConditionSet());
		fromActivityPostCondition.removeAll(postConditionSet);

		Set<String> sequenceConditionValues = fromActivity.getSequenceConditionSet().stream()
				.map(d -> d.getPath().getValue()).collect(Collectors.toSet());

		fromActivity.delete();

		Activity newFromActivity = addActivity(fromActivityName, fromActivityDescription, fromActivityPostCondition);

		Activity newExtractedActivity = addActivity(activityName, activityDescription, postConditionSet);

		for (String path : sequenceConditionValues) {
			if (newFromActivity.getPostProducts().contains(getDataModel().getSourceOfPath(path))) {
				newFromActivity.addSequenceCondition(DefPathCondition.getDefPathCondition(getSpecification(), path));
			} else {
				newExtractedActivity
						.addSequenceCondition(DefPathCondition.getDefPathCondition(getSpecification(), path));
			}
		}

		// if necessary define an order such that the extracted activity occurs
		// after the from activity
		Map<Activity, Set<Activity>> activitySequences = getActivitySequences();
		if (!activitySequences.get(newFromActivity).contains(newExtractedActivity)
				&& !activitySequences.get(newExtractedActivity).contains(newFromActivity)) {

			Set<Entity> fromPostEntities = newFromActivity.getPostEntities();
			Set<Entity> extractedPostEntities = newExtractedActivity.getPostEntities();
			extractedPostEntities.stream().flatMap(e -> e.getRelationSet().stream())
					.filter(r -> !Collections.disjoint(r.getEntitySet(), fromPostEntities))
					.forEach(r -> newExtractedActivity.applyMultiplicities(r));
		}

		applyMultipicityConditions();
		applyRules();

		checkModel();

		return newExtractedActivity;
	}

	private Set<Activity> getRuleActivities(Rule rule) {
		Set<Attribute> ruleAttributes = rule.getAttributeSet();

		Set<Activity> ruleActivities = new HashSet<>();
		for (Activity activity : getActivitySet()) {
			if (activity.getPostConditionSet().stream().anyMatch(d -> ruleAttributes.contains(d.getTargetOfPath()))) {
				ruleActivities.add(activity);
			}
		}

		return ruleActivities;
	}

	public void applyMultipicityConditions() {
		for (RelationBW relation : getDataModel().getRelationBWSet()) {
			applyMultiplicityToPostAndPre(relation);
		}
	}

	public void applyRules() {
		for (Rule rule : getDataModel().getRules()) {
			applyRuleDueToDependencies(rule);
		}
	}

	private void applyMultiplicityToPostAndPre(RelationBW relation) {
		List<Activity> activities = relation.getEntitySet().stream()
				.map(e -> DefEntityCondition.getDefEntityCondition(e).getActivityWithPostCondition())
				.filter(t -> t != null).collect(Collectors.toList());

		// the relation has an exists entity
		if (activities.size() == 1) {
			activities.get(0).applyMultiplicities(relation);
		} else { // the two entities in the relation do no exist
			Map<Activity, Set<Activity>> activitiesSequences = getActivitySequences();

			if (activitiesSequences.get(activities.get(0)).contains(activities.get(1))
					&& activitiesSequences.get(activities.get(1)).contains(activities.get(0))) {
				// it can occur when a merge or a split is tried
				throw new BWException(BWErrorType.DEPENDENCE_CIRCULARITY,
						activities.get(0).getName() + "--" + activities.get(1).getName());
			} else if (activitiesSequences.get(activities.get(0)).contains(activities.get(1))) {
				activities.get(1).applyMultiplicities(relation);
			} else if (activitiesSequences.get(activities.get(1)).contains(activities.get(0))) {
				activities.get(0).applyMultiplicities(relation);
			} else {
				activities.get(1).applyMultiplicities(relation);
			}
		}

	}

	private void applyDependenceConditionsToPre(Activity activity) {
		Set<Product> postProducts = activity.getPostProducts();
		Set<Product> preProducts = activity.getPreProducts();

		Set<Dependence> dependencies = getDefPathDependencies();

		dependencies.stream()
				.filter(d -> postProducts.contains(d.getProduct()) && !postProducts.contains(d.getTarget())
						&& !preProducts.contains(d.getTarget()))
				.forEach(d -> activity.addPreCondition(
						DefPathCondition.getDefPathCondition(getSpecification(), d.getPath().getValue())));
	}

	private Set<Dependence> getDefPathDependencies() {
		Set<Dependence> dependencies = new HashSet<>(getConditionModel().getEntityDependenceConditionSet());
		dependencies.addAll(getConditionModel().getAttributeDependenceConditionSet());
		return dependencies;
	}

	private void applyAttributeEntityDependenceToPre(Activity activity) {
		Set<DefProductCondition> postConditionSet = new HashSet<>(activity.getPostConditionSet());
		ConditionModel.getDefAttributeConditions(postConditionSet).stream()
				.filter(def -> !activity.getPostEntities().contains(def.getAttributeOfDef().getEntity()))
				.forEach(def -> activity.addPreCondition(DefPathCondition.getDefPathCondition(getSpecification(),
						def.getAttributeOfDef().getEntity().getName())));
	}

	private void checkPostConditionsNotUsed(Set<DefProductCondition> postConditionSet) {
		if (getActivitySet().stream().flatMap(t -> t.getPostConditionSet().stream())
				.anyMatch(c -> postConditionSet.contains(c))) {
			throw new BWException(BWErrorType.CANNOT_ADD_TASK, "condition already used");
		}
	}

	private void checkModelNotCompletelyCreated() {
		ConditionModel conditionModel = getSpecification().getConditionModel();
		if (!conditionModel.getEntityAchieveConditionSet().stream()
				.anyMatch(c -> c.getActivityWithPostCondition() == null)
				&& !conditionModel.getAttributeAchieveConditionSet().stream()
						.anyMatch(c -> c.getActivityWithPostCondition() == null)) {
			throw new BWException(BWErrorType.CANNOT_ADD_TASK,
					"all achieve conditions already belong to a post condition");
		}
	}

	private void checkNonEmptyPostCondition(Set<DefProductCondition> postConditionSet) {
		if (postConditionSet.isEmpty()) {
			throw new BWException(BWErrorType.CANNOT_ADD_TASK, "empty post condition set");
		}
	}

	public void checkModel() {
		checkModelCompleteness();

		checkModelConsistency();

		checkCircularities();
	}

	public void checkModelConsistency() {
		getActivitySet().stream().forEach(t -> t.checkConsistency());
	}

	private void checkCircularities() {
		checkCycles(getActivitySequences());
	}

	private void checkCycles(Map<Activity, Set<Activity>> activityDependencies) {
		for (Activity activity : activityDependencies.keySet()) {
			activity.checkCycles(activityDependencies);
		}
	}

	public Map<Activity, Set<Activity>> getActivitySequences() {
		Map<Activity, Set<Activity>> activitySequencies = new HashMap<>();
		getActivitySet().stream().forEach(t -> activitySequencies.put(t, new HashSet<Activity>()));

		for (Activity activity : getActivitySet()) {
			activity.getExecutionDependencies().stream().flatMap(d -> d.getPath().getProductsInPath().stream())
					.map(p -> p.getDefCondition()).filter(d -> d.getActivityWithPostCondition() != null)
					.map(d -> d.getActivityWithPostCondition()).filter(t -> t != activity)
					.forEach(t -> activitySequencies.get(t).add(activity));
		}

		logger.debug("getActivityDependencies {}",
				activitySequencies.entrySet().stream()
						.map(e -> e.getKey().getName() + ":"
								+ e.getValue().stream().map(t -> t.getName()).collect(Collectors.joining(",")))
						.collect(Collectors.joining(";")));

		return activitySequencies;
	}

	private void checkModelCompleteness() {
		checkAllProductionConditionsAreApplied();

		// TODO
		// checkAllDependenceConditionsAreApplied();

		checkAllMultiplicityConditionsAreApplied();

		checkAllRulesAreApplied();
	}

	public void checkAllRulesAreApplied() {
		ConditionModel conditionModel = getConditionModel();

		Set<Rule> allRules = new HashSet<>(conditionModel.getAttributeInvariantConditionSet());
		allRules.removeAll(
				getActivitySet().stream().flatMap(t -> t.getRuleInvariantSet().stream()).collect(Collectors.toSet()));
		if (!allRules.isEmpty()) {
			throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED,
					allRules.stream().map(r -> r.getName()).collect(Collectors.joining(",")));
		}
	}

	public void checkAllMultiplicityConditionsAreApplied() {
		ConditionModel conditionModel = getConditionModel();

		Set<MulCondition> allMulConditions = new HashSet<>(conditionModel.getEntityInvariantConditionSet());
		allMulConditions.removeAll(getActivitySet().stream().flatMap(t -> t.getMultiplicityInvariantSet().stream())
				.collect(Collectors.toSet()));
		if (!allMulConditions.isEmpty()) {
			throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED,
					allMulConditions.stream().map(c -> c.getSubPath()).collect(Collectors.joining(",")));
		}
	}

	private void checkAllProductionConditionsAreApplied() {
		ConditionModel conditionModel = getConditionModel();

		Set<DefProductCondition> allDefConditions = conditionModel.getAllProductionConditions();

		allDefConditions.removeAll(
				getActivitySet().stream().flatMap(t -> t.getPostConditionSet().stream()).collect(Collectors.toSet()));
		if (!allDefConditions.isEmpty()) {
			throw new BWException(BWErrorType.NOT_ALL_CONDITIONS_APPLIED, allDefConditions.stream()
					.map(c -> "DEF(" + c.getPath().getValue() + ")").collect(Collectors.joining(",")));
		}
	}

	public GraphDTO getActivityGraph() {
		GraphDTO graph = new GraphDTO();

		Map<Activity, Set<Activity>> activitySequences = getActivitySequences();

		removeRedundantTransitiveSequences(activitySequences);

		List<NodeDTO> nodes = new ArrayList<>();
		for (Activity activity : activitySequences.keySet()) {
			String description = "PRE(" + activity.getPreConditionSet().stream().map(d -> d.getPath().getValue())
					.collect(Collectors.joining(",")) + ")";
			if (!activity.getSequenceConditionSet().isEmpty()) {
				description = description + ", " + "SEQ(" + activity.getSequenceConditionSet().stream()
						.map(d -> d.getPath().getValue()).collect(Collectors.joining(",")) + ")";
			}
			if (!activity.getPostConditionSet().isEmpty()) {
				description = description + ", " + "POST(" + activity.getPostConditionSet().stream()
						.map(d -> d.getPath().getValue()).collect(Collectors.joining(",")) + ")";
			}
			if (!activity.getMultiplicityInvariantSet().isEmpty()) {
				description = description + ", " + "MUL(" + activity.getMultiplicityInvariantSet().stream().map(
						m -> m.getSourceEntity().getName() + "." + m.getRolename() + "," + m.getCardinality().getExp())
						.collect(Collectors.joining(";")) + ")";
			}
			if (!activity.getRuleInvariantSet().isEmpty()) {
				description = description + ", " + "RULE("
						+ activity.getRuleInvariantSet().stream().map(r -> r.getName()).collect(Collectors.joining(","))
						+ ")";
			}

			nodes.add(new NodeDTO(activity.getExternalId(), activity.getName(), description));
		}

		List<EdgeDTO> edges = new ArrayList<>();
		for (Activity activity : activitySequences.keySet()) {
			activitySequences.get(activity).stream()
					.forEach(t -> edges.add(new EdgeDTO(activity.getExternalId(), t.getExternalId())));
		}

		graph.setNodes(nodes.stream().toArray(NodeDTO[]::new));
		graph.setEdges(edges.stream().toArray(EdgeDTO[]::new));

		return graph;
	}

	private void removeRedundantTransitiveSequences(Map<Activity, Set<Activity>> sequences) {
		for (Activity activityKey : sequences.keySet()) {
			Set<Activity> transitiveSequences = getTransitiveSequences(activityKey, sequences);
			Set<Activity> activitiesToRemove = new HashSet<>();
			for (Activity activityValue : sequences.get(activityKey)) {
				if (transitiveSequences.contains(activityValue)) {
					activitiesToRemove.add(activityValue);
				}
			}
			sequences.get(activityKey).removeAll(activitiesToRemove);
		}
	}

	private Set<Activity> getTransitiveSequences(Activity activity, Map<Activity, Set<Activity>> sequences) {
		Set<Activity> transitiveSequences = new HashSet<>();
		for (Activity nextActivity : sequences.get(activity)) {
			transitiveSequences.addAll(sequences.get(nextActivity));
		}

		Set<Activity> visited = new HashSet<>();
		visited.add(activity);
		return activitiesReachableFrom(transitiveSequences, sequences, visited);
	}

	private Set<Activity> activitiesReachableFrom(Set<Activity> reachableActivities,
			Map<Activity, Set<Activity>> sequences, Set<Activity> visited) {
		Set<Activity> activitiesToAdd = new HashSet<>();
		for (Activity activity : reachableActivities) {
			if (!visited.contains(activity)) {
				activitiesToAdd.addAll(sequences.get(activity));
				visited.add(activity);
			}
		}
		reachableActivities.addAll(activitiesToAdd);

		if (activitiesToAdd.isEmpty()) {
			return reachableActivities;
		} else {
			return activitiesReachableFrom(reachableActivities, sequences, visited);
		}

	}

	private ConditionModel getConditionModel() {
		return getSpecification().getConditionModel();
	}

	private DataModel getDataModel() {
		return getSpecification().getDataModel();
	}

}

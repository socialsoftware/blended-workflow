package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EdgeDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EdgeVisDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GraphDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GraphVisDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.NodeDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.NodeVisDto;

public class GoalModel extends GoalModel_Base {
	private static Logger logger = LoggerFactory.getLogger(GoalModel.class);

	public boolean existsGoal(String name) {
		return getGoalSet().stream().anyMatch(goal -> goal.getName().equals(name));
	}

	public Goal getGoal(String name) throws BWException {
		for (Goal goal : getGoalSet()) {
			if (goal.getName().equals(name)) {
				return goal;
			}
		}
		throw new BWException(BWErrorType.NON_EXISTENT_GOAL_NAME, name);
	}

	public void clean() {
		getGoalSet().stream().forEach(goal -> goal.delete());
	}

	public void delete() {
		clean();
		setSpecification(null);

		deleteDomainObject();
	}

	public void generateGoals() {
		ConditionModel conditionModel = getSpecification().getConditionModel();

		for (DefEntityCondition defEntityCondition : conditionModel.getEntityAchieveConditionSet()) {
			if (!defEntityCondition.getEntity().getExists()) {
				createGoalForEntity(defEntityCondition);
			}
		}

		for (DefAttributeCondition defAttributeCondition : conditionModel.getAttributeAchieveConditionSet()) {
			if (!defAttributeCondition.getAttributeOfDef().getEntity().getExists()) {
				createGoalForAttribute(defAttributeCondition);
			}
		}

		for (RelationBW relation : getSpecification().getDataModel().getRelationBWSet()) {
			createGoalForRelation(relation);
		}

		checkModel();
	}

	private void createGoalForEntity(DefEntityCondition defEntityCondition) {
		Set<DefProductCondition> defProductConditionSet = new HashSet<DefProductCondition>();
		defProductConditionSet.add(defEntityCondition);
		ProductGoal entityGoal = new ProductGoal(this, defEntityCondition.getPath().getValue(), defProductConditionSet);
		entityGoal.initProductGoal();
	}

	private void createGoalForAttribute(DefAttributeCondition defAttributeCondition) {
		Set<DefProductCondition> defProductConditions = new HashSet<>();
		defProductConditions.add(defAttributeCondition);
		ProductGoal attributeGoal = new ProductGoal(this, defAttributeCondition.getPath().getValue(),
				defProductConditions);
		attributeGoal.initProductGoal();
	}

	private void createGoalForRelation(RelationBW relation) {
		AssociationGoal relationGoal = new AssociationGoal(this, relation.getName(), relation.getMulConditionSet());
		relationGoal.initAssociationGoal();
	}

	public Goal mergeGoals(String newGoalName, Goal goalOne, Goal goalTwo) {
		Goal result = null;

		if (goalOne.getClass() != goalTwo.getClass()) {
			throw new BWException(BWErrorType.UNMERGEABLE_GOALS, goalOne.getName() + " - " + goalTwo.getName());
		} else if (goalOne instanceof ProductGoal) {
			result = mergeProductGoals(newGoalName, goalOne, goalTwo);
		} else {
			result = mergeAssociationGoals(newGoalName, goalOne, goalTwo);
		}

		checkModel();

		return result;
	}

	private Goal mergeProductGoals(String newGoalName, Goal goalOne, Goal goalTwo) {
		String tmpName = goalOne.getName() + "-" + goalTwo.getName();
		while (existsGoal(tmpName)) {
			tmpName = tmpName + ".1";
		}

		ProductGoal newGoal = new ProductGoal(this, tmpName,
				Stream.concat(goalOne.getSuccessConditionSet().stream(), goalTwo.getSuccessConditionSet().stream())
						.collect(Collectors.toSet()));

		goalOne.delete();
		goalTwo.delete();

		newGoal.setName(newGoalName);

		newGoal.initProductGoal();

		return newGoal;
	}

	private Goal mergeAssociationGoals(String newGoalName, Goal goalOne, Goal goalTwo) {
		String tmpName = goalOne.getName() + "-" + goalTwo.getName();
		while (existsGoal(tmpName)) {
			tmpName = tmpName + ".1";
		}

		AssociationGoal newGoal = new AssociationGoal(this, tmpName,
				Stream.concat(goalOne.getEntityInvariantConditionSet().stream(),
						goalTwo.getEntityInvariantConditionSet().stream()).collect(Collectors.toSet()));

		goalOne.delete();
		goalTwo.delete();

		newGoal.setName(newGoalName);

		newGoal.initAssociationGoal();

		return newGoal;
	}

	public ProductGoal extractProductGoal(ProductGoal goal, String newGoalName,
			Set<DefProductCondition> successConditions) {
		checkDefProductConditionsNotEmpty(successConditions);
		checkNotAllDefProductConditionsAreSelected(goal.getSuccessConditionSet(), successConditions);
		goal.checkDefProductConditionsExistSucc(successConditions);
		goal.shrinkProductGoal(successConditions);

		ProductGoal newGoal = new ProductGoal(this, newGoalName, successConditions);

		goal.initProductGoal();
		newGoal.initProductGoal();

		checkModel();

		return newGoal;
	}

	public AssociationGoal extractAssociationGoal(AssociationGoal goal, String newGoalName,
			Set<MulCondition> mulConditionSet) {
		checkMulConditionsNotEmpty(mulConditionSet);
		checkNotAllMulConditionsAreSelected(goal.getEntityInvariantConditionSet(), mulConditionSet);
		goal.checkMulConditionsExistInv(mulConditionSet);
		goal.shrinkAssociationGoal(mulConditionSet);

		AssociationGoal newGoal = new AssociationGoal(this, newGoalName, mulConditionSet);

		goal.initAssociationGoal();
		newGoal.initAssociationGoal();

		checkModel();

		return newGoal;
	}

	public void checkModel() {
		checkModelCompleteness();
		checkModelConsistency();
	}

	public void checkModelCompleteness() {
		checkAllDefProductConditionsAreApplied();

		checkAllMulConditionsAreApplied();

		checkAllDependenceConditionsAreApplied();

		checkAllRulesAreApplied();
	}

	private void checkAllDependenceConditionsAreApplied() {
		for (Dependence dependence : getSpecification().getConditionModel().getAttributeDependenceConditionSet()) {
			if (getGoalWithDependence(dependence) == null) {
				throw new BWException(BWErrorType.INCONSISTENT_GOALMODEL,
						"not all Dependencies are applied " + dependence.getPath().getValue());
			}
		}
	}

	private void checkAllRulesAreApplied() {
		for (Rule rule : getSpecification().getConditionModel().getAttributeInvariantConditionSet()) {
			if (getGoalWithRule(rule).isEmpty()) {
				throw new BWException(BWErrorType.INCONSISTENT_GOALMODEL, "not all Rules are applied");
			}
		}
	}

	private void checkAllMulConditionsAreApplied() {
		for (MulCondition mulCondition : getSpecification().getConditionModel().getEntityInvariantConditionSet()) {
			if (getGoalWithMulCondition(mulCondition) == null) {
				throw new BWException(BWErrorType.INCONSISTENT_GOALMODEL, "not all MulConditions are applied");
			}
		}
	}

	private void checkAllDefProductConditionsAreApplied() {
		for (DefProductCondition defProductCondition : getSpecification().getConditionModel()
				.getAllProductionConditions()) {
			if (getGoalWithDefProductCondition(defProductCondition) == null) {
				throw new BWException(BWErrorType.INCONSISTENT_GOALMODEL, "not all DefProductConditions are applied");
			}
		}
	}

	private Goal getGoalWithDependence(Dependence dependence) {
		for (Goal goal : getGoalSet()) {
			if (goal.hasDependence(dependence)) {
				return goal;
			}
		}
		return null;
	}

	public Set<Goal> getGoalWithRule(Rule rule) {
		Set<Goal> result = new HashSet<>();
		for (Goal goal : getGoalSet()) {
			if (goal.hasRule(rule)) {
				result.add(goal);
			}
		}
		return result;
	}

	public Goal getGoalWithDefProductCondition(DefProductCondition defProductCondition) {
		for (Goal goal : getGoalSet()) {
			if (goal.hasDefProductCondition(defProductCondition)) {
				return goal;
			}
		}
		return null;
	}

	public Goal getGoalWithMulCondition(MulCondition mulCondition) {
		for (Goal goal : getGoalSet()) {
			if (goal.hasMulCondition(mulCondition)) {
				return goal;
			}
		}
		return null;
	}

	public void checkModelConsistency() {
		checkGoalsType();
		checkGoalsActivationConditions();
		checkGoalsCircularities();
	}

	private void checkGoalsCircularities() {
		checkCycles(getGoalsSequences());
	}

	private void checkCycles(Map<Goal, Set<Goal>> goalDependencies) {
		for (Goal goal : goalDependencies.keySet()) {
			goal.checkCycles(goalDependencies);
		}
	}

	public Map<Goal, Set<Goal>> getGoalsSequences() {
		Set<ProductGoal> goals = getGoalSet().stream().filter(ProductGoal.class::isInstance)
				.map(ProductGoal.class::cast).collect(Collectors.toSet());

		Map<Goal, Set<Goal>> goalSequencies = new HashMap<>();

		goals.stream().forEach(t -> goalSequencies.put(t, new HashSet<Goal>()));

		for (Goal goal : goals) {
			goal.getActivationConditionSet().stream().flatMap(d -> d.getPath().getProductsInPath().stream())
					.map(p -> p.getDefCondition()).filter(d -> d.getSuccessConditionGoal() != null)
					.map(d -> d.getSuccessConditionGoal()).filter(g -> g != goal)
					.forEach(g -> goalSequencies.get(g).add(goal));
		}

		logger.debug("getGoalDependencies {}",
				goalSequencies.entrySet().stream()
						.map(e -> e.getKey().getName() + ":"
								+ e.getValue().stream().map(t -> t.getName()).collect(Collectors.joining(",")))
						.collect(Collectors.joining(";")));

		return goalSequencies;
	}

	private void checkGoalsType() {
		for (Goal goal : getGoalSet()) {
			goal.checkType();
		}
	}

	private void checkGoalsActivationConditions() {
		for (Goal goal : getGoalSet()) {
			goal.checkActivationCondition();
		}
	}

	private void checkDefProductConditionsNotEmpty(Set<DefProductCondition> successConditions) {
		if (successConditions.isEmpty()) {
			throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL, "checkConditionsNotEmpty");
		}
	}

	private void checkMulConditionsNotEmpty(Set<MulCondition> mulConditions) {
		if (mulConditions.isEmpty()) {
			throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL, "checkConditionsNotEmpty");
		}
	}

	private void checkNotAllDefProductConditionsAreSelected(Set<DefProductCondition> successConditionOne,
			Set<DefProductCondition> successConditionsTwo) {
		if (successConditionOne.equals(successConditionsTwo)) {
			throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL, "checkAllConditionsAreNotSelected");
		}
	}

	private void checkNotAllMulConditionsAreSelected(Set<MulCondition> mulConditionOne,
			Set<MulCondition> mulConditionsTwo) {
		if (mulConditionOne.equals(mulConditionsTwo)) {
			throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL, "checkAllConditionsAreNotSelected");
		}
	}

	public GraphDto getGoalGraph() {
		GraphDto graph = new GraphDto();

		List<NodeDto> nodes = new ArrayList<>();
		List<EdgeDto> edges = new ArrayList<>();

		for (Goal goal : getGoalSet()) {
			String description = "ACT(" + goal.getActivationConditionSet().stream().map(d -> d.getPath().getValue())
					.collect(Collectors.joining(",")) + ")";
			if (goal instanceof ProductGoal) {

				if (!goal.getSuccessConditionSet().isEmpty()) {
					description = description + ", " + "SUC(" + goal.getSuccessConditionSet().stream()
							.map(d -> d.getPath().getValue()).collect(Collectors.joining(",")) + ")";
				}
				if (!goal.getAttributeInvariantConditionSet().isEmpty()) {
					description = description + ", " + "RULE(" + goal.getAttributeInvariantConditionSet().stream()
							.map(r -> r.getName()).collect(Collectors.joining(",")) + ")";
				}
			} else {
				if (!goal.getEntityInvariantConditionSet().isEmpty()) {
					description = description + ", " + "MUL("
							+ goal.getEntityInvariantConditionSet().stream().map(m -> m.getSourceEntity().getName()
									+ "." + m.getRolename() + "," + m.getCardinality().getExp())
									.collect(Collectors.joining(";"))
							+ ")";

				}
			}
			nodes.add(new NodeDto(goal.getExternalId(), goal.getName(), description));

			Set<Product> products = goal.getActivationConditionSet().stream().map(p -> p.getPath().getTarget())
					.collect(Collectors.toSet());
			for (Goal otherGoal : getGoalSet().stream().filter(g -> g.successProductsContainSomeOf(products))
					.collect(Collectors.toSet())) {
				edges.add(new EdgeDto(otherGoal.getExternalId(), goal.getExternalId()));
			}

		}

		graph.setNodes(nodes.stream().toArray(NodeDto[]::new));
		graph.setEdges(edges.stream().toArray(EdgeDto[]::new));

		return graph;
	}

	public GraphVisDto getGoalGraphVis() {
		GraphVisDto graph = new GraphVisDto();

		List<NodeVisDto> nodes = new ArrayList<>();
		List<EdgeVisDto> edges = new ArrayList<>();

		for (Goal goal : getGoalSet().stream().sorted(Comparator.comparing(Goal::getName))
				.collect(Collectors.toList())) {
			String title = "<pre>ACT(" + goal.getActivationConditionSet().stream().map(d -> d.getPath().getValue())
					.collect(Collectors.joining(", ")) + ")</pre>";
			if (goal instanceof ProductGoal) {

				if (!goal.getSuccessConditionSet().isEmpty()) {
					title = title + "<pre>SUC(" + goal.getSuccessConditionSet().stream()
							.map(d -> d.getPath().getValue()).collect(Collectors.joining(", ")) + ")</pre>";
				}
				if (!goal.getAttributeInvariantConditionSet().isEmpty()) {
					title = title + "<pre>RULE(" + goal.getAttributeInvariantConditionSet().stream()
							.map(r -> r.getName()).collect(Collectors.joining(", ")) + ")</pre>";
				}
			} else {
				if (!goal.getEntityInvariantConditionSet().isEmpty()) {
					title = title + "<pre>MUL("
							+ goal.getEntityInvariantConditionSet().stream()
									.map(m -> m.getSourceEntity().getName() + "." + m.getRolename() + " "
											+ m.getCardinality().getExp())
									.collect(Collectors.joining("; "))
							+ ")</pre>";

				}
			}
			nodes.add(new NodeVisDto(goal.getExternalId(), goal.getName(), title));

			Set<Product> products = goal.getActivationConditionSet().stream().map(p -> p.getPath().getTarget())
					.collect(Collectors.toSet());
			for (Goal otherGoal : getGoalSet().stream().filter(g -> g.successProductsContainSomeOf(products))
					.collect(Collectors.toSet())) {
				edges.add(new EdgeVisDto(otherGoal.getExternalId(), goal.getExternalId()));
			}

		}

		graph.setNodes(nodes);
		graph.setEdges(edges);

		return graph;
	}

	public Set<ProductGoal> getProductGoalSet() {
		return getGoalSet().stream().filter(ProductGoal.class::isInstance).map(ProductGoal.class::cast)
				.collect(Collectors.toSet());
	}

	public Set<AssociationGoal> getAssociationGoalSet() {
		return getGoalSet().stream().filter(AssociationGoal.class::isInstance).map(AssociationGoal.class::cast)
				.collect(Collectors.toSet());
	}

	public Goal getGoalFromProduct(Product product) {
		return getGoalSet().stream().filter(goal -> goal.getSuccessProducts().contains(product)).findFirst()
				.orElseThrow(() -> new BWException(BWErrorType.NON_EXISTENT_GOAL_BY_PRODUCT));
	}
}
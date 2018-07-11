package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.EdgeDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.GraphDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.NodeDTO;

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

		List<Goal> goalsToProcess = new ArrayList<>();

		Goal mandatoryGoal = createGoalForEntity(conditionModel.getMandatoryEntityCondition());

		goalsToProcess.add(mandatoryGoal);

		while (!goalsToProcess.isEmpty()) {
			Goal goal = goalsToProcess.remove(0);

			Entity entity = goal.getSuccessConditionSet().stream().filter(DefEntityCondition.class::isInstance)
					.map(DefEntityCondition.class::cast).map(c -> c.getEntity()).findFirst().get();

			for (RelationBW relation : entity.getRelationSet()) {
				if (!getUsedMulConditions().containsAll(relation.getMulConditionSet())) {
					createGoalForRelation(relation);
				}

				Set<DefEntityCondition> subDefEntityConditions = entity.getEntitiesInRelation().stream()
						.map(e -> e.getDefEntityCondition()).filter(c -> !getUsedDefConditionEntities().contains(c))
						.collect(Collectors.toSet());

				for (DefEntityCondition subDefEntityCondition : subDefEntityConditions) {
					Goal otherGoal = createGoalForEntity(subDefEntityCondition);
					goalsToProcess.add(otherGoal);
				}
			}
		}

		checkModel();
	}

	private Goal createGoalForEntity(DefEntityCondition defEntityCondition) {
		Set<DefProductCondition> defProductConditionSet = new HashSet<DefProductCondition>();
		defProductConditionSet.add(defEntityCondition);

		ProductGoal entityGoal = new ProductGoal(this, defEntityCondition.getPath().getValue(), defProductConditionSet);

		entityGoal.initProductGoal();

		createGoalsForAttributesOfEntity(defEntityCondition.getEntity(), entityGoal);

		return entityGoal;
	}

	private void createGoalsForAttributesOfEntity(Entity entity, Goal entityGoal) {
		ConditionModel conditionModel = getSpecification().getConditionModel();
		for (DefAttributeCondition defAttributeCondition : conditionModel.getAttributeAchieveConditionSet()) {
			if (defAttributeCondition.getAttributeOfDef().getEntity() == entity) {
				Set<DefProductCondition> defProductConditionSet = new HashSet<DefProductCondition>();
				defProductConditionSet.add(defAttributeCondition);

				ProductGoal attributeGoal = new ProductGoal(this, defAttributeCondition.getPath().getValue(),
						defProductConditionSet);

				attributeGoal.initProductGoal();
			}
		}
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

		// TODO: it is necessary to verify any circularity between goals
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

	public GraphDTO getGoalGraph() {
		GraphDTO graph = new GraphDTO();

		List<NodeDTO> nodes = new ArrayList<>();
		List<EdgeDTO> edges = new ArrayList<>();

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
			nodes.add(new NodeDTO(goal.getExternalId(), goal.getName(), description));

			Set<Product> products = goal.getActivationConditionSet().stream().map(p -> p.getPath().getTarget())
					.collect(Collectors.toSet());
			for (Goal otherGoal : getGoalSet().stream().filter(g -> g.successProductsContainSomeOf(products))
					.collect(Collectors.toSet())) {
				edges.add(new EdgeDTO(goal.getExternalId(), otherGoal.getExternalId()));
			}

		}

		graph.setNodes(nodes.stream().toArray(NodeDTO[]::new));
		graph.setEdges(edges.stream().toArray(EdgeDTO[]::new));

		return graph;
	}

	private Set<DefEntityCondition> getUsedDefConditionEntities() {
		return getGoalSet().stream().flatMap(g -> g.getSuccessConditionSet().stream())
				.filter(DefEntityCondition.class::isInstance).map(DefEntityCondition.class::cast)
				.collect(Collectors.toSet());
	}

	private Set<MulCondition> getUsedMulConditions() {
		return getGoalSet().stream().flatMap(g -> g.getSuccessConditionSet().stream())
				.filter(MulCondition.class::isInstance).map(MulCondition.class::cast).collect(Collectors.toSet());
	}

}
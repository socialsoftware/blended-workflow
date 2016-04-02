package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.Goal.GoalRelation;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.dto.EdgeDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.GraphDTO;
import pt.ist.socialsoftware.blendedworkflow.service.dto.NodeDTO;

public class GoalModel extends GoalModel_Base {
	private static Logger logger = LoggerFactory.getLogger(GoalModel.class);

	/**
	 * Clone the GoalModel tree
	 */
	public void cloneGoalModel(GoalModelInstance newGoalModelInstance) throws BWException {
		for (MaintainGoal maintainGoal : getMaintainGoalsSet()) {
			maintainGoal.cloneGoal(newGoalModelInstance);
		}

		for (Goal goal : getGoalSet()) {
			goal.cloneGoal(newGoalModelInstance);
		}

		for (Goal goal : getGoalSet()) {
			for (Goal subgoal : goal.getSubGoalSet()) {
				newGoalModelInstance.getGoal(goal.getName())
						.addSubGoal(newGoalModelInstance.getGoal(subgoal.getName()));
			}
		}
	}

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

	public MaintainGoal getMaintainGoal(String name) throws BWException {
		for (MaintainGoal goal : getMaintainGoalsSet()) {
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

		int goalCounter = 0;
		Goal top = new Goal(this, "g" + ++goalCounter);
		for (DefEntityCondition defEntityCondition : conditionModel.getEntityAchieveConditionSet()) {
			if (!defEntityCondition.getEntity().getExists()) {
				Goal entityGoal = new Goal(this, "g" + ++goalCounter);
				top.addSubGoal(entityGoal);
				entityGoal.addSuccessCondition(defEntityCondition);

				entityGoal.applyConditions();

				int subCounter = 0;
				for (DefAttributeCondition defAttributeCondition : conditionModel.getAttributeAchieveConditionSet()) {
					if (defAttributeCondition.getAttributeOfDef().getEntity() == defEntityCondition.getEntity()) {
						Goal attributeGoal = new Goal(this, "g" + goalCounter + ++subCounter);
						entityGoal.addSubGoal(attributeGoal);
						attributeGoal.addSuccessCondition(defAttributeCondition);

						attributeGoal.applyConditions();

					}
				}
			}
		}
		checkModel();

	}

	public Goal mergeGoals(String newGoalName, Goal goalOne, Goal goalTwo) {
		Goal result = null;

		GoalRelation relation = goalOne.getGoalRelation(goalTwo);

		if (relation == GoalRelation.OTHER) {
			throw new BWException(BWErrorType.UNMERGEABLE_GOALS, goalOne.getName() + " - " + goalTwo.getName());
		} else if (relation == GoalRelation.SIBLING) {
			result = mergeSiblingGoals(newGoalName, goalOne, goalTwo);
		} else if (relation == GoalRelation.PARENT) {
			result = mergeParentChildGoals(newGoalName, goalTwo, goalOne);
		} else if (relation == GoalRelation.CHILD) {
			result = mergeParentChildGoals(newGoalName, goalOne, goalTwo);
		}

		checkModel();

		return result;
	}

	private Goal mergeSiblingGoals(String newGoalName, Goal goalOne, Goal goalTwo) {
		String tmpName = goalOne.getName() + "-" + goalTwo.getName();
		while (existsGoal(tmpName))
			tmpName = tmpName + ".1";

		Goal newGoal = new Goal(this, tmpName);
		newGoal.setParentGoal(goalOne.getParentGoal());

		Stream.concat(goalOne.getSubGoalSet().stream(), goalTwo.getSubGoalSet().stream())
				.forEach(goal -> newGoal.addSubGoal(goal));

		Stream.concat(goalOne.getSuccessConditionSet().stream(), goalTwo.getSuccessConditionSet().stream())
				.forEach(cond -> newGoal.addSuccessCondition(cond));

		goalOne.delete();
		goalTwo.delete();

		newGoal.setName(newGoalName);

		newGoal.applyConditions();

		return newGoal;
	}

	private Goal mergeParentChildGoals(String newGoalName, Goal parentGoal, Goal childGoal) {
		childGoal.getSubGoalSet().stream().forEach(goal -> parentGoal.addSubGoal(goal));
		childGoal.getSuccessConditionSet().stream().forEach(cond -> parentGoal.addSuccessCondition(cond));
		childGoal.delete();

		parentGoal.shrinkGoal(new HashSet<DefProductCondition>());
		parentGoal.setName(newGoalName);
		parentGoal.applyConditions();

		return parentGoal;
	}

	public Goal extractChild(Goal goal, String newGoalName, Set<DefProductCondition> successConditions) {
		checkConditionsNotEmpty(successConditions);
		goal.checkConditionsExistSucc(successConditions);
		goal.shrinkGoal(successConditions);

		Goal newGoal = new Goal(this, newGoalName);
		successConditions.stream().forEach((def) -> newGoal.addSuccessCondition(def));
		newGoal.setParentGoal(goal);

		goal.applyConditions();
		newGoal.applyConditions();

		checkModel();

		return newGoal;
	}

	public Goal extractSibling(Goal goal, String newGoalName, Set<DefProductCondition> successConditions) {
		checkConditionsNotEmpty(successConditions);
		checkAllConditionsNotSelected(goal.getSuccessConditionSet(), successConditions);
		goal.checkConditionsExistSucc(successConditions);
		goal.shrinkGoal(successConditions);

		Goal newGoal = new Goal(this, newGoalName);
		successConditions.stream().forEach((def) -> newGoal.addSuccessCondition(def));
		newGoal.setParentGoal(goal.getParentGoal());

		goal.applyConditions();
		newGoal.applyConditions();

		checkModel();

		return newGoal;
	}

	public Goal extractParent(Goal goal, String newGoalName, Set<DefProductCondition> successConditions) {
		checkAllConditionsNotSelected(goal.getSuccessConditionSet(), successConditions);
		goal.checkConditionsExistSucc(successConditions);
		goal.shrinkGoal(successConditions);

		Goal newGoal = new Goal(this, newGoalName);
		successConditions.stream().forEach((def) -> newGoal.addSuccessCondition(def));
		newGoal.setParentGoal(goal.getParentGoal());
		goal.setParentGoal(newGoal);

		checkModel();

		goal.applyConditions();
		newGoal.applyConditions();

		checkModel();

		return newGoal;
	}

	public void checkModel() {
		checkModelCompleteness();
		checkModelConsistency();
	}

	public void checkModelCompleteness() {
		// TODO
		// checkAllProductsAreProduced();
		// checkAllDependenceConditionsAreApplied();
		// checkAllMultiplicityConditionsAreApplied();
		// checkAllRulesAreApplied();
	}

	public void checkModelConsistency() {
		checkSingleRootConstraint();
		checkAttributeChildEntityParentConstraint();
		checkActivationConditionDependenciesConstraint();
	}

	private void checkActivationConditionDependenciesConstraint() {
		getGoalSet().stream().filter(g -> !g.getActivationConditionSet().isEmpty())
				.forEach(g -> g.checkActivationConditionDependenciesConstraint());
	}

	private void checkAttributeChildEntityParentConstraint() {
		getGoalSet().stream().forEach(g -> g.checkAttributeChildEntityParentConstraint());

	}

	private void checkSingleRootConstraint() {
		if (getGoalSet().stream().filter(g -> g.getParentGoal() == null).count() != 1)
			throw new BWException(BWErrorType.INCONSISTENT_GOALMODEL, "multiple top goals");

	}

	private void checkConditionsNotEmpty(Set<DefProductCondition> successConditions) {
		if (successConditions.isEmpty())
			throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL, "checkConditionsNotEmpty");
	}

	private void checkAllConditionsNotSelected(Set<DefProductCondition> successConditionOne,
			Set<DefProductCondition> successConditionsTwo) {
		if (successConditionOne.equals(successConditionsTwo))
			throw new BWException(BWErrorType.CANNOT_EXTRACT_GOAL, "checkAllConditionsAreNotSelected");
	}

	public GraphDTO getGoalGraph() {
		GraphDTO graph = new GraphDTO();

		List<NodeDTO> nodes = new ArrayList<NodeDTO>();
		List<EdgeDTO> edges = new ArrayList<EdgeDTO>();

		for (Goal goal : getGoalSet()) {
			String description = "ACT(" + goal.getActivationConditionSet().stream().map(d -> d.getPath().getValue())
					.collect(Collectors.joining(",")) + ")";
			if (!goal.getSuccessConditionSet().isEmpty()) {
				description = description + ", " + "SUC(" + goal.getSuccessConditionSet().stream()
						.map(d -> d.getPath().getValue()).collect(Collectors.joining(",")) + ")";
			}
			if (!goal.getEntityInvariantConditionSet().isEmpty()) {
				description = description + ", " + "MUL("
						+ goal.getEntityInvariantConditionSet().stream().map(m -> m.getSourceEntity().getName() + "."
								+ m.getTargetRolename() + "," + m.getTargetCardinality().name())
								.collect(Collectors.joining(";"))
						+ ")";
			}
			if (!goal.getAttributeInvariantConditionSet().isEmpty()) {
				description = description + ", " + "RULE(" + goal.getAttributeInvariantConditionSet().stream()
						.map(r -> r.getName()).collect(Collectors.joining(",")) + ")";
			}

			nodes.add(new NodeDTO(goal.getExternalId(), goal.getName(), description));

			for (Goal subGoal : goal.getSubGoalSet()) {
				edges.add(new EdgeDTO(goal.getExternalId(), subGoal.getExternalId()));
			}

		}

		graph.setNodes(nodes.stream().toArray(NodeDTO[]::new));
		graph.setEdges(edges.stream().toArray(EdgeDTO[]::new));

		return graph;
	}

}
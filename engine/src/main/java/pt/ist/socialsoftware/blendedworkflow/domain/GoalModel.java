package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.ArrayList;
import java.util.List;
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

		Goal top = new Goal(this, "top");

		int goalCounter = 0;
		for (DefEntityCondition defEntityCondition : conditionModel.getEntityAchieveConditionSet()) {
			if (!defEntityCondition.getEntity().getExists()) {
				Goal entityGoal = new Goal(this, "g" + ++goalCounter);
				top.addSubGoal(entityGoal);
				entityGoal.addSuccessCondition(defEntityCondition);

				for (Dependence dependence : conditionModel.getEntityDependenceConditionSet()) {
					if (dependence.getProduct() == defEntityCondition.getEntity()) {
						entityGoal.addActivationCondition(DefPathCondition.getDefPathCondition(getSpecification(),
								dependence.getPath().getValue()));
					}
				}

				for (MulCondition mulCondition : conditionModel.getEntityInvariantConditionSet()) {
					if (mulCondition.getSourceEntity() == defEntityCondition.getEntity()) {
						entityGoal.addEntityInvariantCondition(mulCondition);
					}
				}

				for (Rule rule : conditionModel.getAttributeInvariantConditionSet()) {
					if (defEntityCondition.getEntity().getRuleSet().contains(rule)) {
						entityGoal.addAttributeInvariantCondition(rule);
					}
				}

				int subCounter = 0;
				for (DefAttributeCondition defAttributeCondition : conditionModel.getAttributeAchieveConditionSet()) {
					if (defAttributeCondition.getAttributeOfDef().getEntity() == defEntityCondition.getEntity()) {
						Goal attributeGoal = new Goal(this, "g" + goalCounter + ++subCounter);
						entityGoal.addSubGoal(attributeGoal);
						attributeGoal.addSuccessCondition(defAttributeCondition);

						for (Dependence dependence : conditionModel.getAttributeDependenceConditionSet()) {
							if (dependence.getProduct() == defAttributeCondition.getAttributeOfDef()) {
								attributeGoal.addActivationCondition(DefPathCondition
										.getDefPathCondition(getSpecification(), dependence.getPath().getValue()));
							}
						}

					}
				}
			}
		}

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

		return result;
	}

	private Goal mergeSiblingGoals(String newGoalName, Goal goalOne, Goal goalTwo) {
		String tmpName = goalOne.getName() + "-" + goalTwo.getName();
		while (existsGoal(tmpName))
			tmpName = tmpName + ".1";

		Goal newGoal = new Goal(this, tmpName);
		newGoal.setParentGoal(goalOne.getParentGoal());

		Stream.concat(goalOne.getSubGoalSet().stream(), goalTwo.getSubGoalSet().stream())
				.forEach((goal) -> newGoal.addSubGoal(goal));

		Stream.concat(goalOne.getSuccessConditionSet().stream(), goalTwo.getSuccessConditionSet().stream())
				.forEach((cond) -> newGoal.addSuccessCondition(cond));

		Stream.concat(goalOne.getActivationConditionSet().stream(), goalTwo.getActivationConditionSet().stream())
				.forEach((cond) -> newGoal.addActivationCondition(cond));
		newGoal.purgeActivationCondition();

		Stream.concat(goalOne.getEntityInvariantConditionSet().stream(),
				goalTwo.getEntityInvariantConditionSet().stream())
				.forEach((cond) -> newGoal.addEntityInvariantCondition(cond));

		Stream.concat(goalOne.getAttributeInvariantConditionSet().stream(),
				goalTwo.getAttributeInvariantConditionSet().stream())
				.forEach((rule) -> newGoal.addAttributeInvariantCondition(rule));

		goalOne.delete();
		goalTwo.delete();

		newGoal.setName(newGoalName);

		return newGoal;
	}

	private Goal mergeParentChildGoals(String newGoalName, Goal parentGoal, Goal childGoal) {
		parentGoal.checkCanMergeChild(childGoal);

		childGoal.getSubGoalSet().stream().forEach((goal) -> parentGoal.addSubGoal(goal));

		childGoal.getSuccessConditionSet().stream().forEach((cond) -> parentGoal.addSuccessCondition(cond));

		childGoal.getActivationConditionSet().stream().forEach((cond) -> parentGoal.addActivationCondition(cond));
		parentGoal.purgeActivationCondition();

		childGoal.getEntityInvariantConditionSet().stream()
				.forEach((mul) -> parentGoal.addEntityInvariantCondition(mul));

		childGoal.getAttributeInvariantConditionSet().stream()
				.forEach((rule) -> parentGoal.addAttributeInvariantCondition(rule));

		childGoal.delete();

		parentGoal.setName(newGoalName);

		return parentGoal;
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
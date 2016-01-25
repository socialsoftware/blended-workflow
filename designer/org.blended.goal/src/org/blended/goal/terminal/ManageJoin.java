package org.blended.goal.terminal;

import java.util.Optional;

import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.dto.GoalDTO;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.ValueException;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.ParameterException;

import utils.Utils;

/**
 * I always copy everything, except SUC(nothing)
 */
public class ManageJoin {
	private static Logger log = LoggerFactory.getLogger(ManageJoin.class);

	public static void goals(GoalModel model, String name, String specId, CommandJoin join)
			throws ParameterException, ValueException {
		CommonInterface ci = CommonInterface.getInstance();

		// CHECKS
		if (join.goals.size() != 2)
			throw new ParameterException("The number of goals should be two (found " + join.goals.size() + ")");
		String goalName1 = join.goals.get(0);
		String goalName2 = join.goals.get(1);

		Optional<Goal> goalO1 = Utils.getGoalByName(model, goalName1);
		Optional<Goal> goalO2 = Utils.getGoalByName(model, goalName2);
		Optional<Goal> newGoalO = Utils.getGoalByName(model, join.name);

		if ((!goalO1.isPresent()) || (!goalO2.isPresent()))
			throw new ValueException("Parameter not valid: Two existing goals expected");
		if (newGoalO.isPresent())
			throw new ValueException("Parameter not valid: No existing goal expected with the same name");
		Goal goal1 = goalO1.get();
		Goal goal2 = goalO2.get();

		// CHECK RELATIONSHIP BETWEEN NODES
		NodesRelation relation = getTypeOfRelation(model, goal1, goal2);
		switch (relation) {
		case Horizontal:
		case VerticalParent:
		case VerticalChild: {
			// Effectively merge in the server
			GoalDTO goalDTO = ci.mergeGoals(specId, join.name, goalName1, goalName2);

			log.debug("after merge");

			Utils.removeGoalModel(model);

			Utils.loadGoalModelFromServer(model, specId);

			ConsoleManagement.write(name, "Operation performed. New goal " + goalDTO.getName() + " generated");
			break;
		}
		case NotValid: {
			throw new ValueException("Parameter not valid: Direct relationship between nodes expected");
		}
		}

	}

	private static NodesRelation getTypeOfRelation(GoalModel model, Goal goal1, Goal goal2) {
		NodesRelation relation = NodesRelation.NotValid;

		for (Goal goal : model.getGoals()) {
			if (goal.getChildrenGoals().contains(goal1) && goal.getChildrenGoals().contains(goal2)) {
				relation = NodesRelation.Horizontal;
				break;
			}
			if (goal.equals(goal1) && goal.getChildrenGoals().contains(goal2)) {
				relation = NodesRelation.VerticalParent;
				break;
			}
			if (goal.equals(goal2) && goal.getChildrenGoals().contains(goal1)) {
				relation = NodesRelation.VerticalChild;
				break;
			}
		}

		return relation;
	}

}

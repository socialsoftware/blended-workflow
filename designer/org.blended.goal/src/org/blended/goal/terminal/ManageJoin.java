package org.blended.goal.terminal;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.dto.GoalDTO;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.ValueException;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalModel;
import org.blended.goal.repository.ReadGoalModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.ParameterException;

/**
 * I always copy everything, except SUC(nothing)
 */
public class ManageJoin {
    private static Logger log = LoggerFactory
            .getLogger(ReadGoalModelService.class);

    public static void goals(GoalModel model, String name, String specId,
            CommandJoin join) throws ParameterException, ValueException {
        CommonInterface ci = CommonInterface.getInstance();

        // CHECKS
        if (join.goals.size() != 2)
            throw new ParameterException(
                    "The number of goals should be two (found "
                            + join.goals.size() + ")");
        String goalName1 = join.goals.get(0);
        String goalName2 = join.goals.get(1);

        Optional<Goal> goalO1 = getGoalByName(model, goalName1);
        Optional<Goal> goalO2 = getGoalByName(model, goalName2);
        Optional<Goal> newGoalO = getGoalByName(model, join.name);

        if ((!goalO1.isPresent()) || (!goalO2.isPresent()))
            throw new ValueException(
                    "Parameter not valid: Two existing goals expected");
        if (newGoalO.isPresent())
            throw new ValueException(
                    "Parameter not valid: No existing goal expected with the same name");
        Goal goal1 = goalO1.get();
        Goal goal2 = goalO2.get();

        // CHECK RELATIONSHIP BETWEEN NODES
        NodesRelation relation = getTypeOfRelation(model, goal1, goal2);
        switch (relation) {
        case Horizontal:
        case VerticalParent:
        case VerticalChild: {
            // Effectively merge in the server
            GoalDTO goalDTO = ci.mergeGoals(specId, join.name, goalName1,
                    goalName2);

            log.debug("after merge");

            Set<Goal> goals = new HashSet<Goal>();
            goals.addAll(model.getGoals());

            for (Goal goal : goals) {
                log.debug("goal {}", goal.getName());
                model.getGoals().remove(goal);
            }

            // model.getGoals().stream().forEach(g ->
            // model.getGoals().remove(g));

            ReadGoalModelService readGoalService = ReadGoalModelService
                    .getInstance();
            readGoalService.read(specId, model);

            // // DELETE OLD GOALS
            // model.getGoals().stream()
            // .filter(g -> g.getChildrenGoals().contains(goal1))
            // .forEach(g -> g.getChildrenGoals().remove(goal1));
            // model.getGoals().remove(goal1);
            // model.getGoals().stream()
            // .filter(g -> g.getChildrenGoals().contains(goal2))
            // .forEach(g -> g.getChildrenGoals().remove(goal2));
            // model.getGoals().remove(goal2);
            //
            // // CREATE NEW GOAL
            // ReadGoalService readGoalService = ReadGoalService.getInstance();
            // Goal newGoal = readGoalService.read(specId, goalDTO, model);

            ConsoleManagement.write(name, "Operation performed. New goal "
                    + goalDTO.getName() + " generated");
            break;
        }
        case NotValid: {
            throw new ValueException(
                    "Parameter not valid: Direct relationship between nodes expected");
        }
        }

    }

    private static Optional<Goal> getGoalByName(GoalModel model, String name) {
        Optional<Goal> goal = model.getGoals().stream()
                .filter(e -> e.getName().equals(name)).findFirst();
        return goal;
    }

    private static NodesRelation getTypeOfRelation(GoalModel model, Goal goal1,
            Goal goal2) {
        NodesRelation relation = NodesRelation.NotValid;

        for (Goal goal : model.getGoals()) {
            if (goal.getChildrenGoals().contains(goal1)
                    && goal.getChildrenGoals().contains(goal2)) {
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

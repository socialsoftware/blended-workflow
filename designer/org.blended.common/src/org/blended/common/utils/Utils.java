package org.blended.common.utils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalModel;
import org.blended.goal.repository.ReadGoalModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    private static Logger log = LoggerFactory
            .getLogger(Utils.class);

	public static Optional<Goal> getGoalByName(GoalModel model, String name) {
		Optional<Goal> goal = model.getGoals().stream().filter(e -> e.getName().equals(name)).findFirst();
		return goal;
	}

	public static void loadGoalModelFromServer(GoalModel model, String specId) {
		ReadGoalModelService readGoalService = ReadGoalModelService
		        .getInstance();
		readGoalService.read(specId, model);
	}

	public static void removeGoalModel(GoalModel model) {
		Set<Goal> goals = new HashSet<Goal>();
		goals.addAll(model.getGoals());

		for (Goal goal : goals) {
		    log.debug("goal {}", goal.getName());
		    model.getGoals().remove(goal);
		}
	}


}

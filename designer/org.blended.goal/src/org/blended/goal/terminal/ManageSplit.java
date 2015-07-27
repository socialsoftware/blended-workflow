package org.blended.goal.terminal;

import static org.eclipse.emf.ecore.util.EcoreUtil.copyAll;
import java.util.Optional;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.ValueException;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalFactory;
import org.blended.goal.goal.GoalModel;
import com.beust.jcommander.ParameterException;

public class ManageSplit {
	public static void goals(GoalModel model, String name, CommandSplit split) throws ValueException  {
		//CHECKS
		if (split.goals.size() != 1) throw new ParameterException("The number of goals should be one (found " + split.goals.size() + ")");
		String goalName = split.goals.get(0);
		String newGoalName1 = split.name1;
		String newGoalName2 = split.name2;
		
		Optional<Goal> goalO = getGoalByName(model, goalName);		
		
		if (!goalO.isPresent()) 
			throw new ValueException("Parameter not valid: One existing goals expected");		
		Goal goal = goalO.get();
		
		//CREATE NEW GOAL 1
		Goal newGoal1 = createNewGoal(model, newGoalName1);
		
		//CREATE NEW GOAL 2
		Goal newGoal2 = createNewGoal(model, newGoalName2);
//		copyGoalContent(goal1, newGoal);
//		copyGoalContent(goal2, newGoal);
//		changeReferencesByNewOne(model, goal1, goal2, newGoal);
		
		//DELETE OLD GOALS
//		model.getGoals().remove(goal1);
//		model.getGoals().remove(goal2);
		
		ConsoleManagement.write(name, "Operation performed. Goal " + goalName + " Splited in " + newGoalName1 + " and " + newGoalName2);
	}
	
	public static Optional<Goal> getGoalByName(GoalModel model, String name)  {
		Optional<Goal> goal = model.getGoals().stream()
				.filter(e -> e.getName().equals(name))
				.findFirst();
		return goal;
	}
	
	public static Goal createNewGoal(GoalModel model, String name) {
		GoalFactory factory = GoalFactory.eINSTANCE;
		Goal newGoal = factory.createGoal();
		newGoal.setName(name);
		model.getGoals().add(newGoal);
		return newGoal;
	}
	
	public static void copyGoalContent(Goal source, Goal target) {
		target.getActivationConditions().addAll(copyAll(source.getActivationConditions()));
		target.getInvariantConditions().addAll(copyAll(source.getInvariantConditions()));
		target.getSuccessConditions().addAll(copyAll(source.getSuccessConditions()));
		for (Goal g : source.getChildrenGoals()) {
			target.getChildrenGoals().add(g);
		}
	} 
	
	public static void changeReferencesByNewOne(GoalModel model, Goal source1, Goal source2, Goal target) {
		for (Goal goal : model.getGoals()) {
			if (goal.getChildrenGoals().contains(source1)) {
				goal.getChildrenGoals().remove(source1);	
				if (!goal.equals(target))
					goal.getChildrenGoals().add(target);
			}
			if (goal.getChildrenGoals().contains(source2)) {
				goal.getChildrenGoals().remove(source2);	
				if (!goal.getChildrenGoals().contains(target))
					if (!goal.equals(target))
						goal.getChildrenGoals().add(target);
			}
		}
	}
	
}

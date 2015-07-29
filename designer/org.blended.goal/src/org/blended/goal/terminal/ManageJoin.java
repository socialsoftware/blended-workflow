package org.blended.goal.terminal;

import static org.eclipse.emf.ecore.util.EcoreUtil.copyAll;
import static org.eclipse.emf.ecore.util.EcoreUtil.copy;
import java.util.Optional;

import org.blended.common.common.Nothing;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.ValueException;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalFactory;
import org.blended.goal.goal.GoalModel;
import org.eclipse.emf.ecore.EObject;

import com.beust.jcommander.ParameterException;

/**
 * I always copy everything, except SUC(nothing)
 */
public class ManageJoin {
	public static void goals(GoalModel model, String name, CommandJoin join) throws ValueException  {
		//CHECKS
		if (join.goals.size() != 2) throw new ParameterException("The number of goals should be two (found " + join.goals.size() + ")");
		String goalName1 = join.goals.get(0);
		String goalName2 = join.goals.get(1);
		
		Optional<Goal> goalO1 = getGoalByName(model, goalName1);		
		Optional<Goal> goalO2 = getGoalByName(model, goalName2);
		Optional<Goal> newGoalO = getGoalByName(model, join.name);
		
		if ((!goalO1.isPresent()) || (!goalO2.isPresent())) 
			throw new ValueException("Parameter not valid: Two existing goals expected");			
		if (newGoalO.isPresent()) 
			throw new ValueException("Parameter not valid: No existing goal expected with the same name");
		Goal goal1 = goalO1.get();
		Goal goal2 = goalO2.get();
		
		//CHECK RELATIONSHIP BETWEEN NODES
		NodesRelation relation = getTypeOfRelation(model, goal1, goal2);
		switch (relation) {
			case Horizontal:
			case VerticalParent:
			case VerticalChild: {
				//CREATE NEW GOAL
				Goal newGoal = createNewGoal(model, join.name);
				copyGoalContent(goal1, newGoal);
				copyGoalContent(goal2, newGoal);
				changeReferencesByNewOne(model, goal1, goal2, newGoal);
				
				//DELETE OLD GOALS
				model.getGoals().remove(goal1);
				model.getGoals().remove(goal2);
				
				ConsoleManagement.write(name, "Operation performed. New goal " + newGoal.getName() + " generated");
				break;	
			}
			case NotValid: {
				throw new ValueException("Parameter not valid: Direct relationship between nodes expected");		
			}
		}

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
		for (EObject s : source.getSuccessConditions()) {
			if (!(s instanceof Nothing)) { //we don't want to copy SUC(Nothing)
				target.getSuccessConditions().add(copy(s));
			}
		}
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
	
	public static NodesRelation getTypeOfRelation(GoalModel model, Goal goal1, Goal goal2) {
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
	
	public enum NodesRelation {
		Horizontal, VerticalParent, VerticalChild, NotValid
	}

}

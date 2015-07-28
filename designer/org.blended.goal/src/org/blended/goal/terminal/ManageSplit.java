package org.blended.goal.terminal;

import static org.eclipse.emf.ecore.util.EcoreUtil.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.MandatoryAttributeAchieveCondition;
import org.blended.common.common.NotMandatoryAttributeAchieveCondition;
import org.blended.common.common.Nothing;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.ValueException;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalFactory;
import org.blended.goal.goal.GoalModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.beust.jcommander.ParameterException;

public class ManageSplit {
	public static void goals(GoalModel model, String name, CommandSplit split) throws ValueException  {
		//CHECKS
		List<String> successConditions = Arrays.asList(split.successConditions.split(";")); //different elements
		List<List<String>> successConditionsSplitted = new ArrayList<>(successConditions.size());
		for (String suc : successConditions) {
			List<String> part = Arrays.asList(suc.split(","));
			successConditionsSplitted.add(part);
		}
		if (successConditionsSplitted.size() < 1) throw new ParameterException("Parameter not valid: One existing goal expected to be splitted");
		
		String goalName = split.goal;
		String newGoalName = split.name;
		String type = split.type;

		Optional<Goal> goalNameO = getGoalByName(model, goalName);		
		Optional<Goal> newGoalNameO = getGoalByName(model, newGoalName);
		if (!goalNameO.isPresent()) 
			throw new ValueException("Parameter not valid: One existing goal expected to be splitted");
		if (newGoalNameO.isPresent()) 
			throw new ValueException("Parameter not valid: No existing goal expected with the same name");
		Goal goal = goalNameO.get();
		
		if (goal.getSuccessConditions().size() <= 1)
			throw new ValueException("Parameter not valid: Only one source success condition. It is not possible to split");
		List<EObject> succList = checkSuccessConditions(goal.getSuccessConditions(), successConditionsSplitted);
		if (succList == null) {
			throw new ValueException("Parameter not valid: All Achieve conditions should exist in the source goal");
		}
		
		//CREATE NEW GOAL
		Goal newGoal = createNewGoal(model, newGoalName);
		manageSuccessConditions(goal.getSuccessConditions(), newGoal, succList);
		manageActivationConditions(goal.getSuccessConditions(), newGoal, succList);
		manageInvariantConditions(goal.getSuccessConditions(), newGoal, succList);
		//manageRelatives(goal.getSuccessConditions(), newGoal, succList);
		
		ConsoleManagement.write(name, "Operation performed. Goal " + goalName + " Splitted by creating goal " + newGoalName + " ("+ type +")");
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
	
	public static List<EObject> checkSuccessConditions(EList<EObject> goalSuccessConditions, List<List<String>> successConditionsSplitted) {
		List<EObject> result = new ArrayList<EObject>();
		Boolean exists = false;
		for (List<String> cond : successConditionsSplitted) {
			System.out.println("cond: " + cond.size());
			for (String c : cond) {
				System.out.println("\t c: " + c);
			}
			exists = false;
			for (EObject suc : goalSuccessConditions) {
				System.out.println("SUC TYPE: " + suc.getClass().getName());
				if (suc instanceof EntityAchieveCondition) {
					EntityAchieveCondition o = (EntityAchieveCondition)suc;
					if (o.getName().equals(cond)) {
						result.add(suc); //it exists
						exists = true;
					}
				}
				else if (suc instanceof MandatoryAttributeAchieveCondition) {
					MandatoryAttributeAchieveCondition o = (MandatoryAttributeAchieveCondition)suc;
					if (o.getConditions().containsAll(cond)) {
						result.add(suc); //it exists
						exists = true;
					}
				}
				if (suc instanceof NotMandatoryAttributeAchieveCondition) {
					NotMandatoryAttributeAchieveCondition o = (NotMandatoryAttributeAchieveCondition)suc;
					System.out.println("COND: " + cond + " O: " + o.getConditions().toString());
					if (o.getConditions().containsAll(cond)) {
						result.add(suc); //it exists
						exists = true;
					}
					System.out.println("EXISTS: " + exists);
				}
				else if (suc instanceof Nothing) {
					Nothing o = (Nothing)suc;
					//if ((cond.size() == 1)&&(cond.get(0).equals(o.getName()))) {
					if (o.getName().equals(cond)) {
						result.add(suc); //it exists
						exists = true;
					}
				}			
			}
			if (!exists) return null;
		}
		return result;
	}
	
	public static void manageSuccessConditions(EList<EObject> goalSuccessConditions, Goal newGoal, List<EObject> succList) {
		newGoal.getSuccessConditions().addAll(copyAll(succList));
		goalSuccessConditions.removeAll(succList);
	}
	
	public static void manageActivationConditions(EList<EObject> goalSuccessConditions, Goal newGoal, List<EObject> succList) {
	}
	
	public static void manageInvariantConditions(EList<EObject> goalSuccessConditions, Goal newGoal, List<EObject> succList) {
	}
	
	public static void manageRelatives(GoalModel model, Goal goal, Goal newGoal, String type) {	
		switch (type) {
			case "s":
			case "sibling":
				for (Goal g : model.getGoals()) {
					if (g.getChildrenGoals().contains(goal)) {
						g.getChildrenGoals().add(newGoal);
					}
				}
				break;
			case "c":
			case "child":
				goal.getChildrenGoals().add(newGoal);
				break;
		}
	
	}
	
}

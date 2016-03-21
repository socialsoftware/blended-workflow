package org.blended.goal.terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.MandatoryAttributeAchieveCondition;
import org.blended.common.common.NotMandatoryAttributeAchieveCondition;
import org.blended.common.common.Nothing;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.dto.DefPathConditionDTO;
import org.blended.common.repository.resttemplate.dto.GoalDTO;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.ValueException;
import org.blended.goal.goal.Goal;
import org.blended.goal.goal.GoalModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.ParameterException;

import utils.Utils;

public class ManageSplit {
	private static Logger log = LoggerFactory.getLogger(ManageSplit.class);

	enum Type {
		SIBLING, CHILD
	}

	public static void goals(GoalModel model, String name, String specId, CommandSplit split)
			throws ParameterException, ValueException {
		// CHECKS
		List<String> successConditionsToSplit = Arrays.asList(split.successConditions.split(";"));

		if (successConditionsToSplit.size() < 1)
			throw new ParameterException("Parameter not valid: One existing goal expected to be splitted");

		String sourceGoalName = split.goal;
		String newGoalName = split.name;
		Type type = getType(split.type);

		Optional<Goal> sourceGoalO = Utils.getGoalByName(model, sourceGoalName);
		Optional<Goal> newGoalNameO = Utils.getGoalByName(model, newGoalName);
		if (!sourceGoalO.isPresent())
			throw new ValueException("Parameter not valid: One existing goal expected to be splitted");
		if (newGoalNameO.isPresent())
			throw new ValueException("Parameter not valid: No existing goal expected with the same name");
		Goal sourceGoal = sourceGoalO.get();

		if (sourceGoal.getSuccessConditions().size() < 1)
			throw new ValueException("Parameter not valid: No source success conditions. It is not possible to split");
		List<EObject> succList1 = checkSuccessConditionsToSplitAreContainedInGoal(sourceGoal, successConditionsToSplit);
		if (succList1 == null) {
			throw new ValueException("Parameter not valid: All Achieve conditions should exist in the source goal");
		}

		// change goals in the server
		CommonInterface ci = CommonInterface.getInstance();
		GoalDTO goalDTO = null;
		if (type.equals(Type.SIBLING)) {
			goalDTO = ci.extractSiblingGoal(specId, newGoalName, sourceGoalName,
					getDefProductConditionSet(sourceGoal, specId, successConditionsToSplit));
		} else if (type.equals(Type.CHILD)) {
			goalDTO = ci.extractChildGoal(specId, newGoalName, sourceGoalName,
					getDefProductConditionSet(sourceGoal, specId, successConditionsToSplit));
		} else {
			assert false : "wrong type for spliting goals";
		}

		Utils.removeGoalModel(model);

		Utils.loadGoalModelFromServer(model, specId);

		ConsoleManagement.write(name, "Operation performed. Goal " + sourceGoalName + " Splitted by creating goal "
				+ goalDTO.getName() + " (" + type + ")");
	}

	private static Set<DefPathConditionDTO> getDefProductConditionSet(Goal goal, String specId,
			List<String> successConditionsToSplit) {
		Set<DefPathConditionDTO> defs = new HashSet<DefPathConditionDTO>();

		for (String condition : successConditionsToSplit) {
			EObject eo = getGoalSuccessCondition(goal, condition);
			if (eo instanceof EntityAchieveCondition) {
				defs.add(new DefPathConditionDTO(specId, ((EntityAchieveCondition) eo).getName()));
			} else if (eo instanceof MandatoryAttributeAchieveCondition) {
				defs.add(new DefPathConditionDTO(specId, ((MandatoryAttributeAchieveCondition) eo).getAttribute()));
			} else if (eo instanceof NotMandatoryAttributeAchieveCondition) {
				defs.add(new DefPathConditionDTO(specId, ((NotMandatoryAttributeAchieveCondition) eo).getAttribute()));
			} else {
				assert false;
			}
		}

		return defs;
	}

	private static Type getType(String type) {
		switch (type) {
		case "s":
		case "sibling":
			return Type.SIBLING;
		case "c":
		case "child":
			return Type.CHILD;
		}
		return null;
	}

	public static List<EObject> checkSuccessConditionsToSplitAreContainedInGoal(Goal goal,
			List<String> successConditionsToSplit) {
		List<EObject> result = new ArrayList<EObject>();
		for (String cond : successConditionsToSplit) {
			EObject eo = getGoalSuccessCondition(goal, cond);
			if (eo != null)
				result.add(eo);
			else
				return null;
		}
		return result;
	}

	private static EObject getGoalSuccessCondition(Goal goal, String cond) {
		EList<EObject> goalSuccessConditions = goal.getSuccessConditions();
		for (EObject suc : goalSuccessConditions) {
			if (suc instanceof EntityAchieveCondition) {
				EntityAchieveCondition o = (EntityAchieveCondition) suc;
				if (o.getName().equals(cond)) {
					return suc;
				}
			} else if (suc instanceof NotMandatoryAttributeAchieveCondition) {
				NotMandatoryAttributeAchieveCondition o = (NotMandatoryAttributeAchieveCondition) suc;
				if (cond.equals(o.getAttribute())) {
					return suc;
				}
			} else if (suc instanceof MandatoryAttributeAchieveCondition) {
				MandatoryAttributeAchieveCondition o = (MandatoryAttributeAchieveCondition) suc;
				if (cond.equals(o.getAttribute())) {
					return suc;
				}
			} else if (suc instanceof Nothing) {
				Nothing o = (Nothing) suc;
				if (o.getName().equals(cond)) {
					return suc;
				}
			}
		}
		return null;
	}

}

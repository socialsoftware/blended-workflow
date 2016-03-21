package org.blended.activity.terminal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.blended.activity.activity.Activity;
import org.blended.activity.activity.ActivityModel;
import org.blended.common.common.EntityAchieveCondition;
import org.blended.common.common.MandatoryAttributeAchieveCondition;
import org.blended.common.common.NotMandatoryAttributeAchieveCondition;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.dto.ActivityDTO;
import org.blended.common.repository.resttemplate.dto.DefPathConditionDTO;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.ValueException;
import org.eclipse.emf.ecore.EObject;

import com.beust.jcommander.ParameterException;

import utils.Utils;

public class ManageSplit {

	public static void execute(ActivityModel model, String name, String specId, CommandSplit split)
			throws ValueException {
		System.out.println("execute newActivityName:{}, sourceActivityName:{}, postConditions:{}" + split.name
				+ split.activity + split.postConditions);

		// CHECKS
		Set<String> postConditionsToSplit = new HashSet<String>(Arrays.asList(split.postConditions.split(";")));

		if (postConditionsToSplit.size() < 1)
			throw new ParameterException("Parameter not valid: One existing activity expected to be splitted");

		String sourceActivityName = split.activity;
		String newActivityName = split.name;

		Optional<Activity> sourceActivityO = Utils.getActivityByName(model, sourceActivityName);
		Optional<Activity> newActivitylNameO = Utils.getActivityByName(model, newActivityName);
		if (!sourceActivityO.isPresent())
			throw new ValueException("Parameter not valid: One existing activity expected to be splitted");
		if (newActivitylNameO.isPresent())
			throw new ValueException("Parameter not valid: No existing activity expected with the same name");
		Activity sourceActivity = sourceActivityO.get();

		CommonInterface ci = CommonInterface.getInstance();

		ActivityDTO activityDTO = ci.extractActivity(specId, newActivityName, sourceActivityName,
				getDefPathConditionSet(sourceActivity, specId, postConditionsToSplit));

		Utils.removeActivityModel(model);

		Utils.loadActivityModelFromServer(model, specId);

		ConsoleManagement.write(name, "Operation performed. New goal " + activityDTO.getName() + " generated");
	}

	private static Set<DefPathConditionDTO> getDefPathConditionSet(Activity sourceActivity, String specId,
			Set<String> postConditionsToSplit) throws ValueException {
		Set<DefPathConditionDTO> defs = new HashSet<DefPathConditionDTO>();

		for (EObject eo : sourceActivity.getPost()) {
			if (eo instanceof EntityAchieveCondition) {
				String name = ((EntityAchieveCondition) eo).getName();
				if (postConditionsToSplit.contains(name)) {
					defs.add(new DefPathConditionDTO(specId, name));
				}
			} else if (eo instanceof MandatoryAttributeAchieveCondition) {
				String attribute = ((MandatoryAttributeAchieveCondition) eo).getAttribute();
				if (postConditionsToSplit.contains(attribute)) {
					defs.add(new DefPathConditionDTO(specId, attribute));
				}
			} else if (eo instanceof NotMandatoryAttributeAchieveCondition) {
				String attribute = ((NotMandatoryAttributeAchieveCondition) eo).getAttribute();
				if (postConditionsToSplit.contains(attribute)) {
					defs.add(new DefPathConditionDTO(specId, attribute));
				}
			}
		}

		if (postConditionsToSplit.size() != defs.size()) {
			throw new ValueException("Parameter not valid: All Achieve conditions should exist in the source goal");
		}

		return defs;
	}

}

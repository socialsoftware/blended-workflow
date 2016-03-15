package org.blended.activity.terminal;

import java.util.Optional;

import org.blended.activity.activity.Activity;
import org.blended.activity.activity.ActivityModel;
import org.blended.common.repository.CommonInterface;
import org.blended.common.repository.resttemplate.dto.ActivityDTO;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.ValueException;

import com.beust.jcommander.ParameterException;

import utils.Utils;

public class ManageJoin {

	public static void execute(ActivityModel model, String name, String specId, CommandJoin join)
			throws ParameterException, ValueException {
		CommonInterface ci = CommonInterface.getInstance();

		// CHECKS
		if (join.activities.size() != 2)
			throw new ParameterException(
					"The number of activities should be two (found " + join.activities.size() + ")");
		String activityName1 = join.activities.get(0);
		String activityName2 = join.activities.get(1);

		Optional<Activity> activityO1 = Utils.getActivityByName(model, activityName1);
		Optional<Activity> activityO2 = Utils.getActivityByName(model, activityName2);
		Optional<Activity> newActivityO = Utils.getActivityByName(model, join.name);

		if ((!activityO1.isPresent()) || (!activityO2.isPresent()))
			throw new ValueException("Parameter not valid: Two existing activities expected");
		if (newActivityO.isPresent())
			throw new ValueException("Parameter not valid: Existing activity expected with the same name");

		ActivityDTO activityDTO = ci.mergeActivities(specId, join.name, activityName1, activityName2);

		Utils.removeActivityModel(model);

		Utils.loadActivityModelFromServer(model, specId);

		ConsoleManagement.write(name, "Operation performed. New activity " + activityDTO.getName() + " generated");
	}

}

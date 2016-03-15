package org.blended.activity.terminal;

import java.util.Optional;

import org.blended.activity.activity.Activity;
import org.blended.activity.activity.ActivityModel;
import org.blended.common.repository.CommonInterface;
import org.blended.common.utils.ConsoleManagement;
import org.blended.common.utils.ValueException;

import com.beust.jcommander.ParameterException;

import utils.Utils;

public class ManageSeq {

	public static void execute(ActivityModel model, String name, String specId, CommandSeq seq)
			throws ParameterException, ValueException {
		CommonInterface ci = CommonInterface.getInstance();

		// CHECKS
		Optional<Activity> activity = Utils.getActivityByName(model, seq.activity);

		if (!activity.isPresent())
			throw new ValueException("Parameter not valid: Activity expected");

		if (seq.operation.equals("add")) {
			ci.addSequenceConditionToActivity(specId, seq.activity, seq.path);
		} else if (seq.operation.equals("rem")) {
			ci.removeSequenceConditionToActivity(specId, seq.activity, seq.path);
		} else {
			throw new ValueException("Parameter not valid: Operation should be 'add' or 'rem'");
		}

		Utils.removeActivityModel(model);

		Utils.loadActivityModelFromServer(model, specId);

		ConsoleManagement.write(name, "Operation performed.");
	}

}

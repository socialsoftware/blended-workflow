package utils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.blended.activity.activity.Activity;
import org.blended.activity.activity.ActivityModel;
import org.blended.activity.repository.ReadActivityModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private static Logger log = LoggerFactory.getLogger(Utils.class);

	public static Optional<Activity> getActivityByName(ActivityModel model, String name) {
		Optional<Activity> activity = model.getActivities().stream().filter(e -> e.getName().equals(name)).findFirst();
		return activity;
	}

	public static void loadActivityModelFromServer(ActivityModel model, String specId) {
		ReadActivityModelService readActivityService = ReadActivityModelService.getInstance();
		readActivityService.read(specId, model);
	}

	public static void removeActivityModel(ActivityModel model) {
		Set<Activity> activities = new HashSet<Activity>();
		activities.addAll(model.getActivities());

		for (Activity activity : activities) {
			log.debug("activity {}", activity.getName());
			model.getActivities().remove(activity);
		}
	}

}

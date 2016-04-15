package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

@Deprecated
public class OldTaskModelInstance extends OldTaskModelInstance_Base {

	@Override
	public Activity getActivity(String name) throws BWException {
		for (Activity task : getActivitySet()) {
			if (task.getName().equals(name)) {
				return task;
			}
		}
		throw new BWException(BWErrorType.NON_EXISTENT_ACTIVITY_NAME, name);
	}
}

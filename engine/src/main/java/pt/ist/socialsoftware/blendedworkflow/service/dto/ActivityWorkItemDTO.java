package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.Map;
import java.util.Set;

public class ActivityWorkItemDTO {
	String specId;
	String instanceName;
	String activityName;
	Map<String, Set<String>> context;

	public ActivityWorkItemDTO() {

	}

	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Map<String, Set<String>> getContext() {
		return context;
	}

	public void setContext(Map<String, Set<String>> context) {
		this.context = context;
	}

}

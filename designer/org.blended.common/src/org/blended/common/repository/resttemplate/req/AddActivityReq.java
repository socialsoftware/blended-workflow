package org.blended.common.repository.resttemplate.req;

import java.util.Set;

import org.blended.common.repository.resttemplate.dto.DefPathConditionDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddActivityReq {
	private String activityName;
	private String description;
	private Set<DefPathConditionDTO> postConditionSet;

	public AddActivityReq(String activityName, String description, Set<DefPathConditionDTO> postConditionSet) {
		this.activityName = activityName;
		this.description = description;
		this.postConditionSet = postConditionSet;
	}

	public AddActivityReq() {
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<DefPathConditionDTO> getPostConditionSet() {
		return postConditionSet;
	}

	public void setPostConditionSet(Set<DefPathConditionDTO> postConditionSet) {
		this.postConditionSet = postConditionSet;
	}
}

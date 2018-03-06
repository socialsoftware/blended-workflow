package pt.ist.socialsoftware.blendedworkflow.core.service.req;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.DefPathConditionDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddActivityReq {
	private String activityName;
	private String description;
	private Set<DefPathConditionDTO> postConditionSet;

	public AddActivityReq() {
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<DefPathConditionDTO> getPostConditionSet() {
		return this.postConditionSet;
	}

	public void setPostConditionSet(Set<DefPathConditionDTO> postConditionSet) {
		this.postConditionSet = postConditionSet;
	}

}

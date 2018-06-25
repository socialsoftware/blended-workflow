package pt.ist.socialsoftware.blendedworkflow.core.service.dto.req;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefPathConditionDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddActivityDto {
	private String activityName;
	private String description;
	private Set<DefPathConditionDTO> postConditionSet;

	public AddActivityDto() {
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

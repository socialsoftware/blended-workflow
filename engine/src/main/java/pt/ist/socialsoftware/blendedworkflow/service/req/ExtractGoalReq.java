package pt.ist.socialsoftware.blendedworkflow.service.req;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.service.dto.DefPathConditionDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractGoalReq {
	private String newGoalName;
	private String sourceGoalName;
	private Set<DefPathConditionDTO> successConditions;

	public ExtractGoalReq() {
	}

	public String getNewGoalName() {
		return newGoalName;
	}

	public void setNewGoalName(String newGoalName) {
		this.newGoalName = newGoalName;
	}

	public String getSourceGoalName() {
		return sourceGoalName;
	}

	public void setSourceGoalName(String sourceGoalName) {
		this.sourceGoalName = sourceGoalName;
	}

	public Set<DefPathConditionDTO> getSuccessConditions() {
		return successConditions;
	}

	public void setSuccessConditions(Set<DefPathConditionDTO> successConditions) {
		this.successConditions = successConditions;
	}

}

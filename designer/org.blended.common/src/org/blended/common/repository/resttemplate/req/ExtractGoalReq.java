package org.blended.common.repository.resttemplate.req;

import java.util.Set;

import org.blended.common.repository.resttemplate.dto.DefPathConditionDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	public void setSuccessCondition(Set<DefPathConditionDTO> successConditions) {
		this.successConditions = successConditions;
	}

}

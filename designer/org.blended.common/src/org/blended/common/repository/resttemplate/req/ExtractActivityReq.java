package org.blended.common.repository.resttemplate.req;

import java.util.Set;

import org.blended.common.repository.resttemplate.dto.DefPathConditionDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractActivityReq {
	private String newActivityName;
	private String sourceActivityName;
	private Set<DefPathConditionDTO> successConditions;

	public ExtractActivityReq() {
	}

	public String getNewActivityName() {
		return newActivityName;
	}

	public void setNewActivityName(String newActivityName) {
		this.newActivityName = newActivityName;
	}

	public String getSourceActivityName() {
		return sourceActivityName;
	}

	public void setSourceActivityName(String sourceActivityName) {
		this.sourceActivityName = sourceActivityName;
	}

	public Set<DefPathConditionDTO> getSuccessConditions() {
		return successConditions;
	}

	public void setSuccessConditions(Set<DefPathConditionDTO> successConditions) {
		this.successConditions = successConditions;
	}
}

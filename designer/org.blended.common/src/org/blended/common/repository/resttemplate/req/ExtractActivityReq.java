package org.blended.common.repository.resttemplate.req;

import org.blended.common.repository.resttemplate.dto.DefProductConditionSetDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractActivityReq {
	private String newActivityName;
	private String sourceActivityName;
	private DefProductConditionSetDTO successCondition;

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

	public DefProductConditionSetDTO getSuccessCondition() {
		return successCondition;
	}

	public void setSuccessCondition(DefProductConditionSetDTO successCondition) {
		this.successCondition = successCondition;
	}
}

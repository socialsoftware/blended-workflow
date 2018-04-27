package pt.ist.socialsoftware.blendedworkflow.designer.remote.resourcemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RALExprDelegatedByPositionExprDTO extends RALExpressionDTO {
	private String position;

	public RALExprDelegatedByPositionExprDTO() {
		
	}

	public RALExprDelegatedByPositionExprDTO(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
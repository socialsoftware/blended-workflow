package pt.ist.socialsoftware.blendedworkflow.service.req;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.dto.DefPathConditionDTO;

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

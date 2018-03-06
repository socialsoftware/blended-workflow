package pt.ist.socialsoftware.blendedworkflow.core.service.req;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.DefPathConditionDTO;

public class ExtractActivityReq {
	private String newActivityName;
	private String sourceActivityName;
	private Set<DefPathConditionDTO> successConditions;

	public ExtractActivityReq() {

	}

	public String getNewActivityName() {
		return this.newActivityName;
	}

	public void setNewActivityName(String newActivityName) {
		this.newActivityName = newActivityName;
	}

	public String getSourceActivityName() {
		return this.sourceActivityName;
	}

	public void setSourceActivityName(String sourceActivityName) {
		this.sourceActivityName = sourceActivityName;
	}

	public Set<DefPathConditionDTO> getSuccessConditions() {
		return this.successConditions;
	}

	public void setSuccessConditions(Set<DefPathConditionDTO> successConditions) {
		this.successConditions = successConditions;
	}

}

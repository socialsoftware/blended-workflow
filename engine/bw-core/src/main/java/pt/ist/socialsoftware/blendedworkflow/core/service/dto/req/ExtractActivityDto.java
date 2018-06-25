package pt.ist.socialsoftware.blendedworkflow.core.service.dto.req;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefPathConditionDTO;

public class ExtractActivityDto {
	private String newActivityName;
	private String sourceActivityName;
	private Set<DefPathConditionDTO> successConditions;

	public ExtractActivityDto() {

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

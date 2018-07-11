package pt.ist.socialsoftware.blendedworkflow.core.service.dto.req;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefPathConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.MulConditionDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractGoalDto {
	private String newGoalName;
	private String sourceGoalName;
	private Set<DefPathConditionDTO> successConditions;
	private Set<MulConditionDTO> mulconditions;

	public ExtractGoalDto() {
	}

	public String getNewGoalName() {
		return this.newGoalName;
	}

	public void setNewGoalName(String newGoalName) {
		this.newGoalName = newGoalName;
	}

	public String getSourceGoalName() {
		return this.sourceGoalName;
	}

	public void setSourceGoalName(String sourceGoalName) {
		this.sourceGoalName = sourceGoalName;
	}

	public Set<DefPathConditionDTO> getSuccessConditions() {
		return this.successConditions;
	}

	public void setSuccessConditions(Set<DefPathConditionDTO> successConditions) {
		this.successConditions = successConditions;
	}

	public Set<MulConditionDTO> getMulconditions() {
		return this.mulconditions;
	}

	public void setMulconditions(Set<MulConditionDTO> mulconditions) {
		this.mulconditions = mulconditions;
	}

}

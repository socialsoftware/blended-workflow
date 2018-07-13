package pt.ist.socialsoftware.blendedworkflow.core.service.dto.req;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefPathConditionDTO;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RelationDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractGoalDto {
	private String newGoalName;
	private String sourceGoalName;
	private Set<DefPathConditionDTO> successConditions;
	private Set<RelationDTO> relations;

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

	public Set<RelationDTO> getRelations() {
		return this.relations;
	}

	public void setRelations(Set<RelationDTO> relations) {
		this.relations = relations;
	}

}

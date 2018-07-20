package pt.ist.socialsoftware.blendedworkflow.core.service.dto.req;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.DefPathConditionDto;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.RelationDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractGoalDto {
	private String newGoalName;
	private String sourceGoalName;
	private Set<DefPathConditionDto> successConditions;
	private Set<RelationDto> relations;

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

	public Set<DefPathConditionDto> getSuccessConditions() {
		return this.successConditions;
	}

	public void setSuccessConditions(Set<DefPathConditionDto> successConditions) {
		this.successConditions = successConditions;
	}

	public Set<RelationDto> getRelations() {
		return this.relations;
	}

	public void setRelations(Set<RelationDto> relations) {
		this.relations = relations;
	}

}

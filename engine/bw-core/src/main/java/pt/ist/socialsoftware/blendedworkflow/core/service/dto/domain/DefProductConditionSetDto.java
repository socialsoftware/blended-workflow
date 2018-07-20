package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefProductConditionSetDto {
	private Set<DefEntityConditionDto> defEnts = new HashSet<>();
	private Set<DefAttributeConditionDto> defAtts = new HashSet<>();

	public DefProductConditionSetDto() {
	}

	public Set<DefEntityConditionDto> getDefEnts() {
		return this.defEnts;
	}

	public void setDefEnts(Set<DefEntityConditionDto> defEnts) {
		this.defEnts = defEnts;
	}

	public Set<DefAttributeConditionDto> getDefAtts() {
		return this.defAtts;
	}

	public void setDefAtts(Set<DefAttributeConditionDto> defAtts) {
		this.defAtts = defAtts;
	}

}

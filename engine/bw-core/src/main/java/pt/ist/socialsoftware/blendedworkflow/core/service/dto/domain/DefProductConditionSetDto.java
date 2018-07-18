package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefProductConditionSetDto {
	private Set<DefEntityConditionDTO> defEnts = new HashSet<>();
	private Set<DefAttributeConditionDTO> defAtts = new HashSet<>();

	public DefProductConditionSetDto() {
	}

	public Set<DefEntityConditionDTO> getDefEnts() {
		return this.defEnts;
	}

	public void setDefEnts(Set<DefEntityConditionDTO> defEnts) {
		this.defEnts = defEnts;
	}

	public Set<DefAttributeConditionDTO> getDefAtts() {
		return this.defAtts;
	}

	public void setDefAtts(Set<DefAttributeConditionDTO> defAtts) {
		this.defAtts = defAtts;
	}

}

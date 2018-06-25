package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefProductConditionSetDTO {
	private Set<DefEntityConditionDTO> defEnts;
	private Set<DefAttributeConditionDTO> defAtts;

	public DefProductConditionSetDTO() {
	}

	public Set<DefEntityConditionDTO> getDefEnts() {
		return defEnts;
	}

	public void setDefEnts(Set<DefEntityConditionDTO> defEnts) {
		this.defEnts = defEnts;
	}

	public Set<DefAttributeConditionDTO> getDefAtts() {
		return defAtts;
	}

	public void setDefAtts(Set<DefAttributeConditionDTO> defAtts) {
		this.defAtts = defAtts;
	}

}

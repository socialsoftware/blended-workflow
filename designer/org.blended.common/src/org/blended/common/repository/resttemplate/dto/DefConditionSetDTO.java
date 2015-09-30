package org.blended.common.repository.resttemplate.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefConditionSetDTO {
    private Set<DefEntityConditionDTO> defEnts;
    private Set<DefAttributeConditionDTO> defAtts;

    public DefConditionSetDTO() {
    }

    public DefConditionSetDTO(Set<DefEntityConditionDTO> defEnts,
            Set<DefAttributeConditionDTO> defAtts) {
        this.defEnts = defEnts;
        this.defAtts = defAtts;
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

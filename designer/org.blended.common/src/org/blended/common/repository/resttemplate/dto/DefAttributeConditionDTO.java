package org.blended.common.repository.resttemplate.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefAttributeConditionDTO {
    private String specId;
    private Set<String> paths;
    private boolean mandatory;
    private String attributeExtId;

    public DefAttributeConditionDTO() {
    }

    public DefAttributeConditionDTO(String specId, Set<String> paths,
            boolean mandatory) {
        this.setSpecId(specId);
        this.paths = paths;
        this.mandatory = mandatory;
    }

    public DefAttributeConditionDTO(String specId, Set<String> paths) {
        this.setSpecId(specId);
        this.paths = paths;
    }

    public Set<String> getPaths() {
        return paths;
    }

    public void setPaths(Set<String> paths) {
        this.paths = paths;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getAttributeExtId() {
        return attributeExtId;
    }

    public void setAttributeExtId(String attributeExtId) {
        this.attributeExtId = attributeExtId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

}

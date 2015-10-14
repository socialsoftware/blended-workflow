package org.blended.common.repository.resttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefAttributeConditionDTO {
    private String specId;
    private String path;
    private boolean mandatory;
    private String attributeExtId;

    public DefAttributeConditionDTO() {
    }

    public DefAttributeConditionDTO(String specId, String path,
            boolean mandatory) {
        this.setSpecId(specId);
        this.path = path;
        this.mandatory = mandatory;
    }

    public DefAttributeConditionDTO(String specId, String path) {
        this.setSpecId(specId);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

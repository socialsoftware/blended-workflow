package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.Set;

public class DefAttributeConditionDTO {
    private String specId;
    private Set<String> paths;
    private boolean mandatory;
    private String attributeExtId;

    public DefAttributeConditionDTO() {
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
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

}

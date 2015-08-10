package org.blended.common.repository.resttemplate.vo;

import java.util.Set;

public class DefAttributeConditionVO {
    private String conditionModelExtId;
    private Set<String> paths;
    private boolean mandatory;
    private String attributeExtId;
    private String attributeGroupExtId;

    public DefAttributeConditionVO() {
    }

    public DefAttributeConditionVO(String conditionModelExtId,
            Set<String> paths, boolean mandatory) {
        this.conditionModelExtId = conditionModelExtId;
        this.paths = paths;
        this.mandatory = mandatory;
    }

    public String getConditionModelExtId() {
        return conditionModelExtId;
    }

    public void setConditionModelExtId(String conditionModelExtId) {
        this.conditionModelExtId = conditionModelExtId;
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

    public String getAttributeGroupExtId() {
        return attributeGroupExtId;
    }

    public void setAttributeGroupExtId(String attributeGroupExtId) {
        this.attributeGroupExtId = attributeGroupExtId;
    }

}

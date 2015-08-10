package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeGroupVO extends ProductVO {
    private String entityExtId;
    private String name;
    private boolean isMandatory;

    public AttributeGroupVO() {
    }

    public AttributeGroupVO(String entityExtId, String name,
            boolean isMandatory) {
        this.entityExtId = entityExtId;
        this.name = name;
        this.isMandatory = isMandatory;
    }

    public String getEntityExtId() {
        return entityExtId;
    }

    public void setEntityExtId(String entityExtId) {
        this.entityExtId = entityExtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

}

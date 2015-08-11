package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeVO extends ProductVO {
    private String entityExtId;
    private String groupExtId;
    private String name;
    private String type;
    private boolean isMandatory;

    public AttributeVO() {
    }

    public AttributeVO(String specId, String entityExtId, String groupExtId,
            String name, String type, boolean isMandatory) {
        setSpecId(specId);
        this.entityExtId = entityExtId;
        this.groupExtId = groupExtId;
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

    public String getEntityExtId() {
        return entityExtId;
    }

    public void setEntityExtId(String entityExtId) {
        this.entityExtId = entityExtId;
    }

    public String getGroupExtId() {
        return groupExtId;
    }

    public void setGroupExtId(String groupExtId) {
        this.groupExtId = groupExtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

}

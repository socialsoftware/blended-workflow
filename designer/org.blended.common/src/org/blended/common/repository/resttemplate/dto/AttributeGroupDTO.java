package org.blended.common.repository.resttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttributeGroupDTO extends ProductDTO {
    private String entityExtId;
    private String entityName;
    private String name;
    private boolean isMandatory;

    public AttributeGroupDTO() {
    }

    public AttributeGroupDTO(String specId, String entityExtId, String name,
            boolean isMandatory) {
        setSpecId(specId);
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

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
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

package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefEntityConditionVO {
    private String specId;
    private String extId;
    private String entityName;
    private boolean exists;

    public DefEntityConditionVO() {
    }

    public DefEntityConditionVO(String specId, String entityName,
            boolean exists) {
        this.specId = specId;
        this.entityName = entityName;
        this.exists = exists;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

}

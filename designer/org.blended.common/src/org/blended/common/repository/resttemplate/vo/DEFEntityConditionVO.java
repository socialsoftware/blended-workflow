package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DEFEntityConditionVO {
    private String extId;
    private String conditionModelExtId;
    private String entityName;
    private boolean exists;

    public DEFEntityConditionVO() {
    }

    public DEFEntityConditionVO(String conditionModelExtId,
            String entityName, boolean exists) {
        this.conditionModelExtId = conditionModelExtId;
        this.entityName = entityName;
        this.exists = exists;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getConditionModelExtId() {
        return conditionModelExtId;
    }

    public void setConditionModelExtId(String conditionModelExtId) {
        this.conditionModelExtId = conditionModelExtId;
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

package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityVO extends ProductVO {
    private String name;
    private boolean exists;

    public EntityVO() {
    }

    public EntityVO(String dataModelExtId, String name, boolean exists) {
        setDataModelExtId(dataModelExtId);
        this.name = name;
        this.exists = exists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

}

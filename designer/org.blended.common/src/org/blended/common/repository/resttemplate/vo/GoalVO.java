package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoalVO {
    private String specId;
    private String extId;
    private String name;

    public GoalVO() {
    }

    public GoalVO(String specId, String name) {
        this.specId = specId;
        this.name = name;
    }

    public GoalVO(String specId, String extId, String name) {
        this.extId = extId;
        this.specId = specId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoalVO {
    private String extId;
    private String goalModelExtId;
    private String name;

    public GoalVO() {
    }

    public GoalVO(String goalModelExtId, String name) {
        this.goalModelExtId = goalModelExtId;
        this.name = name;
    }

    public GoalVO(String extId, String goalModelExtId, String name) {
        this.extId = extId;
        this.goalModelExtId = goalModelExtId;
        this.name = name;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getGoalModelExtId() {
        return goalModelExtId;
    }

    public void setGoalModelExtId(String goalModelExtId) {
        this.goalModelExtId = goalModelExtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

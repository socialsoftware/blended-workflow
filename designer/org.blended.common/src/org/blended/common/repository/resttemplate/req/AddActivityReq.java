package org.blended.common.repository.resttemplate.req;

import org.blended.common.repository.resttemplate.dto.DefConditionSetDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddActivityReq {
    private String activityName;
    private String description;
    private DefConditionSetDTO postConditionSet;

    public AddActivityReq() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DefConditionSetDTO getPostConditionSet() {
        return postConditionSet;
    }

    public void setPostConditionSet(DefConditionSetDTO postConditionSet) {
        this.postConditionSet = postConditionSet;
    }
}

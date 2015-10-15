package org.blended.common.repository.resttemplate.req;

import org.blended.common.repository.resttemplate.dto.DefProductConditionSetDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddActivityReq {
    private String activityName;
    private String description;
    private DefProductConditionSetDTO postConditionSet;

    public AddActivityReq(String activityName, String description,
            DefProductConditionSetDTO postConditionSet) {
        this.activityName = activityName;
        this.description = description;
        this.postConditionSet = postConditionSet;
    }

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

    public DefProductConditionSetDTO getPostConditionSet() {
        return postConditionSet;
    }

    public void setPostConditionSet(DefProductConditionSetDTO postConditionSet) {
        this.postConditionSet = postConditionSet;
    }
}

package pt.ist.socialsoftware.blendedworkflow.service.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.service.dto.DefConditionSetDTO;

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
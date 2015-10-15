package pt.ist.socialsoftware.blendedworkflow.service.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.service.dto.DefProductConditionSetDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddActivityReq {
    private String activityName;
    private String description;
    private DefProductConditionSetDTO postConditionSet;

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

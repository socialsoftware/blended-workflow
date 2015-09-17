package pt.ist.socialsoftware.blendedworkflow.service.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.service.dto.SuccessConditionDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractSiblingGoalReq {
    private String newGoalName;
    private String goalName;
    private SuccessConditionDTO successCondition;

    public ExtractSiblingGoalReq() {
    }

    public String getNewGoalName() {
        return newGoalName;
    }

    public void setNewGoalName(String newGoalName) {
        this.newGoalName = newGoalName;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public SuccessConditionDTO getSuccessCondition() {
        return successCondition;
    }

    public void setSuccessCondition(SuccessConditionDTO successCondition) {
        this.successCondition = successCondition;
    }

}

package org.blended.common.repository.resttemplate.req;

import org.blended.common.repository.resttemplate.dto.SuccessConditionDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

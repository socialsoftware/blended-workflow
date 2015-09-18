package org.blended.common.repository.resttemplate.req;

import org.blended.common.repository.resttemplate.dto.SuccessConditionDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractChildGoalReq {
    private String newGoalName;
    private String parentGoalName;
    private SuccessConditionDTO successCondition;

    public ExtractChildGoalReq() {
    }

    public String getNewGoalName() {
        return newGoalName;
    }

    public void setNewGoalName(String newGoalName) {
        this.newGoalName = newGoalName;
    }

    public String getParentGoalName() {
        return parentGoalName;
    }

    public void setParentGoalName(String parentGoalName) {
        this.parentGoalName = parentGoalName;
    }

    public SuccessConditionDTO getSuccessCondition() {
        return successCondition;
    }

    public void setSuccessCondition(SuccessConditionDTO successCondition) {
        this.successCondition = successCondition;
    }

}

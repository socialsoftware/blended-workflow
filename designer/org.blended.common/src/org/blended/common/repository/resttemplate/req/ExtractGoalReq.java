package org.blended.common.repository.resttemplate.req;

import org.blended.common.repository.resttemplate.dto.DefConditionSetDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractGoalReq {
    private String newGoalName;
    private String sourceGoalName;
    private DefConditionSetDTO successCondition;

    public ExtractGoalReq() {
    }

    public String getNewGoalName() {
        return newGoalName;
    }

    public void setNewGoalName(String newGoalName) {
        this.newGoalName = newGoalName;
    }

    public String getSourceGoalName() {
        return sourceGoalName;
    }

    public void setSourceGoalName(String sourceGoalName) {
        this.sourceGoalName = sourceGoalName;
    }

    public DefConditionSetDTO getSuccessCondition() {
        return successCondition;
    }

    public void setSuccessCondition(DefConditionSetDTO successCondition) {
        this.successCondition = successCondition;
    }

}

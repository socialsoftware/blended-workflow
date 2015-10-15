package org.blended.common.repository.resttemplate.req;

import org.blended.common.repository.resttemplate.dto.DefProductConditionSetDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractGoalReq {
    private String newGoalName;
    private String sourceGoalName;
    private DefProductConditionSetDTO successCondition;

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

    public DefProductConditionSetDTO getSuccessCondition() {
        return successCondition;
    }

    public void setSuccessCondition(DefProductConditionSetDTO successCondition) {
        this.successCondition = successCondition;
    }

}

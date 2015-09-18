package pt.ist.socialsoftware.blendedworkflow.service.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pt.ist.socialsoftware.blendedworkflow.service.dto.SuccessConditionDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtractGoalReq {
    private String newGoalName;
    private String sourceGoalName;
    private SuccessConditionDTO successCondition;

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

    public SuccessConditionDTO getSuccessCondition() {
        return successCondition;
    }

    public void setSuccessCondition(SuccessConditionDTO successCondition) {
        this.successCondition = successCondition;
    }

}

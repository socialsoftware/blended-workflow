package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

import java.util.List;

public class GoalModelDto {
    private String specId;
    private List<GoalDto> goals;

    public GoalModelDto() {
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public List<GoalDto> getGoals() {
        return goals;
    }

    public void setGoals(List<GoalDto> goals) {
        this.goals = goals;
    }

}

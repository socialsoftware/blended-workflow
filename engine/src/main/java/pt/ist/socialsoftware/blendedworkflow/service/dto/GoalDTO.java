package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class GoalDTO {
    private String extId;
    private String goalModelExtId;
    private String name;

    public GoalDTO() {
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getGoalModelExtId() {
        return goalModelExtId;
    }

    public void setGoalModelExtId(String goalModelExtId) {
        this.goalModelExtId = goalModelExtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

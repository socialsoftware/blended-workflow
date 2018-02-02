package pt.ist.socialsoftware.blendedworkflow.core.service.dto;

public class GoalDTO {
    private String specId;
    private String extId;
    private String name;

    public GoalDTO() {
    }

    public GoalDTO(String specId) {
        this.specId = specId;
        this.extId = null;
        this.name = null;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

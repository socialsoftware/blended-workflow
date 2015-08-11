package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class MulConditionDTO {
    private String specId;
    private String rolePath;
    private String cardinality;

    public MulConditionDTO() {
    }

    public MulConditionDTO(String specId, String rolePath, String cardinality) {
        this.specId = specId;
        this.rolePath = rolePath;
        this.cardinality = cardinality;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getRolePath() {
        return rolePath;
    }

    public void setRolePath(String rolePath) {
        this.rolePath = rolePath;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

}

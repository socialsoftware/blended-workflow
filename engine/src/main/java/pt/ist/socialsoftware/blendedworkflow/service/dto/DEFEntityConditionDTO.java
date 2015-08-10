package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class DEFEntityConditionDTO {
    private String extId;
    private String conditionModelExtId;
    private String entityName;
    private boolean exists;

    public DEFEntityConditionDTO() {
    }

    public DEFEntityConditionDTO(String conditionModelExtId,
            String entityName, boolean exists) {
        this.conditionModelExtId = conditionModelExtId;
        this.entityName = entityName;
        this.exists = exists;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getConditionModelExtId() {
        return conditionModelExtId;
    }

    public void setConditionModelExtId(String conditionModelExtId) {
        this.conditionModelExtId = conditionModelExtId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

}

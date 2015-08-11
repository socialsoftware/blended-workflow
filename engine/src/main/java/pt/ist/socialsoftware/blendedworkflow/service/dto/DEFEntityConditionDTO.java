package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class DEFEntityConditionDTO {
    private String specId;
    private String extId;
    private String entityName;
    private boolean exists;

    public DEFEntityConditionDTO() {
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

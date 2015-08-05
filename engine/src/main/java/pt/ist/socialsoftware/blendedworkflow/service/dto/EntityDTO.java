package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class EntityDTO {
    public String getDataModelExtId() {
        return dataModelExtId;
    }

    public void setDataModelExtId(String dataModelExtId) {
        this.dataModelExtId = dataModelExtId;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public boolean getExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String extId;
    private String dataModelExtId;
    private String name;
    private boolean exists;

    public EntityDTO() {
    }

    public EntityDTO(String extId, String dataModelextId, String name,
            boolean exists) {
        this.extId = extId;
        this.dataModelExtId = dataModelextId;
        this.name = name;
        this.exists = exists;
    }

    public EntityDTO(String dataModelextId, String name, boolean exists) {
        this.dataModelExtId = dataModelextId;
        this.name = name;
        this.exists = exists;
    }

}

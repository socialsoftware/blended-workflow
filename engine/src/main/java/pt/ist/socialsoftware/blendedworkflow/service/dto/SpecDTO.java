package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class SpecDTO {
    private String externalId;
    private String specId;
    private String name;
    private String dataModelExtId;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataModelExtId() {
        return dataModelExtId;
    }

    public void setDataModelExtId(String dataModelExtId) {
        this.dataModelExtId = dataModelExtId;
    }

    public SpecDTO() {
    }

    public SpecDTO(String specId, String name) {
        this.specId = specId;
        this.name = name;
    }

    public SpecDTO(String specId) {
        this.specId = specId;
    }

}

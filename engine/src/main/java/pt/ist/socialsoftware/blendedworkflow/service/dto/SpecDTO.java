package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class SpecDTO {
    private String specId;
    private String name;

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

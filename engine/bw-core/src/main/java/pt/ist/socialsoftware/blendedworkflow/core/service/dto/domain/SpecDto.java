package pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain;

public class SpecDto {
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

    public SpecDto() {
    }

    public SpecDto(String specId, String name) {
        this.specId = specId;
        this.name = name;
    }

    public SpecDto(String specId) {
        this.specId = specId;
    }

}

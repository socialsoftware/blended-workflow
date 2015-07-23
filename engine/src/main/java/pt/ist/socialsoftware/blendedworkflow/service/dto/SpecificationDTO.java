package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class SpecificationDTO {
    public String specId;
    public String name;

    public SpecificationDTO(String specId, String name) {
        this.specId = specId;
        this.name = name;
    }

    public SpecificationDTO(String specId) {
        this.specId = specId;
    }

}

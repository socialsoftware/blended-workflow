package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class EntityDTO {
    public SpecificationDTO specDTO;
    public String name;
    public boolean exists;

    public EntityDTO(String specId, String name, boolean exists) {
        this.specDTO = new SpecificationDTO(specId);
        this.name = name;
        this.exists = exists;
    }

    public EntityDTO(String specId, String name) {
        this.specDTO = new SpecificationDTO(specId);
        this.name = name;
    }
}

package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class EntityDTO {
    public SpecDTO specDTO;
    public String name;
    public boolean exists;

    public EntityDTO(String specId, String name, boolean exists) {
        this.specDTO = new SpecDTO(specId);
        this.name = name;
        this.exists = exists;
    }

    public EntityDTO(String specId, String name) {
        this.specDTO = new SpecDTO(specId);
        this.name = name;
    }
}

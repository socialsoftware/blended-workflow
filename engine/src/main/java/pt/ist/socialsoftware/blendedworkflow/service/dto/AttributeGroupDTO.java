package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class AttributeGroupDTO {
    public EntityDTO entDTO;
    public String name;

    public AttributeGroupDTO(EntityDTO entDTO, String name) {
        this.entDTO = entDTO;
        this.name = name;
    }

    public AttributeGroupDTO(String specId, String entName, String name) {
        this.entDTO = new EntityDTO(specId, entName);
        this.name = name;
    }

}

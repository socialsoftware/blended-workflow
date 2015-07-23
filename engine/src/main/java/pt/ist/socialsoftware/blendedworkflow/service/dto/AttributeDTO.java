package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class AttributeDTO {
    public EntityDTO entDTO;
    public AttributeGroupDTO groupDTO;
    public String name;
    public String type;

    public AttributeDTO(EntityDTO entDTO, String name, String type) {
        this.entDTO = entDTO;
        this.groupDTO = null;
        this.name = name;
        this.type = type;
    }

    public AttributeDTO(String specId, String entName, String name,
            String type) {
        this.entDTO = new EntityDTO(specId, entName);
        this.groupDTO = new AttributeGroupDTO(this.entDTO, null);
        this.name = name;
        this.type = type;
    }

    public AttributeDTO(String specId, String entName, String groupName,
            String name, String type) {
        this.entDTO = new EntityDTO(specId, entName);
        this.groupDTO = new AttributeGroupDTO(this.entDTO, groupName);
        this.name = name;
        this.type = type;
    }

}

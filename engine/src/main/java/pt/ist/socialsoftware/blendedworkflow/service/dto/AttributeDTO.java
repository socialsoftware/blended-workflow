package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class AttributeDTO {
    public EntityDTO entDTO;
    public AttributeGroupDTO groupDTO;
    public String name;
    public String type;
    public boolean isMandatory;

    public AttributeDTO(EntityDTO entDTO, String name, String type,
            boolean isMandatory) {
        this.entDTO = entDTO;
        this.groupDTO = null;
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

    public AttributeDTO(String specId, String entName, String name, String type,
            boolean isMandatory) {
        this.entDTO = new EntityDTO(specId, entName);
        this.groupDTO = new AttributeGroupDTO(this.entDTO, null, isMandatory);
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

    public AttributeDTO(String specId, String entName, String groupName,
            String name, String type, boolean isMandatory) {
        this.entDTO = new EntityDTO(specId, entName);
        this.groupDTO = new AttributeGroupDTO(this.entDTO, groupName,
                isMandatory);
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

}

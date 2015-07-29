package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class AttributeDTO {
    public EntityDTO entityDTO;
    public AttributeGroupDTO groupDTO;
    public String name;
    public String type;
    public boolean isMandatory;

    public AttributeDTO(EntityDTO entDTO, String name, String type,
            boolean isMandatory) {
        this.entityDTO = entDTO;
        this.groupDTO = null;
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

    public AttributeDTO(String specId, String entName, String name, String type,
            boolean isMandatory) {
        this.entityDTO = new EntityDTO(specId, entName);
        this.groupDTO = new AttributeGroupDTO(this.entityDTO, null, isMandatory);
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

    public AttributeDTO(String specId, String entName, String groupName,
            String name, String type, boolean isMandatory) {
        this.entityDTO = new EntityDTO(specId, entName);
        this.groupDTO = new AttributeGroupDTO(this.entityDTO, groupName,
                isMandatory);
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

}

package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class AttributeDTO {
    public String entityExtId;
    public AttributeGroupDTO groupDTO;
    public String name;
    public String type;
    public boolean isMandatory;

    public AttributeDTO(String entityExtId, String name, String type,
            boolean isMandatory) {
        this.entityExtId = entityExtId;
        this.groupDTO = null;
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

    public AttributeDTO(String entityExtId, String entName, String name,
            String type, boolean isMandatory) {
        this.entityExtId = entityExtId;
        this.groupDTO = new AttributeGroupDTO(this.entityExtId, null,
                isMandatory);
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

    public AttributeDTO(String entityExtId, String entName, String groupName,
            String name, String type, boolean isMandatory) {
        this.entityExtId = entityExtId;
        this.groupDTO = new AttributeGroupDTO(this.entityExtId, groupName,
                isMandatory);
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

}

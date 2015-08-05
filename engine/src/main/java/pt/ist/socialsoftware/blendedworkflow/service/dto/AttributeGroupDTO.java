package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class AttributeGroupDTO {
    public String entityExtId;
    public String name;
    public boolean isMandatory;

    public AttributeGroupDTO(String entityExtId, String name,
            boolean isMandatory) {
        this.entityExtId = entityExtId;
        this.name = name;
        this.isMandatory = isMandatory;
    }

}

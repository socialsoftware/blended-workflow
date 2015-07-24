package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class AttributeGroupDTO {
    public EntityDTO entDTO;
    public String name;
    public boolean isMandatory;

    public AttributeGroupDTO(EntityDTO entDTO, String name,
            boolean isMandatory) {
        this.entDTO = entDTO;
        this.name = name;
        this.isMandatory = isMandatory;
    }

    public AttributeGroupDTO(String specId, String entName, String name,
            boolean isMandatory) {
        this.entDTO = new EntityDTO(specId, entName);
        this.name = name;
        this.isMandatory = isMandatory;
    }

}

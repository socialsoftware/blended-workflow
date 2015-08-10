package pt.ist.socialsoftware.blendedworkflow.service.dto;

import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct.ProductType;

public class AttributeGroupDTO extends ProductDTO {
    private String entityExtId;
    private String name;
    private boolean isMandatory;

    public AttributeGroupDTO() {
    }

    public AttributeGroupDTO(String dataModelExtId, String entityExtId,
            String name, boolean isMandatory) {
        super(null, dataModelExtId, ProductType.ATTRIBUTE_GROUP.name());
        this.entityExtId = entityExtId;
        this.name = name;
        this.isMandatory = isMandatory;
    }

    public String getEntityExtId() {
        return entityExtId;
    }

    public void setEntityExtId(String entityExtId) {
        this.entityExtId = entityExtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

}

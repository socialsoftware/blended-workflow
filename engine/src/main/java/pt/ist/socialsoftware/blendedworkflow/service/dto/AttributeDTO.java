package pt.ist.socialsoftware.blendedworkflow.service.dto;

import pt.ist.socialsoftware.blendedworkflow.domain.Product.ProductType;

public class AttributeDTO extends ProductDTO {
    private String entityExtId;
    private String entityName;
    private String groupExtId;
    private String name;
    private String type;
    private boolean isMandatory;

    public AttributeDTO() {
    }

    public AttributeDTO(String specId, String entityExtId, String groupExtId,
            String name, String type, boolean isMandatory) {
        super(specId, null, ProductType.ATTRIBUTE_BASIC.name());
        this.entityExtId = entityExtId;
        this.groupExtId = groupExtId;
        this.name = name;
        this.type = type;
        this.isMandatory = isMandatory;
    }

    public String getEntityExtId() {
        return entityExtId;
    }

    public void setEntityExtId(String entityExtId) {
        this.entityExtId = entityExtId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getGroupExtId() {
        return groupExtId;
    }

    public void setGroupExtId(String groupExtId) {
        this.groupExtId = groupExtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

}

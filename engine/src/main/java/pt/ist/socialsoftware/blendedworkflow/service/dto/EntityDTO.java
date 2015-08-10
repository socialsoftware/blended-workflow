package pt.ist.socialsoftware.blendedworkflow.service.dto;

import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct.ProductType;

public class EntityDTO extends ProductDTO {
    private String name;
    private boolean exists;

    public EntityDTO() {
    }

    public EntityDTO(String extId, String dataModelExtId, String name,
            boolean exists) {
        super(extId, dataModelExtId, ProductType.ENTITY.name());
        this.name = name;
        this.exists = exists;
    }

    public EntityDTO(String dataModelExtId, String name, boolean exists) {
        super(null, dataModelExtId, ProductType.ENTITY.name());
        this.name = name;
        this.exists = exists;
    }

    public boolean getExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

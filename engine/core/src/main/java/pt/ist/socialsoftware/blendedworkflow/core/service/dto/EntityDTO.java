package pt.ist.socialsoftware.blendedworkflow.core.service.dto;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product.ProductType;

public class EntityDTO extends ProductDTO {
    private String name;
    private boolean exists;

    public EntityDTO() {
    }

    public EntityDTO(String specId, String extId, String name, boolean exists) {
        super(specId, extId, ProductType.ENTITY.name());
        this.name = name;
        this.exists = exists;
    }

    public EntityDTO(String specId, String name, boolean exists) {
        super(specId, null, ProductType.ENTITY.name());
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

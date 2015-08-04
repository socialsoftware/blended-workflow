package pt.ist.socialsoftware.blendedworkflow.service.dto;

import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct.ProductType;
import pt.ist.socialsoftware.blendedworkflow.service.BWError;

public class ProductDTO {
    public SpecDTO specDTO;
    public ProductType type;
    public EntityDTO entityDTO;
    public AttributeGroupDTO attributeGroupDTO;
    public AttributeDTO attributeDTO;
    public BWError error;

    public ProductDTO(SpecDTO specDTO, ProductType type,
            EntityDTO entityDTO) {
        this.specDTO = specDTO;
        this.type = type;
        this.entityDTO = entityDTO;
        this.attributeGroupDTO = null;
        this.attributeDTO = null;
        this.error = null;
    }

    public ProductDTO(SpecDTO specDTO, ProductType type,
            AttributeGroupDTO attributeGroupDTO) {
        this.specDTO = specDTO;
        this.type = type;
        this.entityDTO = null;
        this.attributeGroupDTO = attributeGroupDTO;
        this.attributeDTO = null;
        this.error = null;
    }

    public ProductDTO(SpecDTO specDTO, ProductType type,
            AttributeDTO attributeDTO) {
        this.specDTO = specDTO;
        this.type = type;
        this.entityDTO = null;
        this.attributeGroupDTO = null;
        this.attributeDTO = attributeDTO;
        this.error = null;
    }

    public ProductDTO(BWError error) {
        this.specDTO = null;
        this.type = null;
        this.entityDTO = null;
        this.attributeGroupDTO = null;
        this.attributeDTO = null;
        this.error = error;
    }

}

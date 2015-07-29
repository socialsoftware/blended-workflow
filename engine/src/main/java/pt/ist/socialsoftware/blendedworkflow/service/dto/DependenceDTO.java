package pt.ist.socialsoftware.blendedworkflow.service.dto;

import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct.ProductType;

public class DependenceDTO {

    public SpecificationDTO specDTO;
    public EntityDTO entDTO;
    public ProductType type;
    public String name;
    public String value;

    public DependenceDTO(String specId, String name, String value) {
        this.specDTO = new SpecificationDTO(specId);
        this.entDTO = null;
        this.type = ProductType.ENTITY;
        this.name = name;
        this.value = value;
    }

    public DependenceDTO(String specId, String entName, ProductType type,
            String name, String value) {
        this.specDTO = new SpecificationDTO(specId);
        this.entDTO = new EntityDTO(specId, entName);
        this.type = type;
        this.name = name;
        this.value = value;
    }

}

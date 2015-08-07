package pt.ist.socialsoftware.blendedworkflow.service.dto;

import pt.ist.socialsoftware.blendedworkflow.domain.BWProduct.ProductType;
import pt.ist.socialsoftware.blendedworkflow.service.BWError;

public class ProductDTO {
    private String productExtId;
    private ProductType type;
    private BWError error;

    public ProductDTO(String productExtId, ProductType type) {
        this.productExtId = productExtId;
        this.type = type;
        this.error = null;
    }

    public String getProductExtId() {
        return productExtId;
    }

    public void setProductExtId(String productExtId) {
        this.productExtId = productExtId;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public BWError getError() {
        return error;
    }

    public void setError(BWError error) {
        this.error = error;
    }

}

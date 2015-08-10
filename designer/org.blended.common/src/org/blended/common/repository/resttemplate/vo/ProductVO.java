package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductVO {
    private String productExtId;
    private String type;

    public ProductVO() {
    }

    public String getProductExtId() {
        return productExtId;
    }

    public void setProductExtId(String productExtId) {
        this.productExtId = productExtId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

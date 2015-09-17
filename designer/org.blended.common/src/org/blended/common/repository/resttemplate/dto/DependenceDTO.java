package org.blended.common.repository.resttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DependenceDTO {
    private String specId;
    private String extId;
    private String productExtId;
    private String path;

    public DependenceDTO() {
    }

    public DependenceDTO(String specId, String productExtId, String path) {
        this.specId = specId;
        this.productExtId = productExtId;
        this.path = path;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getProductExtId() {
        return productExtId;
    }

    public void setProductExtId(String productExtId) {
        this.productExtId = productExtId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}

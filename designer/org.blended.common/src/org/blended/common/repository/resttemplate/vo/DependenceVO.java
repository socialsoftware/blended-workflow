package org.blended.common.repository.resttemplate.vo;

public class DependenceVO {

    private String extId;
    private String productExtId;
    private String path;

    public DependenceVO() {
    }

    public DependenceVO(String productExtId, String path) {
        this.productExtId = productExtId;
        this.path = path;
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

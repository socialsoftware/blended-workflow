package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DependenceVO {

    private String extId;
    private String conditionModelExtId;
    private String productExtId;
    private String path1;
    private String path2;

    public DependenceVO() {
    }

    public DependenceVO(String productExtId, String path) {
        this.productExtId = productExtId;
        this.path1 = path;
    }

    public DependenceVO(String conditionModelExtId, String path1,
            String path2) {
        this.conditionModelExtId = conditionModelExtId;
        this.path1 = path1;
        this.path2 = path2;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getConditionModelExtId() {
        return conditionModelExtId;
    }

    public void setConditionModelExtId(String conditionModelExtId) {
        this.conditionModelExtId = conditionModelExtId;
    }

    public String getProductExtId() {
        return productExtId;
    }

    public void setProductExtId(String productExtId) {
        this.productExtId = productExtId;
    }

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path) {
        this.path1 = path;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

}

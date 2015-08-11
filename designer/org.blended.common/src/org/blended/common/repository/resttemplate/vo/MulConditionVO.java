package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MulConditionVO {
    private String specId;
    private String rolePath;
    private String cardinality;

    public MulConditionVO() {
    }

    public MulConditionVO(String specId, String rolePath, String cardinality) {
        this.specId = specId;
        this.rolePath = rolePath;
        this.cardinality = cardinality;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getRolePath() {
        return rolePath;
    }

    public void setRolePath(String rolePath) {
        this.rolePath = rolePath;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

}

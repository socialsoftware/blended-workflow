package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MulConditionVO {
    private String conditionModelExtId;
    private String rolePath;
    private String cardinality;

    public MulConditionVO() {
    }

    public MulConditionVO(String conditionModelExtId, String rolePath,
            String cardinality) {
        this.conditionModelExtId = conditionModelExtId;
        this.rolePath = rolePath;
        this.cardinality = cardinality;
    }

    public String getConditionModelExtId() {
        return conditionModelExtId;
    }

    public void setConditionModelExtId(String conditionModelExtId) {
        this.conditionModelExtId = conditionModelExtId;
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

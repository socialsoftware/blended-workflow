package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleVO {
    private String extId;
    private String dataModelExtId;
    private String name;
    private ExpressionVO expression;

    public RuleVO() {
    }

    public RuleVO(String dataModelExtId, String name, ExpressionVO expression) {
        this.extId = null;
        this.dataModelExtId = dataModelExtId;
        this.name = name;
        this.expression = expression;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getDataModelExtId() {
        return dataModelExtId;
    }

    public void setDataModelExtId(String dataModelExtId) {
        this.dataModelExtId = dataModelExtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpressionVO getExpression() {
        return expression;
    }

    public void setExpression(ExpressionVO expression) {
        this.expression = expression;
    }
}

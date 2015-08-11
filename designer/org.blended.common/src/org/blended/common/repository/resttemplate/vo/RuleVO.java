package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleVO {
    private String specId;
    private String extId;
    private String name;
    private ExpressionVO expression;

    public RuleVO() {
    }

    public RuleVO(String specId, String name, ExpressionVO expression) {
        this.specId = specId;
        this.extId = null;
        this.name = name;
        this.expression = expression;
    }

    public RuleVO(String specId, String name) {
        this.specId = specId;
        this.extId = null;
        this.name = name;
        this.expression = null;
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

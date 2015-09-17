package org.blended.common.repository.resttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDTO {
    private String specId;
    private String extId;
    private String name;
    private ExpressionDTO expression;

    public RuleDTO() {
    }

    public RuleDTO(String specId, String name, ExpressionDTO expression) {
        this.specId = specId;
        this.extId = null;
        this.name = name;
        this.expression = expression;
    }

    public RuleDTO(String specId, String name) {
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

    public ExpressionDTO getExpression() {
        return expression;
    }

    public void setExpression(ExpressionDTO expression) {
        this.expression = expression;
    }
}

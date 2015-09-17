package org.blended.common.repository.resttemplate.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressionDTO {

    // copy from the equivalent in engine ExpressionDTO
    public enum Type {
        AND, OR, NOT, ATT_DEF, EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, SMALLER, SMALLER_EQUAL, PLUS, MINUS, MUL, DIV, ATT_VALUE, STRING, INT, BOOL
    }

    private String specId;
    private String type;
    // when unary value
    private String value;
    // when unary not
    private ExpressionDTO unaryExpression;
    // when binary
    private ExpressionDTO leftExpression;
    private ExpressionDTO rightExpression;

    public ExpressionDTO() {
    }

    public ExpressionDTO(String specId, Type type, ExpressionDTO leftExpresssion,
            ExpressionDTO rightExpression) {
        this.specId = specId;
        this.type = type.name();
        this.value = null;
        this.unaryExpression = null;
        this.leftExpression = leftExpresssion;
        this.rightExpression = rightExpression;
    }

    public ExpressionDTO(String specId, Type type,
            ExpressionDTO unaryExpression) {
        this.specId = specId;
        this.type = type.name();
        this.value = null;
        this.unaryExpression = unaryExpression;
        this.leftExpression = null;
        this.rightExpression = null;
    }

    public ExpressionDTO(String specId, Type type, String value) {
        this.specId = specId;
        this.type = type.name();
        this.value = value;
        this.unaryExpression = null;
        this.leftExpression = null;
        this.rightExpression = null;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ExpressionDTO getUnaryExpression() {
        return unaryExpression;
    }

    public void setUnaryExpression(ExpressionDTO unaryExpression) {
        this.unaryExpression = unaryExpression;
    }

    public ExpressionDTO getLeftExpression() {
        return leftExpression;
    }

    public void setLeftExpression(ExpressionDTO leftExpression) {
        this.leftExpression = leftExpression;
    }

    public ExpressionDTO getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(ExpressionDTO rightExpression) {
        this.rightExpression = rightExpression;
    }

}

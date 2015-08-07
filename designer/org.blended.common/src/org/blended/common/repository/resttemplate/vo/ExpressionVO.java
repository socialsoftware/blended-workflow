package org.blended.common.repository.resttemplate.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressionVO {

    // copy from the equivalent in engine ExpressionDTO
    public enum Type {
        AND, OR, NOT, ATT_DEF, EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, SMALLER, SMALLER_EQUAL, PLUS, MINUS, MUL, DIV, ATT_VALUE, STRING, INT, BOOL
    }

    private String dataModelExtId;
    private String type;
    // when unary value
    private String value;
    // when unary not
    private ExpressionVO unaryExpression;
    // when binary
    private ExpressionVO leftExpression;
    private ExpressionVO rightExpression;

    public ExpressionVO() {
    }

    public ExpressionVO(String dataModelExtId, Type type,
            ExpressionVO leftExpresssion, ExpressionVO rightExpression) {
        this.dataModelExtId = dataModelExtId;
        this.type = type.name();
        this.value = null;
        this.unaryExpression = null;
        this.leftExpression = leftExpresssion;
        this.rightExpression = rightExpression;
    }

    public ExpressionVO(String dataModelExtId, Type type,
            ExpressionVO unaryExpression) {
        this.dataModelExtId = dataModelExtId;
        this.type = type.name();
        this.value = null;
        this.unaryExpression = unaryExpression;
        this.leftExpression = null;
        this.rightExpression = null;
    }

    public ExpressionVO(String dataModelExtId, Type type, String value) {
        this.dataModelExtId = dataModelExtId;
        this.type = type.name();
        this.value = value;
        this.unaryExpression = null;
        this.leftExpression = null;
        this.rightExpression = null;
    }

    public String getDataModelExtId() {
        return dataModelExtId;
    }

    public void setDataModelExtId(String dataModelExtId) {
        this.dataModelExtId = dataModelExtId;
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

    public ExpressionVO getUnaryExpression() {
        return unaryExpression;
    }

    public void setUnaryExpression(ExpressionVO unaryExpression) {
        this.unaryExpression = unaryExpression;
    }

    public ExpressionVO getLeftExpression() {
        return leftExpression;
    }

    public void setLeftExpression(ExpressionVO leftExpression) {
        this.leftExpression = leftExpression;
    }

    public ExpressionVO getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(ExpressionVO rightExpression) {
        this.rightExpression = rightExpression;
    }

}

package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressionDTO {
    private static Logger log = LoggerFactory.getLogger(ExpressionDTO.class);

    public enum Type {
        AND, OR, NOT, ATT_DEF, EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, SMALLER, SMALLER_EQUAL, PLUS, MINUS, MUL, DIV, ATT_VALUE, STRING, INT, BOOL
    }

    public static boolean isBoolExp(Type type) {
        Set<Type> bools = new HashSet<>(Arrays.asList(Type.AND, Type.OR,
                Type.NOT, Type.ATT_DEF, Type.EQUAL, Type.NOT_EQUAL,
                Type.GREATER, Type.GREATER_EQUAL, Type.SMALLER,
                Type.SMALLER_EQUAL, Type.BOOL));
        return bools.contains(type);
    }

    private String dataModelExtId;
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

    public ExpressionDTO(String dataModelExtId, Type type,
            ExpressionDTO leftExpDTO, ExpressionDTO rightExpDTO) {
        log.debug("type:{}", type);

        this.dataModelExtId = dataModelExtId;
        this.type = type.name();
        this.value = null;
        this.unaryExpression = null;
        this.leftExpression = leftExpDTO;
        this.rightExpression = rightExpDTO;
    }

    public ExpressionDTO(String dataModelExtId, Type type,
            ExpressionDTO expDTO) {
        log.debug("type:{}", type);

        this.dataModelExtId = dataModelExtId;
        this.type = type.name();
        this.value = null;
        this.unaryExpression = expDTO;
        this.leftExpression = null;
        this.rightExpression = null;
    }

    public ExpressionDTO(String dataModelExtId, Type type, String value) {
        log.debug("type:{}, value:{}", type, value);

        this.dataModelExtId = dataModelExtId;
        this.type = type.name();
        this.value = value;
        this.unaryExpression = null;
        this.leftExpression = null;
        this.rightExpression = null;
    }

    @Override
    public String toString() {
        if (this.value != null)
            return getType() + "(" + getValue() + ")";
        else if (this.unaryExpression != null)
            return getType() + "(" + getUnaryExpression().toString() + ")";
        else
            return getType() + "(" + getLeftExpression().toString() + ","
                    + getRightExpression().toString() + ")";
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

    public ExpressionDTO getUnaryExpression() {
        return unaryExpression;
    }

    public void setUnaryExpression(ExpressionDTO unaryExpression) {
        this.unaryExpression = unaryExpression;
    }

    public ExpressionDTO getLeftExpression() {
        return leftExpression;
    }

    public void setLeftExpresssion(ExpressionDTO leftExpresssion) {
        this.leftExpression = leftExpresssion;
    }

    public ExpressionDTO getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(ExpressionDTO rightExpression) {
        this.rightExpression = rightExpression;
    }

}

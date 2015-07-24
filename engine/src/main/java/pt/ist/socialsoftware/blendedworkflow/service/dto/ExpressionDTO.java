package pt.ist.socialsoftware.blendedworkflow.service.dto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public SpecificationDTO specDTO;
    public Type type;
    // when unary value
    public String value;
    // when unary not
    public ExpressionDTO expDTO;
    // when binary
    public ExpressionDTO leftExpDTO;
    public ExpressionDTO rightExpDTO;

    public ExpressionDTO(String specId, Type type, ExpressionDTO leftExpDTO,
            ExpressionDTO rightExpDTO) {
        log.debug("type:{}", type.name());

        this.specDTO = new SpecificationDTO(specId);
        this.type = type;
        this.value = null;
        this.expDTO = null;
        this.leftExpDTO = leftExpDTO;
        this.rightExpDTO = rightExpDTO;
    }

    public ExpressionDTO(String specId, Type type, ExpressionDTO expDTO) {
        log.debug("type:{}", type.name());

        this.specDTO = new SpecificationDTO(specId);
        this.type = type;
        this.value = null;
        this.expDTO = expDTO;
        this.leftExpDTO = null;
        this.rightExpDTO = null;
    }

    public ExpressionDTO(String specId, Type type, String value) {
        log.debug("type:{}, value:{}", type.name(), value);

        this.specDTO = new SpecificationDTO(specId);
        this.type = type;
        this.value = value;
        this.expDTO = null;
        this.leftExpDTO = null;
        this.rightExpDTO = null;
    }

}

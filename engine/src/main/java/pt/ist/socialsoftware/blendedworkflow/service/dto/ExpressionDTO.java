package pt.ist.socialsoftware.blendedworkflow.service.dto;

public class ExpressionDTO {
    public enum Type {
        AND, OR, NOT, ATT_DEF, EQUAL, NOT_EQUAL, GREATER, GREATER_EQUAL, SMALLER, SMALLER_EQUAL, PLUS, MINUS, MUL, DIV, ATT_VALUE, STRING, INT, BOOL
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
        this.specDTO = new SpecificationDTO(specId);
        this.type = type;
        this.value = null;
        this.expDTO = null;
        this.leftExpDTO = leftExpDTO;
        this.rightExpDTO = rightExpDTO;
    }

    public ExpressionDTO(String specId, Type type, ExpressionDTO expDTO) {
        this.specDTO = new SpecificationDTO(specId);
        this.type = type;
        this.value = null;
        this.expDTO = expDTO;
        this.leftExpDTO = null;
        this.rightExpDTO = null;
    }

    public ExpressionDTO(String specId, Type type, String value) {
        this.specDTO = new SpecificationDTO(specId);
        this.type = type;
        this.value = value;
        this.expDTO = null;
        this.leftExpDTO = null;
        this.rightExpDTO = null;
    }

}

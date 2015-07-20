package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWBinaryExpression extends BWBinaryExpression_Base {

    public static enum BinaryOperator {
        PLUS, MINUS, MUL, DIV
    }

    public BWBinaryExpression(BWExpression leftExpression,
            BWExpression rightExpression, BinaryOperator operator) {
        setLeftExpression(leftExpression);
        setRightExpression(rightExpression);
        setOperator(operator);
        checkConsistency();
    }

    private void checkConsistency() {
        if (!getLeftExpression().getType()
                .equals(getRightExpression().getType()))
            throw new BWException(BWErrorType.INCONSISTENT_TYPE,
                    getOperator().name());

        if (!getLeftExpression().getType().equals(AttributeType.NUMBER))
            throw new BWException(BWErrorType.INCONSISTENT_TYPE,
                    getOperator().name());
    }

    @Override
    public void delete() {
        getLeftExpression().delete();
        getRightExpression().delete();
        super.delete();
    }

    @Override
    public AttributeType getType() {
        checkConsistency();

        return getLeftExpression().getType();
    }

}

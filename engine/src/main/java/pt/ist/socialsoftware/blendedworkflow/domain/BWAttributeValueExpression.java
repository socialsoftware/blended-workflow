package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;

public class BWAttributeValueExpression
        extends BWAttributeValueExpression_Base {

    public BWAttributeValueExpression(BWAttribute att) {
        setAttribute(att);
    }

    @Override
    public void delete() {
        setAttribute(null);
        super.delete();
    }

    public AttributeType getType() {
        return getAttribute().getType();
    }
}

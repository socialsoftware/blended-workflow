package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;

public class BWNumberLiteral extends BWNumberLiteral_Base {

    public BWNumberLiteral(int name) {
        setValue(name);
    }

    public AttributeType getType() {
        return AttributeType.NUMBER;
    }

}

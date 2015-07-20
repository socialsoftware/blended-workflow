package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;

public class BWStringLiteral extends BWStringLiteral_Base {

    public BWStringLiteral(String name) {
        setValue(name);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.STRING;
    }

}

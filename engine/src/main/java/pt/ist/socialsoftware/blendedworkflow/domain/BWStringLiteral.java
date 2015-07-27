package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;

public class BWStringLiteral extends BWStringLiteral_Base {

    public BWStringLiteral(String name) {
        setValueString(name);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.STRING;
    }

    @Override
    public String getSubPath() {
        return getValueString();
    }

}

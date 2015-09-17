package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

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

    @Override
    public Set<BWAttribute> getAttributes() {
        return new HashSet<BWAttribute>();
    }

}

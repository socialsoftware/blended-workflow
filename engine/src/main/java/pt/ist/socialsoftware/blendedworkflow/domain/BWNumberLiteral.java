package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;

public class BWNumberLiteral extends BWNumberLiteral_Base {

    public BWNumberLiteral(int name) {
        setValue(name);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.NUMBER;
    }

    @Override
    public String getSubPath() {
        return Integer.toString(getValue());
    }

    @Override
    public Set<BWAttribute> getAttributes() {
        return new HashSet<BWAttribute>();
    }

}

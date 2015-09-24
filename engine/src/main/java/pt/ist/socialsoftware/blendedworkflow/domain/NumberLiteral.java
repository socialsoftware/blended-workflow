package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;

public class NumberLiteral extends NumberLiteral_Base {

    public NumberLiteral(int name) {
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
    public Set<AttributeBasic> getAttributes() {
        return new HashSet<AttributeBasic>();
    }

}

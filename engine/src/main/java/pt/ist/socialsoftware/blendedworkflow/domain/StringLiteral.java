package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;

public class StringLiteral extends StringLiteral_Base {

    public StringLiteral(String name) {
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
    public Set<Attribute> getAttributes() {
        return new HashSet<Attribute>();
    }

}

package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

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

    @Override
    public AttributeType getType() {
        return getAttribute().getType();
    }

    @Override
    public String getSubPath() {
        return getAttribute().getName();
    }

    @Override
    public Set<BWAttribute> getAttributes() {
        Set<BWAttribute> attributes = new HashSet<BWAttribute>();
        attributes.add(getAttribute());
        return attributes;
    }

}

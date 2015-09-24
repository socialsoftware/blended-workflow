package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;

public class AttributeValueExpression extends AttributeValueExpression_Base {

    public AttributeValueExpression(AttributeBasic att) {
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
    public Set<AttributeBasic> getAttributes() {
        Set<AttributeBasic> attributes = new HashSet<AttributeBasic>();
        if (!getAttribute().getEntity().getExists())
            attributes.add(getAttribute());
        return attributes;
    }

}

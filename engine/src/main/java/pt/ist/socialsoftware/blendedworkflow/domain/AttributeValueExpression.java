package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;

public class AttributeValueExpression extends AttributeValueExpression_Base {

    public AttributeValueExpression(Attribute att) {
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
    public Set<Attribute> getAttributes() {
        Set<Attribute> attributes = new HashSet<Attribute>();
        if (!getAttribute().getEntity().getExists())
            attributes.add(getAttribute());
        return attributes;
    }

}

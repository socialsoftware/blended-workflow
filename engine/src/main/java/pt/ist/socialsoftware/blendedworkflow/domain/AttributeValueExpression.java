package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;

public class AttributeValueExpression extends AttributeValueExpression_Base {

    public AttributeValueExpression(String path, AttributeBasic att) {
        setPath(path);
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
        return getPath();
    }

    @Override
    public Set<AttributeBasic> getAttributes() {
        Set<AttributeBasic> attributes = new HashSet<AttributeBasic>();
        if (!getAttribute().getEntity().getExists())
            attributes.add(getAttribute());
        return attributes;
    }

    @Override
    public ExpressionDTO getDTO(String specId) {
        return new ExpressionDTO(specId, ExpressionAtom.ATT_VALUE, getPath());
    }

}

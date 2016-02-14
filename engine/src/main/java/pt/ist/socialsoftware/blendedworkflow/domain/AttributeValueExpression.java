package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;

public class AttributeValueExpression extends AttributeValueExpression_Base {

	public AttributeValueExpression(Specification spec, String value) {
		Path path = new Path(spec.getDataModel(), value);
		setPath(path);
		setAttribute((AttributeBasic) spec.getDataModel().getTargetOfPath(value));
	}

	@Override
	public void delete() {
		setAttribute(null);
		getPath().delete();
		super.delete();
	}

	@Override
	public AttributeType getType() {
		return getAttribute().getType();
	}

	@Override
	public String getSubPath() {
		return getPath().getValue();
	}

	@Override
	public Set<AttributeBasic> getAttributes() {
		Set<AttributeBasic> attributes = new HashSet<AttributeBasic>();
		if (!getAttribute().getEntity().getExists())
			attributes.add(getAttribute());
		return attributes;
	}

	@Override
	public Set<String> getPathSet() {
		Set<String> paths = new HashSet<String>();
		paths.add(getPath().getValue());

		return paths;
	}

	@Override
	public ExpressionDTO getDTO(String specId) {
		return new ExpressionDTO(specId, ExpressionAtom.ATT_VALUE, getPath().getValue());
	}

}

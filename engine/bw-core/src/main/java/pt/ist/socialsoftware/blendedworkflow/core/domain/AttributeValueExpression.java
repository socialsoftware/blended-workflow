package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;

public class AttributeValueExpression extends AttributeValueExpression_Base {

	public AttributeValueExpression(Specification spec, String value) {
		Path path = new Path(spec.getDataModel(), value);
		setPath(path);
		setAttribute((Attribute) spec.getDataModel().getTargetOfPath(value));
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
	public Set<Attribute> getAttributes() {
		Set<Attribute> attributes = new HashSet<>();
		if (!getAttribute().getEntity().getExists()) {
			attributes.add(getAttribute());
		}
		return attributes;
	}

	@Override
	public Set<Path> getPathSet() {
		Set<Path> paths = new HashSet<>();
		paths.add(getPath());

		return paths;
	}

	@Override
	public ExpressionDto getDTO(String specId) {
		return new ExpressionDto(specId, ExpressionAtom.ATT_VALUE, getPath().getValue());
	}

}

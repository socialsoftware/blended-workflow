package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;

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
	public Set<Attribute> getAttributes() {
		return new HashSet<>();
	}

	@Override
	public Set<Path> getPathSet() {
		return new HashSet<>();
	}

	@Override
	public ExpressionDto getDTO(String specId) {
		return new ExpressionDto(specId, ExpressionAtom.INT, Integer.toString(getValue()));
	}

}

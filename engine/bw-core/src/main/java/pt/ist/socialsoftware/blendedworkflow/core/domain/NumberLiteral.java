package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDTO;

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
	public ExpressionDTO getDTO(String specId) {
		return new ExpressionDTO(specId, ExpressionAtom.INT, Integer.toString(getValue()));
	}

}

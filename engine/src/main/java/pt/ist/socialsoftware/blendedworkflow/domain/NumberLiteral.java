package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;

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

	@Override
	public Set<String> getPathSet() {
		return new HashSet<String>();
	}

	@Override
	public ExpressionDTO getDTO(String specId) {
		return new ExpressionDTO(specId, ExpressionAtom.INT, Integer.toString(getValue()));
	}

}

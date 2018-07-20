package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;

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
		return new HashSet<>();
	}

	@Override
	public Set<Path> getPathSet() {
		return new HashSet<>();
	}

	@Override
	public ExpressionDto getDTO(String specId) {
		return new ExpressionDto(specId, ExpressionAtom.STRING, getValueString());
	}

}

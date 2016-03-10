package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;

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
		return new HashSet<Attribute>();
	}

	@Override
	public Set<Path> getPathSet() {
		return new HashSet<Path>();
	}

	@Override
	public ExpressionDTO getDTO(String specId) {
		return new ExpressionDTO(specId, ExpressionAtom.STRING, getValueString());
	}

}

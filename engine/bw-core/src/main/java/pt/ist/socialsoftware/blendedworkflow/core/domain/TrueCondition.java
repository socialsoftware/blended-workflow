package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;

public class TrueCondition extends TrueCondition_Base {

	public TrueCondition() {
	}

	@Override
	public Set<Entity> getEntities() {
		return new HashSet<>();
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		return new HashSet<>();
	}

	@Override
	public Set<Path> getPathSet() {
		return new HashSet<>();
	}

	@Override
	public String getSubPath() {
		return "true";
	}

	@Override
	public ExpressionDto getExpressionDTO(String specId) {
		return new ExpressionDto(specId, BooleanOperator.BOOL, "true");
	}

}

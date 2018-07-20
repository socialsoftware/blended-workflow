package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.domain.ExpressionDto;

public class FalseCondition extends FalseCondition_Base {

	public FalseCondition() {
		super();
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
		return "false";
	}

	@Override
	public ExpressionDto getExpressionDTO(String specId) {
		return new ExpressionDto(specId, BooleanOperator.BOOL, "false");
	}

}

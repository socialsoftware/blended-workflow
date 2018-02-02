package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ExpressionDTO;

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
	public HashMap<Attribute, String> getcompareConditionValues() {
		return new HashMap<>();
	}

	@Override
	public String getRdrUndefinedCondition() {
		return "True";
	}

	@Override
	public String getRdrSkippedCondition() {
		return "True";
	}

	@Override
	public String getRdrTrueCondition() {
		return "True";
	}

	@Override
	public String getRdrFalseCondition() {
		return "True";
	}

	@Override
	public String toString() {
		return "True";
	}

	@Override
	public Boolean existExistEntity() {
		return false;
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		return false;
	}

	@Override
	public Boolean existTrue() {
		return true;
	}

	@Override
	public String getSubPath() {
		return "true";
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		return new ExpressionDTO(specId, BooleanOperator.BOOL, "true");
	}

}

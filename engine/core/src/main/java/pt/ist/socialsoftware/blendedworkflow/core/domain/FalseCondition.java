package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ExpressionDTO;

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
	public HashMap<Attribute, String> getcompareConditionValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrUndefinedCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrSkippedCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrTrueCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRdrFalseCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existExistEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existTrue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSubPath() {
		return "false";
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		return new ExpressionDTO(specId, BooleanOperator.BOOL, "false");
	}

}

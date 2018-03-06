package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.core.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ExpressionDTO;

public class NotCondition extends NotCondition_Base {

	public NotCondition(Condition condition) {
		setCondition(condition);
		checkConsistency();
	}

	private void checkConsistency() {
		if (getCondition() == null) {
			throw new BWException(BWErrorType.INCONSISTENT_EXPRESSION, getSubPath());
		}
	}

	@Override
	public Set<Entity> getEntities() {
		return getCondition().getEntities();
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		return getCondition().getAttributeSet();
	}

	@Override
	public Set<Path> getPathSet() {
		return getCondition().getPathSet();
	}

	@Override
	public void delete() {
		if (getCondition() != null) {
			getCondition().delete();
		}
		super.delete();
	}

	@Override
	public String getSubPath() {
		String exp = getCondition() != null ? getCondition().getSubPath() : "NULL";

		return "NOT(" + exp + ")";
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		return new ExpressionDTO(specId, BooleanOperator.NOT, getCondition().getExpressionDTO(specId));
	}

}

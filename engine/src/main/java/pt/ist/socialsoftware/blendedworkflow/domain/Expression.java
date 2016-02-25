package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.domain.AttributeBasic.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;

public abstract class Expression extends Expression_Base {
	public enum ExpressionAtom {
		ATT_VALUE, INT, STRING
	};

	public void delete() {
		setLeftComparison(null);
		setRightComparison(null);
		setLeftBinary(null);
		setRightBinary(null);
		deleteDomainObject();
	}

	public DataModel getDataModel() {
		if (getLeftComparison() != null)
			return getLeftComparison().getDataModel();
		if (getRightComparison() != null)
			return getRightComparison().getDataModel();
		if (getLeftBinary() != null)
			return getLeftBinary().getDataModel();
		if (getRightBinary() != null)
			return getRightBinary().getDataModel();
		assert false;
		return null;
	}

	public abstract AttributeType getType();

	public abstract String getSubPath();

	public abstract Set<AttributeBasic> getAttributes();

	public abstract Set<Path> getPathSet();

	public abstract ExpressionDTO getDTO(String specId);

}

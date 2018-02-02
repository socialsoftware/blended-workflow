package pt.ist.socialsoftware.blendedworkflow.core.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.core.service.dto.ExpressionDTO;

public class AttributeBoolCondition extends AttributeBoolCondition_Base {

	public AttributeBoolCondition(Specification spec, String value) {
		Path path = new Path(spec.getDataModel(), value);
		setPath(path);
		setAttributeOfBool((Attribute) spec.getDataModel().getTargetOfPath(value));
	}

	@Override
	public void delete() {
		setAttributeOfBool(null);
		getPath().delete();

		super.delete();
	}

	@Override
	public String getSubPath() {
		return getPath().getValue();
	}

	@Override
	public Set<Entity> getEntities() {
		return new HashSet<>();
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		Set<Attribute> attributes = new HashSet<>();
		attributes.add(getAttributeOfBool());

		return attributes;
	}

	@Override
	public Set<Path> getPathSet() {
		Set<Path> paths = new HashSet<>();
		paths.add(getPath());

		return paths;
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
	public ExpressionDTO getExpressionDTO(String specId) {
		return new ExpressionDTO(specId, BooleanOperator.ATT_VALUE, getPath().getValue());
	}

}

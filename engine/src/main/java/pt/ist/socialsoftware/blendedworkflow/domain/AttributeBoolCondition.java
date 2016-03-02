package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class AttributeBoolCondition extends AttributeBoolCondition_Base {

	public AttributeBoolCondition(Specification spec, String value) {
		Path path = new Path(spec.getDataModel(), value);
		setPath(path);
		setAttributeOfBool((AttributeBasic) spec.getDataModel().getTargetOfPath(value));
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
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub

	}

	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Entity> getEntities() {
		return new HashSet<Entity>();
	}

	@Override
	public Set<AttributeBasic> getAttributeBasicSet() {
		Set<AttributeBasic> attributes = new HashSet<AttributeBasic>();
		attributes.add(getAttributeOfBool());

		return attributes;
	}

	@Override
	public Set<Path> getPathSet() {
		Set<Path> paths = new HashSet<Path>();
		paths.add(getPath());

		return paths;
	}

	@Override
	public HashMap<AttributeBasic, String> getcompareConditionValues() {
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
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		return new ExpressionDTO(specId, BooleanOperator.ATT_VALUE, getPath().getValue());
	}

}

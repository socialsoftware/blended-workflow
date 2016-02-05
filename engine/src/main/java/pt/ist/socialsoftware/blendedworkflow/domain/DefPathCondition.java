package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class DefPathCondition extends DefPathCondition_Base {

	public static DefPathCondition getDefPathCondition(Specification spec, String value) {
		Optional<DefPathCondition> oDef = spec.getDataModel().getDefPathConditionSet().stream()
				.filter(d -> d.getPath().equals(value)).findFirst();
		if (oDef.isPresent())
			return oDef.get();
		else
			return new DefPathCondition(spec, value);
	}

	private DefPathCondition(Specification spec, String value) {
		Path path = new Path(spec.getDataModel(), value);
		setPathObject(path);
		setDataModel(spec.getDataModel());
	}

	@Override
	public Product getTargetOfPath() {
		return getPathObject().getTargetOfPath();
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
		Set<Entity> entities = new HashSet<Entity>();
		if (getTargetOfPath() instanceof Entity) {
			entities.add((Entity) getTargetOfPath());
		}

		return entities;
	}

	@Override
	public Set<AttributeBasic> getAttributeBasicSet() {
		Set<AttributeBasic> attributes = new HashSet<AttributeBasic>();
		if (getTargetOfPath() instanceof AttributeBasic) {
			attributes.add((AttributeBasic) getTargetOfPath());
		} else if (getTargetOfPath() instanceof AttributeGroup) {
			attributes.addAll(((AttributeGroup) getTargetOfPath()).getAttributeBasicSet());
		}

		return attributes;
	}

	@Override
	public Set<String> getPathSet() {
		Set<String> paths = new HashSet<String>();
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
	public String getSubPath() {
		return "DEF(" + getPathObject().getValue() + ")";
	}

	@Override
	public void delete() {
		getTaskWithPreConditionSet().stream().forEach(d -> removeTaskWithPreCondition(d));
		getPathObject().delete();
		setDataModel(null);

		super.delete();
	}

	@Override
	public ExpressionDTO getDTO(String specId) {
		return new ExpressionDTO(specId, BooleanOperator.PATH_DEF, getPathObject().getValue());
	}

	@Override
	public String getPath() {
		return getPathObject().getValue();
	}

}

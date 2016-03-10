package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.dto.DefPathConditionDTO;
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
		setPath(path);
		setDataModel(spec.getDataModel());
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
	public Set<Path> getPathSet() {
		Set<Path> paths = new HashSet<Path>();
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
		return "DEF(" + getPath().getValue() + ")";
	}

	@Override
	public void delete() {
		getTaskWithPreConditionSet().stream().forEach(d -> removeTaskWithPreCondition(d));
		getActivationConditionGoalSet().stream().forEach(d -> removeActivationConditionGoal(d));
		setDataModel(null);

		super.delete();
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		assert false : "expressions cannot have a def condition";
		return null;
	}

	public DefPathConditionDTO getDTO(String specId) {
		return new DefPathConditionDTO(specId, getPath().getValue());
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		Set<Attribute> attributes = new HashSet<Attribute>();
		if (getTargetOfPath() instanceof Attribute) {
			attributes.add((Attribute) getTargetOfPath());
		}
		return attributes;
	}

}

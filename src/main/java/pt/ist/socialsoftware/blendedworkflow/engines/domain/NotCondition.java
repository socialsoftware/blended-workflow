package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.Set;

public class NotCondition extends NotCondition_Base {

	public NotCondition(Condition condition) {
		setCondition(condition);
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		return new NotCondition(getCondition().cloneCondition(goalModelInstance));
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		return new NotCondition(getCondition().cloneCondition(taskModelInstance));
	}
	
	@Override
	void assignAttributeInstances(GoalWorkItem goalWorkItem) {
		getCondition().assignAttributeInstances(goalWorkItem);
	}
	
	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType) {
		getCondition().assignAttributeInstances(taskWorkItem, conditionType);
	}
	
	@Override
	public Set<Entity> getEntities() {
		return null;
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		return null;
	}
	
	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		return new HashMap<Attribute, String>();
	}
	
	@Override
	public String getRdrCondition(String type) {
		return "! " + getCondition(); // FIXME: not tested.
	}

}

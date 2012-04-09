package pt.ist.socialsoftware.blendedworkflow.engines.domain;

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
	String getData() {
		return getCondition().getData();
	}
	
	@Override
	public String getString() {
		return getCondition() + ".not()";
	}
	
	@Override
	public String getEntities() {
		return "null";
	}
	
	@Override
	public String getAttributes() {
		return "null";
	}


}

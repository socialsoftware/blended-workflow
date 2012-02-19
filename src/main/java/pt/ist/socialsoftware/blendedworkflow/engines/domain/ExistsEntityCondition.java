package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class ExistsEntityCondition extends ExistsEntityCondition_Base {

	public ExistsEntityCondition(Entity entity) {
		setEntity(entity);
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		DataModelInstance dataModelInstance = goalModelInstance.getBwInstance().getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getEntity().getName());
		return new ExistsEntityCondition(entity);
	}

	@Override
	void assignAttributeInstances(GoalWorkItem goalWorkItem) {
		getEntity().assignAllAttributeInstances(goalWorkItem, getEntity());
	}

}
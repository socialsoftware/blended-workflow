package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class ExistsEntityCondition extends ExistsEntityCondition_Base {

	public ExistsEntityCondition(Entity entity) {
		setEntity(entity);
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		DataModelInstance dataModelInstance = goalModelInstance.getBwInstance().getDataModelInstance();
		
		System.out.println("999999999- "+ getEntity().getName());
		Entity entity = dataModelInstance.getEntity(getEntity().getName());
		
		System.out.println("999999999- "+ entity.getName());
		return new ExistsEntityCondition(entity);
	}

}

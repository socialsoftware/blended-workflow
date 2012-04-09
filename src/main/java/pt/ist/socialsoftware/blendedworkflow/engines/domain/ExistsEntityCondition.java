package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashSet;
import java.util.Set;

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
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		DataModelInstance dataModelInstance = taskModelInstance.getBwInstance().getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getEntity().getName());
		return new ExistsEntityCondition(entity);
	}
	
	@Override
	public void assignAttributeInstances(GoalWorkItem goalWorkItem) {
		getEntity().assignAllAttributeInstances(goalWorkItem, getEntity());
	}
	
	@Override
	public void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType) {
		getEntity().assignAllAttributeInstances(taskWorkItem, getEntity(), conditionType);
	}
	
	@Override
	String getData() {
		return getEntity().getName();
	}
	
	@Override
	public String getString() {
		return "existsEntity(" + getEntity().getName() +")";
	}
	
	@Override
	public Set<Entity> getEntities() {
		Set<Entity> entity = new HashSet<Entity>();
		entity.add(getEntity());
		return entity;
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		return null;
	}

}
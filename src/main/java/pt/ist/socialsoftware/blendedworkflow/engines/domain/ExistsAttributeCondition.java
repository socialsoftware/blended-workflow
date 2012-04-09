package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashSet;
import java.util.Set;

public class ExistsAttributeCondition extends ExistsAttributeCondition_Base {

	public ExistsAttributeCondition(Attribute attribute) {
		setAttribute(attribute);
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		DataModelInstance dataModelInstance = goalModelInstance.getBwInstance().getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getAttribute().getEntity().getName());
		Attribute attribute = entity.getAttribute(getAttribute().getName());
		return new ExistsAttributeCondition(attribute);
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		DataModelInstance dataModelInstance = taskModelInstance.getBwInstance().getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getAttribute().getEntity().getName());
		Attribute attribute = entity.getAttribute(getAttribute().getName());
		return new ExistsAttributeCondition(attribute);
	}
	
	@Override
	public void assignAttributeInstances(GoalWorkItem goalWorkItem) {
		getAttribute().getEntity().assignAttributeInstances(goalWorkItem,getAttribute());
	}
	
	@Override
	public void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType) {
		getAttribute().getEntity().assignAttributeInstances(taskWorkItem,getAttribute(), conditionType);
	}
	
	@Override
	String getData() {
		return getAttribute().getEntity().getName();
	}
	
	@Override
	public String getString() {
		return "existsAttribute(" + getAttribute().getEntity().getName() + "." + getAttribute().getName() +")";
	}
	
	@Override
	public Set<Entity> getEntities() {
		return null;
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		Set<Attribute> attribute = new HashSet<Attribute>();
		attribute.add(getAttribute());
		return attribute;
	}

}

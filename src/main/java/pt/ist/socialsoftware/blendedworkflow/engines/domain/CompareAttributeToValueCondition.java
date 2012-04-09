package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashSet;
import java.util.Set;

public class CompareAttributeToValueCondition extends CompareAttributeToValueCondition_Base {

	public CompareAttributeToValueCondition(){
		super();
	}

	public CompareAttributeToValueCondition(Attribute attribute, String operator, String value) {
		setAttribute(attribute);
		setOperator(operator);
		setValue(value);
	}

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		DataModelInstance dataModelInstance = goalModelInstance.getBwInstance().getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getAttribute().getEntity().getName());
		Attribute attribute = entity.getAttribute(getAttribute().getName());
		return new CompareAttributeToValueCondition(attribute, getOperator(), getValue());
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		DataModelInstance dataModelInstance = taskModelInstance.getBwInstance().getDataModelInstance();
		Entity entity = dataModelInstance.getEntity(getAttribute().getEntity().getName());
		Attribute attribute = entity.getAttribute(getAttribute().getName());
		return new CompareAttributeToValueCondition(attribute, getOperator(), getValue());
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
		return "compareAttributeTo(" + getAttribute().getEntity().getName() + "." + getAttribute().getName() + "," + getOperator() + "." + getValue() +")";
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
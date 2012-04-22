package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
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
	public Set<Entity> getEntities() {
		return new HashSet<Entity>();
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		Set<Attribute> attribute = new HashSet<Attribute>();
		attribute.add(getAttribute());
		return attribute;
	}
	
	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		HashMap<Attribute, String> result = new HashMap<Attribute, String>();
		result.put(getAttribute(), getValue());
		return result;
	}
	
	@Override
	public String getRdrCondition(String type) {
		String condition = "";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
		
		String joiner = " | ";
		if (type.equals("DEFINED"))
			joiner = " & ";
			
		condition += entityName + "_" + attributeName + "_State = " + type + joiner;
		condition += entityName + "_" + attributeName + " " + getOperator() + " " + getValue();
		
		return condition;
	}

}
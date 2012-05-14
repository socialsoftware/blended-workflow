package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class CompareAttributeToValueCondition extends CompareAttributeToValueCondition_Base {

	public CompareAttributeToValueCondition(){
		super();
	}
	
	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem) {
		for (WorkItemArgument workItemArgument : goalWorkItem.getOutputWorkItemArguments()) {
			Attribute workItemAttribute = workItemArgument.getAttributeInstance().getAttribute();
			Attribute conditionAttribute = getAttribute();
			if (workItemAttribute == conditionAttribute) {
				if (workItemArgument.getState().equals(DataState.UNDEFINED)) {
					return TripleStateBool.FALSE;	
				} else if (workItemArgument.getState().equals(DataState.SKIPPED)) {
					return TripleStateBool.SKIPPED;
				} else {
					if (evaluateComparation(workItemArgument)) {
						return TripleStateBool.TRUE;
					} else {
						return TripleStateBool.FALSE;
					}
				}
			}
		}
		return TripleStateBool.FALSE;
	}

	private boolean evaluateComparation(WorkItemArgument workItemArgument) {

		// Equals (=)
		if (getOperator().equals("=")) {
			if (getAttribute().getType().equals(AttributeType.NUMBER)) {
				if (workItemArgument.getValue() == getValue()) {
					return true;
				} else {
					return false;
				}
			} else {
				if (workItemArgument.getValue().equals(getValue())) {
					return true;
				} else {
					return false;
				}
			}
		} 
		
		else {
			System.out.println("Exception");
			return false;	
		}
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
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		getAttribute().getEntity().assignAttributeInstances(goalWorkItem,getAttribute(), conditionType);
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
	
//	@Override
//	public String getRdrCondition(String type) {
//		String condition = "";
//		String attributeName = getAttribute().getName().replaceAll(" ", "");
//		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
//		
//		String joiner = " | ";
//		if (type.equals("DEFINED"))
//			joiner = " & ";
//			
//		condition += entityName + "_" + attributeName + "_State = " + type + joiner;
//		condition += entityName + "_" + attributeName + " " + getOperator() + " " + getValue();
//		
//		return condition;
//	}
	
	/**
	 * TO TEST
	 */
	@Override
	public String getRdrTrueCondition() {
		String condition = "(";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
		
		condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED + " & ";
		condition += entityName + "_" + attributeName + " " + getOperator() + " " + getValue() + ")";
		return condition;
	}
	
	@Override
	public String getRdrFalseCondition() {
		String condition = "((";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
		
		condition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED + ") | ";
		condition += "((" + entityName + "_" + attributeName + "_State = " + DataState.DEFINED + ") & ";
		condition += "(!(" + entityName + "_" + attributeName + " " + getOperator() + " " + getValue() + "))))";
		
//		condition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED + " | ";
//		condition += "(" + entityName + "_" + attributeName + "_State = " + DataState.DEFINED + " & ";
//		condition += "!(" + entityName + "_" + attributeName + " " + getOperator() + " " + getValue() + ")))"; //! does not work
		return condition;
	}
	
	@Override
	public String getRdrSkippedCondition() {
		String condition = "(";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
		
		condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED + ")";
		return condition;
	}
	
	
	
	/**
	 * NEW
	 */
	@Override
	public String getRdrUndefinedConditionNEW() {
		String condition = "(";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
		
		condition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED + ")";
		return condition;
	}

	@Override
	public String getRdrSkippedConditionNEW() {
		String condition = "(";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
		
		condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED + ")";
		return condition;
	}

	@Override
	public String getRdrTrueConditionNEW() {
		String condition = "(";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
		
		condition += entityName + "_" + attributeName + " " + getOperator() + " " + getValue() + ")";
		return condition;
	}

	@Override
	public String getRdrFalseConditionNEW() {
		return "(!" + getRdrTrueConditionNEW() + ")";
	}
	
	@Override
	public String toString() {
		return "compareAttributeTo(" + getAttribute().getName() + " " + getOperator() + " " + getValue();
	}
	
	@Override
	public Boolean existExistEntity() {
		return false;
	}


}
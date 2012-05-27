package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class CompareAttributeToValueCondition extends CompareAttributeToValueCondition_Base {

	private static Logger log = Logger.getLogger("????????");
	
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
		return "compareAttributeTo(" + getAttribute().getEntity().getName() + "." + getAttribute().getName() + " " + getOperator() + " " + getValue() +")";
	}
	
	@Override
	public Boolean existExistEntity() {
		return false;
	}
	
	/******************************
	 * Evaluate
	 ******************************/
	@Override
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		log.info("evaluateWithWorkItem: |" + goalWorkItem.getID() + "|" + conditionType);
		List<WorkItemArgument> arguments = null;
		if (conditionType.equals(ConditionType.ACTIVATE)) {
			arguments = goalWorkItem.getInputWorkItemArguments();
		} else if (conditionType.equals(ConditionType.SUCESS)) {
			arguments = goalWorkItem.getOutputWorkItemArguments();
		}		
		log.info("WorkItemArgumentCount:" + arguments.size());

		for (WorkItemArgument workItemArgument : arguments) {
			
			Attribute workItemAttribute = workItemArgument.getAttributeInstance().getAttribute();
			Attribute conditionAttribute = getAttribute();
			log.info("workItemAttribute:" + workItemAttribute.getName() + "|conditionAttribute:" + conditionAttribute.getName());
			if (workItemAttribute == conditionAttribute) {
				log.info("state:" + workItemArgument.getState());
				if (workItemArgument.getState().equals(DataState.UNDEFINED)) {
					log.info("CA final result:" + TripleStateBool.FALSE);
					return TripleStateBool.FALSE;	
				} else if (workItemArgument.getState().equals(DataState.SKIPPED)) {
					log.info("CA final result:" + TripleStateBool.SKIPPED);
					return TripleStateBool.SKIPPED;
				} else {
					log.info("value:" + workItemArgument.getValue());
					if (evaluateComparation(workItemArgument.getValue())) {
						log.info("AQUI final result:" + TripleStateBool.TRUE);
						return TripleStateBool.TRUE;
					} else {
						log.info("CA final result:" + TripleStateBool.FALSE);
						return TripleStateBool.FALSE;
					}
				}
			}
		}
		log.info("CA final result:" + TripleStateBool.FALSE);
		return TripleStateBool.FALSE;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem, ConditionType conditionType) {
		if (entityInstance == null) {
			return evaluateWithWorkItem(goalWorkItem, conditionType);
		} 
		
		else {
			log.info("evaluateWithDataModel: |" + entityInstance.getID() + "|" + goalWorkItem.getID() + "|" + conditionType);
			for (AttributeInstance attributeInstance : entityInstance.getAttributeInstances()) {
				
				if (attributeInstance.getAttribute().equals(getAttribute())) {
					log.info("attrbuteIns" + attributeInstance.getID());
					DataState state = getWorkItemState(attributeInstance, goalWorkItem, conditionType);
					if (state == null) {
						state = attributeInstance.getState();
					}	
					log.info("state:" + state);
					if (state.equals(DataState.UNDEFINED)) {
						return TripleStateBool.FALSE;
					} else if (state.equals(DataState.SKIPPED)) {
						return TripleStateBool.SKIPPED;
					} else {
						String value = getWorkItemValue(attributeInstance, goalWorkItem, conditionType);
						if (value == null) {
							value = attributeInstance.getValue();
						}	
						log.info("value:" + value);
						log.info("evaluateComparation(value):" + evaluateComparation(value));
						if (evaluateComparation(value)) {
							return TripleStateBool.TRUE;
						} else {
							return TripleStateBool.FALSE;
						}
					}
				}
			}
			return TripleStateBool.FALSE;
		}
	}
	
	
	private String getWorkItemValue(AttributeInstance attributeInstance, GoalWorkItem goalWorkItem, ConditionType conditionType) {
//		List<WorkItemArgument> arguments = null;
//		if (conditionType.equals(ConditionType.ACTIVATE)) {
//			arguments = goalWorkItem.getInputWorkItemArguments();
//		} else if (conditionType.equals(ConditionType.SUCESS)) {
//			arguments = goalWorkItem.getOutputWorkItemArguments();
//		}
//		for (WorkItemArgument workItemArgument : arguments) {
		if (goalWorkItem != null) {
		for (WorkItemArgument workItemArgument : goalWorkItem.getOutputWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().equals(attributeInstance)) {
				return workItemArgument.getValue();
			}
		} }
		return null;
	}
	
	private DataState getWorkItemState(AttributeInstance attributeInstance, GoalWorkItem goalWorkItem, ConditionType conditionType) {
//		List<WorkItemArgument> arguments = null;
//		if (conditionType.equals(ConditionType.ACTIVATE)) {
//			arguments = goalWorkItem.getInputWorkItemArguments();
//		} else if (conditionType.equals(ConditionType.SUCESS)) {
//			arguments = goalWorkItem.getOutputWorkItemArguments();
//		}
//		for (WorkItemArgument workItemArgument : arguments) {
		if (goalWorkItem != null) {
		for (WorkItemArgument workItemArgument : goalWorkItem.getOutputWorkItemArguments()) {
			if (workItemArgument.getAttributeInstance().equals(attributeInstance)) {
				return workItemArgument.getState();
			}
		} }
		return null;
	}

	
	private boolean evaluateComparation(String evaluateValue) {
		String conditionValueString = getValue();
		
		if (conditionValueString.equals("$TODAY$")) {
			conditionValueString = BlendedWorkflow.getInstance().getToday();
		}		
//		log.info("evaluateValue: " + evaluateValue + " with" + conditionValueString);
		if (((getAttribute().getType().equals(AttributeType.STRING) || 
				(getAttribute().getType().equals(AttributeType.BOOLEAN))))) {
			if (getOperator().equals("=")) {
				if (evaluateValue.equals(conditionValueString)) {
					return true;
				} else {
					return false;
				}
			} else 	if (getOperator().equals("!=")) {
				if (!evaluateValue.equals(conditionValueString)) {
					return true;
				} else {
					return false;
				}
			} else {
//				log.info("Invalid operator for String or Boolean type.");
				return false;
			}
		} else if (getAttribute().getType().equals(AttributeType.NUMBER)) {
			Integer value = Integer.parseInt(evaluateValue);
			Integer conditionValue =  Integer.parseInt(getValue());
			if (getOperator().equals("<")) {
				if (value < conditionValue) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals("<=")) {
				if (value <= conditionValue) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals("=")) {
				if (value == conditionValue) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals(">")) {
				if (value > conditionValue) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals(">=")) {
				if (value >= conditionValue) {
					return true;
				} else {
					return false;
				}
			} else if (getOperator().equals("!=")) {
				if (value != conditionValue) {
					return true;
				} else {
					return false;
				}
			} else {
//				log.info("Invalid operator for Number type.");
				return false;
			}
		} else {
//			log.info("Invalid operator for String or Boolean type.");
			return false;
		}
	}
	
}
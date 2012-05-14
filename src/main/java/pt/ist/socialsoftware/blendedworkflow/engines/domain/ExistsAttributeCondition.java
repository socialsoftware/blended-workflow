package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class ExistsAttributeCondition extends ExistsAttributeCondition_Base {

	public ExistsAttributeCondition(Attribute attribute) {
		setAttribute(attribute);
	}
	
	@Override
	public TripleStateBool evaluate(GoalWorkItem goalWorkItem) {
		for (WorkItemArgument workItemArgument : goalWorkItem.getOutputWorkItemArguments()) {
			Attribute workItemAttribute = workItemArgument.getAttributeInstance().getAttribute();
			Attribute conditionAttribute = getAttribute();

			if (workItemAttribute == conditionAttribute) {
				if (workItemArgument.getState().equals(DataState.SKIPPED)) {
					return TripleStateBool.SKIPPED;
				} else if (workItemArgument.getState().equals(DataState.UNDEFINED)) {
					return TripleStateBool.FALSE;
				}
			}
		}
		return TripleStateBool.TRUE;
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
		return new HashMap<Attribute, String>();
	}
	
//	@Override
//	public String getRdrCondition(String type) {
//		String condition = "";
//		String attributeName = getAttribute().getName().replaceAll(" ", "");
//		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
//		
//		condition += entityName + "_" + attributeName + "_State = " + type;
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
		
		condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED + ")";
		return condition;
	}
	
	@Override
	public String getRdrFalseCondition() {
		String condition = "(";
		String attributeName = getAttribute().getName().replaceAll(" ", "");
		String entityName = getAttribute().getEntity().getName().replaceAll(" ", "");
		
		condition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED + ")";
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
		
		condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED + ")";
		return condition;
	}

	@Override
	public String getRdrFalseConditionNEW() {
		return "(FALSE_NODE = FALSE)";
	}

	@Override
	public String toString() {
		return "existsAttribute(" + getAttribute().getEntity().getName() + "." +getAttribute().getName() +")";
	}
	
	@Override
	public Boolean existExistEntity() {
		return false;
	}


}

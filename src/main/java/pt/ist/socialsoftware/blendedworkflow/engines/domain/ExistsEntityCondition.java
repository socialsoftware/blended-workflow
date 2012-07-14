package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

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
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		getEntity().assignAllAttributeInstances(goalWorkItem, getEntity(), conditionType);
	}

	@Override
	public void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType) {
		getEntity().assignAllAttributeInstances(taskWorkItem, getEntity(), conditionType);
	}

	@Override
	public Set<Entity> getEntities() {
		Set<Entity> entity = new HashSet<Entity>();
		entity.add(getEntity());
		return entity;
	}

	@Override
	public Set<Attribute> getAttributes() {
		Set<Attribute> attributes = new HashSet<Attribute>();
		for (Attribute attribute : getEntity().getAttributes()) {
			if (attribute.getIsKeyAttribute()) {
				attributes.add(attribute);
			}
		}
		return attributes;
	}

	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		return new HashMap<Attribute, String>();
	}

	@Override
	public String getRdrUndefinedCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");
		Boolean first = true;

		for (Attribute attribute : getEntity().getAttributes()) {
			if (attribute.getIsKeyAttribute()) {

				if (first) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED;
					first = false;
				} else {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += " | " + entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED;
				}

			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrSkippedCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");
		Boolean first = true;

		for (Attribute attribute : getEntity().getAttributes()) {
			if (attribute.getIsKeyAttribute()) {

				if (first) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED;
					first = false;
				} else {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += " | " + entityName + "_" + attributeName + "_State = " + DataState.SKIPPED;
				}
			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrTrueCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");

		Boolean first = true;

		for (Attribute attribute : getEntity().getAttributes()) {
			if (attribute.getIsKeyAttribute()) {

				if (first) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED;
					first = false;
				} else {
					String attributeName = attribute.getName().replaceAll(" ", "");
					condition += " & " + entityName + "_" + attributeName + "_State = " + DataState.DEFINED;
				}

			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrFalseCondition() {
		return "(FALSE_NODE = FALSE)";
	}

	@Override
	public String toString() {
		return "existsEntity(" + getEntity().getName() +")";
	}

	@Override
	public Boolean existExistEntity() {
		return true;
	}
	
	@Override
	public Boolean existTrue(){
		return false;
	}

	/******************************
	 * Evaluate
	 ******************************/
	@Override
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		EntityInstance workItemEntityInstance = null;
		List<WorkItemArgument> arguments = null;
		TripleStateBool finalResult = TripleStateBool.TRUE;
		if (conditionType.equals(ConditionType.ACTIVATE)) {
			arguments = goalWorkItem.getInputWorkItemArguments();
		} else if (conditionType.equals(ConditionType.SUCESS)) {
			arguments = goalWorkItem.getOutputWorkItemArguments();
		}

		// Exists Entity
		for (WorkItemArgument workItemArgument : arguments) {
			Attribute workItemAttribute = workItemArgument.getAttributeInstance().getAttribute();
			Attribute conditionAttribute = getEntity().getAttribute(workItemAttribute.getName());
			if (conditionAttribute != null && conditionAttribute.getIsKeyAttribute()) {
				workItemEntityInstance = workItemArgument.getAttributeInstance().getEntityInstance();

				if (workItemArgument.getState().equals(DataState.SKIPPED)) {
					finalResult = finalResult.AND(TripleStateBool.SKIPPED);
				} else if (workItemArgument.getState().equals(DataState.UNDEFINED)) {
					finalResult = finalResult.AND(TripleStateBool.FALSE);
				} else {
					finalResult = finalResult.AND(TripleStateBool.TRUE);
				}
			}
		}

		//Exists Entity Key Relations
		for (RelationInstance relationInstance : workItemEntityInstance.getEntityInstanceOneRelationInstances()) {
			if (relationInstance.getRelationType().getIsTwoKeyEntity()) {
				EntityInstance two = relationInstance.getEntityInstanceTwo();
				finalResult = finalResult.AND(evaluateWithDataModel(two, goalWorkItem, conditionType));
			}
		}

		for (RelationInstance relationInstance : workItemEntityInstance.getEntityInstanceTwoRelationInstances()) {
			if (relationInstance.getRelationType().getIsOneKeyEntity()) {
				EntityInstance one = relationInstance.getEntityInstanceOne();
				finalResult = finalResult.AND(evaluateWithDataModel(one, goalWorkItem, conditionType));
			}
		}

		return finalResult;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance, GoalWorkItem goalWorkItem, ConditionType conditionType) {
		TripleStateBool finalResult = TripleStateBool.TRUE;

		//Exists Entity
		for (AttributeInstance attributeInstance : entityInstance.getAttributeInstances()) {
			if (attributeInstance.getAttribute().getIsKeyAttribute()) {
				TripleStateBool attributeResult;

				DataState state = getWorkItemState(attributeInstance, goalWorkItem, conditionType);
				if (state == null) {
					state = attributeInstance.getState();
				}				

				if (state.equals(DataState.DEFINED)) {
					attributeResult = TripleStateBool.TRUE;
				} else if (state.equals(DataState.SKIPPED)) {
					attributeResult = TripleStateBool.SKIPPED;
				} else {
					attributeResult = TripleStateBool.FALSE;
				}

				finalResult = finalResult.AND(attributeResult);
			}
		}
		
		
		for (RelationInstance relationInstance : entityInstance.getEntityInstanceOneRelationInstances()) {
			if (relationInstance.getRelationType().getIsTwoKeyEntity()) {
				EntityInstance two = relationInstance.getEntityInstanceTwo();
				finalResult = finalResult.AND(evaluateWithDataModel(two, goalWorkItem, conditionType));
			}
		}

		for (RelationInstance relationInstance : entityInstance.getEntityInstanceTwoRelationInstances()) {
			if (relationInstance.getRelationType().getIsOneKeyEntity()) {
				EntityInstance one = relationInstance.getEntityInstanceOne();
				finalResult = finalResult.AND(evaluateWithDataModel(one, goalWorkItem, conditionType));
			}
		}
		 
		return finalResult;
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

	@Override
	public Boolean existCompareAttributeToValue() {
		return false;
	}

}
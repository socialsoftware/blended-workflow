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
	
//	@Override
//	public String getRdrCondition(String type) {
//		String condition = "";
//		String entityName = getEntity().getName().replaceAll(" ", "");
//		
//		int attributteCount = 0;
//		for (Attribute attribute : getEntity().getAttributes()) {
//			//if (attribute.getIsKeyAttribute()) {
//
//			String joiner = " | ";
//			if (type.equals("DEFINED"))
//				joiner = " & ";
//			
//			if (attributteCount < getEntity().getAttributes().size()-1) {
//				String attributeName = attribute.getName().replaceAll(" ", "");
//				condition += entityName + "_" + attributeName + "_State = " + type + joiner;
//			}
//			else {
//				String attributeName = attribute.getName().replaceAll(" ", "");
//				condition += entityName + "_" + attributeName + "_State = " + type;
//			}
//			attributteCount++;
//			//}
//		}
//		return condition;
//	}
	
	/**
	 * AND| T | F | S   
	 * --------------
	 *  T | T | F | S
	 * --------------
	 *  F | F | F | S
	 * --------------
	 *  S | S | S | S
	 */
	@Override
	public String getRdrTrueCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");
		
//		int attributteCount = 0;
		
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
				
//				if (attributteCount < getEntity().getAttributes().size()-1) {
//					String attributeName = attribute.getName().replaceAll(" ", "");
//					condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED + " & ";
//				}
//				else {
//					String attributeName = attribute.getName().replaceAll(" ", "");
//					condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED + ")";
//				}
//				attributteCount++;
			}
		}
		condition += ")";
		return condition;
	}

	@Override
	public String getRdrFalseCondition() {
		return "((!" + getRdrTrueCondition() + ") & (!" + getRdrSkippedCondition() + "))";
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

//				if (attributteCount < getEntity().getAttributes().size()-1) {
//					String attributeName = attribute.getName().replaceAll(" ", "");
//					condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED + " | ";
//				}
//				else {
//					String attributeName = attribute.getName().replaceAll(" ", "");
//					condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED + ")";
//				}
			}
		}
		condition += ")";
		return condition;
	}
	
	
	/**
	 * NEW
	 */
	@Override
	public String getRdrUndefinedConditionNEW() {
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
	public String getRdrSkippedConditionNEW() {
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
	public String getRdrTrueConditionNEW() {
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
	public String getRdrFalseConditionNEW() {
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
		for (RelationInstance relationInstance : workItemEntityInstance.getRelationInstances()) {
			EntityInstance one = relationInstance.getEntityOne();
			EntityInstance two = relationInstance.getEntityTwo();
			if (workItemEntityInstance.equals(one) && relationInstance.getRelationType().getIsTwoKeyEntity()) {
				finalResult = finalResult.AND(evaluateWithDataModel(two));
			} else if (workItemEntityInstance.equals(two) && relationInstance.getRelationType().getIsOneKeyEntity()){
				finalResult = finalResult.AND(evaluateWithDataModel(one));
			}
		}
		
		return finalResult;
	}
	
	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance entityInstance) {
		TripleStateBool finalResult = TripleStateBool.TRUE;
		
		// Exists Entity
		for (AttributeInstance attributeInstance : entityInstance.getAttributeInstances()) {
			if (attributeInstance.getAttribute().getIsKeyAttribute()) {
				TripleStateBool attributeResult;
				if (attributeInstance.getState().equals(DataState.DEFINED)) {
					attributeResult = TripleStateBool.TRUE;
				} else if (attributeInstance.getState().equals(DataState.SKIPPED)) {
					attributeResult = TripleStateBool.SKIPPED;
				} else {
					attributeResult = TripleStateBool.FALSE;
				}
				finalResult = finalResult.AND(attributeResult);
			}
		}
		
		//Exists Entity Key Relations
		for (RelationInstance relationInstance : entityInstance.getRelationInstances()) {
			EntityInstance one = relationInstance.getEntityOne();
			EntityInstance two = relationInstance.getEntityTwo();
			if (entityInstance.equals(one) && relationInstance.getRelationType().getIsTwoKeyEntity()) {
				finalResult = finalResult.AND(evaluateWithDataModel(two));
			} else if (entityInstance.equals(two) && relationInstance.getRelationType().getIsOneKeyEntity()){
				finalResult = finalResult.AND(evaluateWithDataModel(one));
			}
		}
		
		return finalResult;
	}


}
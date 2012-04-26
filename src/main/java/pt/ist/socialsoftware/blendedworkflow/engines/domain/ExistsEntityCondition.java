package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

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
	public Set<Entity> getEntities() {
		Set<Entity> entity = new HashSet<Entity>();
		entity.add(getEntity());
		return entity;
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		Set<Attribute> attributes = new HashSet<Attribute>();
		for (Attribute attribute : getEntity().getAttributes()) {
			attributes.add(attribute);
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
//			//if (attribute.getIsKeyAttribute()) { // FIXME: Episode
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
		
		int attributteCount = 0;
		for (Attribute attribute : getEntity().getAttributes()) {
			//if (attribute.getIsKeyAttribute()) { // FIXME: In MedicalEpisode there is Entities with no keyAttributes!

			if (attributteCount < getEntity().getAttributes().size()-1) {
				String attributeName = attribute.getName().replaceAll(" ", "");
				condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED + " & ";
			}
			else {
				String attributeName = attribute.getName().replaceAll(" ", "");
				condition += entityName + "_" + attributeName + "_State = " + DataState.DEFINED + ")";
			}
			attributteCount++;
			//}
		}
		return condition;
	}

	@Override
	public String getRdrFalseCondition() {
//		String trueCondition = "(";
//		String entityName = getEntity().getName().replaceAll(" ", "");
//		
//		int attributteCount = 0;
//		for (Attribute attribute : getEntity().getAttributes()) {
//			//if (attribute.getIsKeyAttribute()) { // FIXME: In MedicalEpisode there is Entities with no keyAttributes!
//
//			if (attributteCount < getEntity().getAttributes().size()-1) {
//				String attributeName = attribute.getName().replaceAll(" ", "");
//				trueCondition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED + " | ";
//			}
//			else {
//				String attributeName = attribute.getName().replaceAll(" ", "");
//				trueCondition += entityName + "_" + attributeName + "_State = " + DataState.UNDEFINED + ")";
//			}
//			attributteCount++;
//			//}
//		}
//		
//		String skipCondition = "(";
//		entityName = getEntity().getName().replaceAll(" ", "");
//		
//		attributteCount = 0;
//		for (Attribute attribute : getEntity().getAttributes()) {
//			//if (attribute.getIsKeyAttribute()) { // FIXME: In MedicalEpisode there is Entities with no keyAttributes!
//
//			if (attributteCount < getEntity().getAttributes().size()-1) {
//				String attributeName = attribute.getName().replaceAll(" ", "");
//				skipCondition += entityName + "_" + attributeName + "_State != " + DataState.SKIPPED + " & ";
//			}
//			else {
//				String attributeName = attribute.getName().replaceAll(" ", "");
//				skipCondition += entityName + "_" + attributeName + "_State != " + DataState.SKIPPED + ")";
//			}
//			attributteCount++;
//			//}
//		}
//		return "(" + trueCondition + " & " + skipCondition + ")";
		
		return "((!" + getRdrTrueCondition() + ") & (!" + getRdrSkippedCondition() + "))";
	}

	@Override
	public String getRdrSkippedCondition() {
		String condition = "(";
		String entityName = getEntity().getName().replaceAll(" ", "");
		
		int attributteCount = 0;
		for (Attribute attribute : getEntity().getAttributes()) {
			//if (attribute.getIsKeyAttribute()) { // FIXME: In MedicalEpisode there is Entities with no keyAttributes!

			if (attributteCount < getEntity().getAttributes().size()-1) {
				String attributeName = attribute.getName().replaceAll(" ", "");
				condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED + " | ";
			}
			else {
				String attributeName = attribute.getName().replaceAll(" ", "");
				condition += entityName + "_" + attributeName + "_State = " + DataState.SKIPPED + ")";
			}
			attributteCount++;
			//}
		}
		return condition;
	}

}
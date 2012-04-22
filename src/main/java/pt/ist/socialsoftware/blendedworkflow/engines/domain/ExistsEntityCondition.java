package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
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
	public Set<Entity> getEntities() {
		Set<Entity> entity = new HashSet<Entity>();
		entity.add(getEntity());
		return entity;
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		return new HashSet<Attribute>();
	}
	
	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		return new HashMap<Attribute, String>();
	}
	
	@Override
	public String getRdrCondition(String type) {
		String condition = "";
		String entityName = getEntity().getName().replaceAll(" ", "");
		
		int attributteCount = 0;
		for (Attribute attribute : getEntity().getAttributes()) {
			//if (attribute.getIsKeyAttribute()) { // FIXME: Episode

			String joiner = " | ";
			if (type.equals("DEFINED"))
				joiner = " & ";
			
			if (attributteCount < getEntity().getAttributes().size()-1) {
				String attributeName = attribute.getName().replaceAll(" ", "");
				condition += entityName + "_" + attributeName + "_State = " + type + joiner;
			}
			else {
				String attributeName = attribute.getName().replaceAll(" ", "");
				condition += entityName + "_" + attributeName + "_State = " + type;
			}
			attributteCount++;
			//}
		}
		return condition;
	}

}
package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class ForAllCondition extends ForAllCondition_Base {
    
    public ForAllCondition(Relation relation, Entity entity, Condition condition) {
    	setForAllEntity(entity);
    	setForAllRelation(relation);
//    	setRelationRole(new RelationRole(relation, entity));
    	setCondition(condition);
    }

	@Override
	Condition cloneCondition(GoalModelInstance goalModelInstance) {
		DataModelInstance dataModelInstance = goalModelInstance.getBwInstance().getDataModelInstance();
		Relation relation = dataModelInstance.getRelation(getForAllRelation().getName());
		Entity entity = dataModelInstance.getEntity(getForAllEntity().getName());
		return new ForAllCondition(relation, entity, getCondition().cloneCondition(goalModelInstance));
	}

	@Override
	Condition cloneCondition(TaskModelInstance taskModelInstance) {
		DataModelInstance dataModelInstance = taskModelInstance.getBwInstance().getDataModelInstance();
		Relation relation = dataModelInstance.getRelation(getForAllRelation().getName());
		Entity entity = dataModelInstance.getEntity(getForAllEntity().getName());
		return new ForAllCondition(relation, entity, getCondition().cloneCondition(taskModelInstance));
	}

	@Override
	public
	void assignAttributeInstances(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		//TODO:assignAttributeInstances
	}

	@Override
	void assignAttributeInstances(TaskWorkItem taskWorkItem, String conditionType) {
		//TODO:assignAttributeInstances
	}

	@Override
	public Set<Entity> getEntities() {
		Set<Entity> entity = new HashSet<Entity>();
		entity.add(getForAllEntity());
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
	public String toString() {
		return "ForAll[" + getForAllEntity().getName() + "." + getForAllRelation().getName() + "," + getCondition() + "]";
	}
	
	@Override
	public Boolean existExistEntity() {
		return false;
	}
    
	//TODO: WorkletService
	@Override
	public String getRdrTrueCondition() {
		return null;
	}

	@Override
	public String getRdrFalseCondition() {
		return null;
	}

	@Override
	public String getRdrSkippedCondition() {
		return null;
	}

	@Override
	public String getRdrUndefinedConditionNEW() {
		return null;
	}

	@Override
	public String getRdrSkippedConditionNEW() {
		return null;
	}

	@Override
	public String getRdrTrueConditionNEW() {
		return null;
	}

	@Override
	public String getRdrFalseConditionNEW() {
		return null;
	}
	
	/******************************
	 * Evaluate
	 ******************************/
	@Override
	public TripleStateBool evaluateWithWorkItem(GoalWorkItem goalWorkItem, ConditionType conditionType) {
		return null;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(EntityInstance invalid) {
		TripleStateBool result = TripleStateBool.TRUE;
		for (RelationInstance relationInstance : getForAllRelation().getRelationInstances()) {
			EntityInstance entityInstance = relationInstance.getEntityInstance(getForAllEntity());
			result = result.AND(getCondition().evaluateWithDataModel(entityInstance));
		}
		return result;
	}
}

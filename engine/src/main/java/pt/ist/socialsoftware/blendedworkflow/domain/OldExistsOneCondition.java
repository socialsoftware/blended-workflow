package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import pt.ist.socialsoftware.blendedworkflow.service.dto.ExpressionDTO;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class OldExistsOneCondition extends OldExistsOneCondition_Base {

	public OldExistsOneCondition(RelationBW relation, Entity entity, Condition condition) {
		setExistsOneEntity(entity);
		setExistsOneRelation(relation);
		setCondition(condition);
	}

	@Override
	Condition cloneCondition(OldGoalModelInstance goalModelInstance) {
		OldDataModelInstance dataModelInstance = goalModelInstance.getBwInstance().getDataModelInstance();
		RelationBW relation = dataModelInstance.getRelation(getExistsOneRelation().getName());
		Entity entity = dataModelInstance.getEntity(getExistsOneEntity().getName()).get();
		return new OldExistsOneCondition(relation, entity, getCondition().cloneCondition(goalModelInstance));
	}

	@Override
	Condition cloneCondition(OldTaskModelInstance taskModelInstance) {
		OldDataModelInstance dataModelInstance = taskModelInstance.getBwInstance().getDataModelInstance();
		RelationBW relation = dataModelInstance.getRelation(getExistsOneRelation().getName());
		Entity entity = dataModelInstance.getEntity(getExistsOneEntity().getName()).get();
		return new OldForAllCondition(relation, entity, getCondition().cloneCondition(taskModelInstance));
	}

	@Override
	public void assignAttributeInstances(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO:assignAttributeInstances
	}

	@Override
	void assignAttributeInstances(OldTaskWorkItem taskWorkItem, ConditionType conditionType) {
		// TODO:assignAttributeInstances
	}

	@Override
	public Set<Entity> getEntities() {
		Set<Entity> entities = new HashSet<Entity>();
		entities.add(getExistsOneEntity());
		return entities;
	}

	@Override
	public Set<Attribute> getAttributeSet() {
		return new HashSet<Attribute>();
	}

	@Override
	public Set<Path> getPathSet() {
		return new HashSet<Path>();
	}

	@Override
	public HashMap<Attribute, String> getcompareConditionValues() {
		return new HashMap<Attribute, String>();
	}

	@Override
	public String toString() {
		return "existsOne[" + getExistsOneEntity().getName() + "." + getExistsOneRelation().getName() + ","
				+ getCondition() + "]";
	}

	@Override
	public Boolean existExistEntity() {
		return false;
	}

	@Override
	public String getRdrUndefinedCondition() {
		return null;
	}

	@Override
	public String getRdrSkippedCondition() {
		return null;
	}

	@Override
	public String getRdrTrueCondition() {
		return null;
	}

	@Override
	public String getRdrFalseCondition() {
		return null;
	}

	/******************************
	 * Evaluate
	 ******************************/
	@Override
	public TripleStateBool evaluate(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		// TODO:Refactor
		return TripleStateBool.FALSE;
	}

	@Override
	public TripleStateBool evaluateWithWorkItem(OldGoalWorkItem goalWorkItem, ConditionType conditionType) {
		TripleStateBool result = TripleStateBool.FALSE;
		for (OldRelationInstance relationInstance : getExistsOneRelation().getOldRelationInstanceSet()) {
			OldEntityInstance entityInstance = relationInstance.getEntityInstance(getExistsOneEntity());
			result = result.OR(getCondition().evaluateWithDataModel(entityInstance, goalWorkItem, conditionType));
		}
		return result;
	}

	@Override
	public TripleStateBool evaluateWithDataModel(OldEntityInstance invalid, OldGoalWorkItem goalWorkItem,
			ConditionType conditionType) {
		TripleStateBool result = TripleStateBool.FALSE;
		for (OldRelationInstance relationInstance : getExistsOneRelation().getOldRelationInstanceSet()) {
			OldEntityInstance entityInstance = relationInstance.getEntityInstance(getExistsOneEntity());
			result = result.OR(getCondition().evaluateWithDataModel(entityInstance, goalWorkItem, conditionType));
		}
		return result;
	}

	@Override
	public Boolean existCompareAttributeToValue() {
		return false;
	}

	@Override
	public Boolean existTrue() {
		return false;
	}

	@Override
	public String getSubPath() {
		return OldExistsOneCondition.class.getName();
	}

	@Override
	public ExpressionDTO getExpressionDTO(String specId) {
		// TODO Auto-generated method stub
		return null;
	}

}

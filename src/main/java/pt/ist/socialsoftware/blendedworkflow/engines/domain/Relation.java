package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;

public class Relation extends Relation_Base {

	public enum Cardinality {MANY, ZERO_OR_ONE, ONE}

	public Relation(DataModel dataModel, String name, Entity entityOne, Entity entityTwo, Cardinality cardinalityOne, Cardinality cardinalityTwo, boolean isOneKeyEntity, boolean isTwoKeyEntity) throws BlendedWorkflowException {
		checkUniqueRelationName(dataModel,name);
		setDataModel(dataModel);
		setName(name);
		addEntities(entityOne);
		addEntities(entityTwo);
		setCardinalityOne(cardinalityOne);
		setCardinalityTwo(cardinalityTwo);
		setIsOneKeyEntity(isOneKeyEntity);
		setIsTwoKeyEntity(isTwoKeyEntity);
	}

	private void checkUniqueRelationName(DataModel dataModel, String name) throws BlendedWorkflowException {
		for (Relation relation : dataModel.getRelations()) {
			if (relation.getName().equals(name)) {
				throw new BlendedWorkflowException("Exception @Relation: The Relation name: " + name + "already exists.");
			}
		}
	}

	public void cloneRelation(DataModelInstance dataModelInstance, Entity entityOne, Entity entityTwo) throws BlendedWorkflowException {
		new Relation(dataModelInstance, getName(), entityOne, entityTwo, getCardinalityOne(), getCardinalityTwo(), getIsOneKeyEntity(), getIsTwoKeyEntity());
	}

	public Entity getEntityOne () {
		return getEntities().get(0);
	}

	public Entity getEntityTwo () {
		return getEntities().get(1);
	}

}
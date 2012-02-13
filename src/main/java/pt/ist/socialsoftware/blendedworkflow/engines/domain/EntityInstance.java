package pt.ist.socialsoftware.blendedworkflow.engines.domain;

public class EntityInstance extends EntityInstance_Base {

	public EntityInstance(DataModelInstance dataModelInstance, Entity entity) {
		setEntity(entity);
		setId(entity.getName() + "." + entity.getNewEntityInstanceId()); // Id: EntityName.#
		setAttributeInstanceCounter(0);
		setRelationInstanceCounter(0);
	}

	public String getNewAttributeInstanceId () {
		setAttributeInstanceCounter(getAttributeInstanceCounter()+1);
		return getAttributeInstanceCounter().toString();
	}

	public String getNewRelationInstanceId () {
		setRelationInstanceCounter(getRelationInstanceCounter()+1);
		return getRelationInstanceCounter().toString();
	}

}
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

	public void assignAttributeInstances(GoalWorkItem goalWorkItem, Attribute attribute) {
		boolean exists = false;
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			if (attributeInstance.getAttribute().equals(attribute)) {
				goalWorkItem.addAttributeInstances(attributeInstance);
				exists = true;
			}
		}
		if (!exists) {
			AttributeInstance attributeInstance = new AttributeInstance(attribute, this);
			goalWorkItem.addAttributeInstances(attributeInstance);
		}	
	}

}
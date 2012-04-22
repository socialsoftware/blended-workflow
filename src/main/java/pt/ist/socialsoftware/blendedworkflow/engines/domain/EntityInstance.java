package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public class EntityInstance extends EntityInstance_Base {
	
	public EntityInstance(DataModelInstance dataModelInstance, Entity entity) {
		setEntity(entity);
		setID(entity.getName() + "." + entity.getNewEntityInstanceId()); // Id: EntityName.#
		setAttributeInstanceCounter(0);
		setRelationInstanceCounter(0);
		setState(DataState.UNDEFINED);
	}
	
	/**
	 * Create and assign EntityInstances and AttributesInstances to Workitems
	 */
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, Attribute attribute) {
		boolean exists = false;
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			if (attributeInstance.getAttribute().equals(attribute)) {
				goalWorkItem.addContraintViolationAttributeInstances(attributeInstance);
				exists = true;
			}
		}
		if (!exists) {
			AttributeInstance attributeInstance = new AttributeInstance(attribute, this);
			goalWorkItem.addContraintViolationAttributeInstances(attributeInstance);
		}	
	}

	public void assignAttributeInstances(TaskWorkItem taskWorkItem, Attribute attribute, String conditionType) {
		boolean exists = false;
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			if (attributeInstance.getAttribute().equals(attribute)) {
				if (conditionType.equals("pre")) {
					taskWorkItem.addPreConstraintAttributeInstances(attributeInstance);
				}
				if (conditionType.equals("post")) {
					taskWorkItem.addContraintViolationAttributeInstances(attributeInstance);
				}
				exists = true;
			}
		}
		if (!exists) {
			AttributeInstance attributeInstance = new AttributeInstance(attribute, this);
			if (conditionType.equals("pre")) {
				taskWorkItem.addPreConstraintAttributeInstances(attributeInstance);
			}
			if (conditionType.equals("post")) {
				taskWorkItem.addContraintViolationAttributeInstances(attributeInstance);
			}
		}	
	}
	
	public String getNewAttributeInstanceID () {
		setAttributeInstanceCounter(getAttributeInstanceCounter()+1);
		return getAttributeInstanceCounter().toString();
	}

	public String getNewRelationInstanceID () {
		setRelationInstanceCounter(getRelationInstanceCounter()+1);
		return getRelationInstanceCounter().toString();
	}

	/**
	 * Check if all key AttributeInstanceseevaluate are DEFINED
	 */
	public void checkState() {
		int keyAttributesTotal = 0;
		int keyAttributesDefined = 0;
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			// Count total of key Attributes
			if (attributeInstance.getAttribute().getIsKeyAttribute() == true) {
				keyAttributesTotal++;
			}
			
			// Count total of key Attributes defined
			if (attributeInstance.getAttribute().getIsKeyAttribute() == true && attributeInstance.getState() == DataState.DEFINED) {
				keyAttributesDefined++;
			}
		}

		if (keyAttributesDefined == keyAttributesTotal) {
			setState(DataState.DEFINED);
		}
	}

}
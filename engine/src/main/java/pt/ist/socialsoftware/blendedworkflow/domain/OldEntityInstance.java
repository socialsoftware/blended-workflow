package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;

@Deprecated
public class OldEntityInstance extends OldEntityInstance_Base {

	public OldEntityInstance(Entity entity) {
		setEntity(entity);
		setID(entity.getName() + "." + entity.getNewOldEntityInstanceId()); // Id:
																			// EntityName.#
		setAttributeInstanceCounter(0);
		setRelationInstanceCounter(0);
		setState(DataState.UNDEFINED);
	}

	/**
	 * Create and assign EntityInstances and AttributesInstances to Workitems
	 */
	public void assignAttributeInstances(OldGoalWorkItem goalWorkItem, Attribute attribute,
			ConditionType conditionType) {
		boolean exists = false;
		for (OldAttributeInstance attributeInstance : getAttributeInstancesSet()) {
			if (attributeInstance.getAttribute().equals(attribute)) {
				if (conditionType.equals(ConditionType.SUCCESS_CONDITION)) {
					goalWorkItem.addOutputAttributeInstances(attributeInstance);
				} else {
					goalWorkItem.addInputAttributeInstances(attributeInstance);
				}
				exists = true;
			}
		}

		if (!exists) {
			OldAttributeInstance attributeInstance = new OldAttributeInstance(attribute, this);
			if (conditionType.equals(ConditionType.SUCCESS_CONDITION)) {
				goalWorkItem.addOutputAttributeInstances(attributeInstance);
			} else {
				goalWorkItem.addInputAttributeInstances(attributeInstance);
			}

		}
	}

	public void assignAttributeInstances(OldTaskWorkItem taskWorkItem, Attribute attribute,
			ConditionType conditionType) {
		boolean exists = false;
		boolean existsOne = false;
		for (OldAttributeInstance attributeInstance : getAttributeInstancesSet()) {
			if (attributeInstance.getAttribute().equals(attribute)) {
				if (conditionType.equals(ConditionType.PRE_CONDITION)) {

					for (OldAttributeInstance ai : taskWorkItem.getInputAttributeInstancesSet()) {
						if (attributeInstance.getID().equals(ai.getID())) {
							existsOne = true;
						}
					}
					if (!existsOne) {
						taskWorkItem.addInputAttributeInstances(attributeInstance);
					}
				}
				if (conditionType.equals(ConditionType.POS_CONDITION)) {
					for (OldAttributeInstance ai : taskWorkItem.getOutputAttributeInstancesSet()) {
						if (attributeInstance.getID().equals(ai.getID())) {
							existsOne = true;
						}
					}
					if (!existsOne) {
						taskWorkItem.addOutputAttributeInstances(attributeInstance);
					}
				}
				exists = true;
			}
		}
		if (!exists) {
			OldAttributeInstance attributeInstance = new OldAttributeInstance(attribute, this);
			if (conditionType.equals(ConditionType.PRE_CONDITION)) {
				taskWorkItem.addInputAttributeInstances(attributeInstance);
			}
			if (conditionType.equals(ConditionType.POS_CONDITION)) {
				taskWorkItem.addOutputAttributeInstances(attributeInstance);
			}
		}
	}

	public String getNewAttributeInstanceID() {
		setAttributeInstanceCounter(getAttributeInstanceCounter() + 1);
		return getAttributeInstanceCounter().toString();
	}

	public String getNewRelationInstanceID() {
		setRelationInstanceCounter(getRelationInstanceCounter() + 1);
		return getRelationInstanceCounter().toString();
	}

	/**
	 * Check if all key AttributeInstanceseevaluate are DEFINED or SKIPPED
	 */
	public void checkState() {
		int keyAttributesTotal = 0;
		int keyAttributesDefined = 0;
		int keyAttributesSkipped = 0;
		for (OldAttributeInstance attributeInstance : getAttributeInstancesSet()) {
			// Count total of key Attributes
			if (attributeInstance.getAttribute().getIsKeyAttribute() == true) {
				keyAttributesTotal++;
			}

			// Count total of key Attributes defined
			if (attributeInstance.getAttribute().getIsKeyAttribute() == true
					&& attributeInstance.getState() == DataState.DEFINED) {
				keyAttributesDefined++;

				// Count total of key Attributes skipped
			} else if (attributeInstance.getAttribute().getIsKeyAttribute() == true
					&& attributeInstance.getState() == DataState.SKIPPED) {
				keyAttributesSkipped++;
			}
		}

		if (keyAttributesDefined == keyAttributesTotal) {
			setState(DataState.DEFINED);
		} else if (keyAttributesSkipped == keyAttributesTotal) {
			setState(DataState.SKIPPED);
		}
	}

	public OldAttributeInstance getAttributeInstance(String ID) {
		for (OldAttributeInstance attributeInstance : getAttributeInstancesSet()) {
			if (attributeInstance.getID().equals(ID)) {
				return attributeInstance;
			}
		}
		return null;
	}

	public void cloneEntityInstance(OldDataModelInstance dataModelInstance, Entity newEntity) {
		OldEntityInstance newEntityInstance = new OldEntityInstance(newEntity);
		for (OldAttributeInstance attributeInstance : getAttributeInstancesSet()) {
			attributeInstance.cloneAttributeInstance(dataModelInstance, newEntityInstance);
		}
	}

}
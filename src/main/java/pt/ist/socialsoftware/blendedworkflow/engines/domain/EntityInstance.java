package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;

public class EntityInstance extends EntityInstance_Base {
	private static Logger log = Logger.getLogger("++++++++++++++++>");

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
	public void assignAttributeInstances(GoalWorkItem goalWorkItem, Attribute attribute, ConditionType conditionType) {
		log.info("AssignAttributeInstances: for Att: " + attribute.getName() + " CT:" + conditionType + " GW:" + goalWorkItem.getID());
		boolean exists = false;
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			if (attributeInstance.getAttribute().equals(attribute)) {
				if (conditionType.equals(ConditionType.SUCESS)) {
					log.info("addOutputAttributeInstances:" + attributeInstance.getID());
					goalWorkItem.addOutputAttributeInstances(attributeInstance);
				} else {
					log.info("addInputAttributeInstances:" + attributeInstance.getID());
					goalWorkItem.addInputAttributeInstances(attributeInstance);
				}
				exists = true;
			}
		}

		if (!exists) {
			AttributeInstance attributeInstance = new AttributeInstance(attribute, this);
			if (conditionType.equals(ConditionType.SUCESS)) {
				goalWorkItem.addOutputAttributeInstances(attributeInstance);
			} else {
				goalWorkItem.addInputAttributeInstances(attributeInstance);
			}
			
		}		
	}

	public void assignAttributeInstances(TaskWorkItem taskWorkItem, Attribute attribute, String conditionType) {
		boolean exists = false;
		boolean existsOne = false;
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			if (attributeInstance.getAttribute().equals(attribute)) {
				if (conditionType.equals("pre")) {

					for (AttributeInstance ai : taskWorkItem.getInputAttributeInstances()) {
						if (attributeInstance.getID().equals(ai.getID())) {
							existsOne = true;
						}
					}
					if (!existsOne) {
						taskWorkItem.addInputAttributeInstances(attributeInstance);
					}
				}
				if (conditionType.equals("post")) {
					for (AttributeInstance ai : taskWorkItem.getOutputAttributeInstances()) {
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
			AttributeInstance attributeInstance = new AttributeInstance(attribute, this);
			if (conditionType.equals("pre")) {
				taskWorkItem.addInputAttributeInstances(attributeInstance);
			}
			if (conditionType.equals("post")) {
				taskWorkItem.addOutputAttributeInstances(attributeInstance);
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
	 * Check if all key AttributeInstanceseevaluate are DEFINED or SKIPPED
	 */
	public void checkState() {
		int keyAttributesTotal = 0;
		int keyAttributesDefined = 0;
		int keyAttributesSkipped = 0;
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			// Count total of key Attributes
			if (attributeInstance.getAttribute().getIsKeyAttribute() == true) {
				keyAttributesTotal++;
			}
			
			// Count total of key Attributes defined
			if (attributeInstance.getAttribute().getIsKeyAttribute() == true && attributeInstance.getState() == DataState.DEFINED) {
				keyAttributesDefined++;

			// Count total of key Attributes skipped
			} else if (attributeInstance.getAttribute().getIsKeyAttribute() == true && attributeInstance.getState() == DataState.SKIPPED) {
				keyAttributesSkipped++;
			}
		}

		if (keyAttributesDefined == keyAttributesTotal) {
			setState(DataState.DEFINED);
		} else if (keyAttributesSkipped == keyAttributesTotal) {
			setState(DataState.SKIPPED);
		} 
	}

	public AttributeInstance getAttributeInstance(String ID) {
		for (AttributeInstance attributeInstance : getAttributeInstances()) {
			if (attributeInstance.getID().equals(ID)) {
				return attributeInstance;
			}
		}
		return null;
	}

}
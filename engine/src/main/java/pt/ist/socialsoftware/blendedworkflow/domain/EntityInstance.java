package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel.DataState;

public class EntityInstance extends EntityInstance_Base {

    public EntityInstance(BWEntity entity) {
        setEntity(entity);
        setID(entity.getName() + "." + entity.getNewEntityInstanceId()); // Id:
                                                                         // EntityName.#
        setAttributeInstanceCounter(0);
        setRelationInstanceCounter(0);
        setState(DataState.UNDEFINED);
    }

    /**
     * Create and assign EntityInstances and AttributesInstances to Workitems
     */
    public void assignAttributeInstances(GoalWorkItem goalWorkItem,
            BWAttribute attribute, ConditionType conditionType) {
        boolean exists = false;
        for (AttributeInstance attributeInstance : getAttributeInstancesSet()) {
            if (attributeInstance.getAttribute().equals(attribute)) {
                if (conditionType.equals(ConditionType.SUCESS_CONDITION)) {
                    goalWorkItem.addOutputAttributeInstances(attributeInstance);
                } else {
                    goalWorkItem.addInputAttributeInstances(attributeInstance);
                }
                exists = true;
            }
        }

        if (!exists) {
            AttributeInstance attributeInstance = new AttributeInstance(
                    attribute, this);
            if (conditionType.equals(ConditionType.SUCESS_CONDITION)) {
                goalWorkItem.addOutputAttributeInstances(attributeInstance);
            } else {
                goalWorkItem.addInputAttributeInstances(attributeInstance);
            }

        }
    }

    public void assignAttributeInstances(TaskWorkItem taskWorkItem,
            BWAttribute attribute, ConditionType conditionType) {
        boolean exists = false;
        boolean existsOne = false;
        for (AttributeInstance attributeInstance : getAttributeInstancesSet()) {
            if (attributeInstance.getAttribute().equals(attribute)) {
                if (conditionType.equals(ConditionType.PRE_CONDITION)) {

                    for (AttributeInstance ai : taskWorkItem
                            .getInputAttributeInstancesSet()) {
                        if (attributeInstance.getID().equals(ai.getID())) {
                            existsOne = true;
                        }
                    }
                    if (!existsOne) {
                        taskWorkItem
                                .addInputAttributeInstances(attributeInstance);
                    }
                }
                if (conditionType.equals(ConditionType.POS_CONDITION)) {
                    for (AttributeInstance ai : taskWorkItem
                            .getOutputAttributeInstancesSet()) {
                        if (attributeInstance.getID().equals(ai.getID())) {
                            existsOne = true;
                        }
                    }
                    if (!existsOne) {
                        taskWorkItem
                                .addOutputAttributeInstances(attributeInstance);
                    }
                }
                exists = true;
            }
        }
        if (!exists) {
            AttributeInstance attributeInstance = new AttributeInstance(
                    attribute, this);
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
        for (AttributeInstance attributeInstance : getAttributeInstancesSet()) {
            // Count total of key Attributes
            if (attributeInstance.getAttribute().getIsKeyAttribute() == true) {
                keyAttributesTotal++;
            }

            // Count total of key Attributes defined
            if (attributeInstance.getAttribute().getIsKeyAttribute() == true
                    && attributeInstance.getState() == DataState.DEFINED) {
                keyAttributesDefined++;

                // Count total of key Attributes skipped
            } else
                if (attributeInstance.getAttribute().getIsKeyAttribute() == true
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

    public AttributeInstance getAttributeInstance(String ID) {
        for (AttributeInstance attributeInstance : getAttributeInstancesSet()) {
            if (attributeInstance.getID().equals(ID)) {
                return attributeInstance;
            }
        }
        return null;
    }

    public void cloneEntityInstance(DataModelInstance dataModelInstance,
            BWEntity newEntity) {
        EntityInstance newEntityInstance = new EntityInstance(newEntity);
        for (AttributeInstance attributeInstance : getAttributeInstancesSet()) {
            attributeInstance.cloneAttributeInstance(dataModelInstance,
                    newEntityInstance);
        }
    }

}
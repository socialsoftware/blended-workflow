import { SET_GLOBAL_ATTRIBUTE_INSTANCE_VALUE } from "../constants/action-types";

export const setGlobalAttributeInstanceValue = (attributeInstance, entityInstanceId, newValue) => ({
        type: SET_GLOBAL_ATTRIBUTE_INSTANCE_VALUE,
        attributeInstance: attributeInstance,
        entityInstanceId: entityInstanceId,
        newValue: newValue
});
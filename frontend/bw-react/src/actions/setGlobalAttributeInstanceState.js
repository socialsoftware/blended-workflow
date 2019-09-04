import { SET_GLOBAL_ATTRIBUTE_INSTANCE_STATE } from "../constants/action-types";

export const setGlobalAttributeInstanceState = (attributeInstance, entityInstanceId, newState) => ({
        type: SET_GLOBAL_ATTRIBUTE_INSTANCE_STATE,
        attributeInstance: attributeInstance,
        entityInstanceId: entityInstanceId,
        newState: newState
});
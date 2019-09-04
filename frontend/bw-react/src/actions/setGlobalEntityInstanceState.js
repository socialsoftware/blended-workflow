import { SET_GLOBAL_ENTITY_INSTANCE_STATE } from "../constants/action-types";

export const setGlobalEntityInstanceState = (entityInstance, newState) => ({
        type: SET_GLOBAL_ENTITY_INSTANCE_STATE,
        entityInstance: entityInstance,
        newState: newState
});
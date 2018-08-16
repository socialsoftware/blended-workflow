import { SET_SELECTED_ENTITY_INSTANCE_ACTION } from "../constants/action-types";

export const setSelectedEntityInstanceAction = (oldId, newId) => ({
        type: SET_SELECTED_ENTITY_INSTANCE_ACTION,
        oldId: oldId,
        newId: newId
});
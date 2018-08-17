import { SET_SELECTED_ENTITY_INSTANCE } from "../constants/action-types";

export const setSelectedEntityInstance = (oldId, newId) => ({
        type: SET_SELECTED_ENTITY_INSTANCE,
        oldId: oldId,
        newId: newId
});
import { DELETE_ENTITY_INSTANCE } from "../constants/action-types";

export const deleteEntityInstance = id => ({
        type: DELETE_ENTITY_INSTANCE,
        id: id
});

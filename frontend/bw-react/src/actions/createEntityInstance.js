import { CREATE_ENTITY_INSTANCE } from "../constants/action-types";

export const createEntityInstance = entityInstance => ({
        type: CREATE_ENTITY_INSTANCE,
        entityInstance: entityInstance
});

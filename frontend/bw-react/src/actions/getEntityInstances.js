import { GET_ENTITY_INSTANCES } from "../constants/action-types";

export const getEntityInstances = entityInstances => ({
        type: GET_ENTITY_INSTANCES,
        entityInstances: entityInstances
});

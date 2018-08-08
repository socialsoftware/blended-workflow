import { GET_ENTITY_INSTANCES } from "../constants/action-types";

export const getEntityInstancesAction = entityInstances => ({
        type: GET_ENTITY_INSTANCES,
        entityInstances: entityInstances
});

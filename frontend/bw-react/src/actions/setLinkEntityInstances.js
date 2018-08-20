import { SET_LINK_ENTITY_INSTANCES } from "../constants/action-types";

export const setLinkEntityInstances = (entityInstance, mulCondition, entityInstances) => ({
        type: SET_LINK_ENTITY_INSTANCES,
        entityInstance: entityInstance, 
        mulCondition: mulCondition, 
        entityInstances: entityInstances
});
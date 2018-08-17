import { SET_ENTITY_INSTANCES_TO_DEFINE } from "../constants/action-types";

export const setEntityInstancesToDefine = entityInstancesToDefine => ({
        type: SET_ENTITY_INSTANCES_TO_DEFINE,
        entityInstancesToDefine: entityInstancesToDefine
});
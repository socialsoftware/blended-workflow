import { SET_INSTANCES } from "../constants/action-types";

export const setInstances = (instances) => ({
        type: SET_INSTANCES,
        instances: instances
});

import { SELECT_SPECIFICATION } from "../constants/action-types";

export const selectSpecificationAction = specId => ({
        type: SELECT_SPECIFICATION,
        specId: specId
});

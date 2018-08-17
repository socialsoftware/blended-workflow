import { SELECT_SPECIFICATION } from "../constants/action-types";

export const selectSpecification = specId => ({
        type: SELECT_SPECIFICATION,
        specId: specId
});

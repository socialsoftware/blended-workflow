import { SELECT_SPECIFICATION } from "../constants/action-types";

export const selectSpecification = spec => ({
        type: SELECT_SPECIFICATION,
        spec: spec
});

import { SET_SPECIFICATIONS } from "../constants/action-types";

export const setSpecifications = (specifications) => ({
        type: SET_SPECIFICATIONS,
        specifications: specifications
});

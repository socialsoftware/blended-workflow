import { SELECT_USER } from "../constants/action-types";

export const selectUser = user => ({
        type: SELECT_USER,
        user: user
});

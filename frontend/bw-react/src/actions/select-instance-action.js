import { SELECT_INSTANCE } from "../constants/action-types";

export const selectInstanceAction = name => ({
        type: SELECT_INSTANCE,
        name: name
});

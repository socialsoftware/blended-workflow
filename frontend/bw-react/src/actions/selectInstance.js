import { SELECT_INSTANCE } from "../constants/action-types";

export const selectInstance = name => ({
        type: SELECT_INSTANCE,
        name: name
});

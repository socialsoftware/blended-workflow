import { SET_USERS } from "../constants/action-types";

export const setUsers = (users) => ({
    type: SET_USERS,
    users: users
});

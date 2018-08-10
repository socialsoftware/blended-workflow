import { SET_UNIT_OF_WORK } from "../constants/action-types";

export const setUnitOfWorkAction = unitOfWork => ({
        type: SET_UNIT_OF_WORK,
        unitOfWork: unitOfWork
});
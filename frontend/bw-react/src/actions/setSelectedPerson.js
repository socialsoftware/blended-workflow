import { SET_SELECTED_PERSON } from "../constants/action-types";

export const setSelectedPerson = (entityExtId, person) => ({
        type: SET_SELECTED_PERSON,
        entityExtId: entityExtId,
        person: person
});
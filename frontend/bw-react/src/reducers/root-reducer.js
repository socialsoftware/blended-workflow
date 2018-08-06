import { SELECT_SPECIFICATION } from "../constants/action-types";
import { SELECT_INSTANCE } from "../constants/action-types";

const initialState = {
    specId: '',
    name: ''
  };

const rootReducer = (state = initialState, action) => {
    switch (action.type) {
      case SELECT_SPECIFICATION:
        return { ...state, specId: action.specId };
      case SELECT_INSTANCE:
        return { ...state, name: action.name };
      default:
        return state;
    }
};

export default rootReducer;
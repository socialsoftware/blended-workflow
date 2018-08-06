import { SELECT_SPECIFICATION } from "../constants/action-types";

const initialState = {
    specId: ''
  };

const rootReducer = (state = initialState, action) => {
    switch (action.type) {
      case SELECT_SPECIFICATION:
        return { ...state, specId: action.specId };
      default:
        return state;
    }
};

export default rootReducer;
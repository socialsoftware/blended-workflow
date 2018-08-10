import { SELECT_SPECIFICATION, SELECT_INSTANCE, GET_ENTITY_INSTANCES, 
  SET_ENTITY_INSTANCES_TO_DEFINE, CLEAR_ENTITY_INSTANCES_TO_DEFINE, SET_UNIT_OF_WORK } from "../constants/action-types";

const initialState = {
    specId: '',
    name: '',
    entityInstances: [],
    entityInstancesToDefine: {},
    unitOfWork: []
  };

const rootReducer = (state = initialState, action) => {
    switch (action.type) {
      case SELECT_SPECIFICATION:
        return { ...state, specId: action.specId };
      case SELECT_INSTANCE:
        return { ...state, name: action.name };
      case GET_ENTITY_INSTANCES:
        return { ...state, entityInstances: action.entityInstances };
      case SET_ENTITY_INSTANCES_TO_DEFINE:
        return { ...state, entityInstancesToDefine: action.entityInstancesToDefine };
      case CLEAR_ENTITY_INSTANCES_TO_DEFINE:
        return { ...state, entityInstancesToDefine: {}, unitOfWork: [] };
      case SET_UNIT_OF_WORK:
        return { ...state, unitOfWork: action.unitOfWork };
      default:
        return state;
    }
};

export default rootReducer;
import { SELECT_SPECIFICATION, SELECT_INSTANCE, GET_ENTITY_INSTANCES, 
  SET_ENTITY_INSTANCES_TO_DEFINE, CLEAR_ENTITY_INSTANCES_TO_DEFINE, 
  SET_UNIT_OF_WORK, SET_SELECTED_ENTITY_INSTANCE, SET_ATTRIBUTE_INSTANCE_VALUE, 
  SET_LINK_ENTITY_INSTANCES } from "../constants/action-types";

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
      case SET_SELECTED_ENTITY_INSTANCE:
        return { ...state, unitOfWork: state.unitOfWork.map(etd => {
          if (etd.id === action.oldId) {
            return { ...etd, id: action.newId};
          } else {
            return etd;
          }
        }) };
      case SET_ATTRIBUTE_INSTANCE_VALUE:
        return { ...state, unitOfWork: state.unitOfWork.map(etd => {
          if (etd.id === action.entityInstance.id) {
            return { ...etd, 
              attributeInstances: etd.attributeInstances.map(ai => {
                if (ai.attribute.name === action.attributeInstance.attribute.name) {
                  return { ...ai, value: action.value };
                } else {
                  return ai;
                }
            })}
          } else {
            return etd;
          }
        })};
      case SET_LINK_ENTITY_INSTANCES:
        return { ...state, unitOfWork: state.unitOfWork.map(etd => {
          if (etd.id === action.entityInstance.id) {
            return { ...etd,
              links: etd.links.map(l => {
                if (l.mulCondition.rolename === action.mulCondition.rolename) {
                  return { ...l, entityInstances: action.entityInstances};
                } else {
                  return l;
                }
              }) } 
          } else {
            return etd;
          }
        }) };
      default: 
        return state;
    }
};

export default rootReducer;
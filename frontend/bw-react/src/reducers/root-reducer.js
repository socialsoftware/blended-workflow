import { SET_SPECIFICATIONS, SELECT_SPECIFICATION, SELECT_INSTANCE, SET_INSTANCES, GET_ENTITY_INSTANCES, 
  SET_UNIT_OF_WORK, SET_SELECTED_ENTITY_INSTANCE, SET_ATTRIBUTE_INSTANCE_VALUE, 
  SET_LINK_ENTITY_INSTANCES, CREATE_ENTITY_INSTANCE, DELETE_ENTITY_INSTANCE, SET_USERS, SELECT_USER, 
  SET_SELECTED_PERSON, SET_ENTITY_IS_PERSON, SET_GLOBAL_ENTITY_INSTANCE_STATE,
  SET_GLOBAL_ATTRIBUTE_INSTANCE_STATE, SET_GLOBAL_ATTRIBUTE_INSTANCE_VALUE } from "../constants/action-types";

const initialState = {
    specifications: [],
    spec: {},
    instances: [],
    name: '',
    entityInstances: [],
    unitOfWork: [],
    users: [],
    user: {},
    entityIsPersonDtos: [],
  };

const rootReducer = (state = initialState, action) => {
    switch (action.type) {
      case SET_SPECIFICATIONS:
        return { ...state, specifications: action.specifications };
      case SELECT_SPECIFICATION:
        return { ...state, spec: action.spec, name: '' };
      case SELECT_INSTANCE:
        return { ...state, name: action.name };
      case SET_INSTANCES:
        return { ...state, instances: action.instances };
      case GET_ENTITY_INSTANCES:
        return { ...state, entityInstances: action.entityInstances };
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
      case CREATE_ENTITY_INSTANCE:
        return { ...state, unitOfWork: [ ...state.unitOfWork, action.entityInstance]};
      case DELETE_ENTITY_INSTANCE:
        return { ...state, unitOfWork: state.unitOfWork.filter(ei => ei.id !== action.id) 
        };
      case SET_USERS:
        return { ...state, users: action.users };
      case SELECT_USER:
        return { ...state, user: action.user };
      case SET_SELECTED_PERSON:
        return { ...state, entityIsPersonDtos: state.entityIsPersonDtos.map(eip => {
            if (eip.entity.extId === action.entityExtId) {
                return { ...eip, personChosen: action.person}
            } else {
                return eip;
            }
          })};
      case SET_ENTITY_IS_PERSON:
        return { ...state, entityIsPersonDtos: action.set}
      case SET_GLOBAL_ENTITY_INSTANCE_STATE:
        return { ...state, entityInstances: state.entityInstances.map(ei => {
          if (ei.id === action.entityInstance.id)
             return { ...ei, state: action.newState}
          else 
            return ei;
        }) };
      case SET_GLOBAL_ATTRIBUTE_INSTANCE_STATE:
        return { ...state, entityInstances: state.entityInstances.map(ei => {
          if (ei.id === action.entityInstanceId)
            return { ...ei, attributeInstances: ei.attributeInstances.map(ai => {
              if (ai.attribute.name === action.attributeInstance.attribute.name)
                return { ...ai, state: action.newState}
              else
                return ai;
            })}
          else 
            return ei;
        }) };
      case SET_GLOBAL_ATTRIBUTE_INSTANCE_VALUE:
        return { ...state, entityInstances: state.entityInstances.map(ei => {
          if (ei.id === action.entityInstanceId)
            return { ...ei, attributeInstances: ei.attributeInstances.map(ai => {
              if (ai.attribute.name === action.attributeInstance.attribute.name)
                return { ...ai, value: action.newValue}
              else
                return ai;
            })}
          else 
            return ei;
        }) };
      default: 
        return state;
    }
};

export default rootReducer;
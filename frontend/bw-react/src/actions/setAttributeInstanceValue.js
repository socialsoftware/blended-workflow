import { SET_ATTRIBUTE_INSTANCE_VALUE } from "../constants/action-types";

export const setAttributeInstanceValue = (entityInstance, attributeInstance, value) => ({
        type: SET_ATTRIBUTE_INSTANCE_VALUE,
        entityInstance: entityInstance, 
        attributeInstance: attributeInstance, 
        value: value
});
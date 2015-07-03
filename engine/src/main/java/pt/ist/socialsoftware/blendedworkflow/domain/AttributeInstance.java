package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;

public class AttributeInstance extends AttributeInstance_Base {

    public AttributeInstance(Attribute attribute,
            EntityInstance entityInstance) {
        setEntityInstance(entityInstance);
        setAttribute(attribute);
        setID(getAttribute().getName() + "."
                + entityInstance.getNewAttributeInstanceID());
        setState(DataState.UNDEFINED);
        super.setValue("$UNDEFINED$");
    }

    @Override
    public void setValue(String value) {
        if (value.equals("$UNDEFINED$") || value.equals("$SKIPPED$")) {
            super.setValue(value);
        } else {
            if (!getState().equals(DataState.DEFINED)) {
                setState(DataState.DEFINED);
            }
            super.setValue(value);
            getEntityInstance().checkState();
        }
    }

    @Override
    public void setState(DataState state) {
        if (state.equals(DataState.UNDEFINED)) {
            super.setState(state);
        } else {
            super.setState(state);
            getEntityInstance().checkState();
        }
    }

    public void cloneAttributeInstance(DataModelInstance dataModelInstance,
            EntityInstance newEntityInstance) {
        AttributeInstance newAttributeInstance = new AttributeInstance(
                getAttribute(), newEntityInstance);
        newAttributeInstance.setValue(getValue());
    }
}
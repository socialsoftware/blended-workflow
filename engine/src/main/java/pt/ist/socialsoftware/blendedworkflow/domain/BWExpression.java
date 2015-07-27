package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.domain.BWAttribute.AttributeType;

public abstract class BWExpression extends BWExpression_Base {

    public void delete() {
        setLeftComparison(null);
        setRightComparison(null);
        deleteDomainObject();
    }

    public BWDataModel getDataModel() {
        if (getLeftComparison() != null)
            return getLeftComparison().getDataModel();
        if (getRightComparison() != null)
            return getRightComparison().getDataModel();
        if (getLeftBinary() != null)
            return getLeftBinary().getDataModel();
        if (getRightBinary() != null)
            return getRightBinary().getDataModel();
        assert false;
        return null;
    }

    public abstract AttributeType getType();

    public abstract String getSubPath();

}

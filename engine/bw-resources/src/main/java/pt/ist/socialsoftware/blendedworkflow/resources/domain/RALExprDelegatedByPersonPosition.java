package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

public class RALExprDelegatedByPersonPosition extends RALExprDelegatedByPersonPosition_Base {

    public RALExprDelegatedByPersonPosition(ResourceModel resourceModel, RALPersonExpression personExpression) {
        setPersonExpr(personExpression);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setPersonExpr(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources() {
        return null;
    }
    
}

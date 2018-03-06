package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

public class RALExprReportsToPersonPositionExpr extends RALExprReportsToPersonPositionExpr_Base {
    
    public RALExprReportsToPersonPositionExpr(ResourceModel resourceModel, RALPersonExpression personExpression, boolean isDirectly) {
        setPersonExpr(personExpression);
        setDirectly(isDirectly);
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

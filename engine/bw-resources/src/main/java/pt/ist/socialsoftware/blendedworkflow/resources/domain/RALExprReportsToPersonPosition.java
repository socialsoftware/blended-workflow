package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

import java.util.List;

public class RALExprReportsToPersonPosition extends RALExprReportsToPersonPosition_Base {
    
    public RALExprReportsToPersonPosition(ResourceModel resourceModel, RALPersonExpression personExpression, boolean isDirectly) {
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
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return null;
    }
}

package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

import java.util.List;

public class RALExprDelegatesToPersonPosition extends RALExprDelegatesToPersonPosition_Base {

    public RALExprDelegatesToPersonPosition(ResourceModel resourceModel, RALPersonExpression personExpression) {
        setPersonExpr(personExpression);
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

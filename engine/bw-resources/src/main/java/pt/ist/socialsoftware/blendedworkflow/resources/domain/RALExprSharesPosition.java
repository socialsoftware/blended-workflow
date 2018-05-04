package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

import java.util.List;

public class RALExprSharesPosition extends RALExprSharesPosition_Base implements RALExprDeniable {

    public RALExprSharesPosition(ResourceModel resourceModel, Amount amount, RALPersonExpression personExpression) {
        setAmount(amount);
        setPersonExpr(personExpression);
        setResourceModel(resourceModel);
    }

    @Override
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return null;
    }

}

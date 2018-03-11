package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import java.util.List;

public class RALExprSharesPosition extends RALExprSharesPosition_Base {

    public RALExprSharesPosition(ResourceModel resourceModel, Amount amount, RALPersonExpression personExpression) {
        setAmount(amount);
        setPersonExpr(personExpression);
        setResourceModel(resourceModel);
    }

    @Override
    public List<Person> getEligibleResources() {
        return null;
    }

}

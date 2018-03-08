package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;

import java.util.List;

public class RALExprNot extends RALExprNot_Base {

    public RALExprNot(ResourceModel resourceModel, RALExpression expression) {
        setExpression(expression);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setExpression(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources() {
        return null;
    }

}

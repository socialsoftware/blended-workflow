package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.ArrayList;
import java.util.List;

public class RALExprOr extends RALExprOr_Base {

    public RALExprOr(ResourceModel resourceModel, RALExpression leftExpression, RALExpression rightExpression) {
        setLeftExpression(leftExpression);
        setRightExpression(rightExpression);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setLeftExpression(null);
        setRightExpression(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        ArrayList list = new ArrayList();
        list.addAll(getLeftExpression().getEligibleResources(history));
        list.addAll(getRightExpression().getEligibleResources(history));
        return list;
    }

    @Override
    public void isMergable(RALExpression expression) {
        getLeftExpression().isMergable(expression);
        getRightExpression().isMergable(expression);
    }


}

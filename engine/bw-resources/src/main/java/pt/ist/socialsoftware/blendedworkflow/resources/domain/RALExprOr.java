package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        ArrayList list = new ArrayList();
        list.addAll(getLeftExpression().getEligibleResources(history, defProducts));
        list.addAll(getRightExpression().getEligibleResources(history, defProducts));
        return list;
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        return getLeftExpression().getSetOfRequiredResources().merge(getRightExpression().getSetOfRequiredResources());
    }

    @Override
    public void isMergable(RALExpression expression) {
        getLeftExpression().isMergable(expression);
        getRightExpression().isMergable(expression);
    }


}

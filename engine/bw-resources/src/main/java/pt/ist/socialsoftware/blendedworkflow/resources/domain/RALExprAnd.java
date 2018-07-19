package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RALExprAnd extends RALExprAnd_Base {

    public RALExprAnd(ResourceModel resourceModel, RALExpression leftExpression, RALExpression rightExpression) {
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
        return getLeftExpression().getEligibleResources(history, defProducts).stream()
                .filter(person -> getRightExpression().getEligibleResources(history, defProducts).contains(person))
                .collect(Collectors.toList());
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

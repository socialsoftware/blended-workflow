package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

import java.util.ArrayList;
import java.util.List;
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
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return getLeftExpression().getEligibleResources(history).stream()
                .filter(person -> getRightExpression().getEligibleResources(history).contains(person))
                .collect(Collectors.toList());
    }
    
}

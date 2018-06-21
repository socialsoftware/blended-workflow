package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.util.List;
import java.util.stream.Collectors;

public class RALExprNot extends RALExprNot_Base {

    public RALExprNot(ResourceModel resourceModel, RALExpression expression) throws RMException {
        if (!(expression instanceof RALExprDeniable)) {
            throw new RMException(RMErrorType.INVALID_RAL_EXPRESSION_TYPE, "The expression provided to the NOT expression is not deniable");
        }
        setExpression(expression);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setExpression(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return getPersonSet().stream()
                .filter(person -> !getExpression().getEligibleResources(history).contains(person))
                .collect(Collectors.toList());
    }

    @Override
    public void isMergable(RALExpression expression) {
        getExpression().isMergable(expression);
    }
}

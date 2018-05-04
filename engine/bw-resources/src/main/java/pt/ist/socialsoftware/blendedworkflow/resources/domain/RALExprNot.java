package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;

import java.util.List;

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
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return null;
    }

}

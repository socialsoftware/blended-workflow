package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

import java.util.List;

public class RALExprHistoryExecuting extends pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprHistoryExecuting_Base {

    public RALExprHistoryExecuting(ResourceModel resourceModel, Quantifier quantifier, Product dataField) {
        setResourceModel(resourceModel);
        setQuantifier(quantifier);
        setDataField(dataField);
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return null;
    }

}

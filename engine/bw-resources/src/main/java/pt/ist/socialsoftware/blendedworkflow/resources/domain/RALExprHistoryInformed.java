package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RALExprHistoryInformed extends RALExprHistoryInformed_Base {

    public RALExprHistoryInformed(ResourceModel resourceModel, Quantifier quantifier, Product dataField) {
        setResourceModel(resourceModel);
        setQuantifier(quantifier);
        setDataField(dataField);
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        return null;
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        return new SetOfRequiredResources().addHistory(new HashSet(Arrays.asList(getDataField())));
    }

}

package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.apache.ojb.broker.util.logging.Logger;
import org.apache.ojb.broker.util.logging.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RALExprAnyone extends RALExprAnyone_Base {
    private static Logger logger = LoggerFactory.getLogger(RALExprAnyone.class);

    public RALExprAnyone(ResourceModel resourceModel) {
        setResourceModel(resourceModel);
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        return getResourceModel().getPersonSet().stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        return new SetOfRequiredResources();
    }

    @Override
    public void isMergable(RALExpression expression) {

    }
}

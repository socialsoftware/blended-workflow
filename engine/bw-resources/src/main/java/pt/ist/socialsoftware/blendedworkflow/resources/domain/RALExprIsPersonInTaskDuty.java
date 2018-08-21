package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMErrorType;
import pt.ist.socialsoftware.blendedworkflow.resources.service.RMException;
import pt.ist.socialsoftware.blendedworkflow.resources.service.dto.domain.SetOfRequiredResources;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class RALExprIsPersonInTaskDuty extends RALExprIsPersonInTaskDuty_Base {
    private static Logger logger = LoggerFactory.getLogger(RALExprIsPersonInTaskDuty.class);
    
    public RALExprIsPersonInTaskDuty(ResourceModel resourceModel, TaskDutyType taskDuty, String path) {
        setTaskDuty(taskDuty);
        setPath(path);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setPath(null);
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(WorkflowInstance history, Set<Product> defProducts) {
        return history.getEntityInstanceSet().stream()
                .map(entityInstance -> entityInstance.getProductInstancesByPath(getPath()))
                .flatMap(Collection::stream)
                .map(productInstance -> {
                    if (getTaskDuty() == TaskDutyType.RESPONSIBLE_FOR) {
                        if (defProducts.contains(productInstance.getProduct())) {
                            return productInstance.getProduct().getResponsibleFor().getEligibleResources(history, defProducts);
                        } else {
                            return Arrays.asList(productInstance.getCreatorWorkItem()
                                    .getExecutionUser().getPerson(getResourceModel().getSpec()));
                        }
                    } else {
                        return productInstance.getProduct().getInforms().getEligibleResources(history, defProducts);
                    }
                })
                .flatMap(Collection::stream)
                .collect(toList());
    }

    @Override
    public SetOfRequiredResources getSetOfRequiredResources() {
        return new SetOfRequiredResources().addTaskDuty(new HashSet<>(Arrays.asList(getPath())));
    }

    @Override
    public void isMergable(RALExpression expression) {
        throw new RMException(RMErrorType.INVALID_MERGE, "The RALExpression is not mergable.");
    }

}

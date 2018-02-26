package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;

import java.util.List;

public class RALExprIsPersonInTaskDuty extends RALExprIsPersonInTaskDuty_Base {
    
    public RALExprIsPersonInTaskDuty(ResourceModel resourceModel, TaskDutyType taskDuty, Product product) {
        setTaskDuty(taskDuty);
        setProduct(product);
        setResourceModel(resourceModel);
    }

    @Override
    public void delete() {
        setProduct(null);
        setResourceModel(null);
        deleteDomainObject();
    }

    @Override
    public List<Person> getEligibleResources() {
        return null;
    }

}

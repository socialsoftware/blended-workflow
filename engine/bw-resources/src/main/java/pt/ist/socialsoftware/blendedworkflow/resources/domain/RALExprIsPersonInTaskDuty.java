package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

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
        super.delete();
    }

    @Override
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return null;
    }

}

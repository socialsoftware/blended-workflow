package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.Product;
import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkItem;

import java.util.List;

public class RALExprIsPersonInTaskDuty extends pt.ist.socialsoftware.blendedworkflow.resources.domain.RALExprIsPersonInTaskDuty_Base {
    
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
    public List<Person> getEligibleResources(List<WorkItem> history) {
        return null;
    }

}

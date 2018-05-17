package pt.ist.socialsoftware.blendedworkflow.resources.domain;

import pt.ist.socialsoftware.blendedworkflow.core.domain.WorkflowInstance;

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
    public List<Person> getEligibleResources(WorkflowInstance history) {
        return null;
    }

}

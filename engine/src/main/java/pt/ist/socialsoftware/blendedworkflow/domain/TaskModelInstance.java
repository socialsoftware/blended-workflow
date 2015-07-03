package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class TaskModelInstance extends TaskModelInstance_Base {

    @Override
    public Task getTask(String name) throws BWException {
        for (Task task : getTasksSet()) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        throw new BWException(
                BlendedWorkflowError.NON_EXISTENT_TASK_NAME, name);
    }
}

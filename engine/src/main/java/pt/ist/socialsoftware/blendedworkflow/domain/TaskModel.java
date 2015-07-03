package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;

public class TaskModel extends TaskModel_Base {

    /**
     * Clone the TaskModel
     */
    public void cloneTaskModel(TaskModelInstance newTaskModelInstance)
            throws BWException {
        for (Task task : getTasksSet()) {
            task.cloneTask(newTaskModelInstance);
        }
    }

    public Task getTask(String name) throws BWException {
        for (Task task : getTasksSet()) {
            if (task.getName().equals(name)) {
                return task;
            }
        }
        throw new BWException(BlendedWorkflowError.NON_EXISTENT_TASK_NAME,
                name);
    }

    public void delete() {
        setSpecification(null);
        deleteDomainObject();
    }

}

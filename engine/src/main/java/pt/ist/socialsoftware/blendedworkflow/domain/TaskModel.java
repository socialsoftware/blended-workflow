package pt.ist.socialsoftware.blendedworkflow.domain;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

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
        throw new BWException(BWErrorType.NON_EXISTENT_TASK_NAME, name);
    }

    public void delete() {
        setSpecification(null);
        deleteDomainObject();
    }

}

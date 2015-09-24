package pt.ist.socialsoftware.blendedworkflow.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWInstance extends BWInstance_Base {

    private final Logger log = LoggerFactory.getLogger("BWInstance");

    /**
     * Create a new BWInstance, and clones the Data, Task and Goal models from
     * its parent BWSpecification
     */
    public BWInstance(Specification bwSpecification, String name, User user)
            throws BWException {
        setSpecification(bwSpecification);
        setID(getSpecification().getName() + "."
                + getSpecification().generateInstanceId()); // Id:
                                                            // BWSpecificationName.#
        setUser(user);

        setWorkItemCounter(0);
        setDataModelInstance(bwSpecification.getDataModel().cloneDataModel());

        GoalModelInstance goalModelInstance = new GoalModelInstance();
        setGoalModelInstance(goalModelInstance);
        bwSpecification.getGoalModel().cloneGoalModel(goalModelInstance);

        TaskModelInstance taskModelInstance = new TaskModelInstance();
        setTaskModelInstance(taskModelInstance);
        bwSpecification.getTaskModel().cloneTaskModel(taskModelInstance);

        setName(name);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        setCreationDate(dateFormat.format(Calendar.getInstance().getTime()));
        setLog(new Log());

        log.info("BWInstance: created BWInstance with id: " + getID());
    }

    public Integer getNewWorkItemID() {
        setWorkItemCounter(getWorkItemCounter() + 1);
        return getWorkItemCounter();
    }

    public WorkItem getWorkItem(String id) throws BWException {
        for (WorkItem workItem : getWorkItemsSet())
            if (workItem.getID().equals(id))
                return workItem;
        throw new BWException(BWErrorType.NON_EXISTENT_WORKITEM_ID, id);
    }

}
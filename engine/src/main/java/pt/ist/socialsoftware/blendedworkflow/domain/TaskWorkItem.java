package pt.ist.socialsoftware.blendedworkflow.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;

public class TaskWorkItem extends TaskWorkItem_Base {

    public enum ActivityState {
        NEW, PRE_ACTIVITY, ENABLED, SKIPPED, COMPLETED
    };

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private final Logger log = LoggerFactory.getLogger("TaskWorkItem");

    public TaskWorkItem(BWInstance bwInstance, Task task) {
        log.info("New TaskWorkItem for task " + task.getName());
        setBwInstance(bwInstance);
        setTask(task);
        setID(task.getName() + "." + bwInstance.getNewWorkItemID()); // Id:
                                                                     // TaskName.#
        setState(ActivityState.NEW);

        setRole(task.getRole());
        setUser(task.getUser());

        task.getPreConstraint().assignAttributeInstances(this,
                ConditionType.PRE_CONDITION);
        createInputWorkItemArguments();

        task.getPostConstraint().assignAttributeInstances(this,
                ConditionType.POS_CONDITION);
        createOutputWorkItemArguments();
    }

    /******************************
     * State Machine
     ******************************/
    public void notifyPreActivity(ConditionType conditionType) {
        log.info("ActivityWorkitem " + getID()
                + " is now in the PREACTIVITY state");
        setState(ActivityState.PRE_ACTIVITY);
        if (conditionType.equals(ConditionType.PRE_CONDITION)) {
            BlendedWorkflow.getInstance().getWorkletAdapter()
                    .requestWorkItemPostConditionEvaluation(this);
        } else if (conditionType.equals(ConditionType.POS_CONDITION)) {
            BlendedWorkflow.getInstance().getWorkListManager()
                    .notifyEnabledWorkItem(this);
        }
    }

    public void notifyEnabled(ConditionType conditionType) {
        log.info("ActivityWorkitem " + getID() + " is now in ENABLED state");
        setState(ActivityState.ENABLED);
        if (conditionType.equals(ConditionType.PRE_CONDITION)) {
            BlendedWorkflow.getInstance().getWorkletAdapter()
                    .requestWorkItemPostConditionEvaluation(this);
        } else if (conditionType.equals(ConditionType.POS_CONDITION)) {
            BlendedWorkflow.getInstance().getWorkListManager()
                    .notifyEnabledWorkItem(this);
        }
    }

    public void notifyCompleted() {
        log.info("ActivityWorkitem " + getID() + " is now in COMPLETED state");
        setState(ActivityState.COMPLETED);
        setAttributeValues();

        String date = dateFormat.format(Calendar.getInstance().getTime());
        getBwInstance().getLog().addLogRecords(new LogRecord(date, "Completed",
                "[ACTIVITY] " + getID(), getUser().getID()));
        BlendedWorkflow.getInstance().getWorkListManager()
                .notifyCompletedWorkItem(this);
    }

    public void notifySkipped() {
        log.info("ActivityWorkitem " + getID() + " is now in SKIPPED state");
        setState(ActivityState.SKIPPED);
        setAttributeSkipped();

        String date = dateFormat.format(Calendar.getInstance().getTime());
        getBwInstance().getLog().addLogRecords(new LogRecord(date, "Skipped",
                "[ACTIVITY] " + getID(), getUser().getID()));
        BlendedWorkflow.getInstance().getWorkListManager()
                .notifyCompletedWorkItem(this);
    }

    /**********************************
     * Events
     **********************************/
    @Override
    public void notifyDataChanged() {
        if (getState().equals(ActivityState.ENABLED)
                || getState().equals(ActivityState.PRE_ACTIVITY)) {
            log.info("ActivityWorkitem " + getID()
                    + " is re-evaluating due to changes in data");
            updateInputWorkItemArguments();
            updateOutputWorkItemArguments();
            BlendedWorkflow.getInstance().getWorkletAdapter()
                    .requestWorkItemPreConditionEvaluation(this);
        }
    }

    @Override
    public void notifyCheckedIn() {
        BlendedWorkflow.getInstance().getWorkletAdapter()
                .requestWorkItemPreConditionEvaluation(this);
    }
}
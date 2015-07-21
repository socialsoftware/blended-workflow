package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Window.Notification;

import pt.ist.socialsoftware.blendedworkflow.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.domain.MaintainGoal.MaintainGoalState;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.TaskWorkItem.ActivityState;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.execution.CheckInWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.service.execution.CreateGoalInstanceService;
import pt.ist.socialsoftware.blendedworkflow.service.execution.CreateNewGoalService;
import pt.ist.socialsoftware.blendedworkflow.service.execution.ManageGoalConditionService;
import pt.ist.socialsoftware.blendedworkflow.service.execution.RedoGoalWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.service.execution.SkipWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.shared.BWExecutorService;

public class WorkListManager {

    private final Logger log = LoggerFactory.getLogger("WorklistManager");
    protected BWPresentation bwPresentation = null;

    LinkedHashMap<GoalWorkItem, ArrayList<GoalWorkItem>> redoList = new LinkedHashMap<GoalWorkItem, ArrayList<GoalWorkItem>>();

    private void addReActivatedGoalWorkItem(GoalWorkItem newGW,
            GoalWorkItem causeGW) {
        redoList.put(newGW, new ArrayList<GoalWorkItem>());
        if (causeGW != null) {
            redoList.get(causeGW).add(newGW);
        }
    }

    private void removeReActivatedGoalWorkItem(GoalWorkItem goalWorkItem) {
        redoList.remove(goalWorkItem);
        for (Map.Entry<GoalWorkItem, ArrayList<GoalWorkItem>> entry : redoList
                .entrySet()) {
            entry.getValue().remove(goalWorkItem);
        }

        checkPending();
    }

    private void checkPending() {
        for (Map.Entry<GoalWorkItem, ArrayList<GoalWorkItem>> entry : redoList
                .entrySet()) {
            if (entry.getValue().isEmpty()) {
                String workItemOID = entry.getKey().getExternalId();
                BWExecutorService bwExecutorService = BlendedWorkflow
                        .getInstance().getBWExecutorService();
                CheckInWorkItemService service = new CheckInWorkItemService(
                        workItemOID);
                bwExecutorService.runTask(service);
            }
        }
    }

    public BWPresentation getBwPresentation() {
        return bwPresentation;
    }

    public void setBwPresentation(BWPresentation bwPresentation) {
        this.bwPresentation = bwPresentation;
    }

    /**
     * Notify the BWPresentation of enabled WorkItems.
     * 
     * @param workItem
     *            The enabled WorkItem (i.e. State = ENABLED or PRE_TASK).
     */
    public void notifyEnabledWorkItem(WorkItem workItem) {
        log.info("WorkItem " + workItem.getID() + " is now enabled. with OID"
                + workItem.getExternalId());
        String bwInstanceName = workItem.getBwInstance().getName() + ".";
        if (workItem.getClass().equals(GoalWorkItem.class)) {
            getBwPresentation().addGoalWorkItem(workItem.getExternalId(),
                    bwInstanceName + workItem.getID());
        } else {
            getBwPresentation().addTaskWorkItem(workItem.getExternalId(),
                    bwInstanceName + workItem.getID());
        }
    }

    public void notifyReEnabledWorkItem(GoalWorkItem workItem,
            GoalWorkItem cause) {
        log.info("WorkItem " + workItem.getID() + " is now re-enabled. with OID"
                + workItem.getExternalId());

        addReActivatedGoalWorkItem(workItem, cause);

        String bwInstanceName = workItem.getBwInstance().getName() + ".";
        getBwPresentation().addGoalWorkItem(workItem.getExternalId(),
                bwInstanceName + workItem.getID()); // + "(ReEnabled)"
    }

    /**
     * Notify the BWPresentation of completed WorkItems.
     * 
     * @param workItem
     *            The pending WorkItem (i.e. State = COMPLETED).
     */
    public void notifyCompletedWorkItem(WorkItem workItem) {
        String bwInstanceName = workItem.getBwInstance().getName() + ".";
        if (workItem instanceof GoalWorkItem) {
            GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
            log.info("WorkItem " + workItem.getID() + " is now "
                    + goalWorkItem.getState() + ".");
            getBwPresentation().removeGoalWorkItem(goalWorkItem.getExternalId(),
                    bwInstanceName + goalWorkItem.getID(),
                    goalWorkItem.getState().toString());

            removeReActivatedGoalWorkItem(goalWorkItem);
        } else {
            TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
            log.info("WorkItem " + workItem.getID() + " is now "
                    + taskWorkItem.getState() + ".");
            getBwPresentation().removeTaskWorkItem(taskWorkItem.getExternalId(),
                    bwInstanceName + taskWorkItem.getID(),
                    taskWorkItem.getState().toString());
        }
    }

    public void notifyWorkItemState(WorkItem workItem) {
        String message = "WorkItem: " + workItem.getID()
                + " activate condition failed.";
        getBwPresentation().getMainWindow().showNotification(message,
                Notification.TYPE_ERROR_MESSAGE);
    }

    public void notifyNewGoalCreated(AchieveGoal goal) {
        getBwPresentation().getMainWindow().showNotification(
                "New Goal " + goal.getName() + " created successfully",
                Notification.TYPE_TRAY_NOTIFICATION);
    }

    public void notifyException(BWErrorType bwe) {
        getBwPresentation().getMainWindow().showNotification(bwe.toString(),
                Notification.TYPE_ERROR_MESSAGE);
    }

    /**
     * Update the BWPresentation with all the active WorkItems.
     */
    public void updateBWPresentation() {
        for (BWSpecification bwSpecification : BlendedWorkflow.getInstance()
                .getSpecificationSet()) {
            for (BWInstance bwInstance : bwSpecification.getBwInstancesSet()) {
                for (WorkItem workItem : bwInstance.getWorkItemsSet()) {
                    if (workItem instanceof GoalWorkItem) {
                        GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
                        if (goalWorkItem.getState().equals(GoalState.ENABLED)
                                || goalWorkItem.getState()
                                        .equals(GoalState.PRE_GOAL))
                            notifyEnabledWorkItem(goalWorkItem);
                    } else {
                        TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
                        if (taskWorkItem.getState()
                                .equals(ActivityState.ENABLED)
                                || taskWorkItem.getState()
                                        .equals(ActivityState.PRE_ACTIVITY))
                            notifyEnabledWorkItem(taskWorkItem);
                    }
                }
            }
        }
    }

    /********************
     * Services.
     ********************/
    public void checkInWorkItem(String workItemOID) {
        BWExecutorService bwExecutorService = BlendedWorkflow.getInstance()
                .getBWExecutorService();
        CheckInWorkItemService service = new CheckInWorkItemService(
                workItemOID);
        bwExecutorService.runTask(service);
    }

    public void skipWorkItem(String workItemOID) {
        BWExecutorService bwExecutorService = BlendedWorkflow.getInstance()
                .getBWExecutorService();
        SkipWorkItemService service = new SkipWorkItemService(workItemOID);
        bwExecutorService.runTask(service);
    }

    public void createGoal(String bwInstanceOID, String goalName,
            String goalDescription, String parentGoalID, String goalCondition,
            ArrayList<String> activateConditions, String entityOID,
            String userID) {
        BWExecutorService bwExecutorService = BlendedWorkflow.getInstance()
                .getBWExecutorService();
        CreateNewGoalService service = new CreateNewGoalService(bwInstanceOID,
                goalName, goalDescription, parentGoalID, goalCondition,
                activateConditions, entityOID, userID);
        bwExecutorService.runTask(service);
    }

    public void createGoalInstance(String bwInstanceOID, String goalOID,
            Set<String> activateConditions, Set<String> maintainConditions,
            Map<String, String> entitiesOID) {
        BWExecutorService bwExecutorService = BlendedWorkflow.getInstance()
                .getBWExecutorService();
        CreateGoalInstanceService service = new CreateGoalInstanceService(
                bwInstanceOID, goalOID, activateConditions, maintainConditions,
                entitiesOID);
        // CreateGoalInstanceService service = new
        // CreateGoalInstanceService(bwInstanceOID, parentGoalID, context,
        // activateConditions, maintainConditions, relationsEntityInstances);
        bwExecutorService.runTask(service);
    }

    public void redoGoal(String workItemOID, String userID) {
        log.info("Redo GoalWorkItem: " + workItemOID);
        BWExecutorService bwExecutorService = BlendedWorkflow.getInstance()
                .getBWExecutorService();
        RedoGoalWorkItemService service = new RedoGoalWorkItemService(
                workItemOID, userID);
        bwExecutorService.runTask(service);
    }

    public void manageGoalCondition(String workitemOID, String conditionOID) {
        BWExecutorService bwExecutorService = BlendedWorkflow.getInstance()
                .getBWExecutorService();
        ManageGoalConditionService service = new ManageGoalConditionService(
                workitemOID, conditionOID);
        bwExecutorService.runTask(service);
    }

    public void manageGoalCondition(String maintainGoalOID,
            MaintainGoalState state) {
        BWExecutorService bwExecutorService = BlendedWorkflow.getInstance()
                .getBWExecutorService();
        ManageGoalConditionService service = new ManageGoalConditionService(
                maintainGoalOID, state);
        bwExecutorService.runTask(service);
    }

    // TODO: Delete
    // public void enableGoalWorkItemsService(String bwInstanceOID) {
    // BWExecutorService bwExecutorService =
    // BlendedWorkflow.getInstance().getBWExecutorService();
    // EnableGoalWorkItemsService service = new
    // EnableGoalWorkItemsService(bwInstanceOID);
    // bwExecutorService.runTask(service);
    // }

}
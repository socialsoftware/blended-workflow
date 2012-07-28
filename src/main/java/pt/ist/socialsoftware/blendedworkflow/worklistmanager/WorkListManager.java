package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vaadin.ui.Window.Notification;

import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CheckInWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateGoalInstanceService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.CreateNewGoalService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.ManageGoalConditionService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.RedoGoalWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.bwengine.servicelayer.SkipWorkItemService;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem.GoalState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.MaintainGoal.MaintainGoalState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem.ActivityState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;
import pt.ist.socialsoftware.blendedworkflow.shared.BWExecutorService;

public class WorkListManager {

	private Logger log = Logger.getLogger("WorklistManager");
	protected BWPresentation bwPresentation = null;
	
	LinkedHashMap<GoalWorkItem, ArrayList<GoalWorkItem>> redoList = new LinkedHashMap<GoalWorkItem, ArrayList<GoalWorkItem>>();
	
	private void addReActivatedGoalWorkItem(GoalWorkItem newGW, GoalWorkItem causeGW) {
		redoList.put(newGW, new ArrayList<GoalWorkItem>());
		if (causeGW != null) {
			redoList.get(causeGW).add(newGW);
		}
	}
	
	private void removeReActivatedGoalWorkItem(GoalWorkItem goalWorkItem) {
		redoList.remove(goalWorkItem);
		for (Map.Entry<GoalWorkItem, ArrayList<GoalWorkItem>> entry : redoList.entrySet()) {
			entry.getValue().remove(goalWorkItem);
		}
		
		checkPending();
	}
	
	private void checkPending() {
		for (Map.Entry<GoalWorkItem, ArrayList<GoalWorkItem>> entry : redoList.entrySet()) {
			if (entry.getValue().isEmpty()) {
				long workItemOID = entry.getKey().getOID();
				BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
				CheckInWorkItemService service = new CheckInWorkItemService(workItemOID);
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
	 * @param workItem The enabled WorkItem (i.e. State = ENABLED or PRE_TASK).
	 */
	public void notifyEnabledWorkItem(WorkItem workItem) {
		log.info("WorkItem " + workItem.getID() + " is now enabled. with OID" + workItem.getOID());
		String bwInstanceName = workItem.getBwInstance().getName() + ".";
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			getBwPresentation().addGoalWorkItem(workItem.getOID(), bwInstanceName + workItem.getID());
		}
		else {
			getBwPresentation().addTaskWorkItem(workItem.getOID(), bwInstanceName + workItem.getID());
		}
	}
	
	public void notifyReEnabledWorkItem(GoalWorkItem workItem, GoalWorkItem cause) {
		log.info("WorkItem " + workItem.getID() + " is now re-enabled. with OID" + workItem.getOID());
		
		addReActivatedGoalWorkItem(workItem, cause);
		
		String bwInstanceName = workItem.getBwInstance().getName() + ".";
		getBwPresentation().addGoalWorkItem(workItem.getOID(), bwInstanceName + workItem.getID()); //+ "(ReEnabled)"
	}

	/**
	 * Notify the BWPresentation of completed WorkItems.
	 * @param workItem The pending WorkItem (i.e. State = COMPLETED).
	 */
	public void notifyCompletedWorkItem(WorkItem workItem) {
		String bwInstanceName = workItem.getBwInstance().getName() + ".";
		if (workItem instanceof GoalWorkItem) {
			GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
			log.info("WorkItem " + workItem.getID() + " is now " + goalWorkItem.getState() + ".");
			getBwPresentation().removeGoalWorkItem(goalWorkItem.getOID(), bwInstanceName + goalWorkItem.getID(), goalWorkItem.getState().toString());
			
			removeReActivatedGoalWorkItem(goalWorkItem);
		}
		else {
			TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
			log.info("WorkItem " + workItem.getID() + " is now " + taskWorkItem.getState() + ".");
			getBwPresentation().removeTaskWorkItem(taskWorkItem.getOID(), bwInstanceName + taskWorkItem.getID(), taskWorkItem.getState().toString());
		}
	}

	public void notifyWorkItemState(WorkItem workItem) {
		String message = "WorkItem: " + workItem.getID() + " activate condition failed.";
		getBwPresentation().getMainWindow().showNotification(message, Notification.TYPE_ERROR_MESSAGE);
	}
	
	public void notifyNewGoalCreated(AchieveGoal goal) {
		getBwPresentation().getMainWindow().showNotification("New Goal " + goal.getName() + " created successfully", Notification.TYPE_TRAY_NOTIFICATION);
	}
	
	public void notifyException(BlendedWorkflowError bwe) {
		getBwPresentation().getMainWindow().showNotification(bwe.toString(), Notification.TYPE_ERROR_MESSAGE);
	}

	/**
	 * Update the BWPresentation with all the active WorkItems.
	 */
	public void updateBWPresentation() {
		for (BWSpecification bwSpecification : BlendedWorkflow.getInstance().getBwSpecifications()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstances()) {
				for (WorkItem workItem : bwInstance.getWorkItems()) {
					if (workItem instanceof GoalWorkItem) {
						GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
						if (goalWorkItem.getState().equals(GoalState.ENABLED) || goalWorkItem.getState().equals(GoalState.PRE_GOAL))
							notifyEnabledWorkItem(goalWorkItem);
					} else {
						TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
						if (taskWorkItem.getState().equals(ActivityState.ENABLED) || taskWorkItem.getState().equals(ActivityState.PRE_ACTIVITY))
							notifyEnabledWorkItem(taskWorkItem);
					}
				}
			}
		}
	}
	
	/********************
	 * Services.
	 ********************/
	public void checkInWorkItem(long workItemOID){
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		CheckInWorkItemService service = new CheckInWorkItemService(workItemOID);
		bwExecutorService.runTask(service);
	}
	
	public void skipWorkItem(long workItemOID){
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		SkipWorkItemService service = new SkipWorkItemService(workItemOID);
		bwExecutorService.runTask(service);
	}
	
	public void createGoal(long bwInstanceOID, String goalName, String goalDescription, long parentGoalID, String goalCondition, ArrayList<String> activateConditions, long entityOID, String userID){
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		CreateNewGoalService service = new CreateNewGoalService(bwInstanceOID, goalName, goalDescription, parentGoalID, goalCondition, activateConditions, entityOID, userID);
		bwExecutorService.runTask(service);
	}
	
	public void createGoalInstance(long bwInstanceOID, long goalOID, ArrayList<Long> activateConditions, 
			ArrayList<Long> maintainConditions, HashMap<Long, Long> entitiesOID) {
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		CreateGoalInstanceService service = new CreateGoalInstanceService(bwInstanceOID, goalOID, activateConditions, maintainConditions, entitiesOID);
//		CreateGoalInstanceService service = new CreateGoalInstanceService(bwInstanceOID, parentGoalID, context, activateConditions, maintainConditions, relationsEntityInstances);
		bwExecutorService.runTask(service);
	}

	public void redoGoal(long workItemOID, String userID) {
		log.info("Redo GoalWorkItem: " + workItemOID);
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		RedoGoalWorkItemService service = new RedoGoalWorkItemService(workItemOID, userID);
		bwExecutorService.runTask(service);
	}
	
	public void manageGoalCondition(long workitemOID, long conditionOID) {
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		ManageGoalConditionService service = new ManageGoalConditionService(workitemOID, conditionOID);
		bwExecutorService.runTask(service);
	}
	
	public void manageGoalCondition(long maintainGoalOID, MaintainGoalState state) {
		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
		ManageGoalConditionService service = new ManageGoalConditionService(maintainGoalOID, state);
		bwExecutorService.runTask(service);
	}

	//TODO: Delete
//	public void enableGoalWorkItemsService(long bwInstanceOID) {
//		BWExecutorService bwExecutorService = BlendedWorkflow.getInstance().getBWExecutorService();
//		EnableGoalWorkItemsService service = new EnableGoalWorkItemsService(bwInstanceOID);
//		bwExecutorService.runTask(service);
//	}

}
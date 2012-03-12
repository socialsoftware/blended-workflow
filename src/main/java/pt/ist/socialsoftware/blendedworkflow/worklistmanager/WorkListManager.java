package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.presentation.BWPresentation;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;

public class WorkListManager {
	
	private static WorkListManager workListManager = null;
	protected BWPresentation bwPresentation = null;

	public static WorkListManager getInstance() {
		if (workListManager == null) {
			workListManager = new WorkListManager();
		}
		return workListManager;
	}
	
	public void init(BWPresentation bwPresentation) {
		if(workListManager == null) {
			workListManager = new WorkListManager();
			workListManager.init(bwPresentation);
		}
		this.bwPresentation = bwPresentation;
//		Bootstrap.init(); // FIXME: ??
	}
	
	public void notifyEnabledWorkItem(WorkItem workItem) {
		// TODO: update list of workitems being presented
		// FIXME: must be a service
	}
	// **************************************************
	// FIXME: LEGACY CODE
	// **************************************************

//	protected BlendedworkflowserviceApplication app = null;
//	
//	public long caseIDGenerator = 0L;
//
//
//	private static WorklistManager instance = null;
//	protected static Logger _log;                        // debug log4j file
//	
//	private WorklistManager() {
//		_log = Logger.getLogger(WorklistManager.class);
//	}
//	
//	public static WorklistManager get() {
//		if(instance == null) {
//			instance = new WorklistManager();
//		}
//		return instance;
//	}
//	
//	public void init(BlendedworkflowserviceApplication interfaceApp) {
//		if(instance == null) {
//			instance = new WorklistManager();
//			instance.init(interfaceApp);
//		}
//		this.app = interfaceApp;
//	}
//	
//	public ArrayList<BWCase> getLoadedCases() {
//		return CaseManager.get().getLoadedCases();
//	}
//	
//	public boolean launchCase(String caseName) {
//		BWCase bwCase = CaseManager.get().getBWCaseFromName(caseName);
//		
//		if(bwCase == null) {
//			_log.error("Could not find the specified Blended Workflow case.");
//			return false;
//		}
//		
//		// request the BWActivityManager to launch the YAWL case
//		String activityCaseID = BWActivityManager.get().launchActivityCase(bwCase.getYAWLSpecID(), bwCase.getYAWLSpecName(), bwCase.getDataModelURI());
//		
//		// request the BWGoalManager to launch the Goal case
//		String goalCaseID = BWGoalManager.get().launchGoalCase(bwCase.getGoalSpecID(), bwCase.getDataModelURI());
//		
//		// if one of them fails
//		if(activityCaseID != null && goalCaseID != null) {
//			bwCase.setYAWLInstanceID(activityCaseID);
//			bwCase.setGoalInstanceID(goalCaseID);
//			bwCase.setCaseID(Long.toString(++caseIDGenerator));
//			_log.info("The case has been successfully launched");
//			
//			getActiveItems(bwCase);
//			CaseManager.get().launchCase(bwCase);
//			return true;
//		} else {
//			//TODO request the BWActivityManager to cancel the YAWL case
//			
//			
//			//TODO request the BWGoalManager to cancel the Goal case
//		
//			return false;
//		}
//	}
//	
//	protected void getActiveItems(BWCase bwCase) {
//		// get the activity items
//		BWActivityManager.get().activateTasks(bwCase.getYAWLInstanceID());
//		// get the goal items
//		BWGoalManager.get().notifyOfActiveGoals(bwCase.getGoalInstanceID());
//	}
//	
//	public boolean receiveBWcase(BWCase bwCase) {
//		if(bwCase == null) {
//			return false;
//		}
//		
//		// activate activity spec
//		boolean yawlResult = ActivityWorklistManager.get().receiveActivitySpec(bwCase.getYAWLSpecID(), bwCase.getYAWLspec());
//		if(yawlResult) { _log.info("YAWL Specification uploaded correctly"); } else { _log.error("Could not upload YAWL Specification"); }
//		
//		// activate goal spec
//		boolean goalResult = GoalWorklistManager.get().receiveGoalSpec(bwCase.getGoalSpecID(), bwCase.getBWspec());
//		if(goalResult) { _log.info("Goal Specification uploaded correctly"); } else { _log.error("Could not upload Goal Specification"); }
//		
//		// submit conditions spec
//		boolean conditionsResult = receiveConditionsSpec(bwCase.getConditionsSpecID(), bwCase.getBWspec());
//		if(conditionsResult) { _log.info("Conditions Specification uploaded correctly"); } else { _log.error("Could not upload Conditions Specification"); }
//		
//		// if everything went ok, store the case
//		if(yawlResult && goalResult && conditionsResult) {
//			CaseManager.get().registerCase(bwCase);
//			return true;
//		} else {
//			// unload the specifications that uploaded correctly
//			// unload the yawl spec
//			BWActivityManager.get().unloadYAWLSpecification(bwCase.getYAWLSpecID());
//			
//			// unload the goal spec
//			BWGoalManager.get().unloadGoalSpecification(bwCase.getGoalSpecID());
//			
//			// unload the conditions spec
//			BWActivityManager.get().unloadConditionsSpecification(bwCase.getConditionsSpecID());
//			
//			return false;	
//		}
//	}
//	
//	public boolean registerExecutedTask(TaskInfo taskInfo, boolean isActivityTask) {
//		if(WorklistManager.get().app == null) {
//			WorklistManager._log.error("The interface is not registered in the Worklist Manager");
//			return false;
//		}
//		
//		if(isActivityTask) {
//			WorklistManager._log.info("Task "+ taskInfo.getTaskName() + " executed");
//			//send to the interface
//			WorklistManager.get().app.removeTask(taskInfo.getTaskIDForDisplay());
//			// send to BW activity manager
//			if(BWActivityManager.get().receiveTaskExecutionResult(taskInfo)) {
//				String taskID = (taskInfo.getTaskIDForDisplay().contains("[PRE]")) ? taskInfo.getTaskIDForDisplay().replace("[PRE]", "") :
//					taskInfo.getTaskIDForDisplay();
//				CaseManager.get().registerExecutedTask(taskID);
//				return true;
//			} else {
//				return false;
//			}
//			
//		} else {
//			// send to BW goal manager
//			if(BWGoalManager.get().receiveTaskExecutionResult(taskInfo)) {
//				WorklistManager._log.info("Goal "+ taskInfo.getTaskIDForDisplay() + " executed");
//				app.removeGoal(taskInfo.getTaskIDForDisplay() + " (Mandatory)");
//				app.removeGoal(taskInfo.getTaskIDForDisplay() + " (Optional)"); //FIXME we don't know if it is mandatory or optional so we try both...
//				CaseManager.get().registerExecutedGoal(taskInfo.getTaskIDForDisplay());
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	/***** METHODS FOR CONDITIONS ******/
//	
//	/**
//	 * Receives the Blended Workflow Specification and sends it to the Blended Workflow ActivityManager.
//	 * Final destination: the Conditions Engine.
//	 * @param specID the Conditions specification ID
//	 * @param specInString the blended workflow specification (in a string)  
//	 */
//	protected boolean receiveConditionsSpec(String specID, String specInString) {
//		return BWActivityManager.get().receiveConditionsSpec(new CaseURI(specID), specInString);
//	}
//	
//	/***********************************/
//		
//	/***** METHODS FOR ACTIVITIES ******/
//	// called from interface
//	public void executeTask(String taskIDForDisplay) { 
//		String taskName = CaseManager.get().getTaskURIFromTaskIDForDisplay(taskIDForDisplay);
//		ActivityWorklistManager.get().executeTask(taskName); 
//	}	
//	//called from system
//	public void continueExecutingTask(TaskInfo taskInfo) { ActivityWorklistManager.get().continueExecutingTask(taskInfo); }
//	// called from system
//	public void registerActiveTask(String taskURI) { ActivityWorklistManager.get().registerActiveTask(taskURI); }
//	// called from interface
//	public void registerSkippedTask(String taskIDForDisplay) { 
//		ActivityWorklistManager.get().registerSkippedTask(taskIDForDisplay); 
//	}
//	//called from system
//	public void removeTaskFromInterface(String taskURI) { ActivityWorklistManager.get().removeTaskFromInterface(taskURI); }
//	
//	/***********************************/
//	
//	/******* METHODS FOR GOALS *******/
//	// called from interface
//	public void createGoal(String instanceID, String goalName, 
//			boolean isMandatory, String goalDefinition, 
//			String parentGoalIDForDisplay) throws WrongConditionException {
//		//String goalInstanceID = CaseManager.get().getGoalCaseInstanceFromBWCase(instanceID);
//		GoalWorklistManager.get().createGoal(instanceID, goalName, isMandatory, goalDefinition, parentGoalIDForDisplay);
//	}
//	// called from interface
//	public void executeGoal(String goalURI) {
//		String systemGoalURI = CaseManager.get().getGoalURIFromGoalIDForDisplay(goalURI);
//		GoalWorklistManager.get().executeGoal(systemGoalURI);
//	}
//	// called from system
//	public void registerActiveGoal(String specInstanceID, String goalName, boolean isMandatory) {
//		GoalWorklistManager.get().registerActiveGoal(specInstanceID, goalName, isMandatory);
//	}
//	
//	//called from system
//	public void removeGoalFromInterface(String goalURI, boolean isMandatory) {
//		String goalIDForDisplay = CaseManager.get().getGoalIDForDisplayFromGoalURI(goalURI);
//		GoalWorklistManager.get().removeGoalFromInterface(goalIDForDisplay, isMandatory);
//	}
//	//called from interface
//	public void skipGoal(String goalEntry) {
//		GoalWorklistManager.get().skipGoal(goalEntry);
//	}
//	/*********************************/
//	
//	/********** METHODS FOR DATA ***********/
//	
//	public boolean addData(String dataModel, String dataModelInstanceID, 
//			String entityName, ArrayList<AddDataMessage> attributes, AddRelationMessage relation) {
//		_log.info("Request to add data to the data repository");
//		
//		return BWGoalManager.get().addData(dataModel, dataModelInstanceID, entityName, attributes, relation);
//	}
//	
//	public String getDataModel(String goalCaseInstanceID) {
//		return BWGoalManager.get().getDataModel(new CaseInstanceID(goalCaseInstanceID));
//	}
//	
//	public String getDataModelURI(String goalCaseInstanceID) {
//		return BWGoalManager.get().getDataModelURI(new CaseInstanceID(goalCaseInstanceID));
//	}
//	
//	public String getDataModelInstanceID(String goalCaseInstanceID) {
//		return BWGoalManager.get().getDataModelInstanceID(new CaseInstanceID(goalCaseInstanceID));
//	}
	
}

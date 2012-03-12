package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

public class GoalWorklistManager {
//	private static GoalWorklistManager instance = null;
//	
//	public static GoalWorklistManager get() {
//		if(instance == null) {
//			instance = new GoalWorklistManager();
//		}
//		return instance;
//	}
//	
//	/**
//	 * Receives the blended workflow specification and sends it to the Blended Workflow Goal Manager.
//	 * @param specID the goal specification id
//	 * @param specInString the specification (in a string)
//	 */
//	protected boolean receiveGoalSpec(String specID, String specInString) {
//		// send the spec to BWGoalManager
//		return BWGoalManager.get().receiveGoalSpec(specID, specInString);
//	}
//	
//	public void registerActiveGoal(String specInstanceID, String goalName, boolean isMandatory) {
//		WorklistManager._log.info("Adding goal " + goalName + " to the interface");
//		
//		// register goal
//		String goalURI = specInstanceID + ":" + goalName;
//		CaseManager.get().addActiveGoal(goalURI);
//		
//		if(WorklistManager.get().app == null) {
//			WorklistManager._log.error("The interface is not registered in the Worklist Manager");
//		}
//		String goalIDForDisplay = CaseManager.get().getGoalIDForDisplayFromGoalURI(goalURI);
//		// send task info to interface
//		WorklistManager.get().app.addGoal(goalIDForDisplay, isMandatory);
//	}
//	
//	public void executeGoal(String goalURI) {
//		WorklistManager._log.info("Executing goal " + goalURI);
//		
//		if(WorklistManager.get().app == null) {
//			WorklistManager._log.error("The interface is not registered in the Worklist Manager");
//			return;
//		}
//		
//		// get task info from BWGoalManager
//		TaskInfo goalInfo = BWGoalManager.get().getInfoToExecuteGoal(goalURI);
//		String goalIDForDisplay = CaseManager.get().getGoalIDForDisplayFromGoalURI(goalURI);
//		goalInfo.setTaskIDForDisplay(goalIDForDisplay);
//		
//		if(goalInfo != null) {
//			//send it to the interface
//			WorklistManager.get().app.executeTask(goalInfo, false);
//		}
//	}
//	
//	public void skipGoal(String goalEntry) {
//		String goalIDForDisplay = (goalEntry.contains("(Mandatory)")) ? goalEntry.replace(" (Mandatory)", "") :
//			goalEntry.replace(" (Optional)", "");
//		
//		String goalURI = CaseManager.get().getGoalURIFromGoalIDForDisplay(goalIDForDisplay);
//		
//		WorklistManager._log.info("Skipping the goal " + goalURI);
//		
//		if(WorklistManager.get().app == null) {
//			WorklistManager._log.error("The interface is not registered in the Worklist Manager");
//			return;
//		}
//		
//		BWGoalManager.get().skipGoal(goalURI);
//		CaseManager.get().registerSkippedGoal(goalURI);
//	}
//	
//	// create
//	public void createGoal(String instanceID, String goalName, 
//			boolean isMandatory, String goalDefinition, 
//			String parentGoalIDForDisplay) throws WrongConditionException {
//		
//		String newGoalID = BWGoalManager.get().createGoal(instanceID, goalName, isMandatory, goalDefinition, parentGoalIDForDisplay);
//		if(newGoalID != null) { 
//			String goalURI = instanceID + ":" + goalName;
//			CaseManager.get().registerNewGoal(goalURI);
//			
//			WorklistManager._log.info("New goal added: " + goalName);
//		}
//	}
//	
//	public void removeGoalFromInterface(String goalIDForDisplay, boolean isMandatory) {
//		String goalEntry;
//		if(isMandatory) {
//			goalEntry = goalIDForDisplay + " (Mandatory)";
//		} else {
//			goalEntry = goalIDForDisplay + " (Optional)";
//		}
//		
//		if(WorklistManager.get().app == null) {
//			WorklistManager._log.error("The interface is not registered in the Worklist Manager");
//			return;
//		}
//		
//		WorklistManager.get().app.removeGoal(goalEntry);
//	}
}
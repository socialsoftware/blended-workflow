package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

public class ActivityWorklistManager {
//	private static ActivityWorklistManager instance = null;
//	
//	private ActivityWorklistManager() {}
//	
//	public static ActivityWorklistManager get() {
//		if(instance == null) {
//			instance = new ActivityWorklistManager();
//		}
//		return instance;
//	}
//	
//	protected boolean receiveActivitySpec(String specID, String specInString) {
//		// send the spec to BWActivityManager
//		return BWActivityManager.get().receiveActivitySpec(specID, specInString);
//	}
//	
//	public void registerActiveTask(String taskURI) {
//
//		// register task
//		CaseManager.get().addActiveTask(taskURI);
//		
//		if(WorklistManager.get().app == null) {
//			WorklistManager._log.error("The interface is not registered in the Worklist Manager");
//		}
//		
//		String taskForDisplay = CaseManager.get().getTaskIDToDisplayFromTaskURI(taskURI);
//		// send task info to interface
//		WorklistManager.get().app.addTask(taskForDisplay);
//	}
//	
//	public void registerSkippedTask(String taskName) {
//		WorklistManager._log.info("Skipping task " + taskName);
//		
//		if(WorklistManager.get().app == null) {
//			WorklistManager._log.error("The interface is not registered in the Worklist Manager");
//			return;
//		}
//		
//		if(!CaseManager.get().isTaskActive(taskName)) {
//			WorklistManager._log.error("Task " + taskName + " is not registered as active. Cannot skip it.");
//			return;
//		}
//		
//		//remove task from the interface
//		//WorklistManager.get().app.removeTask(taskName);
//		
//		// send information about skipped task to BWActivity Manager
//		String taskURI = CaseManager.get().getTaskURIFromTaskIDForDisplay(taskName);
//		BWActivityManager.get().skipTask(taskURI);
//		
//		CaseManager.get().registerSkippedTask(taskName);
//	}
//	
//	public void executeTask(String taskName) {
//		WorklistManager._log.info("Executing task " + taskName);
//		
//		if(WorklistManager.get().app == null) {
//			WorklistManager._log.error("The interface is not registered in the Worklist Manager");
//			return;
//		}
//		
//		// get task info from BWActivityManager
//		TaskInfo taskInfo = BWActivityManager.get().startEnabledTask(taskName);
//		if(taskInfo != null) {
//			// change the task name (to the interface name)
//			String taskIDForDisplay = CaseManager.get().getTaskIDToDisplayFromTaskURI(taskName);
//			if(taskInfo.getTaskIDForDisplay().contains("[PRE]")) {
//				taskInfo.setTaskIDForDisplay("[PRE]" + taskIDForDisplay);
//			} else {
//				taskInfo.setTaskIDForDisplay(taskIDForDisplay);
//			}
//			//send it to the interface
//			WorklistManager.get().app.executeTask(taskInfo, true);
//		}
//	}
//	
//	public void continueExecutingTask(TaskInfo taskInfo) {
//		if(taskInfo != null && WorklistManager.get().app != null) {
//			//send it to the interface
//			String taskName = taskInfo.getTaskIDForDisplay();
//			String taskIDForDisplay = CaseManager.get().getTaskIDToDisplayFromTaskURI(taskName);
//			taskInfo.setTaskIDForDisplay(taskIDForDisplay);
//			WorklistManager.get().app.executeTask(taskInfo, true);
//		}
//	}
//	
//	public void removeTaskFromInterface(String taskURI) {
//		if(WorklistManager.get().app == null) {
//			WorklistManager._log.error("The interface is not registered in the Worklist Manager");
//			return;
//		}
//		
//		String taskIDForDisplay = CaseManager.get().getTaskIDToDisplayFromTaskURI(taskURI);
//		WorklistManager.get().app.removeTask(taskIDForDisplay);
//	}
//	
}

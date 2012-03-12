package pt.ist.socialsoftware.blendedworkflow.worklistmanager;

public class CaseManager {
//	private static CaseManager instance = null;
//	
//	private ArrayList<BWCase> loadedCases = new ArrayList<BWCase>();
//	private ArrayList<BWCase> launchedCases = new ArrayList<BWCase>();
//	
//	private CaseManager() {}
//	
//	public static CaseManager get() {
//		if(instance == null) {
//			instance = new CaseManager();
//		}
//		
//		return instance;
//	}
//	
//	public void registerCase(BWCase bwcase) {
//		this.loadedCases.add(bwcase);
//	}
//	
//	public void launchCase(BWCase bwcase) {
//		this.launchedCases.add(bwcase);
//	}
//
//	public void addActiveTask(String taskURI) {
//		BWCase bwCase = getBWCaseFromTaskURI(taskURI);
//		if(bwCase == null) { return; }
//		bwCase.registerActiveTask(taskURI);
//	}
//	public void addActiveGoal(String goalURI) {
//		BWCase bwCase = getBWCaseFromGoalURI(goalURI);
//		if(bwCase == null) return;
//		bwCase.registerActiveGoal(goalURI);
//	}
//	
//	public String getTaskIDToDisplayFromTaskURI(String taskURI) {
//		BWCase bwCase = getBWCaseFromTaskURI(taskURI);
//		if(bwCase == null) { return null; }
//		
//		return bwCase.getTaskIDToDisplayFromTaskURI(taskURI);
//	}
//	public String getGoalIDForDisplayFromGoalURI(String goalURI) {
//		BWCase bwCase = getBWCaseFromGoalURI(goalURI);
//		if(bwCase == null) return null;
//		
//		return bwCase.getGoalIDToDisplayFromGoalURI(goalURI);
//	}
//	
//	public String getTaskURIFromTaskIDForDisplay(String taskIDForDisplay) {
//		BWCase bwCase = getBWCaseFromTaskIDForDisplay(taskIDForDisplay);
//		if(bwCase == null) { return null; }
//		return bwCase.getTaskURIFromTaskIDForDisplay(taskIDForDisplay);
//	}
//	public String getGoalURIFromGoalIDForDisplay(String goalIDForDisplay) {
//		BWCase bwCase = getBWCaseFromGoalIDForDisplay(goalIDForDisplay);
//		if(bwCase == null) return null;
//		return bwCase.getGoalURIFromGoalIDForDisplay(goalIDForDisplay);
//	}
//	
//	public BWCase getBWCaseFromTaskURI(String taskURI) {
//		String caseID = getCaseID(taskURI);
//		for (BWCase bwCase : this.loadedCases) {
//			if(bwCase.getYAWLInstanceID().equals(caseID)) {
//				return bwCase;
//			}
//		}
//		return null;
//	}
//	
//	public BWCase getBWCaseFromTaskIDForDisplay(String taskIDForDisplay) {
//		String caseID = getCaseID(taskIDForDisplay);
//		for(BWCase bwc : this.launchedCases) {
//			if(bwc.getCaseID().equals(caseID)) {
//				return bwc;
//			}
//		}
//		return null;
//	}
//	
//	public BWCase getBWCaseFromGoalIDForDisplay(String goalIDForDisplay) {
//		return getBWCaseFromTaskIDForDisplay(goalIDForDisplay);
//	}
//	
//	public BWCase getBWCaseFromGoalURI(String goalURI) {
//		String caseID = getCaseID(goalURI);
//		for (BWCase bwCase : this.loadedCases) {
//			if(bwCase.getGoalInstanceID().equals(caseID)) {
//				return bwCase;
//			}
//		}
//		return null;
//	}
//	
//	public BWCase getBWCaseFromName(String caseName) {
//		for (BWCase bwc : this.loadedCases) {
//			if(bwc.getCaseName().equals(caseName)) {
//				return bwc;
//			}
//		}
//		return null;
//	}
//	
//	private String getCaseID(String taskURI) {
//		String[] taskSplitted = taskURI.split("\\:");
//		if(taskSplitted[0].contains(".")) {
//			String[] caseInstID = taskSplitted[0].split("\\.");
//			return caseInstID[0];
//		}
//		return taskSplitted[0];
//	}
//	
//	public ArrayList<BWCase> getLoadedCases() {
//		return this.loadedCases;
//	}
//	
//	public void registerExecutedTask(String taskIDForDisplay) {
//		BWCase bwc = getBWCaseFromTaskIDForDisplay(taskIDForDisplay);
//		bwc.registerExecutedTask(taskIDForDisplay);
//	}
//	
//	public void registerExecutedGoal(String goalIDForDisplay) {
//		BWCase bwc = getBWCaseFromTaskIDForDisplay(goalIDForDisplay);
//		bwc.registerExecutedGoal(goalIDForDisplay);
//	}
//
//	public boolean isTaskActive(String taskIDForDisplay) {
//		BWCase bwc = getBWCaseFromTaskIDForDisplay(taskIDForDisplay);
//		return bwc.isTaskActive(taskIDForDisplay);
//	}
//	
//	public void registerSkippedTask(String taskIDForDisplay) {
//		BWCase bwc = getBWCaseFromTaskIDForDisplay(taskIDForDisplay);
//		bwc.registerSkippedTask(taskIDForDisplay);
//	}
//	
//	public void registerSkippedGoal(String goalIDForDisplay) {
//		BWCase bwc = getBWCaseFromGoalIDForDisplay(goalIDForDisplay);
//		bwc.registerSkippedGoal(goalIDForDisplay);
//	}
//	public void registerNewGoal(String goalURI) {
//		BWCase bwc = getBWCaseFromGoalURI(goalURI);
//		bwc.registerNewGoal(goalURI);
//	}
//	public String getGoalCaseInstanceFromBWCase(String instanceID) {
//		for (BWCase bwc : this.launchedCases) {
//			if(bwc.getCaseID().equals(instanceID)) {
//				return bwc.getGoalInstanceID();
//			}
//		}
//		return null;
//	}
}

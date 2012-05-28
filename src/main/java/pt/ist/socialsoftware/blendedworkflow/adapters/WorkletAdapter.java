package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletGatewayClient;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
//import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.shared.PropertiesManager;

public class WorkletAdapter {

//	private YSpecificationID yawlSpecID = new YSpecificationID(null, "0.1", "A156"); // Test proposes only.

	private Logger log;

	protected String engineAdminUser = PropertiesManager.getProperty("yawl.AdminUser");
	protected String engineAdminPassword = PropertiesManager.getProperty("yawl.AdminPassword");
	protected String workletGateway = PropertiesManager.getProperty("worklet.gateway");

	private WorkletGatewayClient client= new WorkletGatewayClient();
	private String handle = null;
	private ConcurrentHashMap<WorkItemRecord, String> yawlEnabledWIR = new ConcurrentHashMap<WorkItemRecord,String>(); //WIR:WorkItemID

	public WorkletAdapter() {
		log = Logger.getLogger("WorkletAdpater");
		registerWorkletListener();
	}

	/*********************************
	 * Notifications
	 *********************************/
	/**
	 * Create a new TaskWorkItem.
	 * @param wir the YAWL enabled WorkItemRecord.
	 * @param result the ItemPreConstraint result.
	 */
	public void notifyNewTaskWorkItem(WorkItemRecord wir, String result){
		TaskWorkItem taskWorkItem = null;
		try {
			BWInstance bwInstance = BlendedWorkflow.getInstance().getBWInstanceFromYAWLCaseID(wir.getCaseID());
			TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();

			String taskName = wir.getTaskName().replaceAll("_", " ");
			Task task = taskModelInstance.getTask(taskName);
			taskWorkItem = new TaskWorkItem(bwInstance, task);

			yawlEnabledWIR.put(wir, taskWorkItem.getID());
		} catch (BlendedWorkflowException bwe) {
			log.info("notifyNewTaskWorkItem: exception: " + bwe.getError());
		}

		if (result.equals("TRUE")) {
			taskWorkItem.notifyConstrainViolation();
		} else if (result.equals("SKIPPED")) {
			taskWorkItem.notifyPreTask();
		} else {
			taskWorkItem.notifyPreFalse();
		}
	}

	/**
	 * Evaluate a TaskWorkitem ItemPreConstraint.
	 * @param workItem the workItem to evaluate.
	 */
	public void notifyWorkItemPreConstraint(TaskWorkItem workItem) {
		try {
			TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
			evaluate(taskWorkItem, null, true);
		} catch (BlendedWorkflowException bwe) {
			log.info("notifyWorkItemContraintViolation: exception" + bwe.getMessage());
		}
	}

	/**
	 * Evaluate GoalWorkitem condition or Process TaskWorkitem postCondition.
	 * @param workItem the workItem to evaluate.
	 */
	public void notifyWorkItemContraintViolation(WorkItem workItem) {
		log.info("notifyWorkItemContraintViolation");
		try {
			if (workItem.getClass().equals(GoalWorkItem.class)) {
				GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
				evaluate(null, goalWorkItem, false);
			}
			else {
				log.info("to process");
				TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
//				process(taskWorkItem);
				evaluate(taskWorkItem, null, false); // Test proposes only.
			}
		} catch (BlendedWorkflowException bwe) {
			log.info("notifyWorkItemContraintViolation: exception" + bwe.getMessage());
		}
		log.info("notifyWorkItemContraintViolation-end");
	}

	/**
	 * Process a ItemConstrainViolation evaluation result. General method that can process a TaskWorkItem or a GoalWorkItem.
	 * @param wir a YAWL enabled WorkItemRecord.
	 * @param goalWorkitem a GoalWorkItem.
	 * @param result the ItemConstrainViolation result.
	 */
	public void notifyConstraintViolationResult(WorkItemRecord wir, GoalWorkItem goalWorkitem, String result) {
		WorkItem workItem = null;
		if (wir != null) {
			workItem = getTaskWorkItem(wir);
		} else {
			workItem = goalWorkitem;
		}
		
		if (result.equals("TRUE")) {
			workItem.notifyCompleted();
		} else if (result.equals("SKIPPED")) {
			workItem.notifySkipped();
		} else {
			workItem.notifyEnabled();
		}
	}


	/*********************************
	 * RdrSet
	 *********************************/
	/**
	 * Create and Load a specification RdrSet.
	 * @param bwSpecification the BWSpecification.
	 * @throws BlendedWorkflowException
	 */
	public void loadRdrSet(BWSpecification bwSpecification) throws BlendedWorkflowException {
		TaskModel taskModel = bwSpecification.getTaskModel();
//		GoalModel goalModel = bwSpecification.getGoalModel();

		// Get the YAWLSpecID
		String yawlCaseID = bwSpecification.getYawlSpecficationID();
		YSpecificationID yawlSpecID = null;
		for (YSpecificationID ySpecificationID : BlendedWorkflow.getInstance().getYawlAdapter().getLoadedActivitySpecs()) {
			if(ySpecificationID.getIdentifier().equals(yawlCaseID)) {
				yawlSpecID = ySpecificationID;
				break;
			}
		}

		Boolean firstTask = true;
		String condition = null;
		Element eConclusion = null;
		Element eCornerstone = null;

		// Create Tasks RdrSet
		for (Task task : taskModel.getTasks()) {
			String taskName = task.getName().replaceAll(" ", "_");

			// PreConstraintTree
			if (firstTask) {
				// True Node
				eCornerstone = getCornerstoneData(task, null, true, "NULL");
				condition = task.getPreConstraint().getRdrTrueCondition();
				eConclusion = createRdrConclusion("NULL", true);
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);
				firstTask = false;
			} else {
				// Undefined Node
				eCornerstone = getCornerstoneData(task, null, true, "UNDEFINED");
				condition = task.getPreConstraint().getRdrUndefinedCondition();
				eConclusion = createRdrConclusion("UNDEFINED", true);
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);

				// Skipped Node
				eCornerstone = getCornerstoneData(task, null, true, "SKIPPED");
				condition = task.getPreConstraint().getRdrSkippedCondition();
				eConclusion = createRdrConclusion("SKIPPED", true);
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);

				// True Node
				eCornerstone = getCornerstoneData(task, null, true, "DEFINED");
				condition = task.getPreConstraint().getRdrTrueCondition();
				eConclusion = createRdrConclusion("TRUE", true);
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);
				
//				// False Node
//				eCornerstone = getCornerstoneData(task, null, true, "DEFINED");
//				condition = task.getPreConstraint().getRdrFalseConditionNEW();
//				eConclusion = createRdrConclusion("FALSE", true);
//				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);
			}

			// PostConstraintTree
			// Undefined Node
			eCornerstone = getCornerstoneData(task, null, false, "UNDEFINED");
			condition = task.getPostConstraint().getRdrUndefinedCondition();
			eConclusion = createRdrConclusion("UNDEFINED", false);
			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);

			// Skipped Node
			eCornerstone = getCornerstoneData(task, null, false, "SKIPPED");
			condition = task.getPostConstraint().getRdrSkippedCondition();
			eConclusion = createRdrConclusion("SKIPPED", false);
			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);

			// True Node
			eCornerstone = getCornerstoneData(task, null, false, "DEFINED");
			condition = task.getPostConstraint().getRdrTrueCondition();
			eConclusion = createRdrConclusion("TRUE", false);
			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
			
//			// False Node
//			eCornerstone = getCornerstoneData(task, null, false, "DEFINED");
//			condition = task.getPostConstraint().getRdrFalseConditionNEW();
//			eConclusion = createRdrConclusion("FALSE", false);
//			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
		}

		// Create Goals RdrSet
//		for (AchieveGoal goal : goalModel.getAchieveGoals()) {
//			String goalName = "GOAL_" + goal.getName().replaceAll(" ", "_");
//
//			// Undefined Node
//			eCornerstone = getCornerstoneData(null, goal, false, "UNDEFINED");
//			condition = goal.getSucessCondition().getRdrUndefinedConditionNEW();
//			eConclusion = createRdrConclusion("UNDEFINED", false);
//			addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
//
//			// Skipped Node
//			eCornerstone = getCornerstoneData(null, goal, false, "SKIPPED");
//			condition = goal.getSucessCondition().getRdrSkippedConditionNEW();
//			eConclusion = createRdrConclusion("SKIPPED", false);
//			addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
//
//			// True Node
//			eCornerstone = getCornerstoneData(null, goal, false, "DEFINED");
//			condition = goal.getSucessCondition().getRdrTrueConditionNEW();
//			eConclusion = createRdrConclusion("TRUE", false);
//			addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
//			
//			// False Node
//			eCornerstone = getCornerstoneData(null, goal, false, "DEFINED");
//			condition = goal.getSucessCondition().getRdrFalseConditionNEW();
//			eConclusion = createRdrConclusion("FALSE", false);
//			addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
//		}
	}

	/*******************************
	 * Support methods
	 *******************************/
	/**
	 * Register BlendedWorkflow WorkletListener.
	 */
	public boolean registerWorkletListener(){
		WorkletGatewayClient client= new WorkletGatewayClient();
		try{
			this.handle=client.connect(engineAdminUser, engineAdminPassword);
			client.addListener(workletGateway,handle);
			log.info("Register Worklet Listener: Sucess!");
			return true;
		}
		catch(IOException ioe){
			log.info("Register Worklet Listener: Exception: " + ioe.getMessage());
		}
		log.info("Register Worklet Listener: Failed!");
		return false;
	}

	/**
	 * Get Conditions Data for a Task or a Goal.
	 * @param task a Task.
	 * @param goal a Goal.
	 * @param isPreCondition if its a Task, get the ItemPreConstraint data.
	 * @param type the type of CornerstoneData, i.e DEFINED, UNDEFINED or SKIPPED.
	 * @return an ELement with the cornerstoneData.
	 */
	private Element getCornerstoneData(Task task, AchieveGoal goal, Boolean isPreCondition, String type) {
		String cornerStr = "<cornerstone>";
		Set<Entity> entities;
		Set<Attribute> attributes;
		HashMap<Attribute, String> attributesValues;

		// Get Condition Data
		if (task == null) {
			entities = goal.getSucessCondition().getEntities();
			attributes = goal.getSucessCondition().getAttributes();
			attributesValues = goal.getSucessCondition().getcompareConditionValues();
		}
		else if (task != null && isPreCondition) {
			entities = task.getPreConstraint().getEntities();
			attributes = task.getPreConstraint().getAttributes();
			attributesValues = task.getPreConstraint().getcompareConditionValues();
		} 
		else {
			entities = task.getPostConstraint().getEntities();
			attributes = task.getPostConstraint().getAttributes();
			attributesValues = task.getPostConstraint().getcompareConditionValues();
		}

		Iterator<Attribute> it = attributes.iterator();
		while (it.hasNext()) {
			Attribute attribute = it.next();
			Entity entity = attribute.getEntity();
			if (entities.contains(entity) && !attributesValues.containsKey(attribute) && attribute.getIsKeyAttribute()) {
				it.remove();
			}
		}

		// Parse complete entities
		for (Entity entity : entities) {
			String entityName = entity.getName().replaceAll(" ", "");
			for (Attribute attribute : entity.getAttributes()) {
				if (attribute.getIsKeyAttribute()) {
					String attributeName = attribute.getName().replaceAll(" ", "");
					String value;
					if (type == "UNDEFINED")
						value = "$UNDEFINED$";
					else if (type == "SKIPPED")
						value = "$SKIPPED$"; 
					else
						value = "$DEFINED$";
					
					cornerStr += "<" + entityName + "_" + attributeName + "_State" + ">" + type + "</" + entityName + "_" + attributeName + "_State" + ">";
					cornerStr += "<" + entityName + "_" + attributeName + ">" + value + "</" + entityName + "_" + attributeName + ">";
				}
			}
		}

		// Parse single attributes
		for (Attribute attribute : attributes) {
			String entityName = attribute.getEntity().getName().replaceAll(" ", "");
			String attributeName = attribute.getName().replaceAll(" ", "");
			String value = attributesValues.get(attribute);
			if (value == null) {
				if (type == "UNDEFINED")
					value = "$UNDEFINED$";
				else if (type == "SKIPPED")
					value = "$SKIPPED$"; 
				else
					value = "$DEFINED$";
			}

			cornerStr += "<" + entityName + "_" + attributeName + "_State" + ">" + type + "</" + entityName + "_" + attributeName + "_State" + ">";
			cornerStr += "<" + entityName + "_" + attributeName + ">" + value + "</" + entityName + "_" + attributeName + ">";
		}
		
//		if (!type.endsWith("NULL")) {
//			cornerStr += "<FALSE_NODE>FALSE</FALSE_NODE>"; //new
//		}
		cornerStr += "</cornerstone>";
		Element eCornerstone = JDOMUtil.stringToElement(cornerStr);
		return eCornerstone;
	}

	private Element createRdrConclusion(String type, Boolean isPreCondition) {
		String concStr = null;
		Element eConclusion = null;
		String action = "";
		Boolean constrainVilationSkip = false;

		if (type.equals("NULL")) {
			concStr = "<conclusion>null</conclusion>";
			eConclusion = JDOMUtil.stringToElement(concStr);
			return eConclusion;
		} else if (isPreCondition) {
			if (type.equals("TRUE")) {
				action = "TRUE";
			} else if (type.equals("FALSE")) {
				action = "FALSE";
			} else if (type.equals("SKIPPED")) {
				action = "SKIPPED";
			} else {
				action = "UNDEFINED";
			}
		} else {
			if (type.equals("TRUE")) {
				action = "complete";
			} else if (type.equals("FALSE")) {
				action = "FALSE";
			} else if (type.equals("UNDEFINED")) {
				action = "UNDEFINED";
			} 
			else {
				action = "complete";
				constrainVilationSkip = true;
			}
		}

		concStr = "<conclusion><_1>";
		concStr += "<action>" + action + "</action>";
		concStr += "<target>" + "workitem" + "</target>";
		concStr += "</_1>";
		
		if (constrainVilationSkip) {
			concStr += "<_2>";
			concStr += "<action>" + "SKIPPED" + "</action>";
			concStr += "<target>" + "workitem" + "</target>";
			concStr += "</_2>";
		}
		
		concStr += "</conclusion>";
		
		eConclusion = JDOMUtil.stringToElement(concStr);
		return eConclusion;
	}
	
	/**
	 * Create a RdrNodes for a Goal.
	 * @param bwInstance the BWInstance.
	 * @param goal the Goal.
	 * @throws BlendedWorkflowException
	 */
	public void addGoal(BWInstance bwInstance, AchieveGoal goal) throws BlendedWorkflowException {
		// YAWLSpecID
		String yawlSpecificationID = bwInstance.getBwSpecification().getYawlSpecficationID();
		YSpecificationID yawlSpecID = null;
		for (YSpecificationID ySpecificationID : BlendedWorkflow.getInstance().getYawlAdapter().getLoadedActivitySpecs()) {
			if(ySpecificationID.getIdentifier().equals(yawlSpecificationID)) {
				yawlSpecID = ySpecificationID;
				break;
			}
		}
		
		String goalName = "GOAL_" + goal.getName().replaceAll(" ", "_");

		// Undefined Node
		Element eCornerstone = getCornerstoneData(null, goal, false, "UNDEFINED");
		String condition = goal.getSucessCondition().getRdrFalseCondition();
		Element eConclusion = createRdrConclusion("FALSE", false);
		addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);

		// Skipped Node
		eCornerstone = getCornerstoneData(null, goal, false, "SKIPPED");
		condition = goal.getSucessCondition().getRdrSkippedCondition();
		eConclusion = createRdrConclusion("SKIPPED", false);
		addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);

		// Defined Node
		eCornerstone = getCornerstoneData(null, goal, false, "DEFINED");
		condition = goal.getSucessCondition().getRdrTrueCondition();
		eConclusion = createRdrConclusion("TRUE", false);
		addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
	}

	/**
	 * Add a node to a RdrTree.
	 * @param yawlSpecID the YSpecificationID.
	 * @param name the Task or Goal name.
	 * @param condition the condition.
	 * @param eCornerstone the RdrNode cornerstone  data.
	 * @param eConclusion the RdrNode RdrConclusion.
	 * @param ruleType the RuleType.
	 * @throws BlendedWorkflowException
	 */
	private void addNode(YSpecificationID yawlSpecID, String name, String condition, Element eCornerstone, Element eConclusion, RuleType ruleType) throws BlendedWorkflowException {
		try {
			RdrNode node = new RdrNode(condition, eConclusion, eCornerstone);
			client.addNode(yawlSpecID, name, ruleType, node, handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_ADDNODE);
		}
	}

	/**
	 * Get user submitted data.
	 * @param taskWorkItem a TaskWorkItem.
	 * @param goalWorkItem a GoalWorkItem.
	 * @param isPreCondition if its a Task, get the ItemPreConstraint data.
	 * @return an ELement with the evaluation data.
	 */
	private Element getEvaluationData(TaskWorkItem taskWorkItem, GoalWorkItem goalWorkItem, Boolean isPreCondition) {
		// Get Workitem
		WorkItem workItem= null;
		List<WorkItemArgument> workItemArguments = null;
		if (taskWorkItem == null) {
			workItem = goalWorkItem;
			workItemArguments = workItem.getOutputWorkItemArguments();
		}
		else {
			workItem = taskWorkItem;
			if (isPreCondition) {
				workItemArguments = workItem.getInputWorkItemArguments();
			}
			else {
				workItemArguments = workItem.getOutputWorkItemArguments();
			}
		}


		// Get Workitem data
		String cornerStr = "<cornerstone>";
		for (WorkItemArgument workItemArgument : workItemArguments) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			String entityName = attributeInstance.getEntityInstance().getEntity().getName().replaceAll(" ", "");
			String attributeName = attributeInstance.getAttribute().getName().replaceAll(" ", "");
			String value = workItemArgument.getValue();
			DataState state = workItemArgument.getState();

			cornerStr += "<" + entityName + "_" + attributeName + "_State" + ">" + state + "</" + entityName + "_" + attributeName + "_State" + ">";
			cornerStr += "<" + entityName + "_" + attributeName + ">" + value + "</" + entityName + "_" + attributeName + ">";
		}
		cornerStr += "</cornerstone>";
		Element eCornerstone = JDOMUtil.stringToElement(cornerStr);
		return eCornerstone;
	}

	/**
	 * Parse the RdrConclusion.
	 * @param rdrNode the enabled RdrNode.
	 * @return a string with the result.
	 */
	public String parseConclusion(String conclusion) {
		if (conclusion.contains("SKIPPED"))
			return "SKIPPED";
		else if (conclusion.contains("FALSE") || conclusion.contains("failure") || conclusion.contains("UNDEFINED"))
			return "FALSE";
		else if (conclusion.contains("complete") || conclusion.contains("TRUE"))
			return "TRUE";
		else
			return "FAIL";
	}	

	/**
	 * Get the corresponding WorkItemRecord of the TaskWorkItem.
	 * @param taskWorkItem the TaskWorkItem.
	 * @return the WorkItemRecord.
	 */
	private WorkItemRecord getWorkItemRecord(TaskWorkItem taskWorkItem) {
		Iterator<?> it = yawlEnabledWIR.keySet().iterator();
		while(it.hasNext()) {
			WorkItemRecord wir = (WorkItemRecord) it.next();
			TaskWorkItem tWorkItem = getTaskWorkItem(wir);
			if (tWorkItem.getID().equals(taskWorkItem.getID())) {
				return wir;
			}
		}
		return null;
	}

	/**
	 * Get the corresponding TaskWorkItem of the WorkItemRecord.
	 * @param wir the WorkItemRecord.
	 * @return the TaskWorkItem.
	 */
	private TaskWorkItem getTaskWorkItem(WorkItemRecord wir) {
		String workitemID = yawlEnabledWIR.get(wir);
		WorkItem taskWorkItem = null;
		try {
			BWInstance bwInstance = BlendedWorkflow.getInstance().getBWInstanceFromYAWLCaseID(wir.getCaseID());
			taskWorkItem = bwInstance.getWorkItem(workitemID);
		} catch (BlendedWorkflowException bwe) {
			log.error(bwe.getError());
		}
		return (TaskWorkItem) taskWorkItem;
	}

	/**
	 * Process the TaskWorkItem ItemConstrainViolation.
	 * @param taskWorkItem the TaskWorkItem.
	 * @throws BlendedWorkflowException
	 */
	public void process(TaskWorkItem taskWorkItem) throws BlendedWorkflowException {
		log.info("process");
		// Evaluation Data
		Element eData = getEvaluationData(taskWorkItem, null, false);

		// Get WorkItemRecord
		WorkItemRecord workItemRecord = getWorkItemRecord(taskWorkItem);
		
		// Process
		try {
			client.process(workItemRecord, eData, RuleType.ItemConstraintViolation, handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_PROCESS);
		}
		
		log.info("process-end");
	}

	/**
	 * Evaluate Conditions.
	 * @param taskWorkItem a TaskWorkItem;
	 * @param goalWorkItem a GoalWorkItem;
	 * @param isPreCondition if its a Task, get the ItemPreConstraint data.
	 * @throws BlendedWorkflowException
	 */
	public void evaluate(TaskWorkItem taskWorkItem, GoalWorkItem goalWorkItem, Boolean isPreCondition) throws BlendedWorkflowException {
		
		WorkItem workItem;
		if (taskWorkItem != null) {
			workItem = taskWorkItem;
		} else {
			workItem = goalWorkItem;
		}
		
		// YAWLSpecID
		String yawlSpecificationID = workItem.getBwInstance().getBwSpecification().getYawlSpecficationID();
		YSpecificationID yawlSpecID = null;
		for (YSpecificationID ySpecificationID : BlendedWorkflow.getInstance().getYawlAdapter().getLoadedActivitySpecs()) {
			if(ySpecificationID.getIdentifier().equals(yawlSpecificationID)) {
				yawlSpecID = ySpecificationID;
				break;
			}
		}

		// Task/Goal Name and Evaluation Data
		String name = null;
		Element eData = null;
		RuleType ruleType = RuleType.ItemConstraintViolation;
		if (taskWorkItem != null) {
			name = taskWorkItem.getTask().getName().replaceAll(" ", "_");
			if (isPreCondition) {
				eData = getEvaluationData(taskWorkItem, null, true);
				log.info(JDOMUtil.elementToString(eData));
				ruleType = RuleType.ItemPreconstraint;
			} else {
				eData = getEvaluationData(taskWorkItem, null, false);
			}
		} else {
			name = "GOAL_" + goalWorkItem.getAchieveGoal().getName().replaceAll(" ", "_");
			eData = getEvaluationData(null, goalWorkItem, false);
		}
		
		// Evaluate
		String conclusion = null;
		try {
			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
		}
		
		log.info(JDOMUtil.elementToString(eData));
		log.info(conclusion);
		log.info(parseConclusion(conclusion));

		// Parse result
		if (isPreCondition) {
			if (parseConclusion(conclusion).equals("TRUE")) {
				taskWorkItem.notifyConstrainViolation();
			} else if (parseConclusion(conclusion).equals("FALSE")) {
				taskWorkItem.notifyPreFalse();
			} else if (parseConclusion(conclusion).equals("SKIPPED")) {
				taskWorkItem.notifyPreTask();
			} else {
				taskWorkItem.notifyConstrainViolation();
			}
		} else if (taskWorkItem != null) {
			if (parseConclusion(conclusion).equals("TRUE")) {
				taskWorkItem.notifyCompleted();
			} else if (parseConclusion(conclusion).equals("FALSE")) {
				taskWorkItem.notifyEnabled();
			} else {
				taskWorkItem.notifySkipped();
			}			
		} else {
			if (parseConclusion(conclusion).equals("TRUE")) {
				notifyConstraintViolationResult(null, goalWorkItem, "TRUE");
			} else if (parseConclusion(conclusion).equals("FALSE")) {
				notifyConstraintViolationResult(null, goalWorkItem, "FALSE");
			} else {
				notifyConstraintViolationResult(null, goalWorkItem, "SKIPPED");
			}	
		}
	}
	

}

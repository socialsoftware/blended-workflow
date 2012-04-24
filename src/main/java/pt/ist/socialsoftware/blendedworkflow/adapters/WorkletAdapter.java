package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
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

/**
 * TODO: Clean and Test.
 * @author Davide Passinhas
 *
 */
public class WorkletAdapter {

	private WorkletGatewayClient client= new WorkletGatewayClient();
	private String handle = null;
	private Logger log;
	
	protected String engineAdminUser = PropertiesManager.getProperty("yawl.AdminUser");
	protected String engineAdminPassword = PropertiesManager.getProperty("yawl.AdminPassword");
	protected String workletGateway = PropertiesManager.getProperty("worklet.gateway");
	
	
	private YSpecificationID yawlSpecID = new YSpecificationID(null, "0.1", "A115");
	
	private ConcurrentHashMap<String, WorkItemRecord> yawlEnabledWIR = new ConcurrentHashMap<String,WorkItemRecord>();
	private ConcurrentHashMap<WorkItemRecord, String> yawlPreWIR = new ConcurrentHashMap<WorkItemRecord, String>();
	private ConcurrentHashMap<WorkItemRecord, String> yawlPosWIR = new ConcurrentHashMap<WorkItemRecord, String>();
	
	public WorkletAdapter() {
		log = Logger.getLogger("WorkletAdpater");
		registerWorkletListener();
	}
	
	/**
	 * Evaluate GoalWorkitem condition or Process TaskWorkitem postCondition
	 */
	public void notifyWorkItemContraintViolation(WorkItem workItem) {
		log.info("notifyWorkItemContraintViolation for workitem: " + workItem.getID());
		try {
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			GoalWorkItem goalWorkItem = (GoalWorkItem) workItem;
			evaluate(goalWorkItem);
		}
		else {
			TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
//			process(taskWorkItem);
			evaluatePos(taskWorkItem);
		}
		} catch (BlendedWorkflowException bwe) {
			log.info("notifyWorkItemContraintViolation: exception" + bwe.getMessage());
		}
	}
	
	/**
	 * Evaluate TaskWorkitem preCondition
	 */
	public void notifyWorkItemPreConstraint(TaskWorkItem workItem) {
		log.info("notifyWorkItemPreConstraint for workitem: " + workItem.getID());
		try {
			TaskWorkItem taskWorkItem = (TaskWorkItem) workItem;
			evaluatePre(taskWorkItem);
		} catch (BlendedWorkflowException bwe) {
			log.info("notifyWorkItemContraintViolation: exception" + bwe.getMessage());
		}
	}
	
	/**
	 * Create new TaskWorkitem according to preCondition evaluation result
	 */
	public void notifyNewTaskWorkItem (WorkItemRecord wir, String result) {
		log.info("notifyNewTaskWorkItem for workitem: " + wir + " with preConstraint = " + result);
		// Add new WorkItemRecord
//		this.yawlEnabledWorkItems.add(wir);

		// Create TaskWorkitem
		TaskWorkItem taskWorkItem = null;
		try {
			BWInstance bwInstance = BlendedWorkflow.getInstance().getBWInstanceFromYAWLCaseID(wir.getCaseID());
			TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();

			String taskName = wir.getTaskName().replaceAll("_", " ");
			Task task = taskModelInstance.getTask(taskName);
			taskWorkItem = new TaskWorkItem(bwInstance, task);
		} catch (BlendedWorkflowException bwe) {
			log.info("notifyNewTaskWorkItem: exception: " + bwe.getError());
		}

		// Notify
		if (result.equals("TRUE")) {
			taskWorkItem.notifyConstrainViolation();
		} else if (result.equals("SKIPPED")) {
			taskWorkItem.notifyPreTask();
		} else {
			taskWorkItem.notifyPreFalse();
		}
	}
	
	/**
	 * Process ConstrainViolation evaluation result
	 */
	public void notifyConstraintViolationResult(WorkItemRecord wir, GoalWorkItem goalWorkitem, String result) {
		// Get WorkItem
		WorkItem workItem = null;
		if (wir != null) {
			log.info("notifyConstraintViolationResult for TaskWorkitem: " + wir + " with postConstraint = " + result);
			
			// Remove completed WorkItemRecord
//			if (result.equals("TRUE")) {
//				this.yawlEnabledWorkItems.remove(wir);
//			}

			try {
				BWInstance bwInstance = BlendedWorkflow.getInstance().getBWInstanceFromYAWLCaseID(wir.getCaseID());
				TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();
				String taskName = wir.getTaskName().replaceAll("_", " ");
				workItem = taskModelInstance.getTask(taskName).getTaskWorkItem();
			} catch (BlendedWorkflowException bwe) {
				log.info("WorkletAdapter: notifyConstrainViolationResult Exception: " + bwe.getError());
			}
		} else {
			workItem = goalWorkitem;
			log.info("notifyConstraintViolationResult for GoalWorkitem: " + goalWorkitem.getID() + " with constraint = " + result);
		}

		// Notify
		if (result.equals("TRUE")) {
			workItem.notifyCompleted();
		} else if (result.equals("SKIPPED")) {
			workItem.notifySkipped();
		} else {
			workItem.notifyEnabled();
		}
	}
	
	/*
	 * Create and Load a specification RdrSet
	 */
	public void loadRdrSet(BWSpecification bwSpecification) throws BlendedWorkflowException {
		log.info("loadRdrSet for BWSpecification: " + bwSpecification.getName());

		TaskModel taskModel = bwSpecification.getTaskModel();
		GoalModel goalModel = bwSpecification.getGoalModel();

		// YAWLSpecID
//		String yawlCaseID = bwSpecification.getYawlSpecficationID();
//		YSpecificationID yawlSpecID = null;
//		for (YSpecificationID ySpecificationID : BlendedWorkflow.getInstance().getYawlAdapter().getLoadedActivitySpecs()) {
//			if(ySpecificationID.getIdentifier().equals(yawlCaseID)) {
//				yawlSpecID = ySpecificationID;
//				break;
//			}
//		}
		
		Boolean firstTask = true;
		String condition = null;
		Element eConclusion = null;
		Element eCornerstone = null;
		log.info("Tasks");
		// Create task RdrSet
		for (Task task : taskModel.getTasks()) {
			String taskName = task.getName().replaceAll(" ", "_");
			log.info(taskName);
			
			// PreConstraintTree
			log.info("Pre:");
			if (firstTask) {
				// True Node
				eCornerstone = getCornerstoneData(task, null, true, "NULL");
//				condition = task.getPreConstraint().getRdrCondition("DEFINED");
				condition = task.getPreConstraint().getRdrTrueCondition();
				eConclusion = createRdrConclusion("NULL");
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);
				firstTask = false;
				log.info("T"+condition);
//				log.info(JDOMUtil.elementToString(eCornerstone));

			} else {
				// Undefined Node
				eCornerstone = getCornerstoneData(task, null, true, "UNDEFINED");
//				condition = task.getPreConstraint().getRdrCondition("UNDEFINED");
				condition = task.getPreConstraint().getRdrFalseCondition();
				eConclusion = createRdrConclusion("FALSE");
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);
				log.info("T"+condition);
//				log.info(JDOMUtil.elementToString(eCornerstone));
				
				// Skipped Node
				eCornerstone = getCornerstoneData(task, null, true, "SKIPPED");
//				condition = task.getPreConstraint().getRdrCondition("SKIPPED");
				condition = task.getPreConstraint().getRdrSkippedCondition();
				eConclusion = createRdrConclusion("SKIPPED");
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);
				log.info("S"+condition);
//				log.info(JDOMUtil.elementToString(eCornerstone));
				
				// Defined Node
				eCornerstone = getCornerstoneData(task, null, true, "DEFINED");
//				condition = task.getPreConstraint().getRdrCondition("DEFINED");
				condition = task.getPreConstraint().getRdrTrueCondition();
				eConclusion = createRdrConclusion("TRUE");
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);
				log.info("D"+condition);
//				log.info(JDOMUtil.elementToString(eCornerstone));
			}
			
			// PostConstraintTree
			log.info("Post:");
			
			// Undefined Node
			eCornerstone = getCornerstoneData(task, null, false, "UNDEFINED");
//			condition = task.getPostConstraint().getRdrCondition("UNDEFINED");
			condition = task.getPostConstraint().getRdrFalseCondition();
			eConclusion = createRdrConclusion("FALSE");
			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
			log.info("F"+condition);
//			log.info(JDOMUtil.elementToString(eCornerstone));
			
			// Skipped Node
			eCornerstone = getCornerstoneData(task, null, false, "SKIPPED");
//			condition = task.getPostConstraint().getRdrCondition("SKIPPED");
			condition = task.getPostConstraint().getRdrSkippedCondition();
			eConclusion = createRdrConclusion("SKIPPED");
			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
			log.info("S"+condition);
//			log.info(JDOMUtil.elementToString(eCornerstone));
			
			// Defined Node
			eCornerstone = getCornerstoneData(task, null, false, "DEFINED");
//			condition = task.getPostConstraint().getRdrCondition("DEFINED");
			condition = task.getPostConstraint().getRdrTrueCondition();
			eConclusion = createRdrConclusion("TRUE");
			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
			log.info("D"+ condition);
//			log.info(JDOMUtil.elementToString(eCornerstone));

		}
		
//		log.info("Goals");
		// Create goal RdrSet
		for (Goal goal : goalModel.getGoals()) {
			String goalName = "GOAL_" + goal.getName().replaceAll(" ", "_");
//			log.info(goalName);
			
			// Undefined Node
			eCornerstone = getCornerstoneData(null, goal, false, "UNDEFINED");
//			condition = goal.getCondition().getRdrCondition("UNDEFINED");
			condition = goal.getCondition().getRdrFalseCondition();
			eConclusion = createRdrConclusion("FALSE");
			addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
//			log.info(condition);
			
			// Skipped Node
			eCornerstone = getCornerstoneData(null, goal, false, "SKIPPED");
//			condition = goal.getCondition().getRdrCondition("SKIPPED");
			condition = goal.getCondition().getRdrSkippedCondition();
			eConclusion = createRdrConclusion("SKIPPED");
			addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
//			log.info(condition);
			
			// Defined Node
			eCornerstone = getCornerstoneData(null, goal, false, "DEFINED");
//			condition = goal.getCondition().getRdrCondition("DEFINED");
			condition = goal.getCondition().getRdrTrueCondition();
			eConclusion = createRdrConclusion("TRUE");
			addNode(yawlSpecID, goalName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
//			log.info(condition);
		}
		evaluateTest();
	}
	
	/*******************************
	 * Support methods
	 *******************************/
	/*
	 * Register WorkletListener
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
	
	/*
	 * Get Conditions Data
	 */
	private Element getCornerstoneData(Task task, Goal goal, Boolean isPreCondition, String type) {
		String cornerStr = "<cornerstone>";
		Set<Entity> entities;
		Set<Attribute> attributes;
		HashMap<Attribute, String> attributesValues;
		
		// Get Condition Data
		if (task == null) {
			entities = goal.getCondition().getEntities();
			attributes = goal.getCondition().getAttributes();
			attributesValues = goal.getCondition().getcompareConditionValues();
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
		
		// Remove attributes that already exist in entities
		for (Attribute attribute : attributes) {
			Entity entity = attribute.getEntity();
			if (entities.contains(entity) && !attributesValues.containsKey(attribute)) {
				attributes.remove(attribute);
			}
		}

		// Parse complete entities
		for (Entity entity : entities) {
			String entityName = entity.getName().replaceAll(" ", "");
			
			for (Attribute attribute : entity.getAttributes()) {
				String attributeName = attribute.getName().replaceAll(" ", "");
//				String value = attribute.getAttributeType();

				cornerStr += "<" + entityName + "_" + attributeName + "_State" + ">" + type + "</" + entityName + "_" + attributeName + "_State" + ">";
//				cornerStr += "<" + entityName + "_" + attributeName + ">" + value + "</" + entityName + "_" + attributeName + ">";
			}
		}

		// Parse single attributes
		for (Attribute attribute : attributes) {
			String entityName = attribute.getEntity().getName().replaceAll(" ", "");
			String attributeName = attribute.getName().replaceAll(" ", "");
			String value = attributesValues.get(attribute);
			
			cornerStr += "<" + entityName + "_" + attributeName + "_State" + ">" + type + "</" + entityName + "_" + attributeName + "_State" + ">";
			cornerStr += "<" + entityName + "_" + attributeName + ">" + value + "</" + entityName + "_" + attributeName + ">";
		}
		cornerStr += "</cornerstone>";
		Element eCornerstone = JDOMUtil.stringToElement(cornerStr);
		return eCornerstone;
	}

	/*
	 * Create a RdrConclusion
	 */
	private Element createRdrConclusion(String type) {
		String concStr = null;
		Element eConclusion = null;
		String action = "false";
		
		if (type.equals("NULL")) {
			concStr = "<conclusion>null</conclusion>";
			eConclusion = JDOMUtil.stringToElement(concStr);
			return eConclusion;
		} else if (type.equals("TRUE")) {
			action = "complete";
		} else if (type.equals("SKIPPED")) {
			action = "fail";
		}
		concStr = "<conclusion><_1>";
		concStr += "<action>" + action + "</action>";
		concStr += "<target>" + "workitem" + "</target>";
		concStr += "</_1></conclusion>";
		eConclusion = JDOMUtil.stringToElement(concStr);
		return eConclusion;
	}
	
	/*
	 * Add a node to a RdrTree
	 */
	private void addNode(YSpecificationID yawlSpecID, String name, String condition, Element eCornerstone, Element eConclusion, RuleType ruleType) throws BlendedWorkflowException {
		try {
			RdrNode node = new RdrNode(condition, eConclusion, eCornerstone);
			client.addNode(yawlSpecID, name, ruleType, node, handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_ADDNODE);
		}
	}
	
	/*
	 * Get user submitted data
	 */
	private Element getEvaluationData(TaskWorkItem taskWorkItem, GoalWorkItem goalWorkItem) {
		// Get Workitem
		WorkItem workItem= null;
		if (taskWorkItem == null) {
			workItem = goalWorkItem;
		}
		else {
			workItem = taskWorkItem;
		} 
		
		// Get Workitem data
		String cornerStr = "<cornerstone>";
		for (WorkItemArgument workItemArgument : workItem.getConstrainViolationWorkItemArguments()) {
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
	 * TODO: Merge Get user submitted data
	 */
	private Element getEvaluationDataPre(TaskWorkItem taskWorkItem) {
		// Get Workitem
		WorkItem workItem = taskWorkItem;
		
		// Get Workitem data
		String cornerStr = "<cornerstone>";
		for (WorkItemArgument workItemArgument : workItem.getPreConstrainWorkItemArguments()) {
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
	 * TODO: Merge Evaluate a goal condition
	 */
	public void evaluate(GoalWorkItem goalWorkItem) throws BlendedWorkflowException {
		log.info("evaluate constraint for GoalWorkItem: " + goalWorkItem.getID());

		// YAWLSpecID
//		String yawlSpecificationID = goalWorkItem.getBwInstance().getBwSpecification().getYawlSpecficationID();
//		YSpecificationID yawlSpecID = null;
//
//		for (YSpecificationID ySpecificationID : BlendedWorkflow.getInstance().getYawlAdapter().getLoadedActivitySpecs()) {
//			if(ySpecificationID.getIdentifier().equals(yawlSpecificationID)) {
//				yawlSpecID = ySpecificationID;
//				break;
//			}
//		}

		// Goal Name
		String goalName = "GOAL_" + goalWorkItem.getGoal().getName().replaceAll(" ", "_");

		// Evaluation Data
		Element eData = getEvaluationData(null, goalWorkItem);
		log.info("With data: " + JDOMUtil.elementToString(eData));

		// Evaluate
		String conclusion = null;
		try {
			conclusion = client.evaluate(yawlSpecID, goalName, eData, RuleType.ItemConstraintViolation,handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
		}

		log.info("Conclusion" + conclusion);

		// Parse result
		if (parseConclusion(conclusion).equals("TRUE")) {
			notifyConstraintViolationResult(null, goalWorkItem, "TRUE");
		} else if (parseConclusion(conclusion).equals("FALSE")) {
			notifyConstraintViolationResult(null, goalWorkItem, "FALSE");
		} else {
			notifyConstraintViolationResult(null, goalWorkItem, "SKIPPED");
		}
	}
	
	/**
	 * Process a task postCondition
	 */
	public void process(TaskWorkItem taskWorkItem) throws BlendedWorkflowException {
		log.info("process postConstraint for TaskWorkItem: " + taskWorkItem.getID());
		
		// Evaluation Data
		Element eData = getEvaluationData(taskWorkItem, null);
		
		// Get WorkItemRecord
//		WorkItemRecord workItemRecord = null;
		String taskName = taskWorkItem.getTask().getName();
		
		WorkItemRecord workItemRecord = yawlEnabledWIR.get(taskName);
		
//		for (WorkItemRecord wir : this.yawlEnabledWorkItems) {
//			if (wir.getTaskName().equals(taskName)) {
//				workItemRecord = wir;
//			}
//		}
		
		log.info("with data: " + JDOMUtil.elementToString(eData));
		
		String result = null;
		// Process
		try {
			 result = client.process(workItemRecord, eData, RuleType.ItemConstraintViolation, handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_PROCESS);
		}
		log.info("Process Result: " + result);
	}
	
	/**
	 * Parse RdrConclusion
	 */
	public String parseConclusion(String conclusion) {
		if (conclusion.contains("complete"))
			return "TRUE";
		if (conclusion.contains("false") ) 
			return "FALSE";
		if (conclusion.contains("failure"))
			return "SUCESS";
		else 
			return "SKIPPED";
	}
	
	/**
	 * TODO: Merge Evaluate a goal condition
	 */
	public void evaluatePre(TaskWorkItem taskWorkItem) throws BlendedWorkflowException {
		log.info("evaluate Preconstraint for TaskWorkItem: " + taskWorkItem.getID());

		// YAWLSpecID
//		String yawlSpecificationID = taskWorkItem.getBwInstance().getBwSpecification().getYawlSpecficationID();
//		YSpecificationID yawlSpecID = null;
//
//		for (YSpecificationID ySpecificationID : BlendedWorkflow.getInstance().getYawlAdapter().getLoadedActivitySpecs()) {
//			if(ySpecificationID.getIdentifier().equals(yawlSpecificationID)) {
//				yawlSpecID = ySpecificationID;
//				break;
//			}
//		}

		// Goal Name
		String taskName = taskWorkItem.getTask().getName().replaceAll(" ", "_");

		// Evaluation Data
		Element eData = getEvaluationDataPre(taskWorkItem);
		log.info("With data: " + JDOMUtil.elementToString(eData));

		// Evaluate
		String conclusion = null;
		try {
			conclusion = client.evaluate(yawlSpecID, taskName, eData, RuleType.ItemPreconstraint,handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
		}

//		log.info("Conclusion" + conclusion);

		// Parse result
		if (parseConclusion(conclusion).equals("TRUE") || parseConclusion(conclusion).equals("SUCESS")) {
			taskWorkItem.notifyConstrainViolation();
		} else if (parseConclusion(conclusion).equals("FALSE")) {
			taskWorkItem.notifyPreFalse();
		} else {
			taskWorkItem.notifyPreTask();
		}
	}
	
	/**
	 * TODO: Merge Evaluate a pos condition
	 */
	public void evaluatePos(TaskWorkItem taskWorkItem) throws BlendedWorkflowException {
		log.info("evaluate Postconstraint for TaskWorkItem: " + taskWorkItem.getID());

		// YAWLSpecID
//		String yawlSpecificationID = taskWorkItem.getBwInstance().getBwSpecification().getYawlSpecficationID();
//		YSpecificationID yawlSpecID = null;
//
//		for (YSpecificationID ySpecificationID : BlendedWorkflow.getInstance().getYawlAdapter().getLoadedActivitySpecs()) {
//			if(ySpecificationID.getIdentifier().equals(yawlSpecificationID)) {
//				yawlSpecID = ySpecificationID;
//				break;
//			}
//		}

		// Goal Name
		String taskName = taskWorkItem.getTask().getName().replaceAll(" ", "_");

		// Evaluation Data
		Element eData = getEvaluationData(taskWorkItem, null);
		log.info("With data: " + JDOMUtil.elementToString(eData));

		// Evaluate
		String conclusion = null;
		try {
			conclusion = client.evaluate(yawlSpecID, taskName, eData, RuleType.ItemConstraintViolation,handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
		}

		log.info("Conclusion" + conclusion);

		// Parse result
		if (parseConclusion(conclusion).equals("TRUE")) {
			taskWorkItem.notifyCompleted();
		} else if (parseConclusion(conclusion).equals("FALSE") || parseConclusion(conclusion).equals("SUCESS")) {
			taskWorkItem.notifyEnabled();
		} else {
			taskWorkItem.notifySkipped();
		}
	}
	
	/******************
	 * TODO: Test
	 ******************/
	/**
	 * Add a new WorkItemRecord
	 */
	void addWorkItemRecord(WorkItemRecord wir, String result, Boolean isNew) {
		if (isNew) {
			yawlPreWIR.put(wir, result);
		} else {
			yawlPosWIR.put(wir, result);
		}
	}
	
	/**
	 * Get the new WorkItemRecords created by YAWL
	 */
	int getNewWorkItems () {
		int count = 0;
		Iterator<?> it = yawlPreWIR.keySet().iterator();
		while(it.hasNext()) {
			WorkItemRecord wir= (WorkItemRecord) it.next();
			String result= yawlPreWIR.get(wir);
			notifyNewTaskWorkItem(wir, result);
			yawlPreWIR.remove(wir);
			yawlEnabledWIR.put(wir.getTaskName().replace("_", " "), wir);
			count++;
		}
		return count;
	}
	
	/**
	 * Get the evaluated WorkItemRecords
	 */
	int getConstrainViolationWorkItems () {
		int count = 0;
		Iterator<?> it = yawlPreWIR.keySet().iterator();
		while(it.hasNext()) {
			WorkItemRecord wir= (WorkItemRecord) it.next();
			String result= yawlPreWIR.get(wir);
			notifyNewTaskWorkItem(wir, result);
			yawlPosWIR.remove(wir);
			yawlEnabledWIR.put(wir.getTaskName().replace("_", " "), wir);
			count++;
		}
		return count;
	}
	
	//test
	/**
	 * Evaluate a goal condition
	 */
	public void evaluateTest() throws BlendedWorkflowException {
		log.info("evaluateconstraint test: ");
		// TaskName
		String taskName = "Check-in_Patient";
		log.info("Check-in_Patient CV - POST");

		String data;
		Element eData;
		// Evaluation Data
		data = "<cornerstone>" +
				"<Patient_Name_State>UNDEFINED</Patient_Name_State>" +
				"<Patient_Name>null</Patient_Name>" +
				"<Patient_Address_State>UNDEFINED</Patient_Address_State>" +
				"<Patient_Address>null</Patient_Address>" +
				"<Patient_PhoneNumber_State>UNDEFINED</Patient_PhoneNumber_State>" +
				"<Patient_PhoneNumber>null</Patient_PhoneNumber>" +
				"<Patient_Gender_State>UNDEFINED</Patient_Gender_State>" +
				"<Patient_Gender>null</Patient_Gender>" +
				"<Episode_Closed_State>UNDEFINED</Episode_Closed_State>" +
				"<Episode_Closed>null</Episode_Closed>" + 
				"</cornerstone>";
		eData = JDOMUtil.stringToElement(data);

		// Evaluate
		String conclusion = null;
		try {
			conclusion = client.evaluate(yawlSpecID, taskName, eData, RuleType.ItemConstraintViolation,handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
		}
		log.info("1ºConclusion" + conclusion);
		
		// Evaluation Data
		data = "<cornerstone>" +
				"<Patient_Name_State>SKIPPED</Patient_Name_State>" +
				"<Patient_Name>null</Patient_Name>" +
				"<Patient_Address_State>SKIPPED</Patient_Address_State>" +
				"<Patient_Address>null</Patient_Address>" +
				"<Patient_PhoneNumber_State>SKIPPED</Patient_PhoneNumber_State>" +
				"<Patient_PhoneNumber>null</Patient_PhoneNumber>" +
				"<Patient_Gender_State>SKIPPED</Patient_Gender_State>" +
				"<Patient_Gender>null</Patient_Gender>" +
				"<Episode_Closed_State>SKIPPED</Episode_Closed_State>" +
				"<Episode_Closed>null</Episode_Closed>" + 
				"</cornerstone>";
		eData = JDOMUtil.stringToElement(data);

		// Evaluate
		conclusion = null;
		try {
			conclusion = client.evaluate(yawlSpecID, taskName, eData, RuleType.ItemConstraintViolation,handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
		}
		log.info("2ºConclusion" + conclusion);
		
		// Evaluation Data
		data = "<cornerstone>" +
				"<Patient_Name_State>DEFINED</Patient_Name_State>" +
				"<Patient_Name>john</Patient_Name>" +
				"<Patient_Address_State>DEFINED</Patient_Address_State>" +
				"<Patient_Address>adress</Patient_Address>" +
				"<Patient_PhoneNumber_State>DEFINED</Patient_PhoneNumber_State>" +
				"<Patient_PhoneNumber>21654</Patient_PhoneNumber>" +
				"<Patient_Gender_State>DEFINED</Patient_Gender_State>" +
				"<Patient_Gender>male</Patient_Gender>" +
				"<Episode_Closed_State>DEFINED</Episode_Closed_State>" +
				"<Episode_Closed>false</Episode_Closed>" + 
				"</cornerstone>";
		eData = JDOMUtil.stringToElement(data);

		// Evaluate
		conclusion = null;
		try {
			conclusion = client.evaluate(yawlSpecID, taskName, eData, RuleType.ItemConstraintViolation,handle);
		} catch (IOException e) {
			throw new BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
		}
		log.info("3ºConclusion" + conclusion);
		
	}

}

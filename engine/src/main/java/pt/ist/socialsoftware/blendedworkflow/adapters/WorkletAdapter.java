package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletGatewayClient;

import pt.ist.socialsoftware.blendedworkflow.domain.Activity;
import pt.ist.socialsoftware.blendedworkflow.domain.ActivityModel;
import pt.ist.socialsoftware.blendedworkflow.domain.AndCondition;
import pt.ist.socialsoftware.blendedworkflow.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.domain.OldAttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.OldBWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.OldTaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.OldTaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.OldTaskWorkItem.ActivityState;
import pt.ist.socialsoftware.blendedworkflow.domain.OldWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.OldWorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.TrueCondition;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.BWPropertiesManager;

public class WorkletAdapter {

	private final Logger log;
	protected String engineAdminUser = BWPropertiesManager.getProperty("yawl.AdminUser");
	protected String engineAdminPassword = BWPropertiesManager.getProperty("yawl.AdminPassword");
	protected String workletGateway = BWPropertiesManager.getProperty("worklet.gateway");

	private WorkletGatewayClient client = null;
	private String handle = null;
	private final ConcurrentHashMap<WorkItemRecord, String> yawlEnabledWIR = new ConcurrentHashMap<WorkItemRecord, String>(); // WIR:WorkItemID

	public WorkletAdapter() {
		log = LoggerFactory.getLogger("WorkletAdpater");
		registerWorkletListener();
	}

	// Note: This constructor is used for testing purposes only
	public WorkletAdapter(WorkletGatewayClient client) {
		log = LoggerFactory.getLogger("WorkletAdpater");
		this.client = client;
		this.handle = "NO HANDLE";
	}

	/*************************************************
	 * WorkletAdapterEventListener Services Methods
	 *************************************************/
	/**
	 * Process the notification by the WorkletService of a Workitem PreCondition
	 * evaluation.
	 * 
	 * @param wir
	 *            the YAWL enabled WorkItemRecord.
	 * @param result
	 *            the ItemPreConstraint result.
	 */
	public void notifyWorkItemPreConditionResult(WorkItemRecord wir, String result) {
		OldTaskWorkItem taskWorkItem = null;
		try {
			OldBWInstance bwInstance = BlendedWorkflow.getInstance().getBWInstanceFromYAWLCaseID(wir.getCaseID());
			OldTaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();

			String taskName = wir.getTaskName().replaceAll("_", " ");
			Activity task = taskModelInstance.getActivity(taskName);
			taskWorkItem = new OldTaskWorkItem(bwInstance, task);

			associateWorkItemRecordWithTaskWorkItem(wir, taskWorkItem);
		} catch (BWException bwe) {
			log.error("notifyNewTaskWorkItem: exception: " + bwe.getError());
		}

		processPreConditionEvaluationResult(result, taskWorkItem);
	}

	/**
	 * Process the notification by the WorkletService of a Workitem PreCondition
	 * evaluation.
	 * 
	 * @param wir
	 *            a YAWL enabled WorkItemRecord.
	 * @param result
	 *            the ItemConstrainViolation result.
	 */
	public void notifyWorkItemPostConditionResult(WorkItemRecord wir, String result) {
		OldTaskWorkItem taskWorkItem = getTaskWorkItem(wir);
		processPostConditionEvaluationResult(result, taskWorkItem);
	}

	/*********************************
	 * Process Conditions Result
	 *********************************/
	private void processPreConditionEvaluationResult(String result, OldTaskWorkItem taskWorkItem) {
		if (result.equals("TRUE")) {
			taskWorkItem.notifyEnabled(ConditionType.PRE_CONDITION);
		} else if (result.equals("SKIPPED") || result.equals("FALSE")) {
			taskWorkItem.notifyPreActivity(ConditionType.PRE_CONDITION);
		} else {
			log.error("It should not reach this point");
		}
		// requestWorkItemPostConditionEvaluation(taskWorkItem); //FIXME: call 2
		// times
	}

	private void processPostConditionEvaluationResult(String result, OldTaskWorkItem taskWorkItem) {
		if (result.equals("TRUE")) {
			taskWorkItem.notifyCompleted();
		} else if (result.equals("SKIPPED")) {
			taskWorkItem.notifySkipped();
		} else if (result.equals("FALSE")) {
			if (taskWorkItem.getState().equals(ActivityState.ENABLED)) {
				taskWorkItem.notifyEnabled(ConditionType.POS_CONDITION);
			} else if (taskWorkItem.getState().equals(ActivityState.PRE_ACTIVITY)) {
				taskWorkItem.notifyPreActivity(ConditionType.POS_CONDITION);
			}
		}
	}

	/*********************************
	 * BWEngine Methods
	 *********************************/
	/**
	 * Evaluate a TaskWorkitem PreConditon.
	 * 
	 * @param taskWorkItem
	 *            the taskWorkItem to evaluate.
	 */
	public void requestWorkItemPreConditionEvaluation(OldTaskWorkItem taskWorkItem) {
		try {
			evaluatePreCondition(taskWorkItem);
		} catch (BWException bwe) {
			log.error("notifyWorkItemContraintViolation: exception" + bwe.getMessage());
		}
	}

	/**
	 * Evaluate GoalWorkitem condition or Process TaskWorkitem postCondition.
	 * 
	 * @param workItem
	 *            the workItem to evaluate.
	 */
	public void requestWorkItemPostConditionEvaluation(OldTaskWorkItem taskWorkItem) {
		log.debug("requestWorkItemPostConditionEvaluation" + taskWorkItem.getID());
		try {
			process(taskWorkItem);
		} catch (BWException bwe) {
			log.error("notifyWorkItemContraintViolation: exception" + bwe.getMessage());
		}
	}

	/*********************************
	 * RdrSet
	 *********************************/
	/**
	 * Create and Load a specification RdrSet.
	 * 
	 * @param bwSpecification
	 *            the BWSpecification.
	 * @throws BWException
	 */
	public void loadRdrSet(Specification bwSpecification) throws BWException {
		ActivityModel taskModel = bwSpecification.getActivityModel();
		YSpecificationID yawlSpecID = getYAWLSpecificationID(bwSpecification);
		String condition = null;
		Element eConclusion = null;
		Element eCornerstone = null;

		// Create Tasks RdrSet
		for (Activity task : taskModel.getActivitySet()) {
			String taskName = generateYAWLTaskName(task);

			Condition preCondition = task.getPreConditionSet().stream().map(Condition.class::cast)
					.reduce(new TrueCondition(), (c1, c2) -> new AndCondition(c1, c2));

			// PreCondition Tree
			if (!preCondition.existTrue()) {
				// Undefined Node
				eCornerstone = getCornerstoneData(task, true, "UNDEFINED");
				condition = preCondition.getRdrUndefinedCondition();
				eConclusion = createRdrConclusion("UNDEFINED", true);
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);

				// Skipped Node
				eCornerstone = getCornerstoneData(task, true, "SKIPPED");
				condition = preCondition.getRdrSkippedCondition();
				eConclusion = createRdrConclusion("SKIPPED", true);
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);

				// True Node
				eCornerstone = getCornerstoneData(task, true, "DEFINED");
				condition = preCondition.getRdrTrueCondition();
				eConclusion = createRdrConclusion("TRUE", true);
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);

				// False Node
				if (preCondition.existCompareAttributeToValue()) {
					eCornerstone = getCornerstoneData(task, true, "DEFINED");
					condition = preCondition.getRdrFalseCondition();
					eConclusion = createRdrConclusion("FALSE", true);
					addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);
				}
			} else {
				eCornerstone = getCornerstoneData(task, true, "DEFINED");
				condition = preCondition.getRdrTrueCondition();
				eConclusion = createRdrConclusion("TRUE", true);
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemPreconstraint);
			}

			// PostCondition Tree
			// Undefined Node

			Condition postCondition = task.getPostConditionSet().stream().map(Condition.class::cast)
					.reduce(new TrueCondition(), (c1, c2) -> new AndCondition(c1, c2));

			eCornerstone = getCornerstoneData(task, false, "UNDEFINED");
			condition = postCondition.getRdrUndefinedCondition();
			eConclusion = createRdrConclusion("UNDEFINED", false);
			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);

			// Skipped Node
			eCornerstone = getCornerstoneData(task, false, "SKIPPED");
			condition = postCondition.getRdrSkippedCondition();
			eConclusion = createRdrConclusion("SKIPPED", false);
			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);

			// True Node
			eCornerstone = getCornerstoneData(task, false, "DEFINED");
			condition = postCondition.getRdrTrueCondition();
			eConclusion = createRdrConclusion("TRUE", false);
			addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);

			// False Node
			if (postCondition.existCompareAttributeToValue()) {
				eCornerstone = getCornerstoneData(task, false, "DEFINED");
				condition = postCondition.getRdrFalseCondition();
				eConclusion = createRdrConclusion("FALSE", false);
				addNode(yawlSpecID, taskName, condition, eCornerstone, eConclusion, RuleType.ItemConstraintViolation);
			}
		}
	}

	/*******************************
	 * Get Data
	 *******************************/
	/**
	 * Get Conditions Data for a Task
	 * 
	 * @param task
	 *            a Task.
	 * @param isPreCondition
	 *            if its a Task, get the ItemPreConstraint data.
	 * @param type
	 *            the type of CornerstoneData, i.e DEFINED, UNDEFINED or
	 *            SKIPPED.
	 * @return an ELement with the cornerstoneData.
	 */
	private Element getCornerstoneData(Activity task, Boolean isPreCondition, String type) {
		String cornerStr = "<cornerstone>";
		Set<Entity> entities = null;
		Set<Attribute> attributes = null;
		HashMap<Attribute, String> attributesValues = null;

		// Get Condition Data
		if (task != null && isPreCondition) {
			entities = task.getPreConditionSet().stream().flatMap((cond) -> cond.getEntities().stream())
					.collect(Collectors.toSet());
			attributes = task.getPreConditionSet().stream().flatMap((cond) -> cond.getAttributeSet().stream())
					.collect(Collectors.toSet());
			attributesValues = task.getPreConditionSet().stream().map(Condition.class::cast)
					.reduce(new TrueCondition(), (c1, c2) -> new AndCondition(c1, c2)).getcompareConditionValues();

		} else if (task != null) {
			entities = task.getPreConditionSet().stream().flatMap((cond) -> cond.getEntities().stream())
					.collect(Collectors.toSet());
			attributes = task.getPreConditionSet().stream().flatMap((cond) -> cond.getAttributeSet().stream())
					.collect(Collectors.toSet());
			attributesValues = task.getPostConditionSet().stream().map(Condition.class::cast)
					.reduce(new TrueCondition(), (c1, c2) -> new AndCondition(c1, c2)).getcompareConditionValues();
		}

		if (attributes != null) {
			Iterator<Attribute> it = attributes.iterator();
			while (it.hasNext()) {
				Attribute attribute = it.next();
				Entity entity = attribute.getEntity();
				if (entities.contains(entity) && !attributesValues.containsKey(attribute)
						&& attribute.getIsKeyAttribute()) {
					it.remove();
				}
			}
		}
		if (entities != null) {
			// Parse complete entities
			for (Entity entity : entities) {
				String entityName = entity.getName().replaceAll(" ", "");
				for (Attribute attribute : entity.getAttributeSet()) {
					if (attribute.getIsKeyAttribute()) {
						String attributeName = attribute.getName().replaceAll(" ", "");
						String value;
						if (type.equals("UNDEFINED"))
							value = "$UNDEFINED$";
						else if (type.equals("SKIPPED"))
							value = "$SKIPPED$";
						else
							value = "$DEFINED$";

						cornerStr += "<" + entityName + "_" + attributeName + "_State" + ">" + type + "</" + entityName
								+ "_" + attributeName + "_State" + ">";
						cornerStr += "<" + entityName + "_" + attributeName + ">" + value + "</" + entityName + "_"
								+ attributeName + ">";
					}
				}
			}
		}
		// Parse single attributes
		if (attributes != null) {
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

				cornerStr += "<" + entityName + "_" + attributeName + "_State" + ">" + type + "</" + entityName + "_"
						+ attributeName + "_State" + ">";
				cornerStr += "<" + entityName + "_" + attributeName + ">" + value + "</" + entityName + "_"
						+ attributeName + ">";
			}
		}

		cornerStr += "</cornerstone>";
		Element eCornerstone = JDOMUtil.stringToElement(cornerStr);
		return eCornerstone;
	}

	/**
	 * Get PreCondition user submitted data.
	 * 
	 * @param taskWorkItem
	 *            a TaskWorkItem.
	 * @return an ELement with the evaluation data.
	 */
	private Element getInputEvaluationData(OldTaskWorkItem taskWorkItem) {
		// Get Workitem
		OldWorkItem workItem = taskWorkItem;
		Set<OldWorkItemArgument> workItemArguments = workItem.getInputWorkItemArgumentsSet();

		// Get Workitem data
		String cornerStr = "<cornerstone>";
		for (OldWorkItemArgument workItemArgument : workItemArguments) {
			OldAttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			String entityName = attributeInstance.getEntityInstance().getEntity().getName().replaceAll(" ", "");
			String attributeName = attributeInstance.getAttribute().getName().replaceAll(" ", "");
			DataState state = workItemArgument.getState();

			// FIXME: Today
			String value = workItemArgument.getValue();
			if (workItemArgument.getAttributeInstance().getAttribute().getIsSystem()) {
				if (value.equals(BlendedWorkflow.getInstance().getToday())) {
					value = "" + "$TODAY$".hashCode();
				} else {
					value = "0";
				}
			}

			cornerStr += "<" + entityName + "_" + attributeName + "_State" + ">" + state + "</" + entityName + "_"
					+ attributeName + "_State" + ">";
			cornerStr += "<" + entityName + "_" + attributeName + ">" + value + "</" + entityName + "_" + attributeName
					+ ">";
		}
		cornerStr += "</cornerstone>";
		Element eCornerstone = JDOMUtil.stringToElement(cornerStr);
		return eCornerstone;
	}

	/**
	 * Get PostCondition user submitted data.
	 * 
	 * @param taskWorkItem
	 *            a TaskWorkItem.
	 * @return an ELement with the evaluation data.
	 */
	private Element getOutputEvaluationData(OldTaskWorkItem taskWorkItem) {
		// Get Workitem
		OldWorkItem workItem = taskWorkItem;
		Set<OldWorkItemArgument> workItemArguments = workItem.getOutputWorkItemArgumentsSet();

		// Get Workitem data
		String cornerStr = "<cornerstone>";
		for (OldWorkItemArgument workItemArgument : workItemArguments) {
			OldAttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			String entityName = attributeInstance.getEntityInstance().getEntity().getName().replaceAll(" ", "");
			String attributeName = attributeInstance.getAttribute().getName().replaceAll(" ", "");
			DataState state = workItemArgument.getState();

			// FIXME: Today
			String value = workItemArgument.getValue();
			if (workItemArgument.getAttributeInstance().getAttribute().getIsSystem()) {
				if (value.equals(BlendedWorkflow.getInstance().getToday())) {
					value = "" + "$TODAY$".hashCode();
				} else {
					value = "0";
				}
			}

			cornerStr += "<" + entityName + "_" + attributeName + "_State" + ">" + state + "</" + entityName + "_"
					+ attributeName + "_State" + ">";
			cornerStr += "<" + entityName + "_" + attributeName + ">" + value + "</" + entityName + "_" + attributeName
					+ ">";
		}
		cornerStr += "</cornerstone>";
		Element eCornerstone = JDOMUtil.stringToElement(cornerStr);
		return eCornerstone;
	}

	/*******************************
	 * Rdr Conclusions
	 *******************************/
	// FIXME:
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
			} else if (type.equals("FALSE") || type.equals("UNDEFINED")) {
				action = "FALSE";
			} else if (type.equals("SKIPPED")) {
				action = "SKIPPED";
			}
		} else {
			if (type.equals("TRUE")) {
				action = "complete";
			} else if (type.equals("FALSE") || type.equals("UNDEFINED")) {
				action = "FALSE";
			} else if (type.equals("SKIPPED")) {
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
	 * Parse the RdrConclusion.
	 * 
	 * @param rdrNode
	 *            the enabled RdrNode.
	 * @return a string with the result.
	 */
	// FIXME:
	public String parseConclusion(String conclusion) {
		if (conclusion.contains("SKIPPED"))
			return "SKIPPED";
		else if (conclusion.contains("FALSE"))
			return "FALSE";
		else if (conclusion.contains("complete") || conclusion.contains("TRUE"))
			return "TRUE";
		else
			return "FAIL";
	}

	/************************************************
	 * WorkletGateway Methods
	 *************************************************/
	/**
	 * Register BlendedWorkflow WorkletListener.
	 */
	public boolean registerWorkletListener() {
		this.client = new WorkletGatewayClient();
		try {
			this.handle = this.client.connect(engineAdminUser, engineAdminPassword);
			client.addListener(workletGateway, handle);
			log.info("Register Worklet Listener: Sucess!");
			return true;
		} catch (IOException ioe) {
			log.error("Register Worklet Listener: Exception: " + ioe.getMessage());
		}
		log.error("Register Worklet Listener: Failed!");
		return false;
	}

	/**
	 * Add a node to a RdrTree.
	 * 
	 * @param yawlSpecID
	 *            the YSpecificationID.
	 * @param name
	 *            the Task name.
	 * @param condition
	 *            the condition.
	 * @param eCornerstone
	 *            the RdrNode cornerstone data.
	 * @param eConclusion
	 *            the RdrNode RdrConclusion.
	 * @param ruleType
	 *            the RuleType.
	 * @throws BWException
	 */
	private void addNode(YSpecificationID yawlSpecID, String name, String condition, Element eCornerstone,
			Element eConclusion, RuleType ruleType) throws BWException {
		try {
			RdrNode node = new RdrNode(condition, eConclusion, eCornerstone);
			client.addNode(yawlSpecID, name, ruleType, node, handle);
		} catch (IOException e) {
			throw new BWException(BWErrorType.WORKLET_ADAPTER_ADDNODE);
		}
	}

	/**
	 * Process the TaskWorkItem ItemConstrainViolation.
	 * 
	 * @param taskWorkItem
	 *            the TaskWorkItem.
	 * @throws BWException
	 */
	public void process(OldTaskWorkItem taskWorkItem) throws BWException {
		// Evaluation Data
		Element eData = getOutputEvaluationData(taskWorkItem);

		// Get WorkItemRecord
		WorkItemRecord workItemRecord = getWorkItemRecord(taskWorkItem);

		// Process
		try {
			client.process(workItemRecord, eData, RuleType.ItemConstraintViolation, handle);
		} catch (IOException e) {
			throw new BWException(BWErrorType.WORKLET_ADAPTER_PROCESS);
		}
	}

	/**
	 * Evaluate a Task PreCondition.
	 * 
	 * @param taskWorkItem
	 *            a TaskWorkItem;
	 * @throws BWException
	 */
	public void evaluatePreCondition(OldTaskWorkItem taskWorkItem) throws BWException {
		Specification bwSpecification = taskWorkItem.getBwInstance().getSpecification();
		YSpecificationID yawlSpecID = getYAWLSpecificationID(bwSpecification);

		String name = generateYAWLTaskName(taskWorkItem.getTask());
		Element eData = getInputEvaluationData(taskWorkItem);
		RuleType ruleType = RuleType.ItemPreconstraint;
		String conclusion = null;

		// Evaluate
		try {
			conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
		} catch (IOException e) {
			throw new BWException(BWErrorType.WORKLET_ADAPTER_EVALUATEPRECONDITION);
		}

		processPreConditionEvaluationResult(parseConclusion(conclusion), taskWorkItem);
	}

	// /**
	// * Evaluate a Task PostCondition.
	// * @param taskWorkItem a TaskWorkItem;
	// * @throws BlendedWorkflowException
	// */
	// public void evaluatePostCondition(TaskWorkItem taskWorkItem) throws
	// BlendedWorkflowException {
	// BWSpecification bwSpecification =
	// taskWorkItem.getBwInstance().getBwSpecification();
	// YSpecificationID yawlSpecID = getYAWLSpecificationID(bwSpecification);
	//
	// String name = generateYAWLTaskName(taskWorkItem.getTask());
	// Element eData = getOutputEvaluationData(taskWorkItem);
	// RuleType ruleType = RuleType.ItemConstraintViolation;
	// String conclusion = null;
	//
	// // Evaluate
	// try {
	// conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
	// } catch (IOException e) {
	// throw new
	// BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
	// }
	//
	// // Parse result
	// if (parseConclusion(conclusion).equals("TRUE")) {
	// taskWorkItem.notifyCompleted();
	// } else if (parseConclusion(conclusion).equals("FALSE")) {
	// taskWorkItem.notifyEnabled();
	// } else {
	// taskWorkItem.notifySkipped();
	// }
	// }

	/*************************************
	 * YAWL Converter
	 ************************************/
	/**
	 * Get YAWLSpecificationID of a BWSpecification
	 * 
	 * @param bwSpecification
	 *            the BWSpecification
	 * @return yawlSpecID the YAWLSpecificationID
	 * @throws BWException
	 */
	private YSpecificationID getYAWLSpecificationID(Specification bwSpecification) throws BWException {
		String yawlCaseID = bwSpecification.getYawlSpecficationID();
		YSpecificationID yawlSpecID = null;
		for (YSpecificationID ySpecificationID : BlendedWorkflow.getInstance().getYawlAdapter()
				.getLoadedActivitySpecs()) {
			if (ySpecificationID.getIdentifier().equals(yawlCaseID)) {
				yawlSpecID = ySpecificationID;
				break;
			}
		}
		return yawlSpecID;
	}

	/**
	 * Get the YAWL Task name
	 * 
	 * @param taskWorkItem
	 *            the taskWorkItem
	 * @return name the task name on YAWL
	 */
	private String generateYAWLTaskName(Activity task) {
		return task.getName().replaceAll(" ", "_");
	}

	/**
	 * Get the corresponding WorkItemRecord of the TaskWorkItem.
	 * 
	 * @param taskWorkItem
	 *            the TaskWorkItem.
	 * @return the WorkItemRecord.
	 */
	private WorkItemRecord getWorkItemRecord(OldTaskWorkItem taskWorkItem) {
		Iterator<?> it = yawlEnabledWIR.keySet().iterator();
		while (it.hasNext()) {
			WorkItemRecord wir = (WorkItemRecord) it.next();
			OldTaskWorkItem tWorkItem = getTaskWorkItem(wir);
			if (tWorkItem.getID().equals(taskWorkItem.getID())) {
				return wir;
			}
		}
		return null;
	}

	/**
	 * Get the corresponding TaskWorkItem of the WorkItemRecord.
	 * 
	 * @param wir
	 *            the WorkItemRecord.
	 * @return the TaskWorkItem.
	 */
	private OldTaskWorkItem getTaskWorkItem(WorkItemRecord wir) {
		String workitemID = yawlEnabledWIR.get(wir);
		OldWorkItem taskWorkItem = null;
		try {
			OldBWInstance bwInstance = BlendedWorkflow.getInstance().getBWInstanceFromYAWLCaseID(wir.getCaseID());
			taskWorkItem = bwInstance.getWorkItem(workitemID);
		} catch (BWException bwe) {
			log.error(bwe.getError().name());
		}
		return (OldTaskWorkItem) taskWorkItem;
	}

	public void associateWorkItemRecordWithTaskWorkItem(WorkItemRecord wir, OldTaskWorkItem taskWorkItem) {
		yawlEnabledWIR.put(wir, taskWorkItem.getID());
	}

	// /*****************************************
	// * TEST
	// *****************************************/
	// public void evaluateTest(BWSpecification bwSpecification) throws
	// BlendedWorkflowException {
	// log.debug("--------------------------------------------------------------");
	// log.debug("TEST ALL NODES");
	// log.debug("--------------------------------------------------------------");
	// String name;RuleType ruleType = RuleType.ItemConstraintViolation;
	// Element eData;
	// String conclusion;
	// String cs;
	//
	// YSpecificationID yawlSpecID = getYAWLSpecificationID(bwSpecification);
	//
	// name = "Collect_Physical_Data";
	// log.info(name);
	// // UND
	// cs = "<cornerstone>"+
	// "<PatientData_Height_State>UNDEFINED</PatientData_Height_State>"+
	// "<PatientData_Height>$UNDEFINED$</PatientData_Height>"+
	// "<PatientData_Weight_State>UNDEFINED</PatientData_Weight_State>"+
	// "<PatientData_Weight>$UNDEFINED$</PatientData_Weight>"+
	// "<FALSE_NODE>FALSE</FALSE_NODE>"+
	// "</cornerstone>";
	// eData = JDOMUtil.stringToElement(cs);
	//
	// try {
	// conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
	// } catch (IOException e) {
	// throw new
	// BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
	// }
	// log.info("UND=" + conclusion);
	//
	// // DEF
	// cs = "<cornerstone>"+
	// "<PatientData_Height_State>DEFINED</PatientData_Height_State>"+
	// "<PatientData_Height>$UNDEFINED$</PatientData_Height>"+
	// "<PatientData_Weight_State>DEFINED</PatientData_Weight_State>"+
	// "<PatientData_Weight>$UNDEFINED$</PatientData_Weight>"+
	// "<FALSE_NODE>FALSE</FALSE_NODE>"+
	// "</cornerstone>";
	// eData = JDOMUtil.stringToElement(cs);
	//
	// try {
	// conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
	// } catch (IOException e) {
	// throw new
	// BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
	// }
	// log.info("TRUE=" + conclusion);
	//
	// // SKIP
	// cs = "<cornerstone>"+
	// "<PatientData_Height_State>SKIPPED</PatientData_Height_State>"+
	// "<PatientData_Height>$SKIPPED$</PatientData_Height>"+
	// "<PatientData_Weight_State>SKIPPED</PatientData_Weight_State>"+
	// "<PatientData_Weight>$SKIPPED$</PatientData_Weight>"+
	// "<FALSE_NODE>FALSE</FALSE_NODE>"+
	// "</cornerstone>";
	// eData = JDOMUtil.stringToElement(cs);
	//
	// try {
	// conclusion = client.evaluate(yawlSpecID, name, eData, ruleType, handle);
	// } catch (IOException e) {
	// throw new
	// BlendedWorkflowException(BlendedWorkflowError.WORKLET_ADAPTER_EVALUATE);
	// }
	// log.info("SKIP=" + conclusion);
	// }

}

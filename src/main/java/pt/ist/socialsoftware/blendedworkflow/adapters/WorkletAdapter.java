package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrMarshal;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletEventListener;
import org.yawlfoundation.yawl.worklet.support.WorkletGatewayClient;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Attribute.AttributeType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Entity;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Goal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Task;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModel;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.TaskWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.shared.SpecUtils;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

@SuppressWarnings("serial")
public class WorkletAdapter  { //extends WorkletEventListener {
	
	private ArrayList<WorkItemRecord> yawlEnabledWorkItems = new ArrayList<WorkItemRecord>(); 	// FIXME not persistent
	private ArrayList<YSpecificationID> loadedActivitySpecs = new ArrayList<YSpecificationID>(); // spec UUID
	private WorkletGatewayClient client= new WorkletGatewayClient();
	private String handle = null;
	private Logger log;
	private WorkletAdapterTestInterface wati;

	public WorkletAdapter() {
		log = Logger.getLogger("bwServicelogger");
		registerWorkletListener();
	}
	
	public WorkletAdapter(WorkletAdapterTestInterface wati) {
		log = Logger.getLogger("bwServicelogger");
		this.wati = wati;
		registerWorkletListener();
	}
	
	// Register WorkletListener
	public boolean registerWorkletListener(){
		WorkletGatewayClient client= new WorkletGatewayClient();
		try{
			String url="http://localhost:8081/blendedworkflow/wl";
			this.handle=client.connect("admin","YAWL");
			client.addListener(url,handle);
			log.info("WorkletAdapater: Register Worklet Listener: Sucess!");
			return true;
		}
		catch(IOException ioe){
			log.info("WorkletAdapater: Register Worklet Listener: Exception: " + ioe.getMessage());
		}
		log.info("WorkletAdapater: Register Worklet Listener: Failed!");
		return false;
	}
	
	public boolean successful(String message) {
		return ((message != null) && (message.length() > 0) && (!(message.contains("<failure>"))));
	}
	
	/***********************
	 * TestMethods
	 ***********************/
	public void loadOrganizeConcert () throws IOException {
		registerWorkletListener();
		log.info("loadOrganizeConcert");
		String yawlfilename = "C:/Users/User/git/blended-workflow/dist/OrganiseConcert.yawl";
		String yawlString = StringUtils.fileToString(yawlfilename);
		YSpecificationID ySpecificationID = SpecUtils.getYAWLSpecificationIDFromSpec(yawlString);
		
		String cornerStr= "<cornerstone><VenueCost>100000.00</VenueCost><VenueName>ANZ Stadium</VenueName><Seating>25000</Seating></cornerstone>";
		Element eCornerstone = JDOMUtil.stringToElement(cornerStr);
		
		// Task Pre condition
		String concStr1 = "<conclusion>null</conclusion>";
		Element eConclusion1 = JDOMUtil.stringToElement(concStr1);
		RdrNode node1 = new RdrNode("True", eConclusion1, eCornerstone);
		client.addNode(ySpecificationID, "BookStadium", RuleType.ItemPreconstraint, node1, handle);
		
		// Task Constrain violation
		String concStr2 = "<conclusion><_1><action>complete</action><target>workitem</target></_1></conclusion>";
		Element eConclusion2 = JDOMUtil.stringToElement(concStr2);
		RdrNode node2 = new RdrNode("VenueName = Benfica", eConclusion2, eCornerstone);
		client.addNode(ySpecificationID, "BookStadium", RuleType.ItemConstraintViolation, node2, handle);
		
		log.info("loadOrganizeConcert: evaluate pre-test");
		evaluateTaskTrue();
		evaluateTaskFalse();
	}
	
	public String evaluateWIRTrue(WorkItemRecord wir) throws IOException {
		String dataStr0= "<cornerstone><VenueName>Benfica</VenueName><Seating>25000</Seating><TicketsSold>0</TicketsSold><TicketCost>90</TicketCost><Patient /></cornerstone>";
		Element data0 = JDOMUtil.stringToElement(dataStr0);
		String concTrue0 = client.evaluate(wir, data0, RuleType.ItemConstraintViolation, handle);
		log.info("WorkletAdapater: evaluateWIR: Result=True? " + concTrue0); //successful(concTrue0));
		return "Sucesso";
	}
	
	public String evaluateWIRFalse(WorkItemRecord wir) throws IOException {
		String dataStr0= "<cornerstone><VenueName>ANZ Stadium</VenueName><Seating>25000</Seating><TicketsSold>0</TicketsSold><TicketCost>90</TicketCost><Patient /></cornerstone>";
		Element data0 = JDOMUtil.stringToElement(dataStr0);
		String concTrue0 = client.evaluate(wir, data0, RuleType.ItemConstraintViolation, handle);
		log.info("WorkletAdapater: evaluateWIR: Result=False? " + concTrue0); //successful(concTrue0));
		return "Sucesso";
	}
	
	public String evaluateTaskTrue() throws IOException {
		String yawlfilename = "C:/Users/User/git/blended-workflow/dist/OrganiseConcert.yawl";
		String yawlString = StringUtils.fileToString(yawlfilename);
		YSpecificationID ySpecificationID = SpecUtils.getYAWLSpecificationIDFromSpec(yawlString);
		
		String dataStr0= "<cornerstone><VenueName>Benfica</VenueName><Seating>25000</Seating><TicketsSold>0</TicketsSold><TicketCost>90</TicketCost><Patient /></cornerstone>";
		Element data0 = JDOMUtil.stringToElement(dataStr0);
		String concTrue0 = client.evaluate(ySpecificationID,"BookStadium",data0,RuleType.ItemConstraintViolation,handle);
		
		log.info("WorkletAdapater: evaluatetestTrue: Result=True? " + concTrue0); //successful(concTrue0));
		return "Sucesso";
	}
	
	public String evaluateTaskFalse() throws IOException {
		String yawlfilename = "C:/Users/User/git/blended-workflow/dist/OrganiseConcert.yawl";
		String yawlString = StringUtils.fileToString(yawlfilename);
		YSpecificationID ySpecificationID = SpecUtils.getYAWLSpecificationIDFromSpec(yawlString);
		
		String dataStr0= "<cornerstone><VenueName>ANZ Stadium</VenueName><Seating>25000</Seating><TicketsSold>0</TicketsSold><TicketCost>90</TicketCost><Patient /></cornerstone>";
		Element data0 = JDOMUtil.stringToElement(dataStr0);
		String concTrue0 = client.evaluate(ySpecificationID, "BookStadium",data0,RuleType.ItemConstraintViolation,handle);
		
		log.info("WorkletAdapater: evaluatetestFalse: Result=False? " + concTrue0); //successful(concTrue0));
		return "Sucesso";
	}

	public int getWorkItems () {
		return this.yawlEnabledWorkItems.size();
	}
	
	/*******************************
	 * TODO: WorkletAdapter methods
	 *******************************/
	public void notifyNewTaskWorkItem (WorkItemRecord wir, String conditionResult)  { //throws BlendedWorkflowException {
		this.yawlEnabledWorkItems.add(wir);

		TaskWorkItem taskWorkItem = null;
		try {
			BWInstance bwInstance = BlendedWorkflow.getInstance().getYAWLBWInstance(wir.getCaseID());
			TaskModelInstance taskModelInstance = bwInstance.getTaskModelInstance();

			String taskName = wir.getTaskName();
			Task task = taskModelInstance.getTask(taskName);
			taskWorkItem = new TaskWorkItem(bwInstance, task);
		} catch (BlendedWorkflowException bwe) {
			log.info("WorkletAdapater: notifyNewTaskWorkItem: exception" + bwe.getMessage()); //FIXME: do something
		}

		if (conditionResult.equals("TRUE")) {
			taskWorkItem.notifyPreConstrain();
		} else if (conditionResult.equals("SKIPPED")) {
			taskWorkItem.notifyPreTask();
		} else {
			//throw new BlendedWorkflowException(BlendedWorkflowError.FALSE_PRE_CONSTRAIN); //FIXME:
		}
		
		// FIXME: Test: evaluate all
		log.info("WorkletAdapater: new workitem arrived. Testing...");
		try {
			evaluateWIRTrue(wir); // FAIL: <failure>No rules found for parameters</failure>
			evaluateWIRFalse(wir); // FAIL: <failure>No rules found for parameters</failure>
			evaluateTaskTrue(); // OK: <conclusion><_1><action>complete</action><target>workitem</target></_1>
			evaluateTaskFalse(); // OK: <failure>No rule was satisfied for data parameters</failure>
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		log.info("WorkletAdapater: test end.");
	}
	
	public void loadRDRSet(BWSpecification bwSpecification) {
		TaskModel taskModel = bwSpecification.getTaskModel();
		GoalModel goalModel = bwSpecification.getGoalModel();

		// YAWLSpecID
		String yawlCaseID = bwSpecification.getYawlSpecficationID(); // FIXME: check value
		YSpecificationID yawlSpecID = null;
		for (YSpecificationID ySpecificationID : this.loadedActivitySpecs) {
			if(ySpecificationID.getIdentifier().equals(yawlCaseID)) {
				yawlSpecID = ySpecificationID;
				break;
			}
		}

		// Create task rdrset
		for (Task task : taskModel.getTasks()) {
			// TODO:
		}

		// Create goal rdrset
		try {
			for (Goal goal : goalModel.getGoals()) {
				// GoalName
				String goalName = "Goal_" + goal.getName();

				// Cornerstone data
				String cornerStr = "<cornerstone>";

				List<Entity> entities = goal.getEntities();
				List<Attribute> attributes = goal.getAttributes();

				// remove attributes that already exist in entities
				for (Attribute attribute : attributes) {
					Entity entity = attribute.getEntity();
					if (entities.contains(entity)) {
						attributes.remove(attribute);
					}
				}

				// XMLString
				for (Entity entity : entities) {
					String entityName = entity.getName();
					cornerStr += "<" + entityName + ">";
					for (Attribute attribute : entity.getAttributes()) {
						String attributeName = attribute.getName();
						String value = "";
						if (attribute.getType().equals(AttributeType.BOOLEAN)) {
							value = "false";
						}
						else if (attribute.getType().equals(AttributeType.NUMBER)) {
							value = "0";
						} else {
							value = " ";
						}

						cornerStr += "<" + attributeName + "_State" + ">" + "UNDEFINED" + "</" + attributeName + "_State" + ">";
						cornerStr += "<" + attributeName + ">" + value + "</" + attributeName + ">";
					}
					cornerStr +=  "</" + entityName + ">";
				}

				for (Attribute attribute : attributes) {
					String entityName = attribute.getEntity().getName();
					cornerStr += "<" + entityName + ">";
					String attributeName = attribute.getName();
					String value = "";
					if (attribute.getType().equals(AttributeType.BOOLEAN)) {
						value = "false";
					}
					else if (attribute.getType().equals(AttributeType.NUMBER)) {
						value = "0";
					} else {
						value = " ";
					}

					cornerStr += "<" + attributeName + "_State" + ">" + "UNDEFINED" + "</" + attributeName + "_State" + ">";
					cornerStr += "<" + attributeName + ">" + value + "</" + attributeName + ">";

					cornerStr +=  "</" + entityName + ">";
				}
				cornerStr += "<cornerstone>";
				Element eCornerstone = JDOMUtil.stringToElement(cornerStr);

				// Conclusion
				String concStr = "<conclusion><_1>";
				concStr = "<action>" + "complete" + "</action>"; //FIXME: skip?
				concStr = "<target>" + "workitem" + "</target>";
				concStr += "</_1></conclusion>";
				Element eConclusion = JDOMUtil.stringToElement(concStr);

				// Condition
				String condition = goal.getCondition().getString(); // FIXME: return converted string for RDR

				// add node
				RdrNode node = new RdrNode(condition, eConclusion, eCornerstone);
				String newNodeXML = client.addNode(yawlSpecID, goalName, RuleType.ItemConstraintViolation, node, handle);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// FIXME: return depends if there is tripleState or not
	public String evaluate(WorkItem workItem) throws IOException {
		// YAWLSpecID
		String yawlCaseID = workItem.getBwInstance().getYawlCaseID();
		YSpecificationID yawlSpecID = null;
		for (YSpecificationID ySpecificationID : this.loadedActivitySpecs) {
			if(ySpecificationID.getIdentifier().equals(yawlCaseID)) {
				yawlSpecID = ySpecificationID;
				break;
			}
		}

		// Task/Goal Name
		String jobName ="";
		if (workItem.getClass().equals(GoalWorkItem.class)) {
			jobName = "Goal_" + workItem.getJobName();
		}
		else {
			jobName = workItem.getJobName();
		}

		// Data
		String dataStr = "<data>";
		for (WorkItemArgument workItemArgument : workItem.getConstrainViolationWorkItemArguments()) {

			String attributeName = workItemArgument.getAttributeInstance().getAttribute().getName();
			DataState attributeState = workItemArgument.getAttributeInstance().getState();
			String value = workItemArgument.getValue();

			dataStr += "<" + attributeName + "_State" + ">" + attributeState + "</" + attributeName + "_State" + ">";
			dataStr += "<" + attributeName + ">" + value + "</" + attributeName + ">";

		}
		dataStr+="</data>";
		Element data = JDOMUtil.stringToElement(dataStr);

		String conclusion = client.evaluate(yawlSpecID, jobName, data ,RuleType.ItemConstraintViolation, handle);
		return successful(conclusion) +"";
	}

	public void notifyWorkItemContraintViolation(WorkItem workItem){
		log.info("notifyWorkItemContraintViolation");
//		try {
//			// prepare
//
//			// TODO: evaluate
//			String result = evaluate(workItem);
//
//			// parse result
//			if (result.equals("true")) {
//				workItem.notifyCompleted();
//			}
//			else if (result.equals("skipped")) {
//				workItem.notifySkipped();
//
//			} else if (result.equals("false")) {
//				workItem.notifyEnabled();
//			}
//
//		} catch (Exception e) {
//		}
	}
	
}

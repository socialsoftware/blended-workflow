package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletGatewayClient;

import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.shared.SpecUtils;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;

/**
 * TODO: Delete
 * @author User
 *
 */
public class WorkletServiceTests {

	private Logger log = Logger.getLogger("bwServicelogger");
	private WorkletGatewayClient client= new WorkletGatewayClient();
	private String handle = null;
	private YSpecificationID yawlSpecID = new YSpecificationID(null, "0.1", "A77");

	/***********************
	 * TestMethods
	 ***********************/
	public void loadOrganizeConcert () throws IOException {
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
		log.info("loadOrganizeConcert: evaluate pre-test-end");
	}

	public String evaluateWIRTrue(WorkItemRecord wir) throws IOException {
		String dataStr0= "<cornerstone><VenueName>Benfica</VenueName><Seating>25000</Seating><TicketsSold>0</TicketsSold><TicketCost>90</TicketCost><Patient /></cornerstone>";
		Element data0 = JDOMUtil.stringToElement(dataStr0);
		String concTrue0 = client.evaluate(wir, data0, RuleType.ItemConstraintViolation, handle);
		log.info("WorkletAdapater: evaluateWIR: Result=True? " + concTrue0); //successful(concTrue0));
		return "Sucesso";
	}

	public String processWIRTrue(WorkItemRecord wir) throws IOException {
		String dataStr0= "<cornerstone><VenueName>Benfica</VenueName><Seating>25000</Seating><TicketsSold>0</TicketsSold><TicketCost>90</TicketCost></cornerstone>";
		Element data0 = JDOMUtil.stringToElement(dataStr0);
		String concTrue0 = client.process(wir, data0, RuleType.ItemConstraintViolation, handle);
		log.info("WorkletAdapater: processWIR: Result=True? " + concTrue0 + " end"); //successful(concTrue0));
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
	
	//test
	/**
	 * Evaluate a goal condition
	 */
	public void evaluateTest() throws BlendedWorkflowException {
		log.info("WorkletAdapater: evaluate Postconstraint test: ");

		// Goal Name
		String taskName = "Check-in_Patient";

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

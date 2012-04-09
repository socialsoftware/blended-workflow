package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.util.JDOMUtil;
import org.yawlfoundation.yawl.worklet.rdr.RdrMarshal;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletGatewayClient;

public class WorkletServiceTests {
	
	private Logger log = Logger.getLogger("bwServicelogger");
	private YSpecificationID specID = new YSpecificationID(null,"0.1","test");
	private WorkletGatewayClient client= new WorkletGatewayClient();
	private RdrMarshal marshal = new RdrMarshal();
	private String handle = null;
	
	// Create RDRSet
		public String createTreeTest() throws IOException {
			log.info("createTreeTest");
			String cornerStr= "<cornerstone><Age>40</Age></cornerstone>";
			Element eCornerstone = JDOMUtil.stringToElement(cornerStr);
			
			// Task1
			String concStr1 = "<conclusion><_1><action>suspend</action><target>case</target></_1></conclusion>";
			Element eConclusion1 = JDOMUtil.stringToElement(concStr1);
			RdrNode node1 = new RdrNode("Age = 140", eConclusion1, eCornerstone);
			String newNodeXML1 = client.addNode(specID, "Task1", RuleType.ItemConstraintViolation, node1, handle);
			
			// Task2
			String concStr2 = "<conclusion><_1><action>abort</action><target>case</target></_1></conclusion>";
			Element eConclusion2 = JDOMUtil.stringToElement(concStr2);
			RdrNode node2 = new RdrNode("Age = 40", eConclusion2, eCornerstone);
			String newNodeXML2 = client.addNode(specID, "Task2", RuleType.ItemConstraintViolation, node2, handle);
			
			// Task3
			String concStr3 = "<conclusion><_1><action>select</action><target>case</target></_1></conclusion>";
			Element eConclusion3 = JDOMUtil.stringToElement(concStr3);
			RdrNode node3 = new RdrNode("Age > 401", eConclusion3, eCornerstone);
			String newNodeXML3 = client.addNode(specID, "Task3", RuleType.ItemConstraintViolation, node3, handle);
			
			String ruleSetXML = client.getRdrSet(specID,handle);
			
			log.info("RuleSet: ");
			log.info(ruleSetXML);
			return "Sucesso";
		}
		
		public String evaluateTest() throws IOException {
			log.info("evaluateTest");
			// evaluate data
			// data1
			String dataStr = "<data><Age>140</Age></data>";
			Element data = JDOMUtil.stringToElement(dataStr);
			
			// data2
			String dataStr2 = "<data><Age>40</Age></data>";
			Element data2 = JDOMUtil.stringToElement(dataStr2);

			// data3
			String dataStr3 = "<data><Age>4011</Age></data>";
			Element data3 = JDOMUtil.stringToElement(dataStr3);

			String concTrue1 = client.evaluate(specID,"Task1",data,RuleType.ItemConstraintViolation,handle);
			String concTrue2 = client.evaluate(specID,"Task2",data2,RuleType.ItemConstraintViolation,handle);
			String concTrue3 = client.evaluate(specID,"Task3",data3,RuleType.ItemConstraintViolation,handle);

			log.info("Evaluate Task1: " + concTrue1 + "| ");
			log.info("Evaluate Task2: " + concTrue2 + "| ");
			log.info("Evaluate Task3: " + concTrue3 + "| ");
			return "Sucesso";
		}
		
		public String loadtest(YSpecificationID ySpecificationID) throws IOException {
			log.info("loadTest");
			log.info(ySpecificationID.getIdentifier());
			// "{/Check-in_Patient/Patient/Name/text() = 'john' and /myTaskName/Pet/Name/text() = 'rex'}
			
			// Check-in_Patient
			// Pre: True
			// input: -
			String precornerStr1= "<cornerstone><Patient>patient</Patient></cornerstone>";
			Element preeCornerstone1 = JDOMUtil.stringToElement(precornerStr1);
			String preconcStr1 = "<conclusion><_1><action>suspend</action><target>workitem</target></_1><_2><action>continue</action><target>workitem</target></_2></conclusion>";
			Element preeConclusion1 = JDOMUtil.stringToElement(preconcStr1);
			RdrNode prenode1 = new RdrNode("True", preeConclusion1, preeCornerstone1);
			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemPreconstraint, prenode1, handle);
			
			// Pos: existsEntity(Patient) and existsEntity(Episode)
			// output: patient
			String poscornerStr1= "<cornerstone><Patient>patient</Patient><Episode>episode</Episode></cornerstone>";
			Element poseCornerstone1 = JDOMUtil.stringToElement(poscornerStr1);
			String posconcStr1 = "<conclusion><_1><action>complete</action><target>workitem</target></_1></conclusion>";
			Element poseConclusion1 = JDOMUtil.stringToElement(posconcStr1);
			RdrNode posnode1 = new RdrNode("True", poseConclusion1, poseCornerstone1); // FIXME
			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemConstraintViolation, posnode1, handle);
			
//			// Collect_Data
//			// Pre: existsEntity(Episode)
//			// input: patient
//			String precornerStr2= "<cornerstone><Episode>episode</Episode></cornerstone>";
//			Element preeCornerstone2 = JDOMUtil.stringToElement(precornerStr2);
//			String preconcStr2 = "null";
//			Element preeConclusion2 = JDOMUtil.stringToElement(preconcStr2);
//			RdrNode prenode2 = new RdrNode("True", preeConclusion2, preeCornerstone2);
//			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemPreconstraint, prenode2, handle);
//			
//			// pos: existsAttribute(Patient Data.Height) and existsAttribute(Patient Data.Weight)
//			// output: adminpatientdata
//			String poscornerStr2= "<cornerstone><Patient>patient</Patient><Episode>episode</Epside></cornerstone>";
//			Element poseCornerstone2 = JDOMUtil.stringToElement(poscornerStr2);
//			String posconcStr2 = "<conclusion><_1><action>complete</action><target>workitem</target></_1></conclusion>";
//			Element poseConclusion2 = JDOMUtil.stringToElement(posconcStr2);
//			RdrNode posnode2 = new RdrNode("True", poseConclusion2, poseCornerstone2); // FIXME
//			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemConstraintViolation, posnode2, handle);
//			
//			// Physical_Examination
//			// Pre: existsEntity(Episode)
//			// input: patient
//			String precornerStr3= "<cornerstone><Patient>patient</Patient></cornerstone>";
//			Element preeCornerstone3 = JDOMUtil.stringToElement(precornerStr3);
//			String preconcStr3 = "null";
//			Element preeConclusion3 = JDOMUtil.stringToElement(preconcStr3);
//			RdrNode prenode3 = new RdrNode("True", preeConclusion3, preeCornerstone3);
//			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemPreconstraint, prenode3, handle);
//			
//			// Pos: existsAttribute(Patient Data.Physical Report) and existsAttribute(Patient Data.Physical Examination)
//			// output: nursedata
//			String poscornerStr3= "<cornerstone><Patient>patient</Patient><Episode>episode</Epside></cornerstone>";
//			Element poseCornerstone3 = JDOMUtil.stringToElement(poscornerStr3);
//			String posconcStr3 = "<conclusion><_1><action>complete</action><target>workitem</target></_1></conclusion>";
//			Element poseConclusion3 = JDOMUtil.stringToElement(posconcStr3);
//			RdrNode posnode3 = new RdrNode("True", poseConclusion3, poseCornerstone3); // FIXME
//			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemConstraintViolation, posnode3, handle);		
//			
//			// Doctor_appointment
//			// Pre: existsEntity(Episode) and existsAttribute(Patient Data.Height) and existsAttribute(Patient Data.Weight) and existsAttribute(Patient Data.Physical Report)
//			// input: patient , nurse , admin
//			String precornerStr4= "<cornerstone><Patient>patient</Patient></cornerstone>";
//			Element preeCornerstone4 = JDOMUtil.stringToElement(precornerStr4);
//			String preconcStr4 = "null";
//			Element preeConclusion4 = JDOMUtil.stringToElement(preconcStr4);
//			RdrNode prenode4 = new RdrNode("True", preeConclusion4, preeCornerstone4);
//			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemPreconstraint, prenode4, handle);
//			
//			// Pos: existsEntity(Prescription) and existsEntity(Medical Report) and compareAttributeTo(Medical Report.Closed,=.true) and existsAttribute(Patient Data.Medical Examination)
//			// output: nurse, admin, medical exame, prescription, medical report
//			String poscornerStr4= "<cornerstone><Patient>patient</Patient><Episode>episode</Epside></cornerstone>";
//			Element poseCornerstone4 = JDOMUtil.stringToElement(poscornerStr4);
//			String posconcStr4 = "<conclusion><_1><action>complete</action><target>workitem</target></_1></conclusion>";
//			Element poseConclusion4 = JDOMUtil.stringToElement(posconcStr4);
//			RdrNode posnode4 = new RdrNode("True", poseConclusion4, poseCornerstone4); // FIXME
//			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemConstraintViolation, posnode4, handle);
//			
//			// Check-out_Patient
//			// Pre: existsEntity(Episode)
//			// input: patient
//			String precornerStr5= "<cornerstone><Patient>patient</Patient></cornerstone>";
//			Element preeCornerstone5 = JDOMUtil.stringToElement(precornerStr5);
//			String preconcStr5 = "null";
//			Element preeConclusion5 = JDOMUtil.stringToElement(preconcStr5);
//			RdrNode prenode5 = new RdrNode("True", preeConclusion5, preeCornerstone5);
//			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemPreconstraint, prenode5, handle);
//			
//			// Pos: compareAttributeTo(Episode.Closed,=.true)
//			// output: episode closed
//			String poscornerStr5= "<cornerstone><Patient>patient</Patient><Episode>episode</Epside></cornerstone>";
//			Element poseCornerstone5 = JDOMUtil.stringToElement(poscornerStr5);
//			String posconcStr5 = "<conclusion><_1><action>complete</action><target>workitem</target></_1></conclusion>";
//			Element poseConclusion5 = JDOMUtil.stringToElement(posconcStr5);
//			RdrNode posnode5 = new RdrNode("True", poseConclusion5, poseCornerstone5); // FIXME
//			client.addNode(ySpecificationID, "Check-in_Patient", RuleType.ItemConstraintViolation, posnode5, handle);

			return "Sucesso";
		}
	
}

package pt.ist.socialsoftware.blendedworkflow.engines.bwengine.workletinterface;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.*;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.SpecificationData;
import org.yawlfoundation.yawl.engine.interfce.TaskInformation;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
//import org.yawlfoundation.yawl.engine.interfce.interfaceA.InterfaceA_EnvironmentBasedClient;
import org.yawlfoundation.yawl.engine.interfce.interfaceB.InterfaceBWebsideController;
//import org.yawlfoundation.yawl.engine.interfce.interfaceB.InterfaceB_EnvironmentBasedClient;
import org.yawlfoundation.yawl.util.JDOMUtil;

public class WorkletAdapter extends InterfaceBWebsideController{

	private static WorkletAdapter instance;

	private static Logger log;

	// required data for interfacing with the engine
	protected String engineUser = "blendedWorkflowService";
	protected String enginePassword = "yBW";
	protected String sessionHandle = null;
	protected String engineURI = "http://localhost:8080/yawl/ib";
	protected String bwURI = "http://localhost:8080/blendedWorkflowService/ib";
	//private InterfaceB_EnvironmentBasedClient interfaceBClient; //we need this to do some extra work
	//private InterfaceA_EnvironmentBasedClient interfaceAClient;

	public static WorkletAdapter getInstance() {
		if (instance == null) {
			return new WorkletAdapter();
		}
		return instance;
	}

	//NOTE: filter requests to not send worklet service the same workitem more than once
	public void notifyWorkItemContraintViolation(WorkItem workItem) {
		String caseID = workItem.getBwInstance().getId(); 
		String taskID = workItem.getElementId(); 
		String specURI = workItem.getBwInstance().getBwSpecification().getId();
		String status = workItem.getState().toString();
		String enablementTime = new Date().toString(); //define enablementTime on dml ???

		WorkItemRecord wir = new WorkItemRecord(caseID, taskID, specURI, enablementTime, status);

		String taskInfo = "taskInfo"; //TaskInformation taskInfo = getTaskInformation(wir);
		//TaskInformation taskInfo = new TaskInformation(paramSchema, taskID, specificationID, taskName, taskDocumentation, decompositionID);

		//Verify connection to YAWL
		if (connected()){

			// Create Element and get Root
			Element root = prepareReplyRootElement(wir, taskInfo, sessionHandle);
			Element workItemDataElement = new Element(root.getName());
			Document myDocument = new Document(workItemDataElement);

			// Get Attributes
			for (AttributeInstance attributeInstance : workItem.getAttributeInstance()) {
				workItemDataElement.addContent(new Element(attributeInstance.getId()).addContent(attributeInstance.getValue()));
			}

			// Print XML Element
			printElement(myDocument);

			// Invoke worklet gateway
			process(wir,workItemDataElement,"ConstraintViolation");
		}
	}

	public void notifyWorkItemSkippedWorkItem(WorkItem workItem) {
		// TODO Auto-generated method stub
		//System.out.println("@WorkletAdapter - notifyWorkItemSkippedWorkItem");
	}

	private void printElement(Document document) {
		try {
			XMLOutputter outputter = new XMLOutputter();
			outputter.output(document, System.out);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	// Worklet Stub Methods
	private void process(WorkItemRecord wir, Element workItemDataElement, String string) {
		// TODO Auto-generated method stub
		//System.out.println("@WorkletAdapter - process");
	}

	/*
	 * -------------------------------------------
	 * YAWL METHODS
	 * -------------------------------------------
	 */

	/**
	 * Stripped from DECLARE.
	 * @see http://www.win.tue.nl/declare/
	 *
	 * @param external ExternalWorkItem
	 * @param sessionHandle String
	 * @return Element
	 * @throws IOException
	 */
	protected Element prepareReplyRootElement(WorkItemRecord wir, String taskInfo, String sessionHandle) {
		Element replyToEngineRootDataElement;

		//prepare reply root element.
		/*SpecificationData sdata = getSpecificationData(new YSpecificationID(wir), sessionHandle);

    	String decompID = taskInfo.getDecompositionID();
    	if (sdata.usesSimpleRootData()) {
    		replyToEngineRootDataElement = new Element("data");
    	}
    	else {
    		replyToEngineRootDataElement = new Element(decompID);
    	}*/
		// Stub
		replyToEngineRootDataElement = new Element("BW-WORKLET");
		return replyToEngineRootDataElement;
	}

	// Needed to extend InterfaceBWebsideController
	public void handleEnabledWorkItemEvent(WorkItemRecord wir) { }
	public void handleCancelledWorkItemEvent(WorkItemRecord workItemRecord) { }

	public TaskInformation getTaskInformation(WorkItemRecord workItemRecord) {
		try {
			return super.getTaskInformation(new YSpecificationID(workItemRecord), workItemRecord.getTaskID(), this.sessionHandle);
		} catch(IOException e) {
			log.error("Could not get task information for task " + workItemRecord.getIDForDisplay(), e);
			return null;
		}
	}

	/** Checks if there is a connection to the engine, and
	 *  if there isn't, attempts to connect
	 *  @return true if connected to the engine
	 */
	protected boolean connected() {
		//        try {
		//            // if not connected
		//             if ((this.sessionHandle == null) || (!checkConnection(this.sessionHandle)))
		//                this.sessionHandle = connect(this.engineUser, this.enginePassword);
		//        }
		//        catch (IOException ioe) {
		//             log.error("Exception attempting to connect to engine", ioe);
		//        }
		//        if (!successful(this.sessionHandle)) {
		//            log.error(JDOMUtil.strip(this.sessionHandle));
		//        }
		//        return (successful(this.sessionHandle)) ;
		// Stub
		return true;
	}

}

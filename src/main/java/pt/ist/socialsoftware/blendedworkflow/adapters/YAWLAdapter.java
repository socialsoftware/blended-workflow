package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;

import org.yawlfoundation.yawl.authentication.YExternalClient;
import org.yawlfoundation.yawl.elements.YAWLServiceReference;
import org.yawlfoundation.yawl.elements.data.YParameter;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.SpecificationData;
import org.yawlfoundation.yawl.engine.interfce.TaskInformation;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.engine.interfce.YParametersSchema;
import org.yawlfoundation.yawl.engine.interfce.interfaceA.InterfaceA_EnvironmentBasedClient;
import org.yawlfoundation.yawl.engine.interfce.interfaceB.InterfaceBWebsideController;
import org.yawlfoundation.yawl.engine.interfce.interfaceB.InterfaceB_EnvironmentBasedClient;
import org.yawlfoundation.yawl.exceptions.YAWLException;
import org.yawlfoundation.yawl.logging.YLogDataItem;
import org.yawlfoundation.yawl.logging.YLogDataItemList;
import org.yawlfoundation.yawl.util.JDOMUtil;

import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.SpecUtils;
import pt.ist.socialsoftware.blendedworkflow.adapters.convertor.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;

public class YAWLAdapter extends InterfaceBWebsideController {

	// TODO: Config file
	protected String engineAdminUser = "admin";
	protected String engineAdminPassword = "YAWL";
	protected String engineUser = "bwService";
	protected String enginePassword = "yBwService";
	protected String engineDoco = "The Blended Workflow Service.";

	protected String sessionHandle = null;
	protected String engineIaURI = "http://localhost:8080/yawl/ia";
	protected String engineIbURI = "http://localhost:8080/yawl/ib";
	protected String bwURI = "http://localhost:8081/blendedWorkflowService/ib";

	private static Logger log;

	private InterfaceB_EnvironmentBasedClient interfaceBClient;
	private InterfaceA_EnvironmentBasedClient interfaceAClient;

	private ArrayList<YSpecificationID> loadedActivitySpecs = new ArrayList<YSpecificationID>(); // spec UUID
//	private ArrayList<YSpecificationID> loadedGoalSpecs = new ArrayList<YSpecificationID>();
//	private HashMap<WorkItemRecord, WorkItemRecord> activeWorkItems = new HashMap<WorkItemRecord, WorkItemRecord>(); //key = old wir (before checkout), value = new wir (after checkout)
	private ArrayList<WorkItemRecord> enabledWorkItems = new ArrayList<WorkItemRecord>();

	public YAWLAdapter() throws BlendedWorkflowException{
		super();
		log = Logger.getLogger("bwServicelogger");
		
		this.interfaceBClient = new InterfaceB_EnvironmentBasedClient(this.engineIbURI);
		this.interfaceAClient = new InterfaceA_EnvironmentBasedClient(this.engineIaURI);
		super.setUpInterfaceBClient(this.engineIbURI);
		
		connectYAWL();
	}
	
	// YAWL Connection
	public void connectYAWL() throws BlendedWorkflowException {
		addBWClientAccount();
		addBWService();
	}

	public void disconnectYAWL() throws BlendedWorkflowException {
		for (YExternalClient client : getClientAccounts()) {
			if (client.getUserName().equals(engineUser)) {
				removeBWClientAccount();
			}
		}

		for (YAWLServiceReference service : getRegisteredServices()) {
			if (service.getURI().equals(bwURI)) {
				removeBWService();
			}
		}
	}

	public boolean connected() {
		try {
			if ((this.sessionHandle == null) || (!checkConnection(this.sessionHandle)))
				this.sessionHandle = connect(this.engineAdminUser, this.engineAdminPassword);
		}
		catch (IOException ioe) {
			log.error("Exception attempting to connect to engine", ioe);
		}
		if (!successful(this.sessionHandle)) {
			log.error(JDOMUtil.strip(this.sessionHandle));
		}

		return (successful(this.sessionHandle)) ;
	}

	// BW Service
	public void addBWService() throws BlendedWorkflowException {
		try {
			if (connected()) {
				YAWLServiceReference service = new YAWLServiceReference(bwURI,null,engineUser,enginePassword,engineDoco);
				this.interfaceAClient.addYAWLService(service, sessionHandle);
			}
		}
		catch (IOException ioe) {
			log.error("addRegisteredService", ioe);
			throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
		}
	}

	public void removeBWService() throws BlendedWorkflowException {
		try {
			if (connected()) {
				this.interfaceAClient.removeYAWLService(bwURI, sessionHandle);
			}
		}
		catch (IOException ioe) {
			log.error("removeRegisteredService()", ioe);
			throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
		}
	}

	public Set<YAWLServiceReference> getRegisteredServices() {
		if (connected())
			return this.interfaceAClient.getRegisteredYAWLServices(sessionHandle);

		return null;
	}

	// BW Client Application
	public void addBWClientAccount() throws BlendedWorkflowException {
		try {
			this.interfaceAClient.addClientAccount(engineUser, enginePassword, engineDoco, connect(this.engineAdminUser, this.engineAdminPassword));
		}
		catch (IOException ioe) {
			log.error("addClientAccount", ioe);
			throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
		}
	}

	public void removeBWClientAccount() throws BlendedWorkflowException {
		try {
			if (connected())
				this.interfaceAClient.removeClientAccount(engineUser, sessionHandle);
		}
		catch (IOException ioe) {
			log.error("removeClientAccount", ioe);
			throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
		}
	}

	public Set<YExternalClient> getClientAccounts() {
		if (connected())
			try {
				return this.interfaceAClient.getClientAccounts(sessionHandle);
			} catch (IOException e) {
				log.debug("getClientAccounts = null");
				return null;
			}
		return null;
	}

	// Specifications
	public void loadSpecification(String spec) throws BlendedWorkflowException {
		YSpecificationID ySpecificationID = SpecUtils.getYAWLSpecificationIDFromSpec(spec);

		// Check if specification is already loaded
		for (SpecificationData specificationData : getLoadedSpecs()) {
			if(specificationData.getID().equals(ySpecificationID)) {
				log.info("Specification already loaded. Not loading again.");
				throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
			}
		}
		
		// Load specification in YAWL
		try {	
			if(connected()) {
				String result = interfaceAClient.uploadSpecification(spec, this.sessionHandle);
				if(successful(result)) {
					log.info("Specification " + ySpecificationID.getIdentifier() + " correctly uploaded to YAWL");
					this.loadedActivitySpecs.add(ySpecificationID);
				}
				else {
					log.error("Specification " + ySpecificationID.getIdentifier() + " was not correctly uploaded.");
					throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
				}
			} 		
		} catch(IOException ioe) {
			log.error("IOException: Specification " + ySpecificationID.getIdentifier() + " was not correctly uploaded");
			throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
		}
	}

	// TODO:
	public void unloadSpecification(String specID) throws BlendedWorkflowException {
//		if(specID == null) {
//			log.error("Cannot unload the specification. The specification is null");
//			throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
//		}
//
//		try {
//			// get the specification ID
//			YSpecificationID ySpecID = null;
//			boolean found = false; 
//
//			for (YSpecificationID yspecidit : this.loadedActivitySpecs) {
//				if(yspecidit.getIdentifier().equals(specID)) {
//					ySpecID = yspecidit;
//					found = true;
//					break;
//				}
//			}
//
//			if(!found) {
//				log.error("Could not find the given spec ID.");
//				throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
//			}
//
//			if(connected()) {
//				String result = this.interfaceAClient.unloadSpecification(ySpecID, this.sessionHandle);
//				if(successful(result)) {
//					this.loadedActivitySpecs.remove(ySpecID);
//				} else {
//					// TODO: Verify code
//					// if does not succeed, it may mean there are active cases
//					// get the case id from the work items
//					Collection<WorkItemRecord> workItemRecordCollection = this.activeWorkItems.values();
//					ArrayList<WorkItemRecord> workItemRecordList = new ArrayList<WorkItemRecord>(workItemRecordCollection);
//					String caseID = null;
//					for (WorkItemRecord workItemRecord : workItemRecordList) {
//						if(workItemRecord.getSpecIdentifier().equals(specID)) {
//							caseID = workItemRecord.getCaseID();
//							// cancel the case
//							this.interfaceBClient.cancelCase(caseID, this.sessionHandle); //FIXME brute force: I don't know if it worked.
//						}
//					}
//
//					// remove all the active work items from this case
//					workItemRecordCollection = this.activeWorkItems.keySet();
//					workItemRecordList = new ArrayList<WorkItemRecord>(workItemRecordCollection);
//
//					for (WorkItemRecord workItemRecord : workItemRecordList) {
//						if(workItemRecord.getSpecIdentifier().equals(specID)) {
//							this.activeWorkItems.remove(workItemRecord);
//						}
//					}
//					// unload the specification
//					this.interfaceAClient.unloadSpecification(ySpecID, this.sessionHandle); //FIXME brute force: I don't know if it worked.
//				}
//			} else {
//				log.error("Could not connect to YAWL engine");
//				throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
//			}
//		} catch (IOException e) {
//			log.error("Could not reach the engine", e);
//			throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
//		}
//		throw new BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
	}

	public List<SpecificationData> getLoadedSpecs() {
		if(connected()) {
			try {
				return this.interfaceBClient.getSpecificationList(this.sessionHandle);
			} catch(IOException ioe) {
				log.error("IOException: Could not retreive specification list");
				return null;
			}
		}
		return null;
	}

	// TODO:
	public Element getSpecDataSchema(YSpecificationID specID) {
		try {
			String dataSchema = this.interfaceBClient.getSpecificationDataSchema(specID, this.sessionHandle);
			Document doc = StringUtils.stringToDoc(dataSchema);
			return doc.getRootElement();
		} catch (IOException e) {
			log.error("Could not retreive the data schema");
		}
		return null;
	}

	// Cases
	public String launchCase(String caseID) {
		YSpecificationID ySpecificationID = null;

		// get corresponding YSpecificationID
		for (YSpecificationID ySpecID : this.loadedActivitySpecs) {
			if(ySpecID.getIdentifier().equals(caseID)) {
				ySpecificationID = ySpecID;
				break;
			}
		}
		if(ySpecificationID == null) {
			log.error("The given caseId does not exist in the system.");
			return null;
		}

		// get the loaded case with the given caseID (YSpecificationData)
		SpecificationData specData = null;
		for (SpecificationData specificationData : getLoadedSpecs()) {
			if(specificationData.getID().equals(ySpecificationID)) {
				specData = specificationData;
				break;
			}
		}
		if(specData == null) {
			log.error("The given caseId is not loaded in the engine.");
			return null;
		}

		try {
			// get the case data
			String caseData = null; //FIXME: I have to get this but don't know from here

			// build the case logger
			YLogDataItem logData = new YLogDataItem("service", "name", "blendedWorkflowService", "string");
			YLogDataItemList logDataList = new YLogDataItemList(logData);

			if(connected()) {
				String result = this.interfaceBClient.launchCase(ySpecificationID, caseData, logDataList, this.sessionHandle);
				if(successful(result)) {
					log.info("YAWL specification successfully launched.");
					return result;
				} else {
					log.error("Could not launch the YAWL specification.");
					return null;
				}
			}
		} catch (IOException e) {
			log.error("Could not contact the engine.");
			return null;
		}
		return null;
	}

	// TODO:
	public void cancelCase(String caseID){
		// TODO: Refactor load
		try {
			this.interfaceBClient.cancelCase(caseID, sessionHandle);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// YAWL InterfaceB Methods 
	// TODO:
	@Override
	public void handleEnabledWorkItemEvent(WorkItemRecord enabledWorkItem) {
//		this.enabledWorkItems.add(enabledWorkItem);
//
//		log.info("WorkitemID: " + enabledWorkItem.getID());
//
//		// checkOut
//		try {
//			if (! connected()) sessionHandle = connect(engineUser, enginePassword);
//
//			enabledWorkItem = checkOut(enabledWorkItem.getID(), sessionHandle);
//
//			//	        Document document = new Document();
//			//	        document.setContent(enabledWorkItem.getDataList());
//
//
//			getInputDataFromWorkItem(enabledWorkItem, getTaskInformation(enabledWorkItem));
//			getOutputDataFromWorkItem(enabledWorkItem, getTaskInformation(enabledWorkItem));
//			//	        try {
//			//	            FileWriter writer = new FileWriter("C:/Users/User/Desktop/vw.xml");
//			//	            XMLOutputter outputter = new XMLOutputter();
//			//
//			//	            outputter.setFormat(Format.getPrettyFormat());
//			//
//			//	            outputter.output(document, writer);
//			//	            outputter.output(document, System.out);
//			//	        } catch (IOException e) {
//			//	            e.printStackTrace();
//			//	        }
//
//
//			//			checkInWorkItem(wir.getID(), wir.getDataList(),getOutputData(wir.getTaskID(), result), null,  sessionHandle);
//			//			log.info(wir.toString());
//		}
//		catch (Exception ioe) {
//			log.error(ioe.toString());
//		}
	}


	@Override
	public void handleCancelledWorkItemEvent(WorkItemRecord arg0) {}

	@Override
	public void handleCompleteCaseEvent(String caseID, String casedata) {}

//	// Workitems
//	public WorkItemRecord checkOutWorkItem(WorkItemRecord wir) {
//		if(!this.enabledWorkItems.contains(wir)) {
//			log.error("Unknown work item.");
//			return null;
//		}
//
//		if(connected()) {
//			log.info("Connection to engine is active");
//			log.info("Checking out task " + wir.getTaskID());
//
//			// do the checkout
//			WorkItemRecord checkedOutItem = null;
//			try {
//				if ((checkedOutItem = checkOut(wir.getID(), this.sessionHandle)) != null) {
//					log.info("   checkout successful: " + wir.getID());
//					this.enabledWorkItems.remove(wir);
//					this.activeWorkItems.put(wir, checkedOutItem);
//					return checkedOutItem;
//				}
//				else {
//					log.info("   checkout unsuccessful: " + wir.getID());
//					return null;
//				}
//			}
//			catch (YAWLException ye) {
//				log.error("YAWL Exception with checkout: " + wir.getID(), ye);
//				return null;
//			}
//			catch (IOException ioe) {
//				log.error("IO Exception with checkout: " + wir.getID(), ioe);
//				return null;
//			}
//		}
//		return null;
//	}
//
//
//	public boolean checkInWorkItem(WorkItemRecord wir) {//, TaskInfo taskInfo) {
//		try {
//			if (connected()) {
//				TaskInformation taskInformation = getTaskInformation(wir);
//				Element task = this.prepareReplyRootElement(wir, taskInformation, sessionHandle);
//
//				Element outputData = new Element("");//this.outputData(taskInfo, task);
//				Element inputData = new Element("");//this.inputData(taskInfo, task);
//
//				WorkItemRecord activatedWir = this.activeWorkItems.get(wir);
//
//				if(activatedWir == null) {
//					Collection<WorkItemRecord> activeWirs = this.activeWorkItems.values();
//					ArrayList<WorkItemRecord> wirArr = new ArrayList<WorkItemRecord>(activeWirs);
//					for (WorkItemRecord workItemRecord : wirArr) {
//						if(workItemRecord.getIDForDisplay().equals(wir.getIDForDisplay())) {
//							activatedWir = workItemRecord;
//							break;
//						}
//					}
//				}
//
//				if(activatedWir == null) {
//					log.error("Could not find the given work item record");
//					return false;
//				}
//
//				String result = checkInWorkItem(activatedWir.getID(), inputData, outputData, null, sessionHandle); 
//				WorkItemRecord wirKey = null;
//				if (successful(result)){
//					log.info("Checked in " + wir.getIDForDisplay()); // log this event
//
//					// if it is the value, get the key
//
//					if(wir.equals(activatedWir)) {
//						for ( Map.Entry<WorkItemRecord, WorkItemRecord> entry : this.activeWorkItems.entrySet()) {
//							if(entry.getValue().getIDForDisplay().equals(activatedWir.getIDForDisplay())) {
//								wirKey = entry.getKey();
//								break;
//							}
//						}
//					}
//					if(wirKey == null) {
//						this.activeWorkItems.remove(activatedWir);
//					} else {
//						this.activeWorkItems.remove(wirKey);
//					}
//				} else {
//					log.error("Failed to check in " + wir.getIDForDisplay() + ": " + result); // log this event
//					return false;
//				}
//				if(wirKey == null) {
//					notifyActiveTasks(wir.getCaseID());
//				} else {
//					notifyActiveTasks(wirKey.getCaseID());
//				}
//
//				return true;
//			}
//		}
//		catch (Exception e) {
//			log.error(e);
//		}
//		return false;
//	}
//
//
//	public void skipWorkItem(WorkItemRecord wir) {
//		if(connected()) {
//			try {
//				String result = this.interfaceBClient.skipWorkItem(wir.getID(), this.sessionHandle);
//				if(successful(result)) {
//					log.info(result);
//				} else {
//					log.error(result);
//				}
//			} catch (IOException ioe) {
//				log.error("Could not skip work item", ioe);
//			}
//		}
//
//		notifyActiveTasks(wir.getCaseID());
//	}
//
//
//	// Aux
	public void notifyActiveTasks(String caseInstanceID) {
		List<WorkItemRecord> liveWorkItems = getLiveWorkItems(caseInstanceID);

		if(liveWorkItems != null) {
			for (WorkItemRecord workItemRecord : liveWorkItems) {
				if(!this.enabledWorkItems.contains(workItemRecord)) {
					log.info("importante: " + workItemRecord.getTaskName() + "«»" + workItemRecord.getTaskID());
					handleEnabledWorkItemEvent(workItemRecord);
				}
			}
		}
	}


	protected List<WorkItemRecord> getLiveWorkItems(String caseInstanceID) {
		try {
			return this.interfaceBClient.getWorkItemsForCase(caseInstanceID, this.sessionHandle);
		} catch (IOException e) {
			log.error("Could not get the live work items");
		}
		return null;
	}
//
//
//	public TaskInformation getTaskInformation(WorkItemRecord workItemRecord) {
//		try {
//			return super.getTaskInformation(new YSpecificationID(workItemRecord), workItemRecord.getTaskID(), this.sessionHandle);
//		} catch(IOException e) {
//			log.error("Could not get task information for task " + workItemRecord.getIDForDisplay(), e);
//			return null;
//		}
//	}
//
//
//	private void getInputDataFromWorkItem(WorkItemRecord wir, TaskInformation wirInfo) { //, DataModelURI dataModelURI, DataModelInstanceID dataModelID, TaskInfo taskInfo) {
//		//FIXME what if it is complex data?
//		Element inputData = wir.getDataList();
//		YParametersSchema params;
//		
//		params = wirInfo.getParamSchema();
//		List<YParameter> _input = params.getInputParams();
//		YParameter parameter = null;
//		for (int i = 0; i < _input.size(); i++) {
//			parameter = (YParameter) _input.get(i);
//
//			String name = parameter.getName(); // name
//
//			String value = parameter.getInitialValue(); // value
//			if (inputData != null) {
//				Element inputChild = (Element) inputData.getChild(name); 
//				if(inputChild.getChildren().size() > 0) {
//					// complex type
//					for (Object child : inputChild.getChildren()) {
//						Element childEl = (Element) child;
//
//						log.info("INPUTDATA:" + name + " * " + value + " * " + childEl.getText());
//						//		    			taskInfo.addInputData(name, childEl.getName(), dataModelURI, 
//						//		    					dataModelID, (childEl.getText().equals("true") || 
//						//		    							childEl.getText().equals("false") ? "BOOLEAN" : "STRING"), null, childEl.getText());
//					}
//				} else {
//					//simple type
//					String type = parameter.getDataTypeName(); // type
//					value = inputChild.getText();
//					log.info("INPUTDATA:" + name + " * " + value + " * " + type);
//
//					//		    		taskInfo.addInputData(null, name, dataModelURI, dataModelID, type, null, value);
//				}
//			}
//		}
//	}
//
//
//	private void getOutputDataFromWorkItem(WorkItemRecord wir, TaskInformation wirInfo 
//			/*,DataModelURI dataModelURI, DataModelInstanceID dataModelID, TaskInfo taskInfo*/) { 
//		YParametersSchema params;
//		if (wirInfo == null) {
//			return;
//		}
//		params = wirInfo.getParamSchema();
//		List<YParameter> _output = params.getOutputParams();
//		YParameter parameter = null;
//
//		log.info("OUtsize:"+_output.size());
//
//		//YSpecificationID specID = new YSpecificationID(wir);
//		//Element specSchema = ActivityEventRouter.getInstance().getSpecDataSchema(specID);
//
//		for (int i = 0; i < _output.size(); i++) {
//			parameter = (YParameter) _output.get(i);
//			String value = parameter.getInitialValue();
//			log.info("out2:"+ parameter);
//			// addOutputDataToTaskInfo(taskInfo, parameter, specSchema, dataModelURI, dataModelID, value);
//		}
//	}
//
//	protected Element prepareReplyRootElement(WorkItemRecord wir, TaskInformation taskInfo, String sessionHandle) throws IOException {
//		Element replyToEngineRootDataElement;
//
//		//prepare reply root element.
//		SpecificationData sdata = getSpecificationData(new YSpecificationID(wir), sessionHandle);
//
//		String decompID = taskInfo.getDecompositionID();
//		if (sdata.usesSimpleRootData()) {
//			replyToEngineRootDataElement = new Element("data");
//		}
//		else {
//			replyToEngineRootDataElement = new Element(decompID);
//		}
//		return replyToEngineRootDataElement;
//	}
//
//	private Element inputData(TaskInfo taskInfo, Element root) {
//		Element input = new Element(root.getName());
//		for (GroupDataInfo inputGroup : taskInfo.getInputData()) {
//			Element dataGroup = null;
//			if(inputGroup.getGroupName().equals("")) {
//				for (DataInfo data : inputGroup.getFields()) {
//					Element dataEl = new Element(data.getDataNameURI().getAttributeName());
//					dataEl.setText(data.getValue());
//					input.addContent(dataEl);
//				}
//			} else {
//				dataGroup = new Element(inputGroup.getGroupName());
//				for (DataInfo data : inputGroup.getFields()) {
//					Element dataEl = new Element(data.getDataNameURI().getAttributeName());
//					dataEl.setText(data.getValue());
//					dataGroup.addContent(dataEl);
//				}
//				input.addContent(dataGroup);
//			}
//		}
//		return input;
//	}
//
//	private Element outputData(TaskInfo taskInfo, Element root) {
//		Element output = new Element(root.getName());
//		for (GroupDataInfo outputGroup : taskInfo.getOutputData()) {
//			Element dataGroup = null;
//			if(outputGroup.getGroupName().equals("")) {
//				for (DataInfo data : outputGroup.getFields()) {
//					Element dataEl = new Element(data.getDataNameURI().getAttributeName());
//					dataEl.setText(data.getValue());
//					output.addContent(dataEl);
//				}
//			} else {
//				dataGroup = new Element(outputGroup.getGroupName());
//				for (DataInfo data : outputGroup.getFields()) {
//					Element dataEl = new Element(data.getDataNameURI().getAttributeName());
//					dataEl.setText(data.getValue());
//					dataGroup.addContent(dataEl);
//				}
//				output.addContent(dataGroup);
//			}
//
//		}
//		return output;
//	}

}

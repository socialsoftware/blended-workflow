package pt.ist.socialsoftware.blendedworkflow.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yawlfoundation.yawl.authentication.YExternalClient;
import org.yawlfoundation.yawl.elements.YAWLServiceReference;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.engine.interfce.SpecificationData;
import org.yawlfoundation.yawl.engine.interfce.WorkItemRecord;
import org.yawlfoundation.yawl.engine.interfce.interfaceA.InterfaceA_EnvironmentBasedClient;
import org.yawlfoundation.yawl.engine.interfce.interfaceB.InterfaceBWebsideController;
import org.yawlfoundation.yawl.engine.interfce.interfaceB.InterfaceB_EnvironmentBasedClient;
import org.yawlfoundation.yawl.logging.YLogDataItem;
import org.yawlfoundation.yawl.logging.YLogDataItemList;
import org.yawlfoundation.yawl.util.JDOMUtil;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.shared.BWPropertiesManager;
import pt.ist.socialsoftware.blendedworkflow.shared.SpecUtils;

public class YAWLAdapter extends InterfaceBWebsideController {

    protected String engineAdminUser = BWPropertiesManager
            .getProperty("yawl.AdminUser");
    protected String engineAdminPassword = BWPropertiesManager
            .getProperty("yawl.AdminPassword");
    protected String engineUser = BWPropertiesManager
            .getProperty("yawl.BWUser");
    protected String enginePassword = BWPropertiesManager
            .getProperty("yawl.BWPassword");
    protected String engineDoco = BWPropertiesManager
            .getProperty("yawl.BWDoco");

    protected String sessionHandle = null;
    protected String engineIaURI = BWPropertiesManager
            .getProperty("yawl.IaURI");
    protected String engineIbURI = BWPropertiesManager
            .getProperty("yawl.IbURI");
    protected String bwURI = BWPropertiesManager.getProperty("yawl.BWURI");

    private static Logger log = LoggerFactory.getLogger("YAWLAdapter");

    private final InterfaceB_EnvironmentBasedClient interfaceBClient;
    private final InterfaceA_EnvironmentBasedClient interfaceAClient;

    private final ArrayList<YSpecificationID> loadedActivitySpecs = new ArrayList<YSpecificationID>();

    public YAWLAdapter() throws BWException {
        super();
        this.interfaceBClient = new InterfaceB_EnvironmentBasedClient(
                this.engineIbURI);
        this.interfaceAClient = new InterfaceA_EnvironmentBasedClient(
                this.engineIaURI);
        super.setUpInterfaceBClient(this.engineIbURI);

        connectYAWL();
    }

    /***************************
     * YAWL Registration
     ***************************/
    public boolean connected() {
        try {
            if ((this.sessionHandle == null)
                    || (!checkConnection(this.sessionHandle)))
                this.sessionHandle = connect(this.engineAdminUser,
                        this.engineAdminPassword);
        } catch (IOException ioe) {
            log.error("Exception attempting to connect to engine", ioe);
        }
        if (!successful(this.sessionHandle)) {
            log.error(JDOMUtil.strip(this.sessionHandle));
        }

        return (successful(this.sessionHandle));
    }

    public void connectYAWL() throws BWException {
        log.info("Register BWService in YAWL.");
        addBWClientAccount();
        addBWService();
    }

    public void disconnectYAWL() throws BWException {
        log.info("Remove BWService from YAWL");
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

    public void addBWService() throws BWException {
        try {
            if (connected()) {

                Boolean registered = false;
                for (YAWLServiceReference yawlServiceReference : this.interfaceAClient
                        .getRegisteredYAWLServices(sessionHandle)) {
                    if (yawlServiceReference.getServiceName()
                            .equals(engineUser)) {
                        registered = true;
                    }
                }
                if (!registered) {
                    YAWLServiceReference service = new YAWLServiceReference(
                            bwURI, null, engineUser, enginePassword,
                            engineDoco);
                    this.interfaceAClient.addYAWLService(service,
                            sessionHandle);
                }
            }
        } catch (IOException ioe) {
            log.error("addRegisteredService", ioe);
            throw new BWException(BWErrorType.YAWL_REGISTER_SERVICE);
        }
    }

    public void removeBWService() throws BWException {
        try {
            if (connected()) {
                this.interfaceAClient.removeYAWLService(bwURI, sessionHandle);
            }
        } catch (IOException ioe) {
            log.error("removeRegisteredService()", ioe);
            throw new BWException(BWErrorType.YAWL_REMOVE_SERVICE);
        }
    }

    public Set<YAWLServiceReference> getRegisteredServices() {
        if (connected())
            return this.interfaceAClient
                    .getRegisteredYAWLServices(sessionHandle);

        return null;
    }

    public void addBWClientAccount() throws BWException {
        try {
            Boolean registered = false;
            for (YExternalClient yExternalClient : this.interfaceAClient
                    .getClientAccounts(connect(this.engineAdminUser,
                            this.engineAdminPassword))) {
                if (yExternalClient.getUserName().equals(engineUser)) {
                    registered = true;
                }
            }
            if (!registered) {
                this.interfaceAClient.addClientAccount(engineUser,
                        enginePassword, engineDoco,
                        connect(this.engineAdminUser,
                                this.engineAdminPassword));
            }
        } catch (IOException ioe) {
            log.error("addClientAccount", ioe);
            throw new BWException(BWErrorType.YAWL_REGISTER_CLIENT);
        }
    }

    public void removeBWClientAccount() throws BWException {
        try {
            if (connected())
                this.interfaceAClient.removeClientAccount(engineUser,
                        sessionHandle);
        } catch (IOException ioe) {
            log.error("removeClientAccount", ioe);
            throw new BWException(BWErrorType.YAWL_REMOVE_CLIENT);
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

    /***************************
     * YAWL Specifications
     ***************************/
    public void loadSpecification(String spec) throws BWException {
        YSpecificationID ySpecificationID = SpecUtils
                .getYAWLSpecificationIDFromSpec(spec);

        // Check if specification is already loaded
        for (SpecificationData specificationData : getLoadedSpecs()) {
            if (specificationData.getID().equals(ySpecificationID)) {
                log.info("Specification already loaded. Not loading again.");
                throw new BWException(BWErrorType.YAWL_LOAD_SPECIFICATION,
                        "Specification already loaded. Not loading again.");
            }
        }

        // Load specification in YAWL
        try {
            if (connected()) {
                String result = interfaceAClient.uploadSpecification(spec,
                        this.sessionHandle);
                if (successful(result)) {
                    log.info("Specification " + ySpecificationID.getIdentifier()
                            + " correctly uploaded to YAWL");
                    this.loadedActivitySpecs.add(ySpecificationID);
                } else {
                    log.error(
                            "Specification " + ySpecificationID.getIdentifier()
                                    + " was not correctly uploaded.");
                    throw new BWException(BWErrorType.YAWL_LOAD_SPECIFICATION,
                            "Sucess False");
                }
            }
        } catch (IOException ioe) {
            log.error("IOException: Specification "
                    + ySpecificationID.getIdentifier()
                    + " was not correctly uploaded");
            throw new BWException(BWErrorType.YAWL_LOAD_SPECIFICATION,
                    "IOException");
        }
    }

    // TODO:FutureImplementation: UnloadSpecification
    public void unloadSpecification(String specID) throws BWException {
        // if(specID == null) {
        // log.error("Cannot unload the specification. The specification is
        // null");
        // throw new
        // BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
        // }
        //
        // try {
        // // Get the specification ID
        // YSpecificationID ySpecID = null;
        // boolean found = false;
        //
        // for (YSpecificationID yspecidit : this.loadedActivitySpecs) {
        // if(yspecidit.getIdentifier().equals(specID)) {
        // ySpecID = yspecidit;
        // found = true;
        // break;
        // }
        // }
        //
        // if(!found) {
        // log.error("Could not find the given spec ID.");
        // throw new
        // BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
        // }
        //
        // if(connected()) {
        // String result = this.interfaceAClient.unloadSpecification(ySpecID,
        // this.sessionHandle);
        // if(successful(result)) {
        // this.loadedActivitySpecs.remove(ySpecID);
        // } else {
        // // TODO:BERNADO Verify code
        // // If does not succeed, it may mean there are active cases
        // // Get the case id from the work items
        // Collection<WorkItemRecord> workItemRecordCollection =
        // this.activeWorkItems.values();
        // ArrayList<WorkItemRecord> workItemRecordList = new
        // ArrayList<WorkItemRecord>(workItemRecordCollection);
        // String caseID = null;
        // for (WorkItemRecord workItemRecord : workItemRecordList) {
        // if(workItemRecord.getSpecIdentifier().equals(specID)) {
        // caseID = workItemRecord.getCaseID();
        // // cancel the case
        // this.interfaceBClient.cancelCase(caseID, this.sessionHandle);
        // //FIXME:BERNADO brute force: I don't know if it worked.
        // }
        // }
        //
        // // Remove all the active work items from this case
        // workItemRecordCollection = this.activeWorkItems.keySet();
        // workItemRecordList = new
        // ArrayList<WorkItemRecord>(workItemRecordCollection);
        //
        // for (WorkItemRecord workItemRecord : workItemRecordList) {
        // if(workItemRecord.getSpecIdentifier().equals(specID)) {
        // this.activeWorkItems.remove(workItemRecord);
        // }
        // }
        // // Unload the specification
        // this.interfaceAClient.unloadSpecification(ySpecID,
        // this.sessionHandle); //FIXME:BERNADO brute force: I don't know if it
        // worked.
        // }
        // } else {
        // log.error("Could not connect to YAWL engine");
        // throw new
        // BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
        // }
        // } catch (IOException e) {
        // log.error("Could not reach the engine", e);
        // throw new
        // BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
        // }
        // throw new
        // BlendedWorkflowException(BlendedWorkflowError.YAWL_ADAPTER);
    }

    public List<SpecificationData> getLoadedSpecs() {
        if (connected()) {
            try {
                return this.interfaceBClient
                        .getSpecificationList(this.sessionHandle);
            } catch (IOException ioe) {
                log.error("IOException: Could not retreive specification list");
                return null;
            }
        }
        return null;
    }

    public ArrayList<YSpecificationID> getLoadedActivitySpecs() {
        return loadedActivitySpecs;
    }

    /***************************
     * YAWL Cases
     ***************************/
    public String launchCase(String caseID) {
        YSpecificationID ySpecificationID = null;

        // Get corresponding YSpecificationID
        for (YSpecificationID ySpecID : this.loadedActivitySpecs) {
            if (ySpecID.getIdentifier().equals(caseID)) {
                ySpecificationID = ySpecID;
                break;
            }
        }
        if (ySpecificationID == null) {
            log.error("The given caseId does not exist in the system.");
            return null;
        }

        // Get the loaded case with the given caseID (YSpecificationData)
        SpecificationData specData = null;
        for (SpecificationData specificationData : getLoadedSpecs()) {
            if (specificationData.getID().equals(ySpecificationID)) {
                specData = specificationData;
                break;
            }
        }
        if (specData == null) {
            log.error("The given caseId is not loaded in the engine.");
            return null;
        }

        try {
            // Get the case data
            String caseData = null; // FIXME:BERNADO I have to get this but
                                    // don't know from here

            // Build the case logger
            YLogDataItem logData = new YLogDataItem("service", "name",
                    "blendedWorkflowService", "string");
            YLogDataItemList logDataList = new YLogDataItemList(logData);

            if (connected()) {
                String result = this.interfaceBClient.launchCase(
                        ySpecificationID, caseData, logDataList,
                        this.sessionHandle);
                if (successful(result)) {
                    log.info("YAWL specification successfully launched."
                            + result);
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

    // TODO:FutureImplementation: CancelCase
    public void cancelCase(String caseID) {
        // try {
        // this.interfaceBClient.cancelCase(caseID, sessionHandle);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    /***************************
     * YAWL InterfaceB Methods
     ***************************/
    @Override
    public void handleEnabledWorkItemEvent(WorkItemRecord enabledWorkItem) {
    }

    @Override
    public void handleCancelledWorkItemEvent(WorkItemRecord wir) {
    }

    @Override
    public void handleCompleteCaseEvent(String caseID, String casedata) {
    }
}

package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.text.DateFormat;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException;
import pt.ist.socialsoftware.blendedworkflow.engines.exception.BlendedWorkflowException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.organizationalmanager.OrganizationalManager;
import pt.ist.socialsoftware.blendedworkflow.shared.BWExecutorService;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

public class BlendedWorkflow extends BlendedWorkflow_Base {
    DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT);
    private YAWLAdapter yawlAdapter = null;
    private WorkletAdapter workletAdapter = null;
    private WorkListManager workListManager = null;
    private BWManager bwManager = null;
    private OrganizationalManager organizationalManager = null;
    private String today = dateFormatter.format(new java.util.Date());

    private BWExecutorService bwExecutorService = null;

    public static BlendedWorkflow getInstance() {
        if (FenixFramework.getDomainRoot().getBlendedWorkflow() == null) {
            new BlendedWorkflow();
        }

        return FenixFramework.getDomainRoot().getBlendedWorkflow();
    }

    public BlendedWorkflow() {
        FenixFramework.getDomainRoot().setBlendedWorkflow(this);
    }

    public BWSpecification getBWSpecification(String name)
            throws BlendedWorkflowException {
        for (BWSpecification bwSpecification : getBwSpecificationsSet()) {
            if (bwSpecification.getName().equals(name))
                return bwSpecification;
        }
        throw new BlendedWorkflowException(
                BlendedWorkflowError.INVALID_SPECIFICATION_NAME, name);
    }

    public BWInstance getBWInstance(String ID) throws BlendedWorkflowException {
        for (BWSpecification bwSpecification : getBwSpecificationsSet()) {
            for (BWInstance bwInstance : bwSpecification.getBwInstancesSet()) {
                if (bwInstance.getID().equals(ID))
                    return bwInstance;
            }
        }
        throw new BlendedWorkflowException(
                BlendedWorkflowError.NON_EXISTENT_CASE_ID, ID);
    }

    public BWInstance getBWInstanceFromYAWLCaseID(String yawlCaseID)
            throws BlendedWorkflowException {
        for (BWSpecification bwSpecification : getBwSpecificationsSet()) {
            for (BWInstance bwInstance : bwSpecification.getBwInstancesSet()) {
                if (bwInstance.getYawlCaseID().equals(yawlCaseID))
                    return bwInstance;
            }
        }
        throw new BlendedWorkflowException(
                BlendedWorkflowError.NON_EXISTENT_CASE_ID, yawlCaseID);
    }

    public YAWLAdapter getYawlAdapter() throws BlendedWorkflowException {
        if (yawlAdapter == null) {
            yawlAdapter = new YAWLAdapter();
        }
        return yawlAdapter;
    }

    public void setYawlAdapter(YAWLAdapter yawlAdapter) {
        this.yawlAdapter = yawlAdapter;
    }

    public WorkletAdapter getWorkletAdapter() {
        if (workletAdapter == null) {
            workletAdapter = new WorkletAdapter();
        }
        return workletAdapter;
    }

    public void setWorkletAdapter(WorkletAdapter workletAdapter) {
        this.workletAdapter = workletAdapter;
    }

    public WorkListManager getWorkListManager() {
        if (workListManager == null) {
            workListManager = new WorkListManager();
        }
        return workListManager;
    }

    public void setWorkListManager(WorkListManager workListManager) {
        this.workListManager = workListManager;
    }

    public BWManager getBwManager() {
        if (bwManager == null) {
            bwManager = new BWManager();
        }
        return bwManager;
    }

    public void setBwManager(BWManager bwManager) {
        this.bwManager = bwManager;
    }

    public OrganizationalManager getOrganizationalManager() {
        if (organizationalManager == null) {
            organizationalManager = new OrganizationalManager();
        }
        return organizationalManager;
    }

    public void setOrganizationalManager(
            OrganizationalManager organizationalManager) {
        this.organizationalManager = organizationalManager;
    }

    public BWExecutorService getBWExecutorService() {
        if (bwExecutorService == null) {
            bwExecutorService = new BWExecutorService();
        }
        return bwExecutorService;
    }

    public void setBWExecutorService(BWExecutorService bwExecutorService) {
        this.bwExecutorService = bwExecutorService;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

}
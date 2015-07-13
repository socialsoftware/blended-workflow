package pt.ist.socialsoftware.blendedworkflow.domain;

import java.text.DateFormat;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.organizationalmanager.OrganizationalManager;
import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
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

    @Atomic(mode = TxMode.WRITE)
    public BWSpecification createSpecification(String specId, String name)
            throws BWException {
        String author = "Author";
        String description = "Description";
        String version = "Version";
        String UID = "UID";
        return new BWSpecification(specId, name, author, description, version,
                UID);
    }

    public Set<BWSpecification> getSpecByName(String name) {
        return getSpecificationSet().stream()
                .filter(spec -> spec.getName().equals(name))
                .collect(Collectors.toSet());
    }

    public Optional<BWSpecification> getSpecById(String specId) {
        return getSpecificationSet().stream()
                .filter(spec -> spec.getSpecId().equals(specId)).findFirst();
    }

    public BWInstance getBWInstance(String ID) throws BWException {
        for (BWSpecification specificationpecification : getSpecificationSet()) {
            for (BWInstance bwInstance : specificationpecification
                    .getBwInstancesSet()) {
                if (bwInstance.getID().equals(ID))
                    return bwInstance;
            }
        }
        throw new BWException(BWErrorType.NON_EXISTENT_CASE_ID, ID);
    }

    public BWInstance getBWInstanceFromYAWLCaseID(String yawlCaseID)
            throws BWException {
        for (BWSpecification specification : getSpecificationSet()) {
            for (BWInstance bwInstance : specification.getBwInstancesSet()) {
                if (bwInstance.getYawlCaseID().equals(yawlCaseID))
                    return bwInstance;
            }
        }
        throw new BWException(BWErrorType.NON_EXISTENT_CASE_ID, yawlCaseID);
    }

    public YAWLAdapter getYawlAdapter() throws BWException {
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

    // TODO: a in depth deletion of objects
    @Atomic(mode = TxMode.WRITE)
    public void delete() {
        getSpecificationSet().stream().forEach(spec -> spec.delete());

        setRoot(null);

        deleteDomainObject();
    }

}
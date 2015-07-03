package pt.ist.socialsoftware.blendedworkflow;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.yawlfoundation.yawl.engine.YSpecificationID;
import org.yawlfoundation.yawl.worklet.rdr.RdrNode;
import org.yawlfoundation.yawl.worklet.rdr.RuleType;
import org.yawlfoundation.yawl.worklet.support.WorkletGatewayClient;

import jvstm.Transaction;
import pt.ist.socialsoftware.blendedworkflow.adapters.WorkletAdapter;
import pt.ist.socialsoftware.blendedworkflow.adapters.YAWLAdapter;
import pt.ist.socialsoftware.blendedworkflow.bwmanager.BWManager;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Specification;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItem;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;
import pt.ist.socialsoftware.blendedworkflow.service.BWException.BlendedWorkflowError;
import pt.ist.socialsoftware.blendedworkflow.service.execution.CreateBWInstanceService;
import pt.ist.socialsoftware.blendedworkflow.service.execution.LoadBWSpecificationService;
import pt.ist.socialsoftware.blendedworkflow.shared.Bootstrap;
import pt.ist.socialsoftware.blendedworkflow.shared.StringUtils;
import pt.ist.socialsoftware.blendedworkflow.worklistmanager.WorkListManager;

@RunWith(MockitoJUnitRunner.class)
public abstract class MockitoAbstractServiceTest {

    protected static final String BWSPECIFICATION_FILENAME = "src/test/xml/MedicalEpisode/MedicalEpisodeTest.xml";
    protected static final String BWSPECIFICATION_NAME = "Medical Appointment";
    protected static final String BWINSTANCE_ID = "Medical Appointment.1";
    protected static final String YAWLCASE_ID = "yawlCaseID";
    protected static final String USER_ID = "BlendedWorkflow";
    protected BWInstance bwInstance = null;

    protected WorkletAdapter workletAdapter;
    @Mock
    protected WorkletGatewayClient workletGatewayClient;
    @Mock
    protected BWManager bwManager;
    @Mock
    protected WorkListManager workListManager;
    @Mock
    protected YAWLAdapter yawlAdapter;

    @Before
    public void initialSetUp() {
        Bootstrap.initTestDB();
        workletAdapter = new WorkletAdapter(workletGatewayClient);

        Transaction.begin();
        BlendedWorkflow.getInstance().setYawlAdapter(yawlAdapter);
        BlendedWorkflow.getInstance().setWorkletAdapter(workletAdapter);
        BlendedWorkflow.getInstance().setBwManager(bwManager);
        BlendedWorkflow.getInstance().setWorkListManager(workListManager);
        Transaction.commit();
    }

    @After
    public void tearDown() {
        Bootstrap.clean();
    }

    protected void initializeSpecification() throws Exception {
        final YSpecificationID yawlSpec = new YSpecificationID(
                "UID_3213f8b4-5757-4674-a6a2-415aa191ca91", "1.0",
                "MedicalAppointment");
        final ArrayList<YSpecificationID> specIds = new ArrayList<YSpecificationID>();
        specIds.add(yawlSpec);

        when(yawlAdapter.getLoadedActivitySpecs()).thenReturn(specIds);

        final String bwSpecificationString = StringUtils
                .fileToString(BWSPECIFICATION_FILENAME);
        new LoadBWSpecificationService(bwSpecificationString).call();

        verify(yawlAdapter).loadSpecification(anyString());
        verify(workletGatewayClient, times(36)).addNode(
                any(YSpecificationID.class), anyString(), any(RuleType.class),
                any(RdrNode.class), anyString());
        verify(bwManager).notifyLoadedBWSpecification(any(Specification.class));
    }

    protected void initializeBWInstance() throws BWException, Exception {
        when(yawlAdapter.launchCase(anyString())).thenReturn(YAWLCASE_ID);

        final Specification bwSpecification = getBWSpecification(
                BWSPECIFICATION_NAME);
        new CreateBWInstanceService(bwSpecification.getExternalId(), "",
                USER_ID).call();

        verify(bwManager).notifyCreatedBWInstance((any(BWInstance.class)));
        verify(workListManager, never())
                .notifyEnabledWorkItem(any(WorkItem.class));

        Transaction.begin();
        // bwInstance =
        // BlendedWorkflow.getInstance().getBWInstance(BWINSTANCE_ID);
        List<BWInstance> bwInstances = new ArrayList<BWInstance>(
                bwSpecification.getBwInstancesSet());
        bwInstance = bwInstances.get(0);
        Transaction.commit();

    }

    protected Specification getBWSpecification(String name) throws BWException {
        Transaction.begin();
        final Specification bwSpecification = BlendedWorkflow.getInstance()
                .getSpecification(name).orElseThrow(() -> new BWException(
                        BlendedWorkflowError.INVALID_SPECIFICATION_NAME, name));
        Transaction.commit();
        return bwSpecification;
    }

}

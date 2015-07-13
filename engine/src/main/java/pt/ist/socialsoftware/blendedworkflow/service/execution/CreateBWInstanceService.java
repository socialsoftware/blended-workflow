package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.domain.User;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class CreateBWInstanceService implements Callable<String> {

    private static Logger log = Logger.getLogger("CreateBWInstanceService");
    private final BWSpecification bwSpecification;
    private final String name;
    private final String userID;

    public CreateBWInstanceService(String bwSpecificationOID, String name,
            String userID) {
        this.bwSpecification = FenixFramework
                .getDomainObject(bwSpecificationOID);
        this.name = name;
        this.userID = userID;
    }

    @Atomic(mode = TxMode.WRITE)
    @Override
    public String call() throws Exception {
        log.info("Start");
        try {
            // GetUser
            User user = BlendedWorkflow.getInstance().getOrganizationalModel()
                    .getUser(userID);

            // Get BWSpecification and clone it
            BWInstance bwInstance = new BWInstance(this.bwSpecification, name,
                    user);

            // Launch case on YAWL
            BWSpecification bwSpecification = bwInstance.getSpecification();
            String yawlSpecificationID = bwSpecification
                    .getYawlSpecficationID();
            String yawlCaseID = BlendedWorkflow.getInstance().getYawlAdapter()
                    .launchCase(yawlSpecificationID);
            bwInstance.setYawlCaseID(yawlCaseID);

            // Create GoalWorkItems and TaskWorkItems
            BlendedWorkflow.getInstance().getBwManager()
                    .notifyCreatedBWInstance(bwInstance);

        } catch (BWException bwe) {
            log.error(bwe.getError());
            BlendedWorkflow.getInstance().getBwManager()
                    .notifyException(bwe.getError());
        }
        log.info("END");
        return "CreateBWInstanceService:Sucess";
    }
}
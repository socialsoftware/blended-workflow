package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jvstm.Transaction;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.OldWorkItem;

public class CheckInWorkItemService implements Callable<String> {

    private static Logger log = LoggerFactory
            .getLogger("CheckInWorkItemService");
    private final OldWorkItem workItem;

    public CheckInWorkItemService(String workItemOID) {
        this.workItem = FenixFramework.getDomainObject(workItemOID);
    }

    @Override
    public String call() throws Exception {
        log.info("Start");
        Transaction.begin();
        this.workItem.notifyCheckedIn();
        Transaction.commit();
        log.info("END");
        return "CheckInWorkItemService:Sucess";
    }

}
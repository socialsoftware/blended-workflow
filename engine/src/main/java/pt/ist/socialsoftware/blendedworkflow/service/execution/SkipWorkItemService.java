package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jvstm.Transaction;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.domain.OldWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.OldWorkItemArgument;

public class SkipWorkItemService implements Callable<String> {

    private static Logger log = LoggerFactory.getLogger("SkipWorkItemService");
    private final OldWorkItem workItem;

    public SkipWorkItemService(String workItemOID) {
        this.workItem = FenixFramework.getDomainObject(workItemOID);
    }

    @Override
    public String call() throws Exception {
        log.info("Start");
        Transaction.begin();
        for (OldWorkItemArgument workItemArgument : this.workItem
                .getOutputWorkItemArgumentsSet()) {
            workItemArgument.setState(DataState.SKIPPED);
            workItemArgument.setValue("$SKIPPED$");
        }

        this.workItem.notifyCheckedIn();
        Transaction.commit();
        log.info("END");
        return "SkipWorkItemService:Sucess";
    }

}
package pt.ist.socialsoftware.blendedworkflow.service.execution;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jvstm.Transaction;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.MaintainGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.MaintainGoal.MaintainGoalState;

public class ManageGoalConditionService implements Callable<String> {

    private static Logger log = LoggerFactory
            .getLogger("DisableGoalConditionService");

    private final GoalWorkItem goalWorkItem;
    private final Condition condition;
    private final MaintainGoal maintainGoal;
    private final MaintainGoalState state;

    /**
     * Remove an AchieveGoal ActivateCondition.
     * 
     * @param workItemOID
     *            the workItem OID.
     * @param conditionOID
     *            the condition OID.
     */
    public ManageGoalConditionService(String workItemOID, String conditionOID) {
        this.goalWorkItem = FenixFramework.getDomainObject(workItemOID);
        this.condition = FenixFramework.getDomainObject(conditionOID);
        this.maintainGoal = null;
        this.state = null;
    }

    /**
     * Enable/Disable BWInstance MaintainGoal Conditions.
     * 
     * @param maintainGoalOID
     */
    public ManageGoalConditionService(String maintainGoalOID,
            MaintainGoalState state) {
        this.goalWorkItem = null;
        this.condition = null;
        this.maintainGoal = FenixFramework.getDomainObject(maintainGoalOID);
        this.state = state;
    }

    @Override
    public String call() throws Exception {
        log.info("Start");
        Transaction.begin();

        if (this.maintainGoal == null) {

            for (Condition activateCondition : this.goalWorkItem
                    .getActivateConditionsSet()) {
                if (activateCondition.equals(condition)) {
                    this.goalWorkItem.removeActivateConditions(condition);
                }
            }

            for (Condition maintainCondition : this.goalWorkItem
                    .getMaintainConditionsSet()) {
                if (maintainCondition.equals(condition)) {
                    this.goalWorkItem.removeMaintainConditions(condition);
                }
            }

        } else {
            this.maintainGoal.setState(this.state);
        }
        Transaction.commit();
        log.info("END");
        return "CreateGoalInstanceService:Sucess";
    }

}

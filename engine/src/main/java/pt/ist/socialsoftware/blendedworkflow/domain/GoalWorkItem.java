package pt.ist.socialsoftware.blendedworkflow.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.domain.BWDataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class GoalWorkItem extends GoalWorkItem_Base {

    public enum GoalState {
        NEW, PRE_GOAL, ACTIVATED, ENABLED, SKIPPED, ACHIEVED, RE_ACTIVATED
    };

    private final Logger log = Logger.getLogger("GoalWorkItem");
    private final DateFormat dateFormat = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");

    public GoalWorkItem(BWInstance bwInstance, AchieveGoal goal,
            EntityInstance entityInstanceContext,
            Set<Condition> activateConditions,
            Set<MaintainGoal> maintainGoals) {
        log.info("New GoalWorkitem for goal " + goal.getName());
        setBwInstance(bwInstance);
        setAchieveGoal(goal);

        setRole(goal.getRole());
        setUser(goal.getUser());
        setID(goal.getName() + "." + bwInstance.getNewWorkItemID()); // Id:
                                                                     // GoalName.#
        setEntityInstanceContext(entityInstanceContext);

        // Activate Conditions
        for (Condition activateCondition : activateConditions) {
            addActivateConditions(activateCondition);
            activateCondition.assignAttributeInstances(this,
                    ConditionType.ACTIVATE_CONDITION);
        }
        createInputWorkItemArguments();
        updateInputWorkItemArguments();

        // Success Condition
        setSucessCondition(goal.getSucessCondition());
        getSucessCondition().assignAttributeInstances(this,
                ConditionType.SUCESS_CONDITION);
        createOutputWorkItemArguments();

        // Maintain Conditions
        for (MaintainGoal maintainGoal : maintainGoals) {
            addMaintainConditions(maintainGoal.getMaintainCondition());
        }

        setState(GoalState.NEW);
    }

    /******************************
     * State Machine
     ******************************/
    public void notifyPreGoal() {
        log.info("GoalWorkitem " + getID() + " is now in PreGoal state");
        setState(GoalState.PRE_GOAL);
        BlendedWorkflow.getInstance().getWorkListManager()
                .notifyEnabledWorkItem(this);
    }

    public void notifyActivated() {
        log.info("GoalWorkitem " + getID() + " is now in ACTIVATED state");
        setState(GoalState.ACTIVATED);
        evaluate();
    }

    public void notifyEnabled() {
        log.info("GoalWorkitem " + getID() + " is now in Enabled state");
        setState(GoalState.ENABLED);
        evaluate();
    }

    public void notifyAchieved() {
        log.info("GoalWorkitem " + getID() + " is now in Completed state");
        setState(GoalState.ACHIEVED);
        setAttributeValues();

        String date = dateFormat.format(Calendar.getInstance().getTime());
        getBwInstance().getLog().addLogRecords(new LogRecord(date, "Achieved",
                "[GOAL] " + getID(), getUser().getID()));
        BlendedWorkflow.getInstance().getWorkListManager()
                .notifyCompletedWorkItem(this);

        getBwInstance().getGoalModelInstance().checkActivatedWorkItems();
    }

    public void notifySkipped() {
        log.info("GoalWorkitem " + getID() + " is now in Skipped state");
        setState(GoalState.SKIPPED);
        setAttributeSkipped();

        String date = dateFormat.format(Calendar.getInstance().getTime());
        getBwInstance().getLog().addLogRecords(new LogRecord(date, "Skipped",
                "[GOAL] " + getID(), getUser().getID()));
        BlendedWorkflow.getInstance().getWorkListManager()
                .notifyCompletedWorkItem(this);

        getBwInstance().getGoalModelInstance().checkActivatedWorkItems();
    }

    public void notifyReActivated(GoalWorkItem cause) {
        this.updateInputWorkItemArguments();
        this.updateOutputWorkItemArguments();
        this.setState(GoalState.RE_ACTIVATED);

        String date = dateFormat.format(Calendar.getInstance().getTime());
        getBwInstance().getLog().addLogRecords(new LogRecord(date, "ReEnabled",
                "[GOAL] " + this.getID(), getUser().getID()));

        BlendedWorkflow.getInstance().getWorkListManager()
                .notifyReEnabledWorkItem(this, cause);
    }

    /**********************************
     * Events
     **********************************/
    @Override
    public void notifyDataChanged() {
        if (getState().equals(GoalState.ACHIEVED)
                || getState().equals(GoalState.SKIPPED)) {
            log.info("GoalWorkItem " + getID()
                    + " already in the ACHIEVED/SKIPPED state and will not re-evaluate.");
        } else {
            log.info("GoalWorkitem " + getID()
                    + "is re-evaluating due to changes in data.");
            updateInputWorkItemArguments();
            updateOutputWorkItemArguments();
            evaluate();
        }
    }

    @Override
    public void notifyCheckedIn() {
        if (this.getState().equals(GoalState.RE_ACTIVATED)) {
            if (checkAffectedGoals()) {
                log.debug("GoalWorkItem " + getID()
                        + " cannot be checked-in because the new data have impact other Goals");
            } else {
                log.debug("GoalWorkItem " + getID()
                        + " can be checked-in because the new data does not have impact other Goals");
                evaluate();
            }
        } else {
            evaluate();
        }
    }

    /***********************************
     * Evaluation Methods
     ***********************************/
    public void evaluate() {
        if (getState().equals(GoalState.NEW)
                || getState().equals(GoalState.PRE_GOAL)) {
            TripleStateBool result = evaluateActivateConditions();
            // System.out.println(getID()+ "|AC=" + result);
            if (result.equals(TripleStateBool.TRUE)) {
                notifyActivated();
            } else if (result.equals(TripleStateBool.SKIPPED)
                    || result.equals(TripleStateBool.FALSE)) {
                notifyPreGoal();
            }
        } else if (getState().equals(GoalState.ACTIVATED)) {
            TripleStateBool result = evaluateSubGoals();
            if (result.equals(TripleStateBool.TRUE)) {
                notifyEnabled();
            } else {
                log.info("GoalWorkItem " + getID()
                        + " subgoals are not completed yet!");
            }
        } else if (getState().equals(GoalState.ENABLED)
                || getState().equals(GoalState.RE_ACTIVATED)) {
            TripleStateBool result = evaluateSucessCondition()
                    .AND(evaluateMaintainConditions());
            if (result.equals(TripleStateBool.TRUE)) {
                notifyAchieved();
            } else if (result.equals(TripleStateBool.FALSE)) {
                BlendedWorkflow.getInstance().getWorkListManager()
                        .notifyEnabledWorkItem(this);
            } else if (result.equals(TripleStateBool.SKIPPED)) {
                notifySkipped();
            }
        } else if (getState().equals(GoalState.ACHIEVED)) {
            log.info("GoalWorkItem " + getID()
                    + " already in the ACHIEVED state!");
        } else if (getState().equals(GoalState.SKIPPED)) {
            log.info("GoalWorkItem " + getID()
                    + " already in the SKIPPED state!");
        }
    }

    /**
     * @return TripleStateBool
     */
    public TripleStateBool evaluateSubGoals() {
        int countSubGoals = 0;
        int countSubGoalsWorkItems = 0;

        for (AchieveGoal subGoal : getAchieveGoal().getSubGoalsSet()) {
            countSubGoalsWorkItems = 0;

            for (GoalWorkItem goalWorkItem : subGoal.getGoalWorkItemsSet()) {
                if (goalWorkItem.getState().equals(GoalState.ACHIEVED)
                        || goalWorkItem.getState().equals(GoalState.SKIPPED)) {
                    countSubGoalsWorkItems++;
                }
            }
            if (subGoal.getGoalWorkItemsSet()
                    .size() == countSubGoalsWorkItems) {
                countSubGoals++;
            }
        }

        if (countSubGoals != getAchieveGoal().getSubGoalsSet().size()) {
            log.debug(getAchieveGoal().getSubGoalsSet().size() + "SG="
                    + TripleStateBool.FALSE);
            return TripleStateBool.FALSE;
        } else {
            log.debug(getAchieveGoal().getSubGoalsSet().size() + "SG="
                    + TripleStateBool.FALSE);
            log.debug("SG=" + TripleStateBool.TRUE);
            return TripleStateBool.TRUE;
        }
    }

    public TripleStateBool evaluateActivateConditions() {
        TripleStateBool result = null;
        TripleStateBool activateConditionJointResult = TripleStateBool.TRUE;
        for (Condition activateCondition : getActivateConditionsSet()) {
            result = activateCondition.evaluateWithWorkItem(this,
                    ConditionType.ACTIVATE_CONDITION);
            if (result.equals(TripleStateBool.FALSE)) {
                activateConditionJointResult = TripleStateBool.FALSE;
                break;
            } else if (result.equals(TripleStateBool.SKIPPED)) {
                activateConditionJointResult = TripleStateBool.SKIPPED;
                break;
            }
        }

        log.debug("AC=" + activateConditionJointResult);
        return activateConditionJointResult;
    }

    public TripleStateBool evaluateSucessCondition() {
        return getSucessCondition().evaluateWithWorkItem(this,
                ConditionType.SUCESS_CONDITION);
    }

    public TripleStateBool evaluateMaintainConditions() {
        TripleStateBool maintainConditionsResult = TripleStateBool.TRUE;
        Boolean und = false;
        for (Condition maintainCondition : getMaintainConditionsSet()) {
            for (WorkItemArgument wa2 : getOutputWorkItemArgumentsSet()) {
                if (wa2.getState().equals(DataState.UNDEFINED)) {
                    und = true;
                }
            }
            if (!und) {
                TripleStateBool m = maintainCondition.evaluateWithDataModel(
                        null, this, ConditionType.SUCESS_CONDITION);
                maintainConditionsResult = maintainConditionsResult.AND(m);
            }
        }
        return maintainConditionsResult;
    }

    /***********************************
     * Redo Methods
     ***********************************/
    public boolean checkAffectedGoals() {
        AchieveGoal redoGoal = this.getAchieveGoal();
        DataModelInstance dataModelInstance = getBwInstance()
                .getDataModelInstance();
        GoalModelInstance goalModelInstance = getBwInstance()
                .getGoalModelInstance();
        boolean workitemExists = false;
        boolean affectedMandatory = false;

        Set<AchieveGoal> affectedAchieveGoals = goalModelInstance
                .getAchieveGoalAssociatedAchieveGoals(redoGoal);

        // Search All Affected Goals:
        for (AchieveGoal affectedAG : affectedAchieveGoals) {
            for (EntityInstance entityInstance : affectedAG.getEntityContext()
                    .getEntityInstancesSet()) {
                EntityInstance affectedAGEntityInstance = dataModelInstance
                        .getEntityInstance(getEntityInstanceContext(),
                                entityInstance);

                // Context can be reached
                if (affectedAGEntityInstance != null) {
                    for (GoalWorkItem affectedGW : affectedAG
                            .getGoalWorkItemsSet()) {
                        if (affectedGW.getEntityInstanceContext()
                                .equals(affectedAGEntityInstance)) {
                            // notify?
                            log.debug("GoalWorkItem for EntityInstance: "
                                    + affectedAGEntityInstance.getID()
                                    + " already exists: " + affectedGW.getID());
                            workitemExists = true;
                        }
                    }

                    // If a GoalWorkItem does not exists
                    if (!workitemExists) {
                        log.debug(
                                "Goal does not have GoalWorkItems for EntityInstance: "
                                        + affectedAGEntityInstance.getID());
                        // Create GoalWorkItem
                        Set<Condition> activateConditions = affectedAG
                                .getActivateConditionsSet();
                        Set<MaintainGoal> maintainGoals = getBwInstance()
                                .getGoalModelInstance()
                                .getAchieveGoalAssociatedMaintainGoals(
                                        affectedAG);
                        GoalWorkItem newGoalWorkItem = new GoalWorkItem(
                                getBwInstance(), affectedAG,
                                affectedAGEntityInstance, activateConditions,
                                maintainGoals);
                        // evaluate SucessCondition with old data
                        TripleStateBool result = newGoalWorkItem
                                .evaluateSucessCondition();
                        log.debug("New GoalWorkItem: " + newGoalWorkItem.getID()
                                + " SucessCondition with old data = " + result);
                        if (result.equals(TripleStateBool.TRUE)
                                || result.equals(TripleStateBool.SKIPPED)) {
                            // Put new data into the new workitem
                            for (WorkItemArgument redoOutputWA : this
                                    .getOutputWorkItemArgumentsSet()) {
                                for (WorkItemArgument newInputWA : newGoalWorkItem
                                        .getInputWorkItemArgumentsSet()) {
                                    if (redoOutputWA.getAttributeInstance()
                                            .equals(newInputWA
                                                    .getAttributeInstance())) {
                                        newInputWA.setValue(
                                                redoOutputWA.getValue());
                                    }
                                }
                                for (WorkItemArgument newOutputWA : newGoalWorkItem
                                        .getOutputWorkItemArgumentsSet()) {
                                    if (redoOutputWA.getAttributeInstance()
                                            .equals(newOutputWA
                                                    .getAttributeInstance())) {
                                        newOutputWA.setValue(
                                                redoOutputWA.getValue());
                                    }
                                }
                            }
                            // evaluate SucessCondition with new data
                            log.debug("New GoalWorkItem: "
                                    + newGoalWorkItem.getID()
                                    + " SucessCondition with new data = "
                                    + result);
                            result = newGoalWorkItem.evaluateSucessCondition();
                            if (result.equals(TripleStateBool.TRUE)) {
                                newGoalWorkItem.notifyAchieved();
                            } else if (result.equals(TripleStateBool.TRUE)) {
                                newGoalWorkItem.notifySkipped();
                            } else {
                                log.debug("New GoalWorkItem: "
                                        + newGoalWorkItem.getID()
                                        + " Sucessed with the old data but failed with the new data! ");
                                BlendedWorkflow.getInstance()
                                        .getWorkListManager()
                                        .notifyReEnabledWorkItem(
                                                newGoalWorkItem, this);
                                affectedMandatory = true;
                            }
                        } else {
                            // notify?
                            log.debug("New GoalWorkItem: "
                                    + newGoalWorkItem.getID()
                                    + " SucessCondition with old data failed and will be removed!");
                            newGoalWorkItem.deleteDomainObject();
                        }
                    }
                } else {
                    log.debug("Affected Goal Context cannot be found!");
                }
            }
        }
        return affectedMandatory;
    }
}

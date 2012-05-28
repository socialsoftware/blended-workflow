package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class GoalWorkItem extends GoalWorkItem_Base {
	
	private Logger log = Logger.getLogger("GoalWorkItem");
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public GoalWorkItem(BWInstance bwInstance, AchieveGoal goal, EntityInstance entityInstanceContext) {
		log.info("New GoalWorkitem for goal " + goal.getName());
		setBwInstance(bwInstance);
		setAchieveGoal(goal);
		setRole(goal.getRole());
		setUser(goal.getUser());
		setID(goal.getName() + "." + bwInstance.getNewWorkItemID()); // Id: GoalName.#
		setEntityInstanceContext(entityInstanceContext);
		
		for (Condition activateCondition : goal.getActivateConditions()){
			addActivateConditions(activateCondition);
		}
		
		for (MaintainGoal maintainGoal : bwInstance.getGoalModelInstance().getMaintainGoals()){
			if (goal.getEntityContext().equals(maintainGoal.getMaintainGoalEntityContext())) {
				addMaintainConditions(maintainGoal.getMaintainCondition());	
			}
		}
		
		setSucessCondition(goal.getSucessCondition());

		getSucessCondition().assignAttributeInstances(this, ConditionType.SUCESS);
		createOutputWorkItemArguments();
		setState(WorkItemState.ACTIVATED);
	}

	/******************************
	 * State Change Notifications *
	 ******************************/
	
	@Override
	public void notifyPreTask() {
		log.info("GoalWorkitem " + getID() + " is now in PreGoal state");
		setState(WorkItemState.PRE_GOAL);
		BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
	}
	
	@Override
	public void notifyPreFalse() {
		log.error("GoalWorkitem " + getID() + " is now in PreFalse state");
		setState(WorkItemState.PRE_FALSE);
		BlendedWorkflow.getInstance().getWorkListManager().notifyWorkItemState(this);
	}
	
	@Override
	public void notifyConstrainViolation() {
		log.info("GoalWorkitem " + getID() + " is now in ConstrainViolation state");
		setState(WorkItemState.CONSTRAINT_VIOLATION);
		updateInputWorkItemArguments();
		updateOutputWorkItemArguments();
		evaluate(false);
	}

	@Override
	public void notifyEnabled() {
		log.info("GoalWorkitem " + getID() + " is now in Enabled state");
		setState(WorkItemState.ENABLED);
		BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
	}
	
	@Override
	public void notifyPending() {
		log.info("GoalWorkitem " + getID() + " is now in Pending state");
		setState(WorkItemState.GOAL_PENDING);
		BlendedWorkflow.getInstance().getWorkListManager().notifyPendingWorkItem(this);
	}

	@Override
	public void notifyCompleted() {

		log.info("GoalWorkitem " + getID() + " is now in Completed state");
		if (getState() == WorkItemState.CHECKED_IN || getState() == WorkItemState.CONSTRAINT_VIOLATION) {
			setState(WorkItemState.COMPLETED);
		}	
		setAttributeValues();
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date, "Completed", "[GOAL] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifyCompletedWorkItem(this);
		
		getBwInstance().getGoalModelInstance().checkPedingWorkItems();
	}

	@Override
	public void notifySkipped() {
		log.info("GoalWorkitem " + getID() + " is now in Skipped state");
		if (getState() == WorkItemState.CHECKED_IN || getState() == WorkItemState.CONSTRAINT_VIOLATION) {
			setState(WorkItemState.SKIPPED);
		}	
		
		setAttributeSkipped();
		
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date, "Skipped", "[GOAL] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifySkippedWorkItem(this);

		getBwInstance().getGoalModelInstance().checkPedingWorkItems();
	}
	
	@Override
	public void notifyReActivated() {
		this.updateInputWorkItemArguments();
		this.updateOutputWorkItemArguments();
		this.setState(WorkItemState.RE_ACTIVATED);
		
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date, "ReEnabled", "[GOAL] " + this.getID(), getUser().getID()));
		
		BlendedWorkflow.getInstance().getWorkListManager().notifyReEnabledWorkItem(this);
	}
	
	/***********************************
	 * Commit WorkItemArguments values *
	 ***********************************/
	
	@Override
	public void notifyCheckedIn() {
		if (getState() == WorkItemState.ENABLED || getState() == WorkItemState.PRE_GOAL ||  getState() == WorkItemState.RE_ACTIVATED) {
			setState(WorkItemState.CHECKED_IN);
		}
		evaluate(false);
	}
	
	/**
	 * TODO: Test.
	 */
	public void evaluate(Boolean isActivateCondition) {
		//Check subGoals
		int countSubGoals = 0;
		int countSubGoalsWorkItems = 0;
		for (AchieveGoal subGoal : getAchieveGoal().getSubGoals()) {
			countSubGoalsWorkItems = 0;
			for (GoalWorkItem goalWorkItem : subGoal.getGoalWorkItems()) {
				if (goalWorkItem.getState().equals(WorkItemState.COMPLETED) ||goalWorkItem.getState().equals(WorkItemState.SKIPPED)) {
					countSubGoalsWorkItems++;
				}
			}
			if (subGoal.getGoalWorkItemsCount() == countSubGoalsWorkItems) {
				countSubGoals++;
			}
		}

		if (countSubGoals != getAchieveGoal().getSubGoalsCount()) {
			this.notifyPending();
		} else {
			if (!getState().equals(WorkItemState.CHECKED_IN)) {
			updateInputWorkItemArguments();
			}

			TripleStateBool result = null;
			TripleStateBool activateConditionJointResult = TripleStateBool.TRUE;

			if (isActivateCondition) {
				for (Condition activateCondition : getActivateConditions()) {
					result = activateCondition.evaluateWithWorkItem(this, ConditionType.ACTIVATE);
					if (result.equals(TripleStateBool.FALSE)) {
						activateConditionJointResult = TripleStateBool.FALSE;
						break;
					} else if (result.equals(TripleStateBool.SKIPPED)) {
						activateConditionJointResult = TripleStateBool.SKIPPED;
						break;
					} 
				}
				log.info("ActivateCondition Evaluate result for " + this.getID() + " was " + result);
				if (activateConditionJointResult.equals(TripleStateBool.TRUE)) {
					notifyConstrainViolation();
				} else if (activateConditionJointResult.equals(TripleStateBool.SKIPPED)) {
					notifyPreTask();
				} else {
					notifyPreFalse();
				}
			} else {
				result = getSucessCondition().evaluateWithWorkItem(this, ConditionType.SUCESS);
				log.info("SucessCondition Evaluate result for " + this.getID() + " was " + result);
				
				TripleStateBool maintainConditionsResult = TripleStateBool.TRUE;
				Boolean und = false;
				for (Condition maintainCondition : getMaintainConditions()) {
					for (WorkItemArgument wa2: getOutputWorkItemArguments()) {
						if (wa2.getState().equals(DataState.UNDEFINED)) {
							und = true;
						}
					}
					if (!und) {
						TripleStateBool m = maintainCondition.evaluateWithDataModel(null, this, ConditionType.SUCESS);
						maintainConditionsResult = maintainConditionsResult.AND(m);
					}
				}
				
				log.info("MAINTAIN Evaluate result for " + this.getID() + " was " + maintainConditionsResult);
				result = result.AND(maintainConditionsResult);
				
				if (result.equals(TripleStateBool.TRUE)) {
					notifyCompleted();
				} else if (result.equals(TripleStateBool.SKIPPED)) {
					notifySkipped();
				} else {
					notifyEnabled();
				}
			}
		}
	}

}

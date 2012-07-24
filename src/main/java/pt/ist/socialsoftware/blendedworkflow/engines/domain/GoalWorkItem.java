package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.shared.TripleStateBool;

public class GoalWorkItem extends GoalWorkItem_Base {
	
	public enum GoalState {NEW, PRE_GOAL, ACTIVATED, ENABLED, SKIPPED, ACHIEVED, RE_ACTIVATED};
	
	private Logger log = Logger.getLogger("GoalWorkItem");
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public GoalWorkItem(BWInstance bwInstance, AchieveGoal goal, EntityInstance entityInstanceContext, List<Condition> activateConditions, Set<MaintainGoal> maintainGoals) {
		log.info("New GoalWorkitem for goal " + goal.getName());
		setBwInstance(bwInstance);
		setAchieveGoal(goal);
		
		setRole(goal.getRole());
		setUser(goal.getUser());
		setID(goal.getName() + "." + bwInstance.getNewWorkItemID()); // Id: GoalName.#
		setEntityInstanceContext(entityInstanceContext);
		
		//Activate Conditions
		for (Condition activateCondition : activateConditions) {
			addActivateConditions(activateCondition);
			activateCondition.assignAttributeInstances(this, ConditionType.ACTIVATE_CONDITION);
		}
		createInputWorkItemArguments();
		updateInputWorkItemArguments();
		
		//Success Condition
		setSucessCondition(goal.getSucessCondition());
		getSucessCondition().assignAttributeInstances(this, ConditionType.SUCESS_CONDITION);
		createOutputWorkItemArguments();
		
		//Maintain Conditions
		for (MaintainGoal maintainGoal : maintainGoals){
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
		BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
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
	
	public void notifyCompleted() {
		log.info("GoalWorkitem " + getID() + " is now in Completed state");
		setState(GoalState.ACHIEVED);
		setAttributeValues();
		
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date, "Completed", "[GOAL] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifyCompletedWorkItem(this);
		
		getBwInstance().getGoalModelInstance().checkActivatedWorkItems();
	}

	public void notifySkipped() {
		log.info("GoalWorkitem " + getID() + " is now in Skipped state");
		setState(GoalState.SKIPPED);
		setAttributeSkipped();
		
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date, "Skipped", "[GOAL] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifySkippedWorkItem(this);

		getBwInstance().getGoalModelInstance().checkActivatedWorkItems();
	}
	
	public void notifyReActivated() {
		this.updateInputWorkItemArguments();
		this.updateOutputWorkItemArguments();
		this.setState(GoalState.RE_ACTIVATED);
		
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date, "ReEnabled", "[GOAL] " + this.getID(), getUser().getID()));
		
		BlendedWorkflow.getInstance().getWorkListManager().notifyReEnabledWorkItem(this);
	}
	
	/**********************************
	 * Events
	 **********************************/
	@Override
	public void notifyDataChanged() {
		if (getState().equals(GoalState.ACHIEVED) || getState().equals(GoalState.SKIPPED)) {
			log.info("GoalWorkItem " + getID() + " already in the ACHIEVED/SKIPPED state and will not re-evaluate.");
		} else {
			log.info("GoalWorkitem " + getID() + "is re-evaluating due to changes in data.");
			updateInputWorkItemArguments();
			updateOutputWorkItemArguments();
			evaluate();	
		}
	}
	
	@Override
	public void notifyCheckedIn() {
		evaluate();
	}
	
	/***********************************
	 * GOALWORKITEM METHODS
	 ***********************************/
	public void evaluate() {
		if (getState().equals(GoalState.NEW) || getState().equals(GoalState.PRE_GOAL)) {
			TripleStateBool result = evaluateActivateConditions();
			if (result.equals(TripleStateBool.TRUE)) {
				notifyActivated();
			} else if (result.equals(TripleStateBool.SKIPPED) || result.equals(TripleStateBool.FALSE)) {
				notifyPreGoal();
			} 
		} else if (getState().equals(GoalState.ACTIVATED)) {
			TripleStateBool result = evaluateSubGoals();
			if (result.equals(TripleStateBool.TRUE)) {
				notifyEnabled();
			} else {
				log.info("GoalWorkItem " + getID() + " subgoals are not completed yet!");
			} 		
		} else if (getState().equals(GoalState.ENABLED)) {
			TripleStateBool result = evaluateSucessCondition().AND(evaluateMaintainConditions());
			if (result.equals(TripleStateBool.TRUE)) {
				notifyCompleted();
			} else if (result.equals(TripleStateBool.FALSE)) {
				BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
			} else if (result.equals(TripleStateBool.SKIPPED)) {
				notifySkipped();
			} 
		} else if (getState().equals(GoalState.ACHIEVED)) {
			log.info("GoalWorkItem " + getID() + " already in the ACHIEVED state!");
		} else if (getState().equals(GoalState.SKIPPED)) {
			log.info("GoalWorkItem " + getID() + " already in the SKIPPED state!");
		} else if (getState().equals(GoalState.RE_ACTIVATED)) {
			//TODO: ???
		}
	}
	
	/**
	 * FIXME: Checks if all subGoals of a Goal are completed.
	 * @return TripleStateBool
	 */
	public TripleStateBool evaluateSubGoals() {
		int countSubGoals = 0;
		int countSubGoalsWorkItems = 0;
		
		for (AchieveGoal subGoal : getAchieveGoal().getSubGoals()) {
			countSubGoalsWorkItems = 0;
			
			for (GoalWorkItem goalWorkItem : subGoal.getGoalWorkItems()) {
				if (goalWorkItem.getState().equals(GoalState.ACHIEVED) ||goalWorkItem.getState().equals(GoalState.SKIPPED)) {
					countSubGoalsWorkItems++;
				}
			}
			if (subGoal.getGoalWorkItemsCount() == countSubGoalsWorkItems) {
				countSubGoals++;
			}
		}
		
		if (countSubGoals != getAchieveGoal().getSubGoalsCount()) {
			log.debug(getAchieveGoal().getSubGoalsCount() +"SG=" + TripleStateBool.FALSE);
			return TripleStateBool.FALSE;
		} else {
			log.debug(getAchieveGoal().getSubGoalsCount() +"SG=" + TripleStateBool.FALSE);
			log.debug("SG=" + TripleStateBool.TRUE);
			return TripleStateBool.TRUE;
		}
	}
	
	public TripleStateBool evaluateActivateConditions() {
		TripleStateBool result = null;
		TripleStateBool activateConditionJointResult = TripleStateBool.TRUE;
		for (Condition activateCondition : getActivateConditions()) {
			result = activateCondition.evaluateWithWorkItem(this, ConditionType.ACTIVATE_CONDITION);
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
		return getSucessCondition().evaluateWithWorkItem(this, ConditionType.SUCESS_CONDITION);
	}
	
	public TripleStateBool evaluateMaintainConditions() {
		TripleStateBool maintainConditionsResult = TripleStateBool.TRUE;
		Boolean und = false;
		for (Condition maintainCondition : getMaintainConditions()) {
			for (WorkItemArgument wa2: getOutputWorkItemArguments()) {
				if (wa2.getState().equals(DataState.UNDEFINED)) {
					und = true;
				}
			}
			if (!und) {
				TripleStateBool m = maintainCondition.evaluateWithDataModel(null, this, ConditionType.SUCESS_CONDITION);
				maintainConditionsResult = maintainConditionsResult.AND(m);
			}
		}
		return maintainConditionsResult;
	}
}

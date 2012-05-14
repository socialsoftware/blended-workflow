package pt.ist.socialsoftware.blendedworkflow.engines.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.DataModel.DataState;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal.GoalState;
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
		
		setSucessCondition(goal.getSucessCondition());

		getSucessCondition().assignAttributeInstances(this, ConditionType.SUCESS);
		createOutputWorkItemArguments();
		setState(WorkItemState.ACTIVATED);
	}

	/**
	 * Copy the Goal AttributesInstances to its ConstrainViolation WorkItem Arguments.
	 */
	public void createOutputWorkItemArguments() {
		for (AttributeInstance attributeInstance : getOutputAttributeInstances()) {
			WorkItemArgument workItemArgument = new WorkItemArgument(attributeInstance, attributeInstance.getValue(), attributeInstance.getState());
			addOutputWorkItemArguments(workItemArgument);
		}
	}
	
	/**
	 * Update ConstrainViolation WorkItem Arguments with new Data.
	 */
	public void updateOutputWorkItemArguments() {
		for (WorkItemArgument workItemArgument : getOutputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			workItemArgument.setValue(attributeInstance.getValue());
			workItemArgument.setState(attributeInstance.getState());
		}
	}
	
	//NEW
	/**
	 * Copy the Task PreConstraint AttributesInstances to its PreConstraint WorkItem Arguments.
	 */
	public void createInputWorkItemArguments() {
		for (AttributeInstance attributeInstance : getInputAttributeInstances()) {
//			System.out.println("createInputWorkItemArguments:1");
			WorkItemArgument workItemArgument = new WorkItemArgument(attributeInstance, attributeInstance.getValue(), attributeInstance.getState());
			addInputWorkItemArguments(workItemArgument);
		}
	}
	
	/**
	 * Update PreConstraint WorkItem Arguments with new Data.
	 */
	public void updateInputWorkItemArguments() {
		for (WorkItemArgument workItemArgument : getInputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			workItemArgument.setValue(attributeInstance.getValue());
			workItemArgument.setState(attributeInstance.getState());
		}
	}
	
	/******************************
	 * State Change Notifications *
	 ******************************/
	
	@Override
	public void notifyPreTask() {
		//TODO: PREGOAL
		
		log.info("GoalWorkitem " + getID() + " is now in PreGoal state");
		getAchieveGoal().setState(GoalState.ENABLED);
		setState(WorkItemState.PRE_GOAL);
		BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
	}
	
	@Override
	public void notifyPreFalse() {
		log.info("Goal ActivateCondition is FALSE");
	}
	
	@Override
	public void notifyConstrainViolation() {
		log.info("GoalWorkitem " + getID() + " is now in ConstrainViolation state");
		setState(WorkItemState.CONSTRAINT_VIOLATION);
		updateOutputWorkItemArguments();
//		BlendedWorkflow.getInstance().getWorkletAdapter().notifyWorkItemContraintViolation(this); //WS
		evaluate(false);
	}

	public void evaluate(Boolean isActivateCondition) {
//		System.out.println("Passo1-evaluate for:" + this.getID());
		TripleStateBool result = null;
		TripleStateBool activateConditionJointResult = TripleStateBool.TRUE;
		
		if (isActivateCondition) {
			for (Condition activateCondition : getActivateConditions()) {
				result = activateCondition.evaluate(this);
				if (result.equals(TripleStateBool.SKIPPED)) {
					activateConditionJointResult = TripleStateBool.SKIPPED;
					break;
				} else if (result.equals(TripleStateBool.FALSE)) {
					activateConditionJointResult = TripleStateBool.FALSE;
					break;
				} 
			}
//			System.out.println("ActivateCondition Evaluate result for " + this.getID() + " was " + result);
			if (activateConditionJointResult.equals(TripleStateBool.TRUE)) {
				notifyConstrainViolation();
			} else if (activateConditionJointResult.equals(TripleStateBool.SKIPPED)) {
				notifyPreTask();
			} else {
				notifyPreFalse();
			}
		} else {
			result = getSucessCondition().evaluate(this);
//			System.out.println("SucessCondition Evaluate result for " + this.getID() + " was " + result);
			if (result.equals(TripleStateBool.TRUE)) {
				notifyCompleted();
			} else if (result.equals(TripleStateBool.SKIPPED)) {
				notifySkipped();
			} else {
				notifyEnabled();
			}
		}
	}

	@Override
	public void notifyEnabled() {
		int countSubGoals = 0;
		for (AchieveGoal subGoal : getAchieveGoal().getSubGoals()) {
			if (subGoal.getState().equals(GoalState.ACHIEVED))
				countSubGoals++;
		}
		
		if (countSubGoals == getAchieveGoal().getSubGoalsCount()) {
			log.info("GoalWorkitem " + getID() + " is now in Enabled state");
			getAchieveGoal().setState(GoalState.ENABLED);
			setState(WorkItemState.ENABLED);
			BlendedWorkflow.getInstance().getWorkListManager().notifyEnabledWorkItem(this);
		} else {
			log.info("GoalWorkitem " + getID() + " is now in Pending state");
			setState(WorkItemState.GOAL_PENDING);
			getAchieveGoal().setState(GoalState.DEACTIVATED);
		}
//		System.out.println("notifyEnabled");
	}
	
	@Override
	public void notifyPending() {
		log.info("GoalWorkitem " + getID() + " is now in Pending state");
		setState(WorkItemState.GOAL_PENDING);
		getAchieveGoal().setState(GoalState.DEACTIVATED);
		BlendedWorkflow.getInstance().getWorkListManager().notifyPendingWorkItem(this);
	}

	@Override
	public void notifyCompleted() {
//		System.out.println("Passo2 - completed");
		log.info("GoalWorkitem " + getID() + " is now in Completed state");
		if (getState() == WorkItemState.CHECKED_IN || getState() == WorkItemState.CONSTRAINT_VIOLATION) {
			setState(WorkItemState.COMPLETED);
			getAchieveGoal().setState(GoalState.ACHIEVED);
		}	
		setAttributeValues();
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date, "Completed", "[GOAL] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifyCompletedWorkItem(this);
		
		getBwInstance().getGoalModelInstance().checkPedingWorkItems();

//		getBwInstance().getGoalModelInstance().getEnabledWorkItems();
	}

	@Override
	public void notifySkipped() {
		log.info("GoalWorkitem " + getID() + " is now in Skipped state");
		if (getState() == WorkItemState.CHECKED_IN || getState() == WorkItemState.CONSTRAINT_VIOLATION) {
			setState(WorkItemState.SKIPPED);
			getAchieveGoal().setState(GoalState.SKIPPED);
		}	
		
		setAttributeSkipped();
		
		String date = dateFormat.format(Calendar.getInstance().getTime());
		getBwInstance().getLog().addLogRecords(new LogRecord(date, "Skipped", "[GOAL] " + getID(), getUser().getID()));
		BlendedWorkflow.getInstance().getWorkListManager().notifySkippedWorkItem(this);

		getBwInstance().getGoalModelInstance().checkPedingWorkItems();
//		getBwInstance().getGoalModelInstance().getEnabledWorkItems();
	}
	
	@Override
	public void notifyReActivated() {
		// TODO: notifyReActivated
	}
	
	/***********************************
	 * Commit WorkItemArguments values *
	 ***********************************/
	
	@Override
	public void setAttributeValues() {
		Boolean modified = false;
		// Add ConstrainViolation data
		for (WorkItemArgument workItemArgument : getOutputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			if (!attributeInstance.getState().equals(DataState.DEFINED)) {
				workItemArgument.getAttributeInstance().setValue(workItemArgument.getValue());
				modified = true;
			}
			else if (attributeInstance.getValue() != workItemArgument.getValue()) {
				workItemArgument.getAttributeInstance().setValue(workItemArgument.getValue());
				modified = true;
			}
		}

		if (modified) {
			notifyWorkItemDataChanged(false);
		}
	}
	
	@Override
	public void setAttributeSkipped() {
		Boolean modified = false;
		// Add ConstrainViolation data
		for (WorkItemArgument workItemArgument : getOutputWorkItemArguments()) {
			AttributeInstance attributeInstance = workItemArgument.getAttributeInstance();
			if (attributeInstance.getState() == DataState.UNDEFINED) {
				attributeInstance.setState(DataState.SKIPPED);
				attributeInstance.setValue("$SKIPPED$");
				modified = true;
			}
		}

		if (modified) {
			notifyWorkItemDataChanged(false);
		}
	}
	
	@Override
	public void notifyWorkItemDataChanged(Boolean isPreTask) {
		Set<WorkItem> notifyWorkItems = new HashSet<WorkItem>();

		// Get WorkItems affected by ConstraintViolation attributesInstances values
		for (AttributeInstance attributeInstance : getOutputAttributeInstances()) {
			for (WorkItem workItem : attributeInstance.getContraintViolationWorkItems()) {
				if (!workItem.equals(this) & (workItem.getState().equals(WorkItemState.ENABLED) || workItem.getState().equals(WorkItemState.PRE_TASK) || workItem.getState().equals(WorkItemState.PRE_GOAL))) {
					notifyWorkItems.add(workItem);
				}
			}
		}
		
		for (WorkItem workItem : notifyWorkItems) {
			workItem.updateInputWorkItemArguments();
			workItem.updateOutputWorkItemArguments();
			workItem.notifyDataChange();
		}
	}
	
	@Override
	public void notifyCheckedIn() {
		if (getState() == WorkItemState.ENABLED || getState() == WorkItemState.PRE_GOAL ||  getState() == WorkItemState.RE_ACTIVATED) {
			setState(WorkItemState.CHECKED_IN);
		}
		evaluate(false);
	}

}

package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.event.Action;

@SuppressWarnings("serial")
public class ManageAchieveGoalsConditionsForm extends VerticalLayout {

	protected final Tree treetable = new Tree("Achieve Goals");

	private static final Action DISABLE_CONDITION_ACTION = new Action("Disable Condition");

	public ManageAchieveGoalsConditionsForm(final ActivateGoalForm parent, final long bwInstanceOID) {
		HorizontalLayout footer = new HorizontalLayout();

		// Properties
		setMargin(true);
		setWidth("500px");
		setSpacing(true);

		treetable.setWidth("100%");
		treetable.setSelectable(true);

		footer.setSpacing(true);

		treetable.addActionHandler(new Action.Handler() {
			public void handleAction(Action action, Object sender, Object target) {
				if (action == DISABLE_CONDITION_ACTION) {
					// remove condition
					Long ConditionOID = (Long) target;
					Long workItemOID = (Long) treetable.getParent(ConditionOID);
					
					ConditionOID = ConditionOID - workItemOID;
					Transaction.begin();
					BlendedWorkflow.getInstance().getWorkListManager().manageGoalCondition(workItemOID, ConditionOID);
					Transaction.commit();
					refreshTree(bwInstanceOID);
				} 
			}

			public Action[] getActions(Object target, Object sender) {
				if (!treetable.areChildrenAllowed(target)) {
					return new Action[]{DISABLE_CONDITION_ACTION};
				} else {
					return new Action[] {};
				}
			}
		});

		Button submitButton = new Button("Finish");
		submitButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Transaction.begin();
				BlendedWorkflow.getInstance().getWorkListManager().enableGoalWorkItemsService(bwInstanceOID);
				Transaction.commit();
				getApplication().getMainWindow().removeWindow(ManageAchieveGoalsConditionsForm.this.getWindow());
			}
		});

		addComponent(treetable);
		footer.addComponent(submitButton);
		addComponent(footer);
		setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

		// Populate
		getDataModel(bwInstanceOID);
	}

	public void getDataModel(long bwInstanceOID) {
		Transaction.begin();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		
		for (AchieveGoal achieveGoal : goalModelInstance.getAchieveGoals()) {
			String goalName = achieveGoal.getName();
			treetable.addItem(goalName);
			
			for (GoalWorkItem goalWorkItem : achieveGoal.getGoalWorkItems()) {
				long wOID = goalWorkItem.getOID();
				String goalWorkItemID = goalWorkItem.getID();
				treetable.addItem(wOID);
				treetable.setItemCaption(wOID, goalWorkItemID);
				treetable.setParent(wOID,goalName);

				for (Condition activateCondition : goalWorkItem.getActivateConditions()) {
					long OID = activateCondition.getOID() + wOID; //FIXME: Show only workItems of the selected Entityinstance.
					String activateConditionString = activateCondition.toString();
					
					treetable.addItem(OID);
					treetable.setItemCaption(OID, activateConditionString);
					treetable.setParent(OID,wOID);
					treetable.setChildrenAllowed(OID, false);
				}
				treetable.expandItemsRecursively(goalName);
			}
		}
		setWidth("100%");
		Transaction.commit();
	}

	public void refreshTree(long bwInstanceOID) {
		treetable.removeAllItems();
		getDataModel(bwInstanceOID);
	}

}

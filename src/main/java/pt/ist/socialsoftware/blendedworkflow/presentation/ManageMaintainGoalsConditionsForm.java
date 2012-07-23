package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.MaintainGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.MaintainGoal.MaintainGoalState;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.event.Action;

@SuppressWarnings("serial")
public class ManageMaintainGoalsConditionsForm extends VerticalLayout {

	protected final Tree treetable = new Tree("Achieve Goals");

	private static final Action ENABLE_CONDITION_ACTION = new Action("Enable Condition");
	private static final Action DISABLE_CONDITION_ACTION = new Action("Disable Condition");

	public ManageMaintainGoalsConditionsForm(final long bwInstanceOID) {
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
					Long goalOID = (Long) target;

					Transaction.begin();
					BlendedWorkflow.getInstance().getWorkListManager().manageGoalCondition(goalOID, MaintainGoalState.DEACTIVATED);
					Transaction.commit();
					refreshTree(bwInstanceOID);
				} else {
					// remove condition
					Long goalOID = (Long) target;

					Transaction.begin();
					BlendedWorkflow.getInstance().getWorkListManager().manageGoalCondition(goalOID, MaintainGoalState.ENABLED);
					Transaction.commit();
					refreshTree(bwInstanceOID);
				}
			}

			public Action[] getActions(Object target, Object sender) {
				if (treetable.areChildrenAllowed(target)) {
					String goalName = (String) treetable.getItemCaption(target).toString();
					if (goalName.contains("ENABLED")) {
						return new Action[]{DISABLE_CONDITION_ACTION};
					} else {
						return new Action[]{ENABLE_CONDITION_ACTION};
					}
				} else {
					return new Action[] {};
				}
			}
		});

		Button submitButton = new Button("Cancel");
		submitButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(ManageMaintainGoalsConditionsForm.this.getWindow());
			}
		});

		addComponent(treetable);
		footer.addComponent(submitButton);
		addComponent(footer);
		setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

		// Populate
		getMaintainGoals(bwInstanceOID);
	}

	public void getMaintainGoals(long bwInstanceOID) {
		Transaction.begin();
		BWInstance bwInstance = AbstractDomainObject.fromOID(bwInstanceOID);
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

		for (MaintainGoal achieveGoal : goalModelInstance.getMaintainGoals()) {
			long goalOID = achieveGoal.getOID();
			String goalName = achieveGoal.getName() + "("+ achieveGoal.getState() + ")";
			treetable.addItem(goalOID);
			treetable.setItemCaption(goalOID, goalName);

			long conditionOID = achieveGoal.getMaintainCondition().getOID();
			String conditionName = achieveGoal.getMaintainCondition().toString();
			treetable.addItem(conditionOID);
			treetable.setItemCaption(conditionOID, conditionName);
			treetable.setParent(conditionOID,goalOID);
			treetable.setChildrenAllowed(conditionOID, false);

			treetable.expandItemsRecursively(goalOID);
		}
		setWidth("100%");
		Transaction.commit();
	}

	public void refreshTree(long bwInstanceOID) {
		treetable.removeAllItems();
		getMaintainGoals(bwInstanceOID);
	}

}

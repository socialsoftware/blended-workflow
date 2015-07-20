package pt.ist.socialsoftware.blendedworkflow.presentation;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.MaintainGoal;
import pt.ist.socialsoftware.blendedworkflow.domain.MaintainGoal.MaintainGoalState;
import pt.ist.fenixframework.FenixFramework;

import com.vaadin.event.Action;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ManageMaintainGoalsConditionsForm extends VerticalLayout {

	protected final Tree treetable = new Tree("Achieve Goals");

	private static final Action ENABLE_CONDITION_ACTION = new Action(
			"Enable Condition");
	private static final Action DISABLE_CONDITION_ACTION = new Action(
			"Disable Condition");

	public ManageMaintainGoalsConditionsForm(final String bwInstanceOID) {
		HorizontalLayout footer = new HorizontalLayout();

		// Properties
		setMargin(true);
		setWidth("500px");
		setSpacing(true);

		treetable.setWidth("100%");
		treetable.setSelectable(true);

		footer.setSpacing(true);

		treetable.addActionHandler(new Action.Handler() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				if (action == DISABLE_CONDITION_ACTION) {
					// remove condition
					String goalOID = (String) target;

					BlendedWorkflow
							.getInstance()
							.getWorkListManager()
							.manageGoalCondition(goalOID,
									MaintainGoalState.DEACTIVATED);
					refreshTree(bwInstanceOID);
				} else {
					// remove condition
					String goalOID = (String) target;

					BlendedWorkflow
							.getInstance()
							.getWorkListManager()
							.manageGoalCondition(goalOID,
									MaintainGoalState.ENABLED);
					refreshTree(bwInstanceOID);
				}
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				if (treetable.areChildrenAllowed(target)) {
					String goalName = treetable.getItemCaption(target)
							.toString();
					if (goalName.contains("ENABLED")) {
						return new Action[] { DISABLE_CONDITION_ACTION };
					} else {
						return new Action[] { ENABLE_CONDITION_ACTION };
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
				getApplication().getMainWindow().removeWindow(
						ManageMaintainGoalsConditionsForm.this.getWindow());
			}
		});

		addComponent(treetable);
		footer.addComponent(submitButton);
		addComponent(footer);
		setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

		// Populate
		getMaintainGoals(bwInstanceOID);
	}

	@Atomic(mode = TxMode.WRITE)
	public void getMaintainGoals(String bwInstanceOID) {
		BWInstance bwInstance = FenixFramework.getDomainObject(bwInstanceOID);
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();

		for (MaintainGoal achieveGoal : goalModelInstance.getMaintainGoalsSet()) {
			String goalOID = achieveGoal.getExternalId();
			String goalName = achieveGoal.getName() + "("
					+ achieveGoal.getState() + ")";
			treetable.addItem(goalOID);
			treetable.setItemCaption(goalOID, goalName);

			String conditionOID = achieveGoal.getMaintainCondition()
					.getExternalId();
			String conditionName = achieveGoal.getMaintainCondition()
					.toString();
			treetable.addItem(conditionOID);
			treetable.setItemCaption(conditionOID, conditionName);
			treetable.setParent(conditionOID, goalOID);
			treetable.setChildrenAllowed(conditionOID, false);

			treetable.expandItemsRecursively(goalOID);
		}
		setWidth("100%");
	}

	public void refreshTree(String bwInstanceOID) {
		treetable.removeAllItems();
		getMaintainGoals(bwInstanceOID);
	}

}

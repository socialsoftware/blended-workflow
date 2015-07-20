package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.Set;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.socialsoftware.blendedworkflow.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.domain.Condition.ConditionType;
import pt.ist.socialsoftware.blendedworkflow.domain.GoalWorkItem.GoalState;
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
public class ManageGoalWorkItemsConditionsForm extends VerticalLayout {

	protected final Tree treetable = new Tree("Achieve Goals");

	private static final Action DISABLE_CONDITION_ACTION = new Action(
			"Disable Condition");

	public ManageGoalWorkItemsConditionsForm(final String workItemOID) {
		HorizontalLayout footer = new HorizontalLayout();

		// Properties
		setMargin(true);
		setSpacing(true);

		setWidth("1000px");
		// treetable.setWidth("100%");
		treetable.setSelectable(true);

		footer.setSpacing(true);

		treetable.addActionHandler(new Action.Handler() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void handleAction(Action action, Object sender, Object target) {
				if (action == DISABLE_CONDITION_ACTION) {
					String ConditionOID = (String) target;
					BlendedWorkflow.getInstance().getWorkListManager()
							.manageGoalCondition(workItemOID, ConditionOID);
					refreshTree(workItemOID);
				}
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				if (!treetable.areChildrenAllowed(target)) {
					return new Action[] { DISABLE_CONDITION_ACTION };
				} else {
					return new Action[] {};
				}
			}
		});

		Button submitButton = new Button("Finish");
		submitButton.addListener(new ClickListener() {
			@Atomic(mode = TxMode.WRITE)
			@Override
			public void buttonClick(ClickEvent event) {
				GoalWorkItem w = FenixFramework.getDomainObject(workItemOID);
				w.setState(GoalState.ACTIVATED);
				// String bwInstanceOID = w.getBwInstance().getOID();

				// remove old ai
				Set<AttributeInstance> oldAI = w
						.getInputAttributeInstancesSet();
				for (AttributeInstance ai : oldAI) {
					w.removeInputAttributeInstances(ai);
				}
				// remove old wa
				Set<WorkItemArgument> old = w.getInputWorkItemArgumentsSet();
				for (WorkItemArgument wa : old) {
					w.removeInputWorkItemArguments(wa);
				}

				for (Condition activateCondition : w.getActivateConditionsSet()) {
					activateCondition.assignAttributeInstances(w,
							ConditionType.ACTIVATE_CONDITION);
				}

				w.createInputWorkItemArguments();
				w.updateInputWorkItemArguments();

				w.updateOutputWorkItemArguments();

				// BlendedWorkflow.getInstance().getWorkListManager().enableGoalWorkItemsService(bwInstanceOID);
				getApplication().getMainWindow().removeWindow(
						ManageGoalWorkItemsConditionsForm.this.getWindow());
			}
		});

		addComponent(treetable);
		footer.addComponent(submitButton);
		addComponent(footer);
		setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

		// Populate
		getDataModel(workItemOID);
	}

	@Atomic(mode = TxMode.WRITE)
	public void getDataModel(String workItemOID) {
		GoalWorkItem goalWorkItem = FenixFramework.getDomainObject(workItemOID);

		// Activate
		String activateConditionCaption = "Activate Conditions:";
		treetable.addItem(goalWorkItem.getID());
		treetable.addItem(activateConditionCaption);
		treetable.setParent(activateConditionCaption, goalWorkItem.getID());
		for (Condition activateCondition : goalWorkItem
				.getActivateConditionsSet()) {
			String OID = activateCondition.getExternalId();
			String activateConditionString = activateCondition.toString();
			treetable.addItem(OID);
			treetable.setItemCaption(OID, activateConditionString);
			treetable.setParent(OID, activateConditionCaption);
			treetable.setChildrenAllowed(OID, false);
		}

		// Maintain
		String maintainConditionCaption = "Maintain Conditions:";
		treetable.addItem(maintainConditionCaption);
		treetable.setParent(maintainConditionCaption, goalWorkItem.getID());
		for (Condition maintainCondition : goalWorkItem
				.getMaintainConditionsSet()) {
			String OID = maintainCondition.getExternalId();
			String maintainConditionString = maintainCondition.toString();
			treetable.addItem(OID);
			treetable.setItemCaption(OID, maintainConditionString);
			treetable.setParent(OID, maintainConditionCaption);
			treetable.setChildrenAllowed(OID, false);
		}

		// treetable.setWidth("100%");
		// setWidth("100%");
	}

	public void refreshTree(String workItemOID) {
		treetable.removeAllItems();
		getDataModel(workItemOID);
	}

}

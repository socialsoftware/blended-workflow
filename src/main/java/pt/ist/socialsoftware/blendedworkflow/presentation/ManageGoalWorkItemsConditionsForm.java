package pt.ist.socialsoftware.blendedworkflow.presentation;

import java.util.List;

import jvstm.Transaction;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AttributeInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.Condition;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItemArgument;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.WorkItem.WorkItemState;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.event.Action;

@SuppressWarnings("serial")
public class ManageGoalWorkItemsConditionsForm extends VerticalLayout {

	protected final Tree treetable = new Tree("Achieve Goals");

	private static final Action DISABLE_CONDITION_ACTION = new Action("Disable Condition");

	public ManageGoalWorkItemsConditionsForm(final long workItemOID) {
		HorizontalLayout footer = new HorizontalLayout();

		// Properties
		setMargin(true);
		setSpacing(true);

		treetable.setWidth("100%");
		treetable.setSelectable(true);

		footer.setSpacing(true);

		treetable.addActionHandler(new Action.Handler() {
			public void handleAction(Action action, Object sender, Object target) {
				if (action == DISABLE_CONDITION_ACTION) {
					Long ConditionOID = (Long) target;
					Transaction.begin();
					BlendedWorkflow.getInstance().getWorkListManager().manageGoalCondition(workItemOID, ConditionOID);
					Transaction.commit();
					refreshTree(workItemOID);
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
				GoalWorkItem w = AbstractDomainObject.fromOID(workItemOID);
				w.setState(WorkItemState.ACTIVATED);
				long bwInstanceOID = w.getBwInstance().getOID();
				
				//remove old ai
				List<AttributeInstance> oldAI = w.getInputAttributeInstances();
				for (AttributeInstance ai : oldAI) {
					w.removeInputAttributeInstances(ai);
				}
				//remove old wa
				List<WorkItemArgument> old = w.getInputWorkItemArguments();
				for (WorkItemArgument wa : old) {
					w.removeInputWorkItemArguments(wa);
				}
				Transaction.commit();
				Transaction.begin();
				BlendedWorkflow.getInstance().getWorkListManager().enableGoalWorkItemsService(bwInstanceOID);
				Transaction.commit();
				getApplication().getMainWindow().removeWindow(ManageGoalWorkItemsConditionsForm.this.getWindow());
			}
		});

		addComponent(treetable);
		footer.addComponent(submitButton);
		addComponent(footer);
		setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

		// Populate
		getDataModel(workItemOID);
	}

	public void getDataModel(long workItemOID) {
		Transaction.begin();
		GoalWorkItem goalWorkItem= AbstractDomainObject.fromOID(workItemOID);
		
		// Activate
		String activateConditionCaption = "Activate Conditions:";
		treetable.addItem(goalWorkItem.getID());
		treetable.addItem(activateConditionCaption);
		treetable.setParent(activateConditionCaption,goalWorkItem.getID());
		for (Condition activateCondition : goalWorkItem.getActivateConditions()) {
			long OID = activateCondition.getOID();
			String activateConditionString = activateCondition.toString();
			treetable.addItem(OID);
			treetable.setItemCaption(OID, activateConditionString);
			treetable.setParent(OID,activateConditionCaption);
			treetable.setChildrenAllowed(OID, false);
		}
		
		// Maintain
		String maintainConditionCaption = "Maintain Conditions:";
		treetable.addItem(maintainConditionCaption);
		treetable.setParent(maintainConditionCaption,goalWorkItem.getID());
		for (Condition maintainCondition : goalWorkItem.getMaintainConditions()) {
			long OID = maintainCondition.getOID();
			String maintainConditionString = maintainCondition.toString();
			treetable.addItem(OID);
			treetable.setItemCaption(OID, maintainConditionString);
			treetable.setParent(OID,maintainConditionCaption);
			treetable.setChildrenAllowed(OID, false);
		}

		treetable.setWidth("100%");
		setWidth("100%");
		Transaction.commit();
	}

	public void refreshTree(long workItemOID) {
		treetable.removeAllItems();
		getDataModel(workItemOID);
	}

}

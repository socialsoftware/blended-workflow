package pt.ist.socialsoftware.blendedworkflow.presentation;

import jvstm.Transaction;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.AchieveGoal;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BWSpecification;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.BlendedWorkflow;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalModelInstance;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem;
import pt.ist.socialsoftware.blendedworkflow.engines.domain.GoalWorkItem.GoalState;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class RedoGoalForm extends VerticalLayout {

	private final NativeSelect bwInstances = new NativeSelect("BWInstance");
	private final NativeSelect parentGoal = new NativeSelect("Goal:");
	private final NativeSelect workItems = new NativeSelect(
			"Completed or Skipped GoalWorkItems:");

	public RedoGoalForm() {

		HorizontalLayout dataHL = new HorizontalLayout();
		VerticalLayout dataVL = new VerticalLayout();
		HorizontalLayout submitPanel = new HorizontalLayout();

		// Properties
		setMargin(true);
		setSpacing(false);
		setHeight("200px");
		setWidth("200px");

		dataHL.setSpacing(true);
		submitPanel.setSpacing(true);

		bwInstances.setImmediate(true);
		bwInstances.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (bwInstances.getValue() == null) {
					parentGoal.removeAllItems();
					workItems.removeAllItems();
				} else {
					long bwInstanceOID = (Long) bwInstances.getValue();
					BWInstance bwInstance = FenixFramework
							.getDomainObject(bwInstanceOID);
					getGoals(bwInstance);
				}
			}
		});

		parentGoal.setImmediate(true);
		parentGoal.addListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (parentGoal.getValue() == null) {
					workItems.removeAllItems();
				} else {
					long bwInstanceOID = (Long) bwInstances.getValue();
					long goalOID = (Long) parentGoal.getValue();
					updateWorkItemsInfo(bwInstanceOID, goalOID);
				}
			}
		});

		Button submit = new Button("Submit");
		submit.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					long workItemlOID = (Long) workItems.getValue();
					Transaction.begin();
					String activeUserID = BlendedWorkflow.getInstance()
							.getOrganizationalManager().getActiveUser().getID();
					BlendedWorkflow.getInstance().getWorkListManager()
							.redoGoal(workItemlOID, activeUserID);
					Transaction.commit();

					getApplication().getMainWindow().removeWindow(
							RedoGoalForm.this.getWindow());
				} catch (java.lang.NullPointerException jle) {
					getApplication().getMainWindow().showNotification(
							"Please fill all the fields");
				}

			}
		});

		Button cancel = new Button("Cancel");
		cancel.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().removeWindow(
						RedoGoalForm.this.getWindow());
			}
		});

		// Layout
		addComponent(bwInstances);
		addComponent(parentGoal);
		addComponent(workItems);

		dataHL.addComponent(dataVL);
		addComponent(dataHL);

		submitPanel.addComponent(submit);
		submitPanel.addComponent(cancel);
		addComponent(submitPanel);
		setComponentAlignment(submitPanel, Alignment.BOTTOM_CENTER);

		// Populate
		getBWInstances();
	}

	private void getGoals(BWInstance bwInstance) {
		Transaction.begin();
		GoalModelInstance goalModelInstance = bwInstance.getGoalModelInstance();
		for (AchieveGoal goal : goalModelInstance.getAchieveGoals()) {
			if (goal.getGoalWorkItemsCount() > 0) {
				this.parentGoal.addItem(goal.getOID());
				this.parentGoal.setItemCaption(goal.getOID(), goal.getName());
			}
		}
		Transaction.commit();
	}

	private void updateWorkItemsInfo(long bwInstanceOID, long goalOID) {
		this.workItems.removeAllItems();
		AchieveGoal goal = FenixFramework.getDomainObject(goalOID);

		Transaction.begin();
		for (GoalWorkItem goalWorkItem : goal.getGoalWorkItems()) {
			if (goalWorkItem.getState().equals(GoalState.ACHIEVED)
					|| goalWorkItem.getState().equals(GoalState.SKIPPED)) {
				this.workItems.addItem(goalWorkItem.getOID());
				this.workItems.setItemCaption(goalWorkItem.getOID(),
						goalWorkItem.getID());
				// TODO: Give workitem data information
			}
		}
		Transaction.commit();
	}

	private void getBWInstances() {
		Transaction.begin();
		for (BWSpecification bwSpecification : BlendedWorkflow.getInstance()
				.getBwSpecifications()) {
			for (BWInstance bwInstance : bwSpecification.getBwInstances()) {
				this.bwInstances.addItem(bwInstance.getOID());
				this.bwInstances.setItemCaption(bwInstance.getOID(),
						bwInstance.getName());
			}
		}
		Transaction.commit();
	}

}
